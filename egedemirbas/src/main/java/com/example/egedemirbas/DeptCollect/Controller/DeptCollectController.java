package com.example.egedemirbas.DeptCollect.Controller;

import com.example.egedemirbas.Dept.Entity.Dept;
import com.example.egedemirbas.Dept.Enum.EnumDeptType;
import com.example.egedemirbas.Dept.Service.DeptEntityService;
import com.example.egedemirbas.DeptCollect.Entity.DeptCollect;
import com.example.egedemirbas.DeptCollect.Service.DeptCollectEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/deptcollect")
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
    public List<DeptCollect> findAllDeptCollectionBetweenDates(@PathVariable Date lowerBound, @PathVariable Date upperBound){
        return deptCollectEntityService.findAllByTransacDateBetweenLowerAndUpper(lowerBound, upperBound);
    }

    @PostMapping("")
    public BigDecimal save(Dept dept){

        BigDecimal totalPayment = BigDecimal.valueOf(0);
        if(dept.getDueDate().getTime() < new Date().getTime()){
            Dept newDept = new Dept();
            newDept.setUserId(dept.getUserId());
            newDept.setEnumDeptType(EnumDeptType.LATE);
            newDept.setInsDate(new Date());
        }
        return totalPayment;
    }
}
