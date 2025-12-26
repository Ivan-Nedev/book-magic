package com.ivan.book_magic.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Optional;

public class CielaAuthorFinder {
    private static final String AUTHOR_SITEMAP =
            "https://www.ciela.com/map_author.xml";

    public Optional<String> findAuthorUrl(String author) {
        try {
            Document sitemap = Jsoup.connect(AUTHOR_SITEMAP)
                    .ignoreContentType(true)
                    .userAgent("BookMagicBot/1.0 (educational project) (contact: project.base.educational.email@gmail.com")
                    .timeout(15_000)
                    .get();

            return sitemap.select("loc").stream()
                    .map(loc -> loc.text())
                    .filter(url -> url.endsWith("/" + author))
                    .findFirst();

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
