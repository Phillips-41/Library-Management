package com.example.Library.controllers;

import com.example.Library.model.Author;
import com.example.Library.model.Book;
import com.example.Library.model.Publisher;
import com.example.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/")
    public List<Book> getallBooks(){
        return bookService.getAll();
    }
    @GetMapping("/books/{bookId}")
    public Optional<Book> getallBooks(@PathVariable("bookId") Integer id){
       return bookService.getBook(id);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }
    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable("bookId") Integer id,@RequestBody Book book){
        return bookService.updateBook(id,book);
    }
    @DeleteMapping("books/{bookId}")
    public Book deletebook(@PathVariable("bookId") Integer id){
        return bookService.deleteBook(id);
    }
    @GetMapping("books/{bookId}/publisher")
    public Publisher getPublisher(@PathVariable("bookId") Integer id){
        return bookService.getPublisher(id);
    }
    @GetMapping("books/{bookId}/authors")
    public List<Author> getAuthors(@PathVariable("bookId") Integer id){
        return bookService.getAuthor(id);
    }
}
