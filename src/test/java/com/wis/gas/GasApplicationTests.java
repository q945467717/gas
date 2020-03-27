package com.wis.gas;

import com.alibaba.fastjson.JSON;
import com.wis.annotation.ApiResponse;
import com.wis.exception.ApiException;
import com.wis.pojo.po.Pvalues;
import com.wis.pojo.po.ScadaData;
import com.wis.pojo.po.User;
import com.wis.utils.ResponseCode;
import com.wis.utils.WebServiceUtil;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.namespace.QName;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GasApplicationTests {

    @Test
    public void contextLoads(){

        try {
            Client client = WebServiceUtil.createWebServiceClient("http://172.16.102.226:8080/QJYJWService/scadaService?wsdl");
            Object[] objects = client.invoke("findStationDataBySid","60");

            System.out.println(objects[0]);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {

        try {

            Client client = WebServiceUtil.createWebServiceClient("http://portal.cdgas.com/QJYJWService/scadaService?wsdl");
            QName qName = new QName("http://scada.ws.qjyj.com/","findStationDataBySid");

            Object[] objects = client.invoke(qName,"2",526l,"");

            String json = JSON.toJSONString(objects[0]);

            ScadaData scadaData = JSON.parseObject(json, ScadaData.class);

            List<Pvalues> list = scadaData.getPvalues();

            System.out.println(list.get(0).getPid());

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void test3() {

        System.out.println(new Date());

    }

}
