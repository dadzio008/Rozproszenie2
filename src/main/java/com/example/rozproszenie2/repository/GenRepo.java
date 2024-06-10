package com.example.rozproszenie2.repository;

import com.example.rozproszenie2.model.Generator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GenRepo  extends JpaRepository<Generator, Long> {

    void deleteAllByCreateDate(Date date);

//    Long getGeneratorIdByCreateDate(Date date);

    List<Long> getGeneratorIdsByCreateDate(Date date);
}
