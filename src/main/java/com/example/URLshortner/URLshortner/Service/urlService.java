package com.example.URLshortner.URLshortner.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.example.URLshortner.URLshortner.Model.urlModel;
import com.example.URLshortner.URLshortner.Repository.urlRepo;

import java.util.Optional;

@Service
public class urlService {

    @Value("${server.port}")
    private String serverPort;

    private final urlRepo urlRepository;

    public urlService(urlRepo urlRepository) {
        this.urlRepository = urlRepository;
    }

    // Create new short URL
    @CachePut(value = "urlsByOriginal", key = "#originalURL")
    public String generateShortURL(String originalURL) {
        String code = Integer.toHexString(originalURL.hashCode());

        urlModel urlModel = new urlModel();
        urlModel.setOriginalURL(originalURL);
        urlModel.setShortUrl(code);

        urlRepository.save(urlModel);

        return "http://localhost:" + serverPort + "/" + code;
    }

    // Look up short URL by original URL
    @Cacheable(value = "urlsByOriginal", key = "#originalURL")
    public String returnShortURL(String originalURL) {
        Optional<urlModel> url = Optional.ofNullable(urlRepository.findByOriginalURL(originalURL));
        return url.map(u -> "http://localhost:" + serverPort + "/" + u.getShortUrl()).orElse(null);
    }

    // Look up original URL by short URL
    @Cacheable(value = "urlsByShort", key = "#shortURL")
    public String returnOriginalURL(String shortURL) {
        Optional<urlModel> url = Optional.ofNullable(urlRepository.findByShortUrl(shortURL));
        return url.map(urlModel::getOriginalURL).orElse(null);
    }

    // Delete mapping
    @CacheEvict(value = { "urlsByShort", "urlsByOriginal" }, allEntries = true)
    public void deleteURLMapping(String shortURL) {
        Optional<urlModel> urlModel = Optional.ofNullable(urlRepository.findByShortUrl(shortURL));
        urlModel.ifPresent(urlRepository::delete);
    }
}
