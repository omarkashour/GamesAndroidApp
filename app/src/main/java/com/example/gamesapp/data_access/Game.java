package com.example.gamesapp.data_access;

import android.media.Image;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable {

    // main attributes
    private String title;
    private Date releaseDate;
    private int rating; // out of ten
    private int price; // shekels
    private String platform;
    private String genre;
    private String description;
    private int quantity = 1;

    private int coverImage;

    // platforms
    public static final String PC = "PC";
    public  static final String PLAYSTATION = "PLAYSTATION";
    public static final String XBOX = "XBOX";
    public static final String NINTENDO_SWITCH = "NINTENDO SWITCH";

    public Game(String title, Date releaseDate, int rating, int price, String platform, String genre, String description, int quantity, int coverImage) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.price = price;
        this.platform = platform;
        this.genre = genre;
        this.description = description;
        this.quantity = quantity;
        this.coverImage = coverImage;
    }

    public Game(){

    }

    public int getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(int coverImage) {
        this.coverImage = coverImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }


    @Override
    public String toString() {
        return "Title: " + title  + "\n" +
                "Genre: " + genre  +"\n" +
                "Rating: " + rating +"\n" +
                "Platform: " + platform  +"\n" +
                "Price: ₪" + price +"\n";
    }
}
