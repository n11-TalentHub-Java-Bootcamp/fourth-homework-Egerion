package com.example.egedemirbas.Dept.Service;

import com.example.egedemirbas.Dept.Dao.DeptDao;
import com.example.egedemirbas.Dept.Entity.Dept;
import com.example.egedemirbas.Dept.Enum.EnumDeptType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class DeptEntityService {

    @Autowired
    private DeptDao deptDao;

    public List<Dept> findAll(){
        return deptDao.findAll();
    }

    public Dept findDeptById(Long id){
        return deptDao.findDeptById(id);
    }

    public Dept save(Dept dept){
        return deptDao.save(dept);
    }

    public void delete(Dept dept){
        deptDao.delete(dept);
    }

    public List<Dept> findAllByEnumDeptTypeAndUserId(EnumDeptType deptType, Long id){
        return deptDao.findAllByEnumDeptTypeAndUserId(deptType, id);
    }

    public List<Dept> findAllDeptsBetweenDates(Date beginDate, Date endDate, Long id){
        return deptDao.findAllDeptsBetweenDates(id, beginDate, endDate);
    }

    public BigDecimal findAllLateFeeByUserId(Long userId, EnumDeptType deptType){
        return deptDao.findAllLateFeeByUserId(userId, deptType);
    }
}
