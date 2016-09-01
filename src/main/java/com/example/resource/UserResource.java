package com.example.resource;

import com.alibaba.fastjson.JSON;
import com.example.cache.UserCache;
import com.example.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/user")
@Produces({"application/json;charset=utf-8"})//返回类型
public class UserResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }


    @POST
    @Consumes("application/json;charset=utf-8")
    public User newUser(User user) throws IOException {
        System.out.println("bean:\tuserName: " + user);
        UserCache.getUserCache().put(user.getUserId(), user);
        return user;
    }

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") String id) {
        System.out.println("json get -----------------------------------");
        User user = UserCache.getUserCache().get(id);
        if (user == null) {
            throw new NotFoundException("No such User.");
        }
        System.out.println("查询结果:\t" + user);
        return user;
    }

    @GET
    @Path("/map/all")
    @Consumes("application/json")
    public Map<String, User> getAllUsers() {
        System.out.println("返回Map");
        return UserCache.getUserCache();
    }

    @GET
    @Path("/list/all")
//    @Produces({"application/xml"})
    public Response getListUsers() {
        List<String> result = UserCache.getUserCache().keySet().stream().collect(Collectors.toList());
        System.out.println("返回列表:\t" + result);
        return Response.ok(result).build();
    }

    @GET
    @Path("/list2/all")
    public String getListUsers2() {
        List<User> result = new ArrayList<>();
                UserCache.getUserCache().keySet().stream().forEach(key -> {
            result.add(UserCache.getUserCache().get(key));
        });
        System.out.println("返回列表2:\t" + JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }


//    @GET
//    @Path("/list_json/all")
//    @Consumes("application/json")
//    public JsonArray getListJsonUsers() {
//        final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
//        List<String> result = UserCache.getUserCache().keySet().stream().collect(Collectors.toList());
//        System.out.println("返回列表:\t" + result);
//        for (String aResult : result) {
//            arrayBuilder.add(aResult);
//        }
//        return arrayBuilder.build();
//    }

//    @GET
//    @Path("/list_json2/all")
//    @Consumes("application/json")
//    public JsonArray getListJsonUsers2() {
//        Map<String,User> users = UserCache.getUserCache();
//        List<User> result = users.keySet().stream().map(users::get).collect(Collectors.toList());
//        System.out.println(result);
//        JSONArray jsonArray = new JSONArray().put(result);
//        System.out.println("结果: " + jsonArray );
//        JsonArrayBuilder builder = Json.createArrayBuilder();
//        System.out.println(builder.add(String.valueOf(jsonArray)).build());
//        return builder.add(String.valueOf(jsonArray)).build();
//    }


    public static void main(String[] args) {
        Response response = new UserResource().getListUsers();
        System.out.println();
    }

}