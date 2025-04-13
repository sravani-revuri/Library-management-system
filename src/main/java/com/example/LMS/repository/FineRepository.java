package com.example.LMS.repository;

import com.example.LMS.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<Fine, Long> {
    Fine findByReturnBookId(Long returnBookId);
}
