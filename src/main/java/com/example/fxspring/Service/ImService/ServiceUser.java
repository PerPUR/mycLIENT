package com.example.fxspring.Service.ImService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.fxspring.Dao.InterfaceDao;
import com.example.fxspring.Service.InterFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.fxspring.entity.phoneUser;
import java.util.List;

@Service
public class ServiceUser implements InterFaceService{


    @Autowired
    private InterfaceDao dao;

    @Override
    public List<phoneUser> getAllUsers() {

        return dao.selectList(null);
    }

    @Override
    public phoneUser getUserByPhone(String phone) {
        QueryWrapper<phoneUser> wrapper = new QueryWrapper<>();
        wrapper.eq("uphone",phone);
        return dao.selectOne(wrapper);
    }

    @Override
    public List<phoneUser> getUserByName(String name) {
        QueryWrapper <phoneUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",name);
        return dao.selectList(wrapper);
    }

    @Override
    public void deleteByPhone(String phone) {
        QueryWrapper <phoneUser> wrapper = new QueryWrapper<>();
        wrapper.eq("uphone",phone);
        dao.delete(wrapper);
    }

    @Override
    public void deleteByName(String name) {
        QueryWrapper <phoneUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",name);
        dao.delete(wrapper);
    }

    @Override
    public void alterPhone(String phone,phoneUser user) {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uphone",user.getUphone());
        updateWrapper.set("uphone",phone);
        dao.update(user,updateWrapper);
    }
    @Override
    public void alterName(String name, phoneUser user) {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uphone",user.getUphone());
        updateWrapper.set("username",name);
        dao.update(user,updateWrapper);
    }

    @Override
    public void addUser(phoneUser user) {
         dao.insert(user);
    }

}
