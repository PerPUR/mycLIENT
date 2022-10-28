package com.example.fxspring;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.fxspring.Dao.InterfaceDao;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.fxspring.entity.phoneUser;
import com.example.fxspring.Service.ImService.ServiceUser;

import java.util.List;

@MapperScan("com.example.fxspring.Dao")
@SpringBootTest
class FxSpringApplicationTests {

    @Autowired
    ServiceUser userService;

    @Autowired
    InterfaceDao interfaceDao;

    @Test
    void contextLoads() {
        for(phoneUser user : userService.getAllUsers()){
            System.out.println("123");
        }
    }
    @Test
    void testSql(){
        phoneUser user = new phoneUser();
        user.setUphone("110");
        user.setUsername("王义兰");

        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uphone",user.getUphone());
        updateWrapper.set("username",user.getUsername());
        interfaceDao.update(user,updateWrapper);
    }

}
