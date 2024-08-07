package com.example.Library.controllers;

import com.example.Library.model.Book;
import com.example.Library.model.Publisher;
import com.example.Library.service.BookService;
import com.example.Library.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublicController {
    @Autowired
    private PublishService bookService;
    @GetMapping("/publish")
    public List<Publisher> getallBooks(){
        return bookService.getAll();
    }
    @GetMapping("/publish/{bookId}")
    public Optional<Publisher> getallBooks(@PathVariable("bookId") Integer id){
       return bookService.getBook(id);
    }

    @PostMapping("/publish")
    public Publisher getallBooks(@RequestBody Publisher book){
        return bookService.addBook(book);
    }
    @PutMapping("/publish/{bookId}")
    public Publisher updateBook(@PathVariable("bookId") Integer id,@RequestBody Publisher book){
        return bookService.updateBook(id,book);
    }
    @DeleteMapping("publish/{bookId}")
    public Publisher deletebook(@PathVariable("bookId") Integer id){
        return bookService.deleteBook(id);
    }

}
