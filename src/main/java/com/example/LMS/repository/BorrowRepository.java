package com.example.LMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LMS.model.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
