/**
 * @create 2019-07-05 16:32
 * @desc
 **/
package com.shvector.springtrip;

import com.shvector.springtrip.dao.IUserDao;
import com.shvector.springtrip.dao.IUserDaoExtendsPagingAndSortingRepository;
import com.shvector.springtrip.pojo.Users;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test {

    @Autowired
    private IUserDao IUserDao;
    @Autowired
    private IUserDaoExtendsPagingAndSortingRepository userDaoExtendsPagingAndSortingRepository;


    @org.junit.Test
    public void find() {
        List<Users> usersList = IUserDao.findAllByUserAge(20);

        System.out.println("userList size " + usersList.size());
        System.out.println("content is " + usersList.get(0).getUserAge()+" " + usersList.get(0).getUserName());

    }

    @org.junit.Test
    public void save() {
        IUserDao.save("dukati",29);
    }

    @org.junit.Test
    public void delete() {
        IUserDao.delete(1);
    }

    @org.junit.Test
    public void pageing() {
        int page = 0;
        int size = 3;
        Pageable pageable = new PageRequest(page,size);
        Page<Users> usersPage = userDaoExtendsPagingAndSortingRepository.findAll(pageable);
        System.out.println("all data count " + usersPage.getTotalElements());
        System.out.println("all pages count " + usersPage.getTotalPages());
        List<Users> usersList = usersPage.getContent();
        for (Users users:usersList) {
            System.out.println(users);
        }
    }

    @org.junit.Test
    public void sortRecord() {
        Sort sort = new Sort(Sort.Direction.DESC,"userId");
        List<Users> list = (List<Users>) userDaoExtendsPagingAndSortingRepository.findAll(sort);
        for (Users users:list){
            System.out.println(users);
        }
    }

    @org.junit.Test
    public void multicolumnSort() {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"userAge");
        Sort.Order multi = new Sort.Order(Sort.Direction.ASC,"userName");
        Sort sort = new Sort(order,multi);
        List<Users> usersList = (List<Users>) userDaoExtendsPagingAndSortingRepository.findAll(sort);
        for (Users users:usersList) {
            System.out.println(users);
        }
    }
}

