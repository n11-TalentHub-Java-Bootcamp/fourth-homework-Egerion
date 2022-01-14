package com.example.egedemirbas.Dept.Dao;

import com.example.egedemirbas.Dept.Entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptDao extends JpaRepository<Dept, Long> {

    Dept findDeptById(Long id);

    Dept save(Dept dept);
}
