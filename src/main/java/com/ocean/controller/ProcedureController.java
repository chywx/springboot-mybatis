package com.ocean.controller;

import com.ocean.service.ProcedureService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("proc")
public class ProcedureController {

    @Autowired
    private ProcedureService service;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 传入sexid返回对应的性别数目 1表示男 0表示女
     * @return
     */
    @GetMapping("/testProcedure.do")
    public Integer testProcedure(){
        Map<String,Integer> parmeterMap=new HashMap<String,Integer>();
        parmeterMap.put("sexid", 1);
        parmeterMap.put("usercount", -1);
        service.testProcedure(parmeterMap);
        System.out.println("testProcedure:"+parmeterMap.get("usercount"));
        return parmeterMap.get("usercount");
    }


    @GetMapping("/testProcedure2.do")
    public Integer testProcedure2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession.getConnection());
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUserCount是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.ocean.dao.ProcedureDao.testProcedure2";//映射sql的标识字符串
        Map<String, Integer> parameterMap = new HashMap<String, Integer>();
        parameterMap.put("sexid", 1);
        parameterMap.put("usercount", -1);
        sqlSession.selectOne(statement, parameterMap);
        Integer result = parameterMap.get("usercount");
        System.out.println("testProcedure2:"+result);
        return parameterMap.get("usercount");
    }

}
