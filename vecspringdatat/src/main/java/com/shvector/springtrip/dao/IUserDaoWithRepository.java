/**
 * @create 2019-07-25 15:34
 * @desc test of extends UserRepository
 **/
package com.shvector.springtrip.dao;

import com.shvector.springtrip.pojo.Users;
import com.shvector.springtrip.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserDaoWithRepository extends JpaRepository<Users,Integer>,JpaSpecificationExecutor<Users>,UserRepository{
}

