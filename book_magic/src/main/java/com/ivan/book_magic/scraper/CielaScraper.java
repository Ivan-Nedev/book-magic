package com.ivan.book_magic.scraper;

import com.ivan.book_magic.model.BookOffer;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CielaScraper implements BookScraper {
    // Засега само проверяваме, че можем да направим HTTP request.
    // Следващата стъпка ще е да вземем product URL (от sitemap или търсене) и да парснем данните.


    private final CielaAuthorFinder authorFinder = new CielaAuthorFinder();

    @Override
    public List<BookOffer> scrapeBooksByAuthor(String authorSlug) {
        Optional<String> authorUrOpt = authorFinder.findAuthorUrl(authorSlug);
        if (authorUrOpt.isEmpty()) return List.of();

        String authorUrl = authorUrOpt.get();
        List<String> bookUrls = extractBookUrlsFromAuthorPage(authorUrl);

        return bookUrls.stream()
                .limit(5)
                .map(url -> this.scrapeProductPage(url, authorSlug))
                .flatMap(opt -> opt.stream())
                .toList();

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

    private Optional<BookOffer> scrapeProductPage(String productUrl, String authorSlug) {
        try{
            Document doc = Jsoup.connect(productUrl)
                    .userAgent("BookMagicBot/1.0 (educational project) (contact: project.base.educational.email@gmail.com)")
                    .timeout(15_000)
                    .get();

            String title = doc.select("h1.page-title span.base").text();
            String isbn = doc.select("td[data-th=ISBN]").text();

//            String priceTextTest = doc.select("span.price").first().text();
//            String priceText = doc.select("span.price").first().text()
//                    .replace("лв.", "")
//                    .replace(",", ".")
//                    .trim();
            String priceText = doc.select("meta[itemprop=price]").attr("content");
            BigDecimal price = new  BigDecimal(priceText);

            String imageUrl = doc.select("meta[property=og:image]").first().absUrl("content");

            return Optional.of(new BookOffer(
                    authorSlug,
                    title,
                    isbn,
                    "Ciela",
                    price,
                    "BGN",
                    productUrl,
                    imageUrl
            ));


        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
    private void politeSleep() {
        try {
            Thread.sleep(1200); // 1.2 сек
        } catch (InterruptedException ignored) {}
    }

}
