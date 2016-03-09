package com.github.k3286.one_system.ws;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

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
    @Consumes({ MediaType.APPLICATION_JSON })
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @GET
    @Path("get_by_id/{id}")
    @JSONP
    @Consumes({ MediaType.APPLICATION_JSON })
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
    @Consumes({ MediaType.APPLICATION_JSON })
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
    @Consumes({ MediaType.APPLICATION_JSON })
    public void updateById(@PathParam("id") String id, User user) {
        if (id != null && !id.isEmpty()) {
            userDao.update(user);
        } else {
        }
    }

    @POST
    @Path("add")
    @JSONP
    @Consumes({ MediaType.APPLICATION_JSON })
    public void add(User user) {
        if (user.getId() != null) {
            userDao.add(user);
        }
    }

    @POST
    @Path("delete_by_id/{id}")
    @JSONP
    @Consumes({ MediaType.APPLICATION_JSON })
    public void deleteById(@PathParam("id") String id) {
        System.out.println("deleteById: " + id);
        if (id != null && !id.isEmpty()) {
            userDao.delete(id);
        }
    }
}
