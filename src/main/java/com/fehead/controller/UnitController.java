package com.fehead.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fehead.aspect.UriCounter;
import com.fehead.config.vo.UnitVO;
import com.fehead.dao.PointMapper;
import com.fehead.dao.UnitMapper;
import com.fehead.dao.entity.SustPoint;
import com.fehead.dao.entity.Unit;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import com.fehead.response.FeheadResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-31 20:37
 * @Version 1.0
 */
@RestController
public class UnitController extends BaseController{

    @Autowired
    UnitMapper unitMapper;
    @Autowired
    PointMapper pointMapper;

    /**
     * 获取单元的单独信息
     * @return
     */
    @GetMapping("/unit/lists")
    public FeheadResponse unitList() {

        return CommonReturnType.create(unitMapper.selectList(null));
    }

    /**
     * 根据name模糊匹配
     * @param name
     * @return
     */
    @GetMapping("/unit/{name}")
    @UriCounter
    public FeheadResponse unitLikeName(@PathVariable String name){

        return CommonReturnType.create(unitMapper.selectUnitInfoByLikeName(name));
    }

    /**
     * 获取详细信息
     * @param id
     * @return
     * @throws BusinessException
     */
    @GetMapping("/unit/info")

    public FeheadResponse unitInfo(Integer id) throws BusinessException {
        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        UnitVO unitVO = unitMapper.selectUnitInfoById(id);
        if(unitVO==null){
            throw new BusinessException(EmBusinessError.DATARESOURCE_CONNECT_FAILURE,"所请求数据资源不存在");
        }
        return CommonReturnType.create(unitVO);
    }

    /**
     * 新建unit
     * @param name
     * @param position
     * @param poiId
     * @param des
     * @return
     * @throws BusinessException
     */
    @PostMapping("/unit")
    public FeheadResponse createUnit(String name,String position
            ,int poiId,String des) throws BusinessException {

        Unit unit  = validateAndPack(name, position, poiId, des);

        SustPoint sustPoint = pointMapper.selectById(poiId);
        if(sustPoint==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        // 默认不可用
        unit.setStatus(2);
        unitMapper.insert(unit);

        return CommonReturnType.create(unit);

    }

    /**
     * 校验和封装
     * @param name
     * @param position
     * @param pointId
     * @param des
     * @return
     * @throws BusinessException
     */
    private Unit validateAndPack(String name,String position
            ,int pointId,String des) throws BusinessException {
        if(StringUtils.isEmpty(name)||StringUtils.isEmpty(des)||StringUtils.isEmpty(position)||
                pointId==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        Unit unit = new Unit();
        unit.setDes(des);
        unit.setName(name);
        unit.setPointId(pointId);
        unit.setPosition(position);

        return unit;
    }

    /**
     * 删除unit
     * @param id
     * @return
     * @throws BusinessException
     */
    @DeleteMapping("/unit")
    public FeheadResponse delUnit(Integer id) throws BusinessException {

        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        unitMapper.deleteById(id);

        return CommonReturnType.create(null);
    }
    @PutMapping("/unit/status")
    public FeheadResponse updateStatus(Integer id) throws BusinessException {
        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        Unit unit = unitMapper.selectById(id);
        if(unit.getStatus()==1){
            unit.setStatus(2);
        }else{
            unit.setStatus(1);
        }

        unitMapper.updateById(unit);

        return CommonReturnType.create(unit);
    }

}
