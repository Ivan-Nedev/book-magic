package com.ivan.book_magic.scraper;

import com.ivan.book_magic.model.BookOffer;

import java.util.List;

public interface BookScraper {
    List<BookOffer> scrapeBooksByAuthor(String authorSlug);
}
