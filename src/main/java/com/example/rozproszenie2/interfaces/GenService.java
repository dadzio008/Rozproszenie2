package com.example.rozproszenie2.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface GenService {

    String getUrl(Long id);

    HttpStatus deleteUrlByCreationDate(Date date);
}
