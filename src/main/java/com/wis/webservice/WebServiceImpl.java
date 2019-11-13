package com.wis.webservice;


import com.wis.pojo.po.User;
import org.springframework.stereotype.Component;

@Component("helloImpl")
@javax.jws.WebService(
        serviceName = "webService",
        targetNamespace = "http://webservice.wis.com",
        endpointInterface = "com.wis.webservice.WebService")
public class WebServiceImpl implements WebService{


    @Override
    public User seyHello(String name) {

        System.out.println("=================>"+name);

        User user = new User();
        user.setCname(name);
        user.setLxdh("15023750519");


        return user;

    }

}
