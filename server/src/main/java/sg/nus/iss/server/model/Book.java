package sg.nus.iss.server.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonValue;

public class Book implements Serializable {
    
    private String bookId;
    private String title;
    private String authors;
    private String description;
    private String format;
    private Integer pages;
    private Float rating;
    private Integer ratingCount;
    private Integer reviewCount;
    private String genres;
    private String image_url;

    
    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthors() {
        return authors;
    }
    public void setAuthors(String authors) {
        this.authors = authors;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public Integer getPages() {
        return pages;
    }
    public void setPages(Integer pages) {
        this.pages = pages;
    }
    public Float getRating() {
        return rating;
    }
    public void setRating(Float rating) {
        this.rating = rating;
    }
    public Integer getRatingCount() {
        return ratingCount;
    }
    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }
    public Integer getReviewCount() {
        return reviewCount;
    }
    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }
    public String getGenres() {
        return genres;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }


   

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", title=" + title + ", authors=" + authors + ", description=" + description
                + ", format=" + format + ", pages=" + pages + ", rating=" + rating + ", ratingCount=" + ratingCount
                + ", reviewCount=" + reviewCount + ", genres=" + genres + ", image_url=" + image_url + "]";
    }
    public JsonValue toJson(){
        
        return Json.createObjectBuilder()
        .add("book_id", getBookId())
        .add("title", getTitle())
        .add("authors", getAuthors())
        .add("description", getDescription())
        .add("format", getFormat())
        .add("pages", getPages())
        .add("rating", getRating())
        .add("rating_count", getRatingCount())
        .add("review_count", getReviewCount())
        .add("genres", getGenres())
        .add("image_url", getImage_url())
        .build();
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    

}
