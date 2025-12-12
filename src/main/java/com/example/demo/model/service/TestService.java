package com.example.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.TestDB;
import com.example.demo.model.repository.TestRepository;
import java.util.List;

// [4주차] 데이터베이스 연동을 테스트하기 위해 생성한 서비스 클래스.

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository; // [4주차] TestRepository 주입

    // [4주차] TestDB 테이블의 모든 데이터를 조회하는 메소드.
    public List<TestDB> findAll() {
        return testRepository.findAll();
    }
}
