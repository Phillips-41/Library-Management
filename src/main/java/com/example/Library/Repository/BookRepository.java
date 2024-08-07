package com.example.Library.Repository;

import com.example.Library.model.Book;
import com.example.Library.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface BookRepository extends JpaRepository<Book,Integer> {
    ArrayList<Book> findByPublisher(Publisher publisher);
}
