package com.ivan.book_magic.model;

import java.math.BigDecimal;

public class BookOffer {
    private String authorName;
    private String title;
    private String isbn;
    private String store;      // "Ciela"
    private BigDecimal price;
    private String currency;   // "BGN"
    private String productUrl;
    private String imageUrl;

    public BookOffer() {}

    public BookOffer(String authorName,String title, String isbn, String store, BigDecimal price, String currency, String productUrl, String imageUrl) {
        this.authorName = authorName;
        this.title = title;
        this.isbn = isbn;
        this.store = store;
        this.price = price;
        this.currency = currency;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
    }

    // getters/setters

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getStore() { return store; }
    public void setStore(String store) { this.store = store; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getProductUrl() { return productUrl; }
    public void setProductUrl(String productUrl) { this.productUrl = productUrl; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "BookOffer{" +
                "authorName='" + authorName + '\'' +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", store='" + store + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}

