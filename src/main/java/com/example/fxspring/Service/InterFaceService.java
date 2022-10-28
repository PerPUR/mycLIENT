package com.example.fxspring.Service;

import com.example.fxspring.entity.phoneUser;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface InterFaceService {

    public List<phoneUser> getAllUsers();

    public phoneUser getUserByPhone(String phone);

    public List<phoneUser> getUserByName(String name);

    public void deleteByPhone(String phone);

    public void deleteByName(String name);

    public void alterPhone(String phone,phoneUser user);

    public void alterName(String phone,phoneUser user);

    public void addUser(phoneUser user);

}
