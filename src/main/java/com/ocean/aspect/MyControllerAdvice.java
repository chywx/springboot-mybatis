package com.ocean.aspect;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

    @ExceptionHandler(Exception.class)
    public String errorHandler(Exception ex, HttpServletRequest request){
//        map.addAttribute("code",500);
//        map.addAttribute("msg",ex.getMessage());
        request.setAttribute("msg",ex.getMessage());
        return "error";
    }

//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public Map<String,Object> errorHandler(Exception ex){
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("code",500);
//        map.put("msg",ex.getMessage());
//        return map;
//    }

}
