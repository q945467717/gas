package com.wis.utils;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.stereotype.Component;

public class WebServiceUtil {

    public static Client createWebServiceClient(String wsdl){

        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient(wsdl);
        HTTPConduit conduit = (HTTPConduit)client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(2000);
        httpClientPolicy.setAllowChunking(false);
        httpClientPolicy.setReceiveTimeout(120000);
        conduit.setClient(httpClientPolicy);

        return client;
    }


}
