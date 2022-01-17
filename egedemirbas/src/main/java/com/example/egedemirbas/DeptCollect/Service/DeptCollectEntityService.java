package com.example.egedemirbas.DeptCollect.Service;

import com.example.egedemirbas.Dept.Entity.Dept;
import com.example.egedemirbas.Dept.Enum.EnumDeptType;
import com.example.egedemirbas.DeptCollect.Dao.DeptCollectDao;
import com.example.egedemirbas.DeptCollect.Entity.DeptCollect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class DeptCollectEntityService {

    @Autowired
    private DeptCollectDao deptCollectDao;

    public List<DeptCollect> findAllDeptCollect(){
        return deptCollectDao.findAllDeptCollect();
    }

    public DeptCollect findDeptCollectById(Long id){
        return deptCollectDao.findDeptCollectById(id);
    }

    public List<DeptCollect> findAllByUserId(Long userId){
        return deptCollectDao.findAllByUserId(userId);
    }

    public void save(DeptCollect deptCollect){
         deptCollectDao.save(deptCollect);
    }

    public List<DeptCollect> findAllByTransacDateBetweenLowerAndUpper(Date lowerBound, Date upperBound){
        return deptCollectDao.findAllByTransacDateBetweenLowerAndUpper(lowerBound, upperBound);
    }

    public BigDecimal countAllByUserIdAndDeptType(Long userId, EnumDeptType deptType){
        return deptCollectDao.countAllByUserIdAndDeptType(userId, deptType);
    }
}
