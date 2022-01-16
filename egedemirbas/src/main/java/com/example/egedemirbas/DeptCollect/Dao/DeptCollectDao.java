package com.example.egedemirbas.DeptCollect.Dao;

import com.example.egedemirbas.DeptCollect.Entity.DeptCollect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DeptCollectDao extends JpaRepository<DeptCollect, Long> {

    @Query("select deptCollect from DeptCollect deptCollect")
    List<DeptCollect> findAllDeptCollect();

    DeptCollect findDeptCollectById(Long id);

    DeptCollect save(DeptCollect deptCollect);

    List<DeptCollect> findAllByUserId(Long userId);

    @Query("select deptCollect from DeptCollect deptCollect where deptCollect.transacDate >= :lowerBound and deptCollect.transacDate <= :upperBound")
    List<DeptCollect> findAllByTransacDateBetweenLowerAndUpper(@Param("lowerBound")Date lowerBound, @Param("upperBound")Date upperBound);

}
