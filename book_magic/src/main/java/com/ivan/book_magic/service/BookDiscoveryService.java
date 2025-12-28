package com.ivan.book_magic.service;

import com.ivan.book_magic.entity.AuthorEntity;
import com.ivan.book_magic.entity.BookEntity;
import com.ivan.book_magic.model.BookOffer;
import com.ivan.book_magic.repository.AuthorRepository;
import com.ivan.book_magic.repository.BookRepository;
import com.ivan.book_magic.scraper.CielaAuthorFinder;
import com.ivan.book_magic.scraper.CielaScraper;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

// този service служи за логиката между scraper-а и базата данни
// Следи за нови книги
@Service
public class BookDiscoveryService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CielaScraper scraper;
    // две допълнителни заявки
    private final CielaAuthorFinder authorFinder = new CielaAuthorFinder();


    public BookDiscoveryService(AuthorRepository authorRepository, BookRepository bookRepository, CielaScraper scraper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.scraper = scraper;
    }

    public void discoverNewBooks(String authorSlug) {
        // Author: вземи или създай
        Optional<String> authorUrOpt = authorFinder.findAuthorUrl(authorSlug);
        if (authorUrOpt.isEmpty()){
            System.out.println("Author not found");
            return;
        }

        String authorUrl = authorUrOpt.get();
        String authorName = scraper.getExtractAuthorNameFromAuthorPage(authorUrl);
        AuthorEntity author = authorRepository.findBySlug(authorSlug)
                .orElseGet(()-> authorRepository.save(new AuthorEntity(authorSlug, authorName)));

        // Scrape всички книги
        List<BookOffer> books = scraper.scrapeBooksByAuthor(authorSlug);

        // за всяка книга проверка
        for (BookOffer offer : books) {
            boolean exists = bookRepository.existsByProductUrl(offer.getProductUrl());

            if (!exists){
                BookEntity book = new BookEntity(
                        author,
                        offer.getTitle(),
                        offer.getProductUrl(),
                        offer.getImageUrl()
                );
                bookRepository.save(book);
                System.out.println("NEW BOOK: " + offer.getTitle() + " by " + offer.getAuthorName());
            }
        }
    }

}
