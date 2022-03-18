package com.athx.computermall.controller;

import com.athx.computermall.bean.User;
import com.athx.computermall.controller.exception.*;
import com.athx.computermall.service.IUserService;
import com.athx.computermall.utils.JsonResult;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.oracle.webservices.internal.api.message.BasePropertySet;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/12 16:10
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/regist")
    public JsonResult<Void> regist(User user){
        //调用业务对象执行注册
        userService.userRegist(user);
        //返回 (未出现错误)
        return new JsonResult<Void>(OK,"注册成功");
    }
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        System.out.println("controller");
        User user = userService.login(username, password);
        JsonResult<User> userJsonResult = new JsonResult<>(OK,user);
        userJsonResult.setMessage("登录成功");
        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",user.getUsername());
        System.out.println(getUidFromSession(session));
        return userJsonResult;
    }

    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.ModifyPassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK,"修改成功");
    }


    @RequestMapping("/update_info")
    public JsonResult<Void> updateIndo(User user,HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.updateInfoByUserid(uid,username,user);
        return new JsonResult<>(OK);
     }
     @RequestMapping("/get_by_uid")
    public JsonResult<User> getUserByid(HttpSession session){
         User user
                 = userService.getById(getUidFromSession(session));
        return new JsonResult<User>(OK,user);

     }

    /**
     * 限制文件上传的大小
     */
    public static final int AVATAR_MAX_SIZE = 10*1024*1024;

    public static final List<String> AVATAR_TYPE=new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/jpeg");
    }
     @RequestMapping("/change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file) throws FileNotFoundException {
        //1.判断上传的文件是否为空
         if(file.isEmpty()){
             throw new FileEmptyException("上传文件为空");
         }
         //2.判断上传的文件大小是否超出限制默认以字节为单位
         if(file.getSize()>AVATAR_MAX_SIZE){
             throw new FileSIzeException("文件超出最大限制"+AVATAR_MAX_SIZE+"KB");
         }
         //3.判断上传文件类型是否正确
         String contentType = file.getContentType();
         System.out.println(contentType);
         if(!AVATAR_TYPE.contains(contentType)){
             throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPE);
         }
         // 获取当前项目的绝对磁盘路径
         String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/upload/";

//         String realPath = session.getServletContext().getRealPath("upload");
//         System.out.println(realPath);
         // 保存头像文件的文件夹
         File dir = new File(basePath);
         if(!dir.exists()){
             //文件不存在就创建文件
             dir.mkdir();
         }
         // 保存的头像文件的文件名
         String originalFilename = file.getOriginalFilename();
         System.out.println(originalFilename);
         //获取上传文件的后缀
         String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));

         String filename =
                 UUID.randomUUID().toString().toUpperCase() + suffix;


         // 创建文件对象，表示保存的头像文件
         File dest = new File(dir, filename);



         // 执行保存头像文件
         try {
             file.transferTo(dest);
         } catch (IllegalStateException e) {
             // 抛出异常
             throw new FileStateException("文件状态异常，可能文件已被移动或删除");
         } catch (IOException e) {
             // 抛出异常
             throw new FileUploadIoException("上传文件时出现读写错误,请稍后重试");
         }
         // 如果产生异常则抛出

         // 头像路径
         String avatar  = "/upload/"+filename;
         // 从Session中获取uid和username
         // 将头像写入到数据库中
        userService.changeAvatar(getUidFromSession(session),avatar,getUsernameFromSession(session));
         // 返回成功和头像路径
         JsonResult<String> stringJsonResult = new JsonResult<>(OK);
         stringJsonResult.setData(avatar);
         return stringJsonResult;
     }
}
