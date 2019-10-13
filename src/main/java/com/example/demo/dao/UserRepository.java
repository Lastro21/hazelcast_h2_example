package com.example.demo.dao;

import com.example.demo.model.MyTestUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyTestUsers, Integer> {
}
