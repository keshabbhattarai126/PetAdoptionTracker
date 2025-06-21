/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.model;

/**
 *
 * @author LeathLOQ
 */
public class Review {
    private int id;
    private String reviewText;
    private int rating;

    public Review() {}

    public Review(String reviewText, int rating) {
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id;
    }

    public String getReviewText() {
        return reviewText;
    }
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText; 
    }

    public int getRating() { 
        return rating; 
    }
    public void setRating(int rating) {
        this.rating = rating; 
    }
}
