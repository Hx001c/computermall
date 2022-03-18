package com.athx.computermall.controller;

import com.athx.computermall.controller.exception.*;
import com.athx.computermall.service.exception.*;
import com.athx.computermall.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import java.nio.file.spi.FileTypeDetector;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/12 16:32
 * @description：
 * @modified By：
 * @version: $
 */
@Controller
public class BaseController {
    //操作成功的效应状态码
    public static final int OK = 200;
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>();
        if (e instanceof UsernameDuplicateException) {
            //用户名被占用异常
            result.setState(5000);
            result.setMessage("用户名已占用的异常");
        } else if (e instanceof InsertException) {
            result.setState(5001);
            result.setMessage("插入时产生的异常");
        }else if(e instanceof UsernameNotFoundException){
            result.setState(5002);
            result.setMessage("用户名不存在的异常");
        }else if(e instanceof PasswordNotMatchException){
            result.setState(5003);
            result.setMessage("密码匹配失败的异常");
        }else if(e instanceof  UpdateException){
            result.setState(5004);
            result.setMessage("更新时产生的异常");
        }else if(e instanceof FileTypeException){
            result.setState(6000);
            result.setMessage("文件类型错误异常");
        }else if(e instanceof FileSIzeException){
            result.setState(6001);
            result.setMessage("文件大小异常");
        }else if(e instanceof FileUploadIoException){
            result.setState(6002);
            result.setMessage("文件上传出现io异常");
        }else if (e instanceof FileStateException){
            result.setState(6003);
            result.setMessage("文件状态异常");
        }else if(e instanceof AddressCountLimitException){
            result.setState(7000);
            result.setMessage("地址数量达到限制异常");
        }else if (e instanceof AccessDeniedException){
            result.setState(7001);
            result.setMessage("非法访问异常");
        }else if (e instanceof AddressNotFoundException){
            result.setState(7002);
            result.setMessage("地址不存在异常");
        }else if (e instanceof DeleteException){
            result.setState(8000);
            result.setMessage("删除时存在异常");
        }else if (e instanceof  ProductNotFoundException){
            result.setState(8001);
            result.setMessage("商品不存在的异常");
        }
        return result;
    }

    /**
     * session中的uid
     * @param session
     * @return
     */
    public final Integer getUidFromSession(HttpSession session){
        Integer uid = (Integer) session.getAttribute("uid");
        return uid;
    }
    public final String getUsernameFromSession(HttpSession session){
        String username = (String) session.getAttribute("username");
        return username;
    }
}

