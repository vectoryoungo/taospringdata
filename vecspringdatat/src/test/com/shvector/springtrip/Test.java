/**
 * @create 2019-07-05 16:32
 * @desc
 **/
package com.shvector.springtrip;

import com.shvector.springtrip.dao.IUserDao;
import com.shvector.springtrip.pojo.Users;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test {

    @Autowired
    private IUserDao IUserDao;


    @org.junit.Test
    public void find() {
        List<Users> usersList = IUserDao.findAllByUserAge(20);

        System.out.println("userList size " + usersList.size());
        System.out.println("content is " + usersList.get(0).getUserAge()+" " + usersList.get(0).getUserName());

    }

    @org.junit.Test
    public void save() {
        IUserDao.save("supertony",29);
    }

    @org.junit.Test
    public void delete() {
        IUserDao.delete(1);
    }
}

