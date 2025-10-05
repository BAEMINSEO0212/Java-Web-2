package com.example.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.domain.TestDB;
import com.example.demo.model.repository.TestRepository;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository; // DB와 통신하는 부품(Repository)

    // DB에 저장된 모든 사용자 정보를 List 형태로 가져오는 기능을 만듭니다.
    public List<TestDB> findAll() {
        return testRepository.findAll();
    }
}
