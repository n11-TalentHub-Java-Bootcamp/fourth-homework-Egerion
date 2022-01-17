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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    //Kullanıcının ödediği toplam gecikme zammı listelenebilmelidir
    @GetMapping("/total/late-fee/user/{userId}")
    public BigDecimal findTotalLateFeeByUserId(@PathVariable Long userId){
        BigDecimal totalPayment = deptCollectEntityService.countAllByUserIdAndDeptType(userId, EnumDeptType.LATE_FEE);
       return totalPayment;
    }

    //Belirtilen tarihler arasında yapılan tahsilatlar listelenebilmelidir
    @GetMapping("/date-between/{lowerBound}/{upperBound}")
    public List<DeptCollect> findAllDeptCollectionBetweenDates(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date lowerBound, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date upperBound){
        return deptCollectEntityService.findAllByTransacDateBetweenLowerAndUpper(lowerBound, upperBound);
    }

    //Tahsilat yapan,
    @PostMapping("")
    public BigDecimal save(@RequestBody Dept dept)  {

        BigDecimal totalPayment = BigDecimal.valueOf(0);
        BigDecimal lateFee = BigDecimal.valueOf(0);

        Date dateNow = new Date();

        //ödeme vakti geçmiş demek!
        if (dept.getDueDate().compareTo(dateNow) <= 0) {

            //Tabloya gerek yok. Statik bir değişken üzerinden oranları tutabilirsiniz. 01.01.2018 öncesi 1.5, sonrası ise 2.0 olacak şekilde Kabul edilebilir.
            Long days = ChronoUnit.DAYS.between(dept.getDueDate().toInstant(), dateNow.toInstant());
            Date goldenDate = new GregorianCalendar(2018, Calendar.FEBRUARY, 01).getTime();

            if(dept.getDueDate().compareTo(goldenDate) > 0)
                lateFee = BigDecimal.valueOf(days * 2.0);
            else
                lateFee = BigDecimal.valueOf(days * 1.5);

            //Bir borç tahsilatı yapıldığında, eğer vade tarihi geçmiş ise, gecikme zammı tutarı kadar bir borç kaydı atılır.
            Dept newDept = new Dept();
            newDept.setInsDate(dateNow);
            newDept.setUserId(dept.getUserId());

            //Bağlı olduğu borç bilgisi muhakkak tutulmalıdır ki hangi borca istinaden bu gecikme zammı oluşmuş, görünebilsin.
            newDept.setMainDeptId(dept.getMainDeptId());
            newDept.setMainDept(lateFee);
            newDept.setEnumDeptType(EnumDeptType.LATE_FEE);
            deptEntityService.save(newDept);
        }
        totalPayment = dept.getMainDept().add(lateFee);

        DeptCollect deptCollect = new DeptCollect();
        deptCollect.setDeptId(dept.getMainDeptId());
        deptCollect.setTransacDate(dateNow);
        deptCollect.setAmount(totalPayment);
        deptCollect.setUserId(dept.getUserId());
        deptCollectEntityService.save(deptCollect);

        //Asıl borç ve buna bağlı olan, tahsilat anında oluşturulan gecikme zammı borcunun borç tutarları 0(sıfır) yapılır.
        dept.setMainDept(BigDecimal.valueOf(0));
        deptEntityService.save(dept);

        return totalPayment;
    }
}
