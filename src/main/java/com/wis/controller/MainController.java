package com.wis.controller;

import com.wis.mapper.ItemMapper;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.Item;
import com.wis.pojo.po.Scene;
import com.wis.pojo.po.User;
import com.wis.pojo.vo.ApiResult;
import com.wis.pojo.vo.ItemInfo;
import com.wis.pojo.vo.SceneInfo;
import com.wis.security.CustomUserService;
import com.wis.service.ItemService;
import com.wis.service.MainService;
import com.wis.service.SceneService;
import com.wis.utils.ExcelUtil;
import com.wis.utils.ItemTypeUtil;
import com.wis.utils.ResponseCode;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ApiIgnore
public class MainController {

    @Autowired
    private MainService mainService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private SceneService sceneService;
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Value("${IP}")
    private String ip;


    @CrossOrigin
    @RequestMapping("/login")
    @ResponseBody
    public ApiResult login(@RequestBody User user){

        return new ApiResult(ResponseCode.VALIDATED_ERROR,null);
    }

    @RequestMapping("/")
    public String index(){

        return "redirect:toLogin";
    }

    @RequestMapping("/updateScene")
    public String updateScene(){
        return "redirect:http://"+ip+":8081/home";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    //跳转到管理员首页
    @RequestMapping("/admin/index")
    public String adminIndex(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user",user);
        model.addAttribute("ip",ip);

        return "admin/index";
    }

    //登陆成功欢迎页面
    @RequestMapping("/admin/hello")
    public String hello(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user",user);

        List<Scene> sceneInfoList = sceneMapper.findAllScene();

        model.addAttribute("allCount",sceneInfoList.size());

        //model.addAttribute("sceneInfoList",sceneInfoList);

        List<Map<String ,Object>> mapList = new ArrayList<>();

        List<Item> itemList;
        int dangerCount = 0;
        int normalCount = 0;

        for(Scene scene:sceneInfoList){

            Map map = new HashMap();
            map.put("name",scene.getSceneName());


            int nCount = 0;
            itemList = itemMapper.findBySceneId(scene.getSceneId());
            for(Item item:itemList){
                if(item.getWtzt()==3){
                    map.put("status",3);
                    dangerCount++;
                    break;
                }
                if(item.getWtzt()==2){
                    nCount++;

                }
            }
            if(nCount!=0){
                map.put("status",2);
                normalCount++;
            }
            mapList.add(map);

        }

        model.addAttribute("sceneInfoList",mapList);

        model.addAttribute("warningDanger",dangerCount);
        model.addAttribute("warningNormal",normalCount);

        return "admin/hello";
    }

    //文件下载
//    @RequestMapping("/downloadFile")
//    public String downloadFile(HttpServletResponse response) throws UnsupportedEncodingException {
//
//        String filename="demo.xls";
//        //String filePath = "D:\\IdeaProjects\\gas\\src\\main\\resources\\static\\file" ;
//
//       String  filePath = "D:\\IdeaProjects\\gas\\src\\main\\resources\\static\\file" ;
//
//
//
//        File file = new File(filePath + "/" + filename);
//        if(file.exists()){ //判断文件父目录是否存在
//            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//            response.setCharacterEncoding("UTF-8");
//            // response.setContentType("application/force-download");
//            response.setHeader("Content-Disposition","attachment;fileName="+java.net.URLEncoder.encode(filename,"UTF-8"));
//            byte[] buffer = new byte[1024];
//            FileInputStream fis = null; //文件输入流
//            BufferedInputStream bis = null;
//
//            OutputStream os = null; //输出流
//            try {
//                os = response.getOutputStream();
//                fis = new FileInputStream(file);
//                bis = new BufferedInputStream(fis);
//                int i = bis.read(buffer);
//                while(i != -1){
//                    os.write(buffer);
//                    i = bis.read(buffer);
//                }
//
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            System.out.println("----------file download---" + filename);
//            try {
//                bis.close();
//                fis.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }


    @ResponseBody
    @RequestMapping(value = "/downloadFile")
    public void downloadFile(HttpServletResponse response,String type) throws IOException {
        InputStream f = null;
        if("demo".equals(type)){
            f= this.getClass().getResourceAsStream("/static/file/demo.xls");
        }else if("uid".equals(type)){
            f= this.getClass().getResourceAsStream("/static/file/uid.xls");
        }

        response.reset();
        response.setContentType("application/x-msdownload;charset=utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((type + ".xls").getBytes("gbk"), "iso-8859-1"));//下载文件的名称
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ServletOutputStream sout = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(f);
            bos = new BufferedOutputStream(sout);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bos.flush();
            bos.close();
            bis.close();
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null){
                bis.close();
            }
            if (bos != null){
                bos.close();
            }
        }
    }

    //上传Excel文件
    @PostMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartHttpServletRequest.getFile("courseFile");

        if(StringUtils.isEmpty(file)) {
            return "redirect:/item/importItem";
        }

        try {
            InputStream inputStream = file.getInputStream();
            List<List<Cell>> list = ExcelUtil.getCourseListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();

            for(List<Cell> list1:list){

                ItemInfo itemInfo = new ItemInfo();

                String sceneId = list1.get(0).toString();
                SceneInfo sceneInfo = sceneService.showScene(sceneId);
                itemInfo.setSceneName(sceneInfo.getSceneName());

                int itemType = Integer.parseInt(list1.get(1).toString()) ;
                itemInfo.setItemType(ItemTypeUtil.type(itemType));

                String uid = list1.get(2).toString();
                itemInfo.setUid(uid);

                String itemName = list1.get(3).toString();
                itemInfo.setItemName(itemName);

                String text = list1.get(4).toString();
                itemInfo.setText(text);

                itemService.addItem(itemInfo);

            }

        } catch (Exception e) {
            return "redirect:/item/importItem";
        }

        return "redirect:/item/itemManage";
    }

    //去批量删除模态框
    @RequestMapping("/toDeleteAll")
    public String toDeleteAll(String id, Model model){

        model.addAttribute("id",id);

        return "modal/deleteAllModal";
    }

    /**
     * 批量删除
     * @param checks 被选中
     */
    @ResponseBody
    @RequestMapping("/admin/deleteAll")
    public ApiResult deleteAll(@RequestParam("checks[]") int[] checks,String typeName){

        mainService.deleteAll(checks,typeName);
        return new ApiResult(ResponseCode.SUCCESS,"删除成功");
    }
    //去修改密码模态框
    @RequestMapping("/admin/toChangePassword")
    public String toChangePassword(){
        return "modal/changePasswordModal";
    }

    @PostMapping("/addScene")
    @ResponseBody
    public ApiResult addScene(String momodaId,HttpServletRequest request) {

        String request_referer = request.getHeader("Referer");

        System.out.println(request_referer);

        if(StringUtils.isEmpty(request_referer)||StringUtils.isEmpty(momodaId)){
            return new ApiResult(ResponseCode.VALIDATED_ERROR);
        }else if(!request_referer.equals("http://"+ip+":8081/")){
            return new ApiResult(ResponseCode.VALIDATED_ERROR);
        }

        Scene scene = sceneMapper.findByMomodaId(momodaId);

        if(StringUtils.isEmpty(scene)){
            sceneService.uploadScene(momodaId);
            sceneService.matchingSceneId();
        }

        return new ApiResult(ResponseCode.UPLOAD_SUCCESS);
    }

}
