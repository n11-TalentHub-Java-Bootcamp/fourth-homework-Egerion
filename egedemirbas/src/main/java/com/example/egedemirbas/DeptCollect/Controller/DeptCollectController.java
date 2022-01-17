package com.example.egedemirbas.DeptCollect.Controller;

import com.example.egedemirbas.Dept.Entity.Dept;
import com.example.egedemirbas.Dept.Enum.EnumDeptType;
import com.example.egedemirbas.Dept.Service.DeptEntityService;
import com.example.egedemirbas.DeptCollect.Entity.DeptCollect;
import com.example.egedemirbas.DeptCollect.Service.DeptCollectEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dept-collect")
public class DeptCollectController {

    @Autowired
    private DeptCollectEntityService deptCollectEntityService;
    @Autowired
    private DeptEntityService deptEntityService;

    @GetMapping("/list")
    public List<DeptCollect> findAll(){
        return deptCollectEntityService.findAllDeptCollect();
    }

    //Kullanıcının tüm tahsilatları listelenebilmelidir.
    @GetMapping("/user/{userId}")
    public List<DeptCollect> findAllByUserId(@PathVariable Long userId){
        return deptCollectEntityService.findAllByUserId(userId);
    }

    //Belirtilen tarihler arasında yapılan tahsilatlar listelenebilmelidir
    @GetMapping("/date-between/{lowerBound}/{upperBound}")
    public List<DeptCollect> findAllDeptCollectionBetweenDates(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date lowerBound, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date upperBound){
        return deptCollectEntityService.findAllByTransacDateBetweenLowerAndUpper(lowerBound, upperBound);
    }

    //Tahsilat yapan,
    @PostMapping("")
    public BigDecimal save(@RequestBody Dept dept){

        BigDecimal totalPayment = BigDecimal.valueOf(0);
        BigDecimal lateFee = BigDecimal.valueOf(0);

        Date dateNow = new Date();

        if (dept.getDueDate().compareTo(dateNow) <= 0) {

            Long days = ChronoUnit.DAYS.between(dept.getDueDate().toInstant(), dateNow.toInstant());
            lateFee = BigDecimal.valueOf(days * 2.0);

            Dept newDept = new Dept();
            newDept.setInsDate(dateNow);
            newDept.setUserId(dept.getUserId());
            newDept.setMainDeptId(dept.getMainDeptId());
            newDept.setMainDept(lateFee);
            newDept.setEnumDeptType(EnumDeptType.LATE);
        }
        totalPayment = dept.getMainDept().add(lateFee);

        DeptCollect deptCollect = new DeptCollect();
        deptCollect.setDeptId(dept.getMainDeptId());
        deptCollect.setTransacDate(dateNow);
        deptCollect.setAmount(totalPayment);

        dept.setMainDept(BigDecimal.valueOf(0));
        return totalPayment;
    }
}
