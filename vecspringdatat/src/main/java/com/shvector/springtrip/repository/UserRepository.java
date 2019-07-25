/**
 * @create 2019-07-25 15:30
 * @desc test of repository
 **/
package com.shvector.springtrip.repository;

import com.shvector.springtrip.pojo.Users;

public interface UserRepository {

    public Users findUserById(Integer userId);
}

