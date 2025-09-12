package com.example.URLshortner.URLshortner.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import com.example.URLshortner.URLshortner.Model.urlModel;
import com.example.URLshortner.URLshortner.Repository.urlRepo;
import java.util.Optional;


@Service
public class urlService {

    @Value("${server.port}")
    private String serverPort;

    public urlRepo urlRepository;

    public urlService(urlRepo urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String generateShortURL(String originalURL) {
        // generate code only
        String code = Integer.toHexString(originalURL.hashCode());
        
        // create entity
        urlModel urlModel = new urlModel();
        urlModel.setOriginalURL(originalURL);
        urlModel.setShortUrl(code);

        // save entity
        urlRepository.save(urlModel);

        // return full short URL
        return "http://localhost:" + serverPort + "/" + code;
    }

    public String returnOriginalURL(String shortURL) {
        urlModel url = urlRepository.findByShortUrl(shortURL);
        return (url != null) ? url.getOriginalURL() : null;
    }

    public void deleteURLMapping(String shortURL) {
        urlModel urlModel = urlRepository.findByShortUrl(shortURL);
        if (urlModel != null) {
            urlRepository.delete(urlModel);
        }
    }
}
