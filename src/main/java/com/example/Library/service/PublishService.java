package com.example.Library.service;

import com.example.Library.Repository.BookRepository;
import com.example.Library.Repository.PublishRepository;
import com.example.Library.model.Book;
import com.example.Library.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
@Service
public class PublishService {
    private PublishRepository publishRepository;
    @Autowired
    private BookRepository bookRepository;
    PublishService(PublishRepository publishRepository){
        this.publishRepository = publishRepository;
    }


    public List<Publisher> getAll() {
        List<Publisher> book = publishRepository.findAll();
        List<Publisher> books = new ArrayList<>(book);
        return books;
    }
    public Optional<Publisher> getBook(Integer bookId){
        Optional<Publisher> book = publishRepository.findById(bookId);
        return book;
    }
    public Publisher addBook(Publisher book) {
        publishRepository.save(book);
        return book;
    }
    public Publisher updateBook(Integer id, Publisher book){

        try {
            Publisher newBook= publishRepository.findById(id).get();
            if(book.getPublisherName() != null){
                newBook.setPublisherName(book.getPublisherName());
            }
            publishRepository.save(newBook);
            return newBook;
        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    public Publisher deleteBook(Integer id){
        try {
            Publisher publisher = publishRepository.findById(id).get();
            List<Book> books =bookRepository.findByPublisher(publisher);
            for(Book book : books){
                book.setPublisher(null);
            }
            bookRepository.saveAll(books);
            publishRepository.deleteById(id);
            return publisher;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
