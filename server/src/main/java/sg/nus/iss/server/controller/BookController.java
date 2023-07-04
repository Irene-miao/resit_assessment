package sg.nus.iss.server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import sg.nus.iss.server.model.Book;
import sg.nus.iss.server.service.BookService;

@RestController
@RequestMapping
@CrossOrigin(origins="*")
public class BookController {
    

    @Autowired
    private BookService bookSvc;

    @GetMapping(path="/books")
    @CrossOrigin(origins="*")
    public ResponseEntity<String> getBooks(@RequestParam(required=true) String character, @RequestParam(required=true) Integer limit, @RequestParam(required=true) Integer offset){

        List<Book> books = null;
        
        Integer defaultLimit = 10;
        Integer defaultOffset = 0;

        defaultLimit = limit > 0 ? limit : 10;
        defaultOffset = offset > 0 ? offset : 0;

        try {
            books = bookSvc.getBooks(character, defaultLimit, defaultOffset);
        }catch (Exception e){ 
            e.printStackTrace();
        }

        if (books.size() == 0){
            return ResponseEntity.status(404).body(Json.createObjectBuilder()
            .add("error", "Books not found")
            .build().toString());
        }

        System.out.println("httpbooks: " +books);

        JsonArrayBuilder arr = Json.createArrayBuilder();
        for (Book b: books){
            arr.add(b.toJson());
        }
        String json = arr.build().toString();

        System.out.println("httpjson: "+ json);
        return ResponseEntity.ok().body(json);
    }
    
      @GetMapping(path="/book")
    @CrossOrigin(origins="*")
    public ResponseEntity<String> getBookByTitle(@RequestParam(required=true) String title, @RequestParam(required=true) Integer limit, @RequestParam(required=true) Integer offset){

        Optional<Book> b = null;
        Book book = null;
        Integer defaultLimit = 10;
        Integer defaultOffset = 0;

        defaultLimit = limit > 0 ? limit : 10;
        defaultOffset = offset > 0 ? offset : 0;

        try {
            b = bookSvc.getBookByTitle(title, defaultLimit, defaultOffset);
         book = b.get();
        }catch (Exception e){ 
            e.printStackTrace();
        }

        if (b.isEmpty()){
            return ResponseEntity.status(404).body(Json.createObjectBuilder()
            .add("error", "Book not found")
            .build().toString());
        }
        

        System.out.println("httpbook: " +book);


        String json = book.toJson().toString();

        System.out.println("httpjson: "+ json);
        return ResponseEntity.ok().body(json);
    }
    

}
