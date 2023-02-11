package com.dailycodebuffer.repository;

import com.dailycodebuffer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "USER";

    @Override
    public boolean saveUser(User user) {
        try {
            redisTemplate.opsForHash().put(KEY, user.getId().toString(), user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> fetchAllUsers() {
        List<User> users;
        users = redisTemplate.opsForHash().values(KEY);
        return users;
    }

    @Override
    public User fetchUserById(Long id) {
        User user = (User) redisTemplate.opsForHash().get(KEY, id.toString());
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            long result = redisTemplate.opsForHash().delete(KEY, id.toString());
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
