package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "memos") // データベース上のテーブル名
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDを自動採番する
    private Long id;

    private String content;

    // --- コンストラクタやgetter/setter ---
    public Memo() {} // JPAのお約束で空のコンストラクタが必要

    public Memo(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}