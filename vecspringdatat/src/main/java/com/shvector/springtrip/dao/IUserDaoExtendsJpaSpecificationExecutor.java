/**
 * @create 2019-07-24 16:39
 * @desc test JpaSpecificationExecutor
 **/
package com.shvector.springtrip.dao;

import com.shvector.springtrip.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserDaoExtendsJpaSpecificationExecutor extends JpaRepository<Users,Integer>,JpaSpecificationExecutor<Users>{
}

