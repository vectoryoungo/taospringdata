/**
 * @create 2019-07-05 15:04
 * @desc userdao
 **/
package com.shvector.springtrip.dao;

import com.shvector.springtrip.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface IUserDao extends Repository<Users,Integer> {

    List<Users> findAllByUserAge(Integer userage);

    @Query(value = "insert into tb_users(username,userage)values(?,?)",nativeQuery = true)
    @Modifying
    @Transactional
    void save(String username,Integer userage);

    @Query(value = "delete from tb_users where userid = ?",nativeQuery = true)
    @Modifying
    void delete(Integer id);

    @Query(value = "update Users set userage = ? where userid = ?")
    @Modifying
    void update(Integer userage,Integer id);

    @Query(value = "from Users where username = ? and userage >= ?")
    List<Users> queryUserByNameAndAge(String name,Integer age);
}

