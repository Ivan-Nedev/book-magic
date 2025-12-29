package com.ivan.book_magic.scraper;

import com.ivan.book_magic.model.BookOffer;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class CielaScraper implements BookScraper {

    private final CielaAuthorFinder authorFinder = new CielaAuthorFinder();

    @Override
    public List<BookOffer> scrapeBooksByAuthor(String authorSlug) {
        Optional<String> authorUrOpt = authorFinder.findAuthorUrl(authorSlug);
        if (authorUrOpt.isEmpty()) return List.of();

        String authorUrl = authorUrOpt.get();
        List<String> bookUrls = extractBookUrlsFromAuthorPage(authorUrl);
        String authorName = extractAuthorNameFromAuthorPage(authorUrl);

        return bookUrls.stream()
                .limit(7)
                .map(url -> this.scrapeProductPage(url, authorSlug, authorName))
                .flatMap(opt -> opt.stream())
                .toList();

    }

    private String extractAuthorNameFromAuthorPage(String authorUrl) {
        try {
            Document doc = Jsoup.connect(authorUrl)
                    .userAgent("BookMagicBot/1.0 (educational project) (contact: project.base.educational.email@gmail.com")
                    .timeout(15_000)
                    .get();

            return doc.select("h1#page-title-heading span.base ").text();
        } catch (IOException e) {
            return  null;
        }
    }

    private List<String> extractBookUrlsFromAuthorPage(String authorUrl){
        try{
            Document doc = Jsoup.connect(authorUrl)
                    .userAgent("BookMagicBot/1.0 (educational project) (contact: project.base.educational.email@gmail.com")
                    .timeout(15_000)
                    .get();

            return doc.select("a.product-item-link").stream()
                    .map(el -> el.absUrl("href"))
                    .distinct()
                    .toList();

        }catch (Exception e){
            return List.of();
        }
    }

    private Optional<BookOffer> scrapeProductPage(String productUrl, String authorSlug, String authorName) {
        try{
            Document doc = Jsoup.connect(productUrl)
                    .userAgent("BookMagicBot/1.0 (educational project) (contact: project.base.educational.email@gmail.com)")
                    .timeout(15_000)
                    .get();

            String title = doc.select("h1.page-title span.base").text();
//            String isbn = doc.select("td[data-th=ISBN]").text();

//            String priceText = doc.select("meta[itemprop=price]").attr("content");
//            BigDecimal price = new  BigDecimal(priceText);

            String imageUrl = doc.select("meta[property=og:image]").first().absUrl("content");

            return Optional.of(new BookOffer(
                    authorSlug,
                    authorName,
                    title,
//                    isbn,
//                    "Ciela",
//                    price,
//                    "BGN",
                    productUrl,
                    imageUrl
            ));


        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public String getExtractAuthorNameFromAuthorPage(String authorUrl) {
        return extractAuthorNameFromAuthorPage(authorUrl);
    }
    private void politeSleep() {
        try {
            Thread.sleep(1200); // 1.2 сек
        } catch (InterruptedException ignored) {}
    }

}
