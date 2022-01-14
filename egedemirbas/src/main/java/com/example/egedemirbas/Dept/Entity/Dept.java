package com.example.egedemirbas.Dept.Entity;


import com.example.egedemirbas.Dept.Enum.EnumDeptType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "MPM_USER_DEPTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private BigDecimal mainDept;
    private Date dueDate;
    private BigDecimal remainingDept;
    private BigDecimal lateFee;
    private EnumDeptType enumDeptType;
}
