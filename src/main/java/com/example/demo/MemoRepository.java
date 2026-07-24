package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository<エンティティのクラス, 主キー(ID)の型> を継承するだけでOK！
public interface MemoRepository extends JpaRepository<Memo, Long> {
}