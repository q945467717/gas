package com.wis.webservice;


import org.springframework.stereotype.Component;

@Component("helloImpl")
@javax.jws.WebService(
        serviceName = "webService",
        targetNamespace = "http://webservice.wis.com",
        endpointInterface = "com.wis.webservice.WebService")
public class WebServiceImpl implements WebService{


    @Override
    public String seyHello(String name) {

        System.out.println("=================>"+name);

        return "hello:"+ name;

    }

}
