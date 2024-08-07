package com.example.Library.service;

import com.example.Library.Repository.AuthorRepository;
import com.example.Library.Repository.BookRepository;
import com.example.Library.model.Author;
import com.example.Library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    public ArrayList<Author> getAuthors() {
        List<Author> authorList = authorRepository.findAll();
        ArrayList<Author> authors = new ArrayList<>(authorList);
        return authors;
    }


    public Author getAuthorById(int authorId) {
        try {
            Author author = authorRepository.findById(authorId).get();
            return author;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public Author addAuthor(Author author) {
        List<Integer> bookIds = new ArrayList<>();
        for(Book book : author.getBooks()){
            bookIds.add(book.getId());
        }
        List<Book> books = bookRepository.findAllById(bookIds);
        author.setBooks(books);
        for(Book book : books){
            book.getAuthors().add(author);
        }
        Author savedAuthor = authorRepository.save(author);
        bookRepository.saveAll(books);
        return savedAuthor;
    }


    public Author updateAuthor(int authorId, Author author) {
        try {
            Author new_author = authorRepository.findById(authorId).get();
            if (author.getAuthorName() != null){
                new_author.setAuthorName(author.getAuthorName());}
            if(author.getBooks()!=null){
                List<Book> books = author.getBooks();
                for(Book book : books){
                    book.getAuthors().remove(new_author);
                }
                bookRepository.saveAll(books);
                List<Integer> bookIds = new ArrayList<>();
                for(Book book : author.getBooks()){
                    bookIds.add(book.getId());
                }
                List<Book> newBooks= bookRepository.findAllById(bookIds);
                for(Book book : newBooks){
                    book.getAuthors().add(new_author);
                }
                bookRepository.saveAll(newBooks);
                new_author.setBooks(newBooks);
            }
            authorRepository.save(new_author);
            return new_author;
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    public void deleteAuthor(int authorId) {
        try {
            Author author = authorRepository.findById(authorId).get();
            List<Book> books = author.getBooks();
            for(Book book : books){
                book.getAuthors().remove(author);
            }
            bookRepository.saveAll(books);
            authorRepository.deleteById(authorId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}
