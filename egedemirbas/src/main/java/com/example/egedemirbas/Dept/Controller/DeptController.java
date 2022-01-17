package com.example.egedemirbas.Dept.Controller;

import com.example.egedemirbas.Dept.Entity.Dept;
import com.example.egedemirbas.Dept.Enum.EnumDeptType;
import com.example.egedemirbas.Dept.Service.DeptEntityService;
import com.example.egedemirbas.User.Entity.User;
import com.example.egedemirbas.User.Service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptEntityService deptEntityService;
    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("/list")
    public List<Dept> findAll(){
        return deptEntityService.findAll();
    }

    //Belirtilen tarihler arasında oluşturulan borçlar listelenebilmelidir.
    @PostMapping("/user/{id}/date-between/{beginDate}/{endDate}")
    public List<Dept> findAllDeptsBetweenDates(@PathVariable Long userId, @PathVariable Date beginDate, @PathVariable Date endDate){
        return deptEntityService.findAllDeptsBetweenDates(beginDate, endDate, userId);
    }

    //Bir kullanıcının tüm borçları listenelebilmelidir. (Borç tutarı sıfırdan büyük olanlar)
    @GetMapping("/normal/user/{id}")
    public List<Dept> findAllNormalDeptsByUserId(@PathVariable Long id){
        List<Dept> deptList = null;
        User user = userEntityService.findUserById(id);
        if(user != null){
            deptList = deptEntityService.findAllByEnumDeptTypeAndUserId(EnumDeptType.NORMAL, user.getId());
        }
        return deptList;
    }

    //Bir kullanıcının vadesi geçmiş borçları listenelebilmelidir. (Borç tutarı sıfırdan büyük olanlar)
    @GetMapping("/late/user/{id}")
    public List<Dept> findAllLateDeptsByUserId(@PathVariable Long id){
        List<Dept> deptList = null;
        User user = userEntityService.findUserById(id);
        if(user != null){
            deptList = deptEntityService.findAllByEnumDeptTypeAndUserId(EnumDeptType.LATE, user.getId());
        }
        return deptList;
    }

    //Bir kullanıcının toplam borç tutarını dönen bir servis olmaldıır.
    @GetMapping("/total/normal/user/{userId}")
    public BigDecimal findTotalNormalDeptByUserId(@PathVariable Long userId){
        BigDecimal totalLateDept = BigDecimal.valueOf(0.0);
        User user = userEntityService.findUserById(userId);
        if(user != null){
            totalLateDept = deptEntityService.findAllLateFeeByUserId(user.getId(),EnumDeptType.NORMAL);
        }
        return totalLateDept;
    }

    //Bir kullanıcının vadesi geçmiş toplam borç tutarını dönen bir servis olmaldıır.
    @GetMapping("/total/late/user/{userId}")
    public BigDecimal findTotalLateDeptByUserId(@PathVariable Long userId){
        BigDecimal totalLateDept = BigDecimal.valueOf(0.0);
        User user = userEntityService.findUserById(userId);
        if(user != null){
            totalLateDept = deptEntityService.findAllLateFeeByUserId(user.getId(),EnumDeptType.LATE);
        }
        return totalLateDept;
    }

    //Borç kaydeden, (sadece normal borçlar)
    @PostMapping("/{userId}")
    public Dept saveNormalDept(@PathVariable Long userId, @RequestBody Dept dept){
        Dept newDept = null;
        User user = userEntityService.findUserById(userId);
        if(user != null && dept.getEnumDeptType() == EnumDeptType.NORMAL){
            newDept = deptEntityService.save(dept);
        }
        return newDept;
    }
}
