/**
 * @create 2019-07-25 15:37
 * @desc implement IUserDaoWithRepository
 **/
package com.shvector.springtrip.dao;

import com.shvector.springtrip.pojo.Users;
import com.shvector.springtrip.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class IUserDaoWithRepositoryImpl implements UserRepository {

    @PersistenceContext(name = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public Users findUserById(Integer userId) {
        System.out.println(" query in findUserById ");
        return this.entityManager.find(Users.class,userId);
    }
}

