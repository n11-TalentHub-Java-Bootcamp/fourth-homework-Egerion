package com.example.egedemirbas.DeptCollect.Entity;

import com.example.egedemirbas.Dept.Enum.EnumDeptType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name="MPM_DEPT_COLLECTION")
public class DeptCollect implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private Long deptId;
    private Long userId;
    private Long mainDeptId;
    private Date transacDate;
    private BigDecimal amount;
    private EnumDeptType deptType;
}
