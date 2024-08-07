package com.example.Library.service;

import com.example.Library.Repository.AuthorRepository;
import com.example.Library.Repository.BookRepository;
import com.example.Library.Repository.PublishRepository;
import com.example.Library.model.Author;
import com.example.Library.model.Book;
import com.example.Library.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class BookService {
    private BookRepository bookRepository;
    @Autowired
    private PublishRepository publishRepository;
    @Autowired
    private AuthorRepository authorRepository;
    BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public List<Book> getAll() {
        try {
            List<Book> book = bookRepository.findAll();
            List<Book> books = new ArrayList<>(book);
            return books;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
    }
    public Optional<Book> getBook(Integer bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        return book;
    }
    public Book addBook(Book book) {


        try {
            Publisher publisher = book.getPublisher();
            Integer publishId = publisher.getPublisherId();
            List<Integer> authorId=new ArrayList<>();
            publisher = publishRepository.findById(publishId).get();
            book.setPublisher(publisher);
            for(Author author : book.getAuthors()){
                authorId.add(author.getAuthorId());
            }
            List<Author> authors = authorRepository.findAllById(authorId);
            if(authors.size() != authorId.size()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"some authors are found");
            }
            book.setAuthors(authors);

           return bookRepository.save(book);

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    public Book updateBook(Integer id, Book book){

       try {
           Book newBook= bookRepository.findById(id).get();

           if(book.getName() != null){
               newBook.setName(book.getName());
           }
           if (book.getImageUrl() != null){
               newBook.setImageUrl(book.getImageUrl());
           }
           if(book.getPublisher() != null){
              Publisher publisher = book.getPublisher();
              Integer publishid = publisher.getPublisherId();
              Publisher newPublisher = publishRepository.findById(publishid).get();
              newBook.setPublisher(newPublisher);
           }
           if(book.getAuthors()!=null){
               List<Integer> authorId = new ArrayList<>();
               for(Author author : book.getAuthors()){
                   authorId.add(author.getAuthorId());
               }
               List<Author> authors = authorRepository.findAllById(authorId);
               if(authors.size() != authorId.size()){
                   throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"some authors are found");
               }
               newBook.setAuthors(authors);
           }
           bookRepository.save(newBook);
           return newBook;
       }catch (Exception e){
         throw  new  ResponseStatusException(HttpStatus.NOT_FOUND);
       }

    }
    public Book deleteBook(Integer id){
        try {
            Book book = bookRepository.findById(id).get();
           bookRepository.delete(book);
           return book;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Publisher getPublisher(Integer id){
        try {
            Book book = bookRepository.findById(id).get();
            return book.getPublisher();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    public List<Author> getAuthor(Integer id){
        try{
            Book book = bookRepository.findById(id).get();
            return book.getAuthors();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
