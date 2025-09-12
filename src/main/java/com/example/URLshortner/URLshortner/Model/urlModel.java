package com.example.URLshortner.URLshortner.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class urlModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalURL;
    private String shortUrl;

    public urlModel(String originalURL, String shortUrl) {
        this.originalURL = originalURL;
        this.shortUrl = shortUrl;
    }
}
