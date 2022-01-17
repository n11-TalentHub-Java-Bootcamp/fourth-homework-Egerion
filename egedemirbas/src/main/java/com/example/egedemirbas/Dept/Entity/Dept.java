package com.example.egedemirbas.Dept.Entity;


import com.example.egedemirbas.Dept.Enum.EnumDeptType;
import com.example.egedemirbas.User.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Long mainDeptId;
    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private User user;
    private Long userId;
    @Column(precision = 10, scale = 2)
    private BigDecimal mainDept;
    private Date dueDate;
    private Date insDate;
    private EnumDeptType enumDeptType;
}
