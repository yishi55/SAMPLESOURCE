package com.github.k3286.one_system.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.github.k3286.one_system.dao.UserDao;
import com.github.k3286.one_system.dao.impl.UserDaoImpl;

public class OneSystemConfig extends ResourceConfig {

    public OneSystemConfig() {
        packages("com.github.k3286.one_system.ws");

        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(UserDaoImpl.class).to(UserDao.class);
            }
        });
    }

}
