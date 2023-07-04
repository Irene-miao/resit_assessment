package sg.nus.iss.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.server.model.Book;
import sg.nus.iss.server.repository.BookRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepo;

    public List<Book> getBooks(String character, Integer limit, Integer offset){
        
        return bookRepo.getBooks(character, limit, offset);
    }

    public Optional<Book> getBookByTitle(String title, Integer limit, Integer offset){
        return bookRepo.getBookByTitle(title, limit, offset);
    }
}
