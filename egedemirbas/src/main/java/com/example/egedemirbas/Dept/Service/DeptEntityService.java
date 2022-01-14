package com.example.egedemirbas.Dept.Service;

import com.example.egedemirbas.Dept.Dao.DeptDao;
import com.example.egedemirbas.Dept.Entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
