package com.wis.webservice;


import com.wis.pojo.po.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

@javax.jws.WebService(targetNamespace = "http://webservice.wis.com")
public interface WebService {

    @WebMethod
    @WebResult
    User seyHello(@WebParam(name = "userName") String name);

}
