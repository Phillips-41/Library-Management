package com.example.Library.model;

import jakarta.persistence.*;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisherid")
    private Integer PublisherId;
    @Column(name = "publishername")
    private String publisherName;

    public Publisher() {
    }


    public Integer getPublisherId() {
        return PublisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.PublisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
