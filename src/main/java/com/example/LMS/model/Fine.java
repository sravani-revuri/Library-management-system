package com.example.LMS.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fine")
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long returnBookId;
    private int amount;

    public Fine() {}

    public Fine(Long returnBookId, int amount) {
        this.returnBookId = returnBookId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Long getReturnBookId() {
        return returnBookId;
    }

    public void setReturnBookId(Long returnBookId) {
        this.returnBookId = returnBookId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
