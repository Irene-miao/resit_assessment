package sg.nus.iss.server.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.nus.iss.server.model.Book;
import sg.nus.iss.server.model.Review;
import sg.nus.iss.server.repository.BookRepository;


@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepo;

 @Value("${book.url}")
    private String bookUrl;

    @Value("${api.key}")
    private String apiKey;

    public List<Book> getBooks(String character, Integer limit, Integer offset){
        
        return bookRepo.getBooks(character, limit, offset);
    }

    public Optional<Book> getBookByTitle(String title, Integer limit, Integer offset){
        return bookRepo.getBookByTitle(title, limit, offset);
    }

    public List<Review> getBookReview(String title) throws IOException{
        
        System.out.println("book api key " + apiKey);
        System.out.println("book url: " + bookUrl);


        String url = UriComponentsBuilder
        .fromUriString(bookUrl)
        .queryParam("title", title)
        .queryParam("api-key", apiKey)
        .toUriString();

        System.out.println("url: "+ url);
        RestTemplate template = new RestTemplate();
   HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> req = new HttpEntity<>(headers);
		
       ResponseEntity<String> resp = template.exchange(url,  HttpMethod.GET,  req, String.class);

        System.out.println("restTemplateResp: "+ resp.getBody());
        List<Review> reviews = new ArrayList<>();
        
        reviews.add(Review.create(resp.getBody()));
        System.out.println("reviews: "+ reviews);
        return reviews;
    }
}
