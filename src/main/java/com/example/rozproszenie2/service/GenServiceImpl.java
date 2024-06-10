package com.example.rozproszenie2.service;

import com.example.rozproszenie2.interfaces.GenService;
import com.example.rozproszenie2.model.Generator;
import com.example.rozproszenie2.repository.GenRepo;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GenServiceImpl implements GenService {


    private GenRepo repo;

    public GenServiceImpl(GenRepo repo) {
        this.repo = repo;
    }

    @Override
    public String getUrl(Long id) {
        Optional<Generator> gen = repo.findById(id);
        return gen.map(Generator::getUrl).orElse(null);
    }

    @Override
    public HttpStatus deleteUrlByCreationDate(Date date) {
        List<Long> genId = repo.getGeneratorIdsByCreateDate(date);
        HttpStatus status = deleteUrlByCreationDateOnCassandra(genId);
        repo.deleteAllByCreateDate(date);
        return status;
    }

    private HttpStatus deleteUrlByCreationDateOnCassandra(List genId) {
        String url = "127.0.0.1:80/deleteUrlByCreationDateOnCassandra";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, List> map = new HashMap<>();
        map.put("genId", genId);
        HttpEntity<Map<String, List>> requestEntity = new HttpEntity<>(map, headers);
        RestTemplate rest = new RestTemplate();
        ResponseEntity<HttpStatus> response = rest.postForEntity(url, requestEntity, HttpStatus.class);
        return response.getBody();
    }
}
