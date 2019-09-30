package com.fehead.controller;

import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.fehead.dao.PasswordMapper;
import com.fehead.dao.PointMapper;
import com.fehead.dao.UnitMapper;
import com.fehead.dao.UserMapper;
import com.fehead.dao.entity.Password;
import com.fehead.dao.entity.SustPoint;
import com.fehead.dao.entity.Unit;
import com.fehead.dao.entity.User;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import com.fehead.response.FeheadResponse;
import com.fehead.response.MetronicDatatableType;
import com.fehead.response.MetronicMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-29 14:07
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class AdminController extends BaseController{

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PointMapper pointMapper;

    @Autowired
    PasswordMapper passwordMapper;
    @Autowired
    UnitMapper unitMapper;

    /**
     * 默认管理员信息初始化
     * @param token
     * @return
     * @throws BusinessException
     */
    @PostMapping("/init")
    public FeheadResponse initAdmin(String token) throws BusinessException {
        if("fehead".equals(token)){

            User lmwis = userMapper.selectByUsername("lmwis");
            Password password = new Password();
            String passwordStr = getEncodeDefaultPassword();
            password.setPassword(passwordStr);
            if(lmwis==null){
                lmwis = new User();
                passwordMapper.insert(password);
                lmwis.setPasswordId(password.getId());
                lmwis.setUsername("lmwis");
                lmwis.setTel("15389159576");
                userMapper.insert(lmwis);
            }else {
                // 存在则重置密码
                password.setId(lmwis.getPasswordId());
                passwordMapper.updateById(password);
            }

            return CommonReturnType.create(lmwis);
        }
        throw new BusinessException(EmBusinessError.WRONG_TOKEN);
    }

    private String getEncodeDefaultPassword(){
        return passwordEncoder.encode("admin");
    }

    /**
     * 获取所有的poi地址
     * @return Metronic模版要求的类型
     */
    @GetMapping("/points/list")
    public  FeheadResponse pointsList(@PageableDefault(size = 10,page=1) Pageable pageable){
        List<SustPoint> sustPoints = pointMapper.selectList(null);

        // 总页数
        int totalPage = sustPoints.size()/pageable.getPageSize()+1;

        MetronicMeta meta = geneatorMeta(pageable,totalPage,sustPoints.size());

        return MetronicDatatableType.create(sustPoints,meta);
    }

    /**
     * 获取所有的unit单元
     * @return Metronic模版要求的类型
     */
    @GetMapping("/units/list")
    public  FeheadResponse unitsList(@PageableDefault(size = 10,page=1) Pageable pageable){
        List<Unit> unitList = unitMapper.selectList(null);

        // 总页数
        int totalPage = unitList.size()/pageable.getPageSize()+1;
        MetronicMeta meta = geneatorMeta(pageable,totalPage,unitList.size());

        return MetronicDatatableType.create(unitList,meta);
    }

    private MetronicMeta geneatorMeta(Pageable pageable,Integer totalPage,Integer size){
        MetronicMeta meta = new MetronicMeta();
        meta.setPage(pageable.getPageNumber());
        meta.setPerpage(pageable.getPageSize());
        meta.setSort("asc");
        meta.setTotal(size);
        meta.setPages(totalPage);
        meta.setField("id");
        return meta;
    }

}
