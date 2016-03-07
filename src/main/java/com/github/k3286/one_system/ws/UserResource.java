package com.github.k3286.one_system.ws;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.glassfish.jersey.server.JSONP;

import com.github.k3286.one_system.dao.UserDao;
import com.github.k3286.one_system.model.User;

@Path("user")
public class UserResource {

    @Inject
    private UserDao userDao;

    @GET
    @Path("list")
    @JSONP
    @Produces({ "application/json" })
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @GET
    @Path("get_by_id/{id}")
    @JSONP
    @Produces({ "application/json" })
    public User getById(@PathParam("id") String id) {
        System.out.println("getById: " + id);
        if (id != null && !id.isEmpty()) {
            return userDao.getById(id);
        } else {
            return null;
        }
    }

    @GET
    @Path("list_by_name/{name}")
    @JSONP
    @Produces({ "application/json" })
    public List<User> getUserListByName(@PathParam("name") String name) {
        if (name != null && !name.isEmpty()) {
            return userDao.getUserListByName(name);
        } else {
            return null;
        }
    }

    @PUT
    @Path("update_by_id/{id}")
    @JSONP
    @Consumes({ "application/json" })
    public void updateById(@PathParam("id") String id, User user) {

        System.out.println("updateById: " + user);
        if (id != null && !id.isEmpty()) {
            // TODO Userインスタンスの作成
            userDao.update(new User());
        } else {
        }
    }

    @POST
    @Path("add")
    @JSONP
    @Produces({ "application/json" })
    public void add(@BeanParam User user) {
        System.out.println("add: " + user);
        // TODO Userインスタンスの作成
        userDao.add(user);
    }

    @POST
    @Path("delete_by_id/{id}")
    @JSONP
    @Produces({ "application/json" })
    public void deleteById(@PathParam("id") String id) {
        System.out.println("deleteById: " + id);
        if (id != null && !id.isEmpty()) {
            userDao.delete(id);
        } else {
        }
    }
}
