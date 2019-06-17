package com.ocean.aspect;

import com.ocean.config.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyControllerAdvice {

//    应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
    @InitBinder
    public void initBinder(WebDataBinder binder) {}


//    把值绑定到Model中，使全局@RequestMapping可以获取到该值
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "chenhaiyang");
    }


    /*
    返回到error页面
     */
    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String errorHandler(MyException ex, HttpServletRequest request){
        request.setAttribute("code",ex.getCode());
        request.setAttribute("msg",ex.getMsg());
        return "error";
    }


    /*
    接口类型返回
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> errorHandler(Exception ex){
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",500);
        map.put("msg",ex.getMessage());
        return map;
    }

}
