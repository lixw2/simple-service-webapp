package com.example.cache;


import com.example.entity.User;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by li on 2016/8/6.
 */
public class UserCache {
    private static Map<String,User> userCache;
    private static UserCache instance = null;
    private UserCache() {
        userCache = new HashMap<String,User>();
        initOneUser();
    }
    public static Map<String,User> getUserCache() {
        if(instance==null) {
            instance = new UserCache();
        }
        return userCache;
    }
    private static void initOneUser() {
        User user = new User("000","wanger",20);
        userCache.put(user.getUserId(),user);
        User user1 = new User("001","zhaohongxuan",24);
        userCache.put(user1.getUserId(),user1);
    }
}

