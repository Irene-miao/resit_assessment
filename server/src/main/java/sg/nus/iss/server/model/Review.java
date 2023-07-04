package sg.nus.iss.server.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collections;

import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Review implements Serializable {
    
    private String url;
    private String book_title;
    private String book_author;
    private String summary;
    private String byline;
    private String publication_dt;
    private String uuid;
    private String uri;
    private  String isbn13;
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
  

    public String getBook_title() {
        return book_title;
    }
    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }
    public String getBook_author() {
        return book_author;
    }
    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }
    public String getByline() {
        return byline;
    }
    public void setByline(String byline) {
        this.byline = byline;
    }
    public String getPublication_dt() {
        return publication_dt;
    }
    public void setPublication_dt(String publication_dt) {
        this.publication_dt = publication_dt;
    }


    public JsonValue toJson(){
        return Json.createObjectBuilder()
        .add("url", getUrl())
        .add("book_title", getBook_title())
        .add("book_author", getBook_author())
        .add("summary", getSummary())
        .add("byline", getByline())
        .add("publication_dt", getPublication_dt())
        .add("uuid", getUuid())
        .add("uri", getUri())
        .add("isbn13", getIsbn13())
        .build();
    }

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getIsbn13() {
        return isbn13;
    }
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    @Override
    public String toString() {
        return "Review [url=" + url + ", book_title=" + book_title + ", book_author=" + book_author + ", summary="
                + summary + ", byline=" + byline + ", publication_dt=" + publication_dt + ", uuid=" + uuid + ", uri="
                + uri + ", isbn13=" + isbn13 + "]";
    }


   
    public static Review create(String json) throws IOException{
        System.out.println("jsonStr: "+ json);
        Review review = new Review();

           try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o =  r.readObject();
          
            JsonArray arr = o.getJsonArray("results");
              System.out.println("arr: "+ arr.toString());
           review.setBook_author(o.getString("book_author"));
           review.setBook_title(o.getString("book_title"));
           review.setByline(o.getString("byline"));
            review.setIsbn13(o.getString("isbn13"));
            review.setPublication_dt(o.getString("publication_dt"));
            review.setSummary(o.getString("summary"));
            review.setUri(o.getString("uri"));
            review.setUrl(o.getString("url"));
            review.setUuid(o.getString("uuid"));
        }

        System.out.println("review: "+ review);
        return review;
    }
    
}
