package com.wis.gas;

import com.alibaba.fastjson.JSON;
import com.wis.pojo.po.User;
import com.wis.utils.WebServiceUtil;
import com.wis.webservice.WebService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.namespace.QName;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GasApplicationTests {

    @Test
    public void contextLoads(){

        try {
            Client client = WebServiceUtil.createWebServiceClient("file:D://TrainTimeWebService.wsdl");
            Object[] objects = client.invoke("getDetailInfoByTrainCode","Z50","");

            System.out.println(objects[0]);


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void test8() {


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
            User result = us.seyHello(userId);

            System.out.println("返回结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test2() {

        try {

            Client client = WebServiceUtil.createWebServiceClient("file:D://TrainTimeWebService.wsdl");
            QName qName = new QName("http://WebXml.com.cn/","getVersionTime");

            Object[] objects = client.invoke(qName);

            String json = JSON.toJSONString(objects);

            System.out.println(json);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
