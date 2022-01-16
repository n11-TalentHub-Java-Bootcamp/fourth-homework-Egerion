package com.example.egedemirbas.Dept.Dao;

import com.example.egedemirbas.Dept.Entity.Dept;
import com.example.egedemirbas.Dept.Enum.EnumDeptType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface DeptDao extends JpaRepository<Dept, Long> {

    Dept findDeptById(Long id);

    Dept save(Dept dept);

    @Query(
            "select dept from Dept dept where dept.mainDept > 0 and " +
                    "dept.enumDeptType = :deptType and " +
                    "dept.userId = :myId"
    )
    List<Dept> findAllByEnumDeptTypeAndUserId(@Param("deptType")EnumDeptType deptType, @Param("myId")Long userId);

    @Query(
            "select sum(dept.mainDept) from Dept dept where dept.userId = :myId and dept.enumDeptType =:deptType"
    )
    BigDecimal findAllLateFeeByUserId(@Param("myId")Long userId, @Param("deptType")EnumDeptType deptType);

    @Query(
            "select dept from Dept dept where dept.userId = :myId and " +
                    "dept.insDate >= :beginDate and " +
                    "dept.insDate <= :endDate"
    )
    List<Dept> findAllDeptsBetweenDates(@Param("myId")Long myId, @Param("beginDate")Date beginDate, @Param("endDate")Date endDate);
}
