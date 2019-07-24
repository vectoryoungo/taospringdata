/**
 * @create 2019-07-24 15:47
 * @desc test paging and sorting
 **/
package com.shvector.springtrip.dao;

import com.shvector.springtrip.pojo.Users;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUserDaoExtendsPagingAndSortingRepository extends PagingAndSortingRepository<Users,Integer>{


}

