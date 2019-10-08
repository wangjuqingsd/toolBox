package cn.ytg.toolBox.service.impl;

import cn.ytg.toolBox.dao.UserDao;
import cn.ytg.toolBox.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class IndexServiceImpl implements IndexService {
    @Autowired
    private UserDao userDao;

    @Override
    public void login(String userName,String password){
        userDao.save(userName, password);
    }
}
