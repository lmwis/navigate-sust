package com.fehead.navigate.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fehead.navigate.dao.PointMapper;
import com.fehead.navigate.dao.entity.SustPoint;
import com.fehead.lang.controller.BaseController;
import com.fehead.lang.error.BusinessException;
import com.fehead.lang.error.EmBusinessError;
import com.fehead.lang.response.CommonReturnType;
import com.fehead.lang.response.FeheadResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-30 16:57
 * @Version 1.0
 */
@RestController
@CrossOrigin(allowedHeaders="*",allowCredentials = "true")
public class PoiController extends BaseController {

    @Autowired
    PointMapper pointMapper;

    /**
     * 获取所有
     * @return
     */
    @GetMapping("/points/list")
    public FeheadResponse pointList(){

        return CommonReturnType.create(pointMapper.selectList(null));
    }

    /**
     * 根据name模糊匹配
     * @param name
     * @return
     */
    @GetMapping("/point/{name}")
    public FeheadResponse poiLikeName(@PathVariable("name")String name){

        QueryWrapper<SustPoint> queryWrapper = new QueryWrapper();

        queryWrapper.like("name",name);

        return CommonReturnType.create(pointMapper.selectList(queryWrapper));
    }

    /**
     * 创建poi
     * @param name
     * @param address
     * @param position
     * @param lng
     * @param lat
     * @param left
     * @param top
     * @param type
     * @param isDef
     * @return
     * @throws BusinessException
     */
    @PostMapping("/point")
    public FeheadResponse createPoi(String name,String address,String position,
                                    String lng,String lat,
                                    String left,String top,
                                    String type,String isDef) throws BusinessException {

        SustPoint sustPoint = validateAndPack(name, address, position, lng, lat, left, top, type, isDef);

        pointMapper.insert(sustPoint);

        return CommonReturnType.create(sustPoint);
    }

    /**
     * 获取详细信息
     * @param id
     * @return
     * @throws BusinessException
     */
    @GetMapping("/point/info")
    public FeheadResponse poiInfo(Integer id) throws BusinessException {
        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        SustPoint sustPoint = pointMapper.selectById(id);
        if(sustPoint==null){
            throw new BusinessException(EmBusinessError.DATARESOURCE_CONNECT_FAILURE,"所请求数据资源不存在");
        }
        return CommonReturnType.create(sustPoint);
    }

    /**
     * 校验和封装
     * @param name
     * @param address
     * @param position
     * @param lng
     * @param lat
     * @param left
     * @param top
     * @param type
     * @param isDef
     * @return
     * @throws BusinessException
     */
    private SustPoint validateAndPack(String name,String address,String position,
                                      String lng,String lat,
                                      String left,String top,
                                      String type,String isDef) throws BusinessException {
        if(StringUtils.isEmpty(name)||StringUtils.isEmpty(address)||StringUtils.isEmpty(position)||
                StringUtils.isEmpty(lng)||StringUtils.isEmpty(lat)||StringUtils.isEmpty(left)||
                StringUtils.isEmpty(top)||StringUtils.isEmpty(type)||StringUtils.isEmpty(isDef)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        SustPoint sustPoint = new SustPoint();
        sustPoint.setName(name);
        sustPoint.setAddress(address);
        sustPoint.setPosition(position);
        sustPoint.setLng(lng);
        sustPoint.setLat(lat);
        sustPoint.setOffset(left+","+top);
        sustPoint.setType(type);
        sustPoint.setIsDef(isDef.equals("yes")?1:0);
        return sustPoint;
    }

    /**
     * 修改point
     * @param id
     * @param name
     * @param address
     * @param position
     * @param lng
     * @param lat
     * @param left
     * @param top
     * @param type
     * @param isDef
     * @return
     * @throws BusinessException
     */
    @PutMapping("/point")
    public FeheadResponse changePoi(@RequestParam("id") Integer id ,@RequestParam("name")String name
            ,@RequestParam("address")String address,@RequestParam("position")String position,
                                    @RequestParam("lng")String lng,@RequestParam("lat")String lat,
                                    @RequestParam("left")String left,@RequestParam("top")String top,
                                    @RequestParam("type")String type,@RequestParam("isDef")String isDef) throws BusinessException {

        if(id==0) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);

        SustPoint sustPoint = validateAndPack(name, address, position, lng, lat, left, top, type, isDef);
        sustPoint.setId(id);

        pointMapper.updateById(sustPoint);

        return CommonReturnType.create(sustPoint);
    }

    /**
     * 删除point
     * @param id
     * @return
     * @throws BusinessException
     */
    @DeleteMapping("/point")
    public FeheadResponse delPoint(Integer id) throws BusinessException {

        if(id==0) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);

        pointMapper.deleteById(id);

        return CommonReturnType.create(null);
    }
}
