/**
 * @create 2019-07-05 16:32
 * @desc
 **/
package com.shvector.springtrip;

import com.shvector.springtrip.dao.IUserDao;
import com.shvector.springtrip.dao.IUserDaoExtendsJpaSpecificationExecutor;
import com.shvector.springtrip.dao.IUserDaoExtendsPagingAndSortingRepository;
import com.shvector.springtrip.dao.IUserDaoWithRepository;
import com.shvector.springtrip.pojo.Users;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test {

    @Autowired
    private IUserDao IUserDao;
    @Autowired
    private IUserDaoExtendsPagingAndSortingRepository userDaoExtendsPagingAndSortingRepository;
    @Autowired
    private IUserDaoExtendsJpaSpecificationExecutor userDaoExtendsJpaSpecificationExecutor;
    @Autowired
    private IUserDaoWithRepository iUserDaoWithRepository;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


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
    @org.junit.Test
    public void JpaSpecificationExecutor() {
        Specification<Users> specification = new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.equal(root.get("userName"),"kawasaki");
                return predicate;
            }
        };

        List<Users> usersList = userDaoExtendsJpaSpecificationExecutor.findAll(specification);

        for (Users users:usersList) {
            System.out.println(users);
        }
    }

    @org.junit.Test
    public void JpaSpecificationWithMany() {
        Specification<Users> specification = new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.or(criteriaBuilder.equal(root.get("userName"),"kawasaki"),criteriaBuilder.equal(root.get("userAge"),29));
                return predicate;
            }
        };

        List<Users> usersList = userDaoExtendsJpaSpecificationExecutor.findAll(specification);

        for (Users users:usersList) {
            System.out.println(users);
        }
    }

    @org.junit.Test
    public void userRepositoryWithDaoTest() {
        Users users = iUserDaoWithRepository.findUserById(5);
        System.out.println(users);
    }

    @org.junit.Test
    public void redisSingleSetTest(){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("tonyMark","2147496");
    }

    @org.junit.Test
    public void redisSingleGetTest() {
        String cache = (String) redisTemplate.opsForValue().get("tonyMark");
        System.out.println(" cache is " + cache);
    }

    @org.junit.Test
    public void objectRedisTest() {
        Users users = new Users();
        users.setUserAge(23);
        users.setUserId(10);
        users.setUserName("Martin");
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.opsForValue().set("users",users);
    }

    @org.junit.Test
    public void objectRedisReadTest() {
        Users object = (Users) redisTemplate.opsForValue().get("users");
        System.out.println(object);
    }

    @org.junit.Test
    public void testWithJackson() {
        Users users = new Users();
        users.setUserName("fucker");
        users.setUserId(99);
        users.setUserAge(88);
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Users>(Users.class));
        redisTemplate.opsForValue().set("userjson",users);
    }

    @org.junit.Test
    public void testWithJacksonQuery() {
        String str = (String) redisTemplate.opsForValue().get("userjson");
        System.out.println(str);
    }
}

