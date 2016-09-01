package com.example.resource

import com.alibaba.fastjson.JSON
import com.example.entity.User

/**
 * Created by Administrator on 2016/8/28.
 */
class UserResourceTest {


    static void listToJson() {

        List<User> users = new ArrayList<>();
        int count = 0;
        5.times {
            User user = new User();
            user.userAge = new Random().nextInt(20) + 5;
            user.userName = "张三";
            user.setUserId(String.valueOf(++count));
            users.add(user)
        }

        println users
        println JSON.toJSON(users)
        println JSON.toJSONString(users)
    }

    public static void main(String[] args) {
        listToJson()
    }
}
