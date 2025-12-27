package com.ivan.book_magic.model;

import java.math.BigDecimal;

public class BookOffer {
    private String authorSlug;
    private String authorName;
    private String title;
//    private String isbn;
//    private String store;      // "Ciela"
//    private BigDecimal price;
//    private String currency;   // "BGN"
    private String productUrl;
    private String imageUrl;

    public BookOffer() {}

    public BookOffer(String authorSlug, String authorName,String title,  String productUrl, String imageUrl) {
        this.authorSlug = authorSlug;
        this.authorName = authorName;
        this.title = title;
//        this.isbn = isbn;
//        this.store = store;
//        this.price = price;
//        this.currency = currency;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
    }

    // getters/setters

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

//    public String getIsbn() { return isbn; }
//    public void setIsbn(String isbn) { this.isbn = isbn; }
//
//    public String getStore() { return store; }
//    public void setStore(String store) { this.store = store; }
//
//    public BigDecimal getPrice() { return price; }
//    public void setPrice(BigDecimal price) { this.price = price; }
//
//    public String getCurrency() { return currency; }
//    public void setCurrency(String currency) { this.currency = currency; }

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

    public String getAuthorSlug() {
        return authorSlug;
    }
    public void setAuthorSlug(String authorSlug) {
        this.authorSlug = authorSlug;
    }

    @Override
    public String toString() {
        return "BookOffer{" +
                "authorSlug='" + authorSlug + '\'' +
                ", authorName='" + authorName + '\'' +
                ", title='" + title + '\'' +
//                ", isbn='" + isbn + '\'' +
//                ", store='" + store + '\'' +
//                ", price=" + price +
//                ", currency='" + currency + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}

