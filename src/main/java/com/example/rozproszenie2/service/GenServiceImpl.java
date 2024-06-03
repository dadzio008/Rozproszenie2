package com.example.rozproszenie2.service;

import com.example.rozproszenie2.interfaces.GenService;
import com.example.rozproszenie2.model.Generator;
import com.example.rozproszenie2.repository.GenRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
