package com.wis.webservice;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

@javax.jws.WebService(targetNamespace = "http://webservice.wis.com")
public interface WebService {

    @WebMethod
    public @WebResult String seyHello(@WebParam(name = "userName") String name);

}
