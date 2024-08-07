package com.example.Library.Repository;

import com.example.Library.model.Book;
import com.example.Library.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PublishRepository extends JpaRepository<Publisher,Integer> {

}
