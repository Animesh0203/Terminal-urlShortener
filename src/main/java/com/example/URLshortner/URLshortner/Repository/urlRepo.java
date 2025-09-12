package com.example.URLshortner.URLshortner.Repository;

import com.example.URLshortner.URLshortner.Model.urlModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface urlRepo extends JpaRepository<urlModel, Long> {
    urlModel findByShortUrl(String shortURL);
}
