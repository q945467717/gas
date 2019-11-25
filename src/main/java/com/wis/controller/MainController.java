package com.wis.controller;

import com.wis.pojo.po.User;
import com.wis.pojo.vo.ItemInfo;
import com.wis.pojo.vo.SceneInfo;
import com.wis.service.ItemService;
import com.wis.service.MainService;
import com.wis.service.SceneService;
import com.wis.utils.ExcelUtil;
import com.wis.utils.ItemTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private SceneService sceneService;

    @RequestMapping("/")
    public String index(){

        return "redirect:toLogin";
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

        return "admin/index";
    }

    //登陆成功欢迎页面
    @RequestMapping("/admin/hello")
    public String hello(){
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
    public void downloadFile(HttpServletResponse response) throws IOException {
        InputStream f= this.getClass().getResourceAsStream("/static/file/demo.xls");

        response.reset();
        response.setContentType("application/x-msdownload;charset=utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(("demo" + ".xls").getBytes("gbk"), "iso-8859-1"));//下载文件的名称
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
        if(file.isEmpty()) {
            return "redirect:/item/importItem";
        }

        try {
            InputStream inputStream = file.getInputStream();
            List<List> list = ExcelUtil.getCourseListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();

            for(List list1:list){

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
    public String deleteAll(@RequestParam("checks[]") int[] checks,String typeName){

        mainService.deleteAll(checks,typeName);
        return "删除成功";
    }
    //去修改密码模态框
    @RequestMapping("/admin/toChangePassword")
    public String toChangePassword(){
        return "modal/changePasswordModal";
    }

}
