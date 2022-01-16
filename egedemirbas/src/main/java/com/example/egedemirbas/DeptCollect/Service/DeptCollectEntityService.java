package com.example.egedemirbas.DeptCollect.Service;

import com.example.egedemirbas.DeptCollect.Dao.DeptCollectDao;
import com.example.egedemirbas.DeptCollect.Entity.DeptCollect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
