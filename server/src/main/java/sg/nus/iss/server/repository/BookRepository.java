package sg.nus.iss.server.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.nus.iss.server.model.Book;

@Repository
public class BookRepository {
    

    @Autowired
    private JdbcTemplate template;


    private static final String GET_BOOKS = """
            select * from book2018 where title like ? order by title ASC limit ? offset ?
            """;

    private static final String GET_BOOK_BY_TITLE = """
            select * from book2018 where title = ? limit ? offset ?
            """;

        // http://localhost:8080/books?character=T&limit=10&offset=0
    public List<Book> getBooks(String character, Integer limit, Integer offset){

        List<Book> books = new ArrayList<>();

        SqlRowSet rs = null;

        String letter = character + "%";
        System.out.println("letter: "+ letter);
       rs =  template.queryForRowSet(GET_BOOKS, new Object[] {letter, limit, offset});

       System.out.println("rs: "+ rs.next());
        while (rs.next()){
            Book b = new Book();
            System.out.println(rs.getString("title"));
            b.setBookId(rs.getString("book_id"));
            b.setTitle(rs.getString("title"));
            b.setAuthors(rs.getString("authors"));
            b.setDescription(rs.getString("description"));
            b.setFormat(rs.getString("format"));
            b.setPages(rs.getInt("pages"));
            b.setRating(rs.getFloat("rating"));
            b.setRatingCount(rs.getInt("rating_count"));
            b.setReviewCount(rs.getInt("review_count"));
            b.setGenres(rs.getString("genres"));
            b.setImage_url(rs.getString("image_url"));
            System.out.println(b.toString());;
            books.add(b);
        }

      System.out.println("books: "+ books);
        return books;
    }


public Optional<Book> getBookByTitle(String title, Integer limit, Integer offset){
        
  SqlRowSet rs = null;

   rs =  template.queryForRowSet(GET_BOOK_BY_TITLE, new Object[] {title, limit, offset});

while (!rs.next()){
    return Optional.empty();        
}

 Book b = new Book();
            System.out.println(rs.getString("title"));
            b.setBookId(rs.getString("book_id"));
            b.setTitle(rs.getString("title"));
            b.setAuthors(rs.getString("authors"));
            b.setDescription(rs.getString("description"));
            b.setFormat(rs.getString("format"));
            b.setPages(rs.getInt("pages"));
            b.setRating(rs.getFloat("rating"));
            b.setRatingCount(rs.getInt("rating_count"));
            b.setReviewCount(rs.getInt("review_count"));
            b.setGenres(rs.getString("genres"));
            b.setImage_url(rs.getString("image_url"));
            System.out.println(b.toString());


            return Optional.of(b);
    }

    
}
