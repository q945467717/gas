package com.wis.gas;

import com.alibaba.fastjson.JSON;
import com.wis.utils.WebServiceUtil;
import com.wis.webservice.WebService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GasApplicationTests {

    @Test
    void contextLoads(){

        try {
            Client client = WebServiceUtil.createWebServiceClient("http://www.webxml.com.cn/WebServices/TrainTimeWebService.asmx?wsdl");
            Object[] objects = client.invoke("getDetailInfoByTrainCode","Z50");
            System.out.println(objects[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void test() {


        try {
            // 接口地址
            String address = "http://localhost:8080/cxf/hello?wsdl";
            // 代理工厂
            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
            // 设置代理地址
            jaxWsProxyFactoryBean.setAddress(address);
            // 设置接口类型
            jaxWsProxyFactoryBean.setServiceClass(WebService.class);
            // 创建一个代理接口实现
            WebService us = (WebService) jaxWsProxyFactoryBean.create();

            // 数据准备
            String userId = "945467717";
            // 调用代理接口的方法调用并返回结果
            String result = us.seyHello(userId);

            System.out.println("返回结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void test2() {

        try {

            Client client = WebServiceUtil.createWebServiceClient("http://localhost:8080/cxf/hello?wsdl");
            Object[] objects = client.invoke("seyHello","zhang");

            Object json = JSON.toJSON(objects[0]);

            System.out.println(json);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
