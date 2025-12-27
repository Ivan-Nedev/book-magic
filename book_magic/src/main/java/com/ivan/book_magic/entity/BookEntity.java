package com.ivan.book_magic.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @Column(nullable = false)
    private String title;

    @Column(name = "product_url", unique = true, nullable = false)
    private String productUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "first_seen_at", nullable = false)
    private LocalDateTime firstSeenAt = LocalDateTime.now();

    public BookEntity() {}

    public BookEntity(AuthorEntity author, String title, String productUrl, String imageUrl) {
        this.author = author;
        this.title = title;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
    }

    public Long getId() { return id; }
    public AuthorEntity getAuthor() { return author; }
    public void setAuthor(AuthorEntity author) { this.author = author; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getProductUrl() { return productUrl; }
    public void setProductUrl(String productUrl) { this.productUrl = productUrl; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public LocalDateTime getFirstSeenAt() { return firstSeenAt; }
}
