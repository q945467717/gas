package com.wis.gas;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctc.wstx.util.DataUtil;
import com.wis.mapper.GasApiMapper;
import com.wis.mapper.ItemMapper;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.*;
import com.wis.utils.WebServiceUtil;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import lombok.SneakyThrows;
import okhttp3.*;
import org.apache.cxf.endpoint.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import javax.xml.namespace.QName;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GasApplicationTests {

    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private GasApiMapper gasApiMapper;

    @Test
    public void contextLoads() {

        try {
            Client client = WebServiceUtil.createWebServiceClient("http://172.16.102.226:8080/QJYJWService/scadaService?wsdl");
            Object[] objects = client.invoke("findStationDataBySid", "60");

            System.out.println(objects[0]);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {

        try {

            Client client = WebServiceUtil.createWebServiceClient("http://portal.cdgas.com/QJYJWService/scadaService?wsdl");
            QName qName = new QName("http://scada.ws.qjyj.com/", "findStationDataBySid");

            Object[] objects = client.invoke(qName, "2", 526l, "");

            String json = JSON.toJSONString(objects[0]);

            ScadaData scadaData = JSON.parseObject(json, ScadaData.class);

            List<Pvalues> list = scadaData.getPvalues();

            System.out.println(scadaData.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3() {

        OkHttpClient okHttpClient = new OkHttpClient();
        //String json = "{'116411578619954225':{'SLKBLLJ_01':{'descript1':'onebox','descript2':'0.361','value':'111'},'SLYLB_04':{'descript1':'onebox','descript2':'0.361','value':'111'}},'116411577086229939':{'SLKBLLJ_01':{'descript1':'onebox','descript2':'0.361','value':'14'},'SHCYLB_06':{'descript1':'onebox','descript2':'0.361','value':'111124'}}}";

        HashMap<String, Object> sceneMap = new HashMap<>();
        List<Scene> scenes = sceneMapper.findAllScene();

        for (Scene scene : scenes) {

            List<Item> items = itemMapper.findUidByLxAndSceneId(scene.getSceneId());
            HashMap<String, Object> itemMap = new HashMap<>();


            for (Item item : items) {

                List<ItemData> itemDataList = itemMapper.findDataByItemId(item.getId());
                HashMap<String, String> itemDataMap = new HashMap<>();

                if (itemDataList != null && itemDataList.size() > 0) {
                    for (int i = 0; i < itemDataList.size(); i++) {

                        itemDataMap.put("value" + i, itemDataList.get(i).getPname() + itemDataList.get(i).getPvalue() + itemDataList.get(i).getUnit());
                    }
                    itemMap.put(item.getUid(), itemDataMap);
                }

            }

            sceneMap.put(scene.getSceneId(), itemMap);
        }


//        StringBuffer stringBuffer = new StringBuffer("{");
//        List<Scene> scenes = sceneMapper.findAllScene();
//        for (int i = 0; i < scenes.size(); i++) {
//
//            List<Item> items = itemMapper.findUidByLxAndSceneId(scenes.get(i).getSceneId());
//
//            if (items != null && items.size() > 0) {
//                stringBuffer.append("'").append(scenes.get(i).getSceneId()).append("':{");
//                for (int j = 0; j < items.size(); j++) {
//
//                    List<ItemData> itemDataList = itemMapper.findDataByItemId(items.get(j).getId());
//
//                    if (itemDataList != null && itemDataList.size() > 0) {
//
//                        stringBuffer.append("'").append(items.get(j).getUid()).append("':{");
//
//                        for (int k = 0; k < itemDataList.size(); k++) {
//                            stringBuffer.append("'").append("value").append(k).append("':").append("'").append(itemDataList.get(k).getPname()).append(itemDataList.get(k).getPvalue()).append(itemDataList.get(k).getUnit()).append("'");
//
//                            if (k != itemDataList.size() - 1) {
//                                stringBuffer.append(",");
//                            }
//
//                        }
//                        if (j != items.size() - 1) {
//                            stringBuffer.append("},");
//                        }
//                    }
//                }
//                if (i != scenes.size() - 1) {
//                    stringBuffer.append("},");
//                }
//            }
//        }
//
//        stringBuffer.append("}");
//
//        System.out.println(stringBuffer);


        RequestBody body = new FormBody.Builder()
                .add("param", JSON.toJSONString(sceneMap))
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8081/data/putdata")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);

        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    @Test
    public void test4() {

        OkHttpClient okHttpClient = new OkHttpClient();

        String json = "{'116411578619954225':{'SLKBLLJ_01':{'descript1':'onebox','descript2':'0.361','value':'111'},'SLYLB_04':{'descript1':'onebox','descript2':'0.361','value':'111'}},'116411577086229939':{'SLKBLLJ_01':{'descript1':'onebox','descript2':'0.361','value':'14'},'SHCYLB_06':{'descript1':'onebox','descript2':'0.361','value':'111124'}}}";


        RequestBody body = new FormBody.Builder()
                .add("param", json)
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8081/init/scene.config")
                .build();

        Call call = okHttpClient.newCall(request);

        try {
            Response response = call.execute();

            if (response.body() != null) {

                Map<String, Object> map = JSONObject.parseObject(response.body().string());

                //System.out.println(response.body().string());

            }


        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    @Test
    public void test5() {

        List<Scene> scenes = sceneMapper.findAllScene();

        StringBuffer stringBuffer = new StringBuffer("{");

        for (int i = 0; i < scenes.size(); i++) {

            stringBuffer.append("'").append(scenes.get(i).getSceneId()).append("':{");

            List<Item> items = itemMapper.findUidByLxAndSceneId(scenes.get(i).getSceneId());

            for (int j = 0; j < items.size(); j++) {

                stringBuffer.append("'").append(items.get(j).getUid()).append("':{");

                List<ItemData> itemDataList = itemMapper.findDataByItemId(items.get(j).getId());

                for (int k = 0; k < itemDataList.size(); k++) {
                    stringBuffer.append("'").append(itemDataList.get(k).getPname()).append("':").append("'").append(itemDataList.get(k).getPvalue()).append(itemDataList.get(k).getUnit()).append("'");

                    if (k != itemDataList.size() - 1) {
                        stringBuffer.append(",");
                    }

                }

                if (j == items.size() - 1) {
                    stringBuffer.append("}");
                } else {
                    stringBuffer.append("},");
                }

            }

            if (i == scenes.size() - 1) {
                stringBuffer.append("}");
            } else {
                stringBuffer.append("},");
            }

        }

        stringBuffer.append("}");


        System.out.println(stringBuffer);

        String json = "{'116411578619954225':{'SLKBLLJ_01':{'descript1':'onebox','descript2':'0.361','value':'111'},'SLYLB_04':{'descript1':'onebox','descript2':'0.361','value':'111'}},'116411577086229939':{'SLKBLLJ_01':{'descript1':'onebox','descript2':'0.361','value':'14'},'SHCYLB_06':{'descript1':'onebox','descript2':'0.361','value':'111124'}}}";


    }

    @Test
    public void test10() {

        HashMap<String, Object> sceneMap = new HashMap<>();
        List<Scene> scenes = sceneMapper.findAllScene();

        for (Scene scene : scenes) {

            List<Item> items = itemMapper.findUidByLxAndSceneId(scene.getSceneId());
            HashMap<String, Object> itemMap = new HashMap<>();


            for (Item item : items) {

                List<ItemData> itemDataList = itemMapper.findDataByItemId(item.getId());
                HashMap<String, String> itemDataMap = new HashMap<>();

                if (itemDataList != null && itemDataList.size() > 0) {
                    for (int i = 0; i < itemDataList.size(); i++) {

                        itemDataMap.put("value" + i, itemDataList.get(i).getPname() + itemDataList.get(i).getPvalue() + itemDataList.get(i).getUnit());
                    }
                    itemMap.put(item.getUid(), itemDataMap);
                }

            }

            sceneMap.put(scene.getSceneId(), itemMap);
        }

        System.out.println(JSON.toJSONString(sceneMap));

    }

    public static class tests extends Thread {

        private List<Integer> list;
        private int i;


        @SneakyThrows
        @Override
        public void run() {

            list.add(i);

            System.out.println(Thread.currentThread().getName());

            Thread.sleep(1000);
        }

        public tests(List<Integer> list, int i) {

            this.list = list;
            this.i = i;

        }


    }

    public static class addTest {
        public void setList(List<Integer> list) {

            for (int i = 0; i < 10; i++) {

                //test.setList(list, i);
                tests test = new tests(list, i);
                test.start();

            }

        }

    }


    @Test
    public void test11() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8081/init/scene.config")
                .build();

        Call call = okHttpClient.newCall(request);

        try {
            Response response = call.execute();
            if (response.body() != null) {
                //sceneId和访问idJSON字符串转为map
                Map<String, Object> map = JSONObject.parseObject(response.body().string());

                //遍历map
                for (Map.Entry<String, Object> entry : map.entrySet()) {

                    //将map的value（值为sceneId）转为string
                    String sceneId = String.valueOf(entry.getValue());

                    //判断该sceneId是否存在，如果不存在添加记录
                    if (StringUtils.isEmpty(sceneMapper.findSceneById(sceneId))) {

                        Scene scene = new Scene(){{
                            setSceneId(sceneId);
                            setMomodaId(entry.getKey());
                            setAddTime(new Date());
                        }};

                        sceneMapper.addScene(scene);

                    }else {      //否则修改

                        sceneMapper.updateMomodaId(sceneId,entry.getKey());
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static class SynchronizedScene1 implements Runnable{

        @Override
        public void run(){
            if(Thread.currentThread().getName().equals("Thread-4")){
                method1();
            }else{
                method2();
            }
        }

        public synchronized static void method1(){
            System.out.println("method1开始执行:" + Thread.currentThread().getName());
            try {
                // 模拟执行内容
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("method1执行结束:" + Thread.currentThread().getName());
        }


        public synchronized static void method2(){
            System.out.println("method2开始执行:" + Thread.currentThread().getName());
            try {
                // 模拟执行内容
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("method2执行结束:" + Thread.currentThread().getName());
        }
    }

    static SynchronizedScene1 ss1 = new SynchronizedScene1();
    static SynchronizedScene1 ss2 = new SynchronizedScene1();

    @Test
    public void test111() throws InterruptedException {

            Thread t1 = new Thread(ss1);
            Thread t2 = new Thread(ss2);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("run over");

    }


}
