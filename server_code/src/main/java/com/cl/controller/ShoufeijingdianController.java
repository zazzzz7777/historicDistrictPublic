package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.ShoufeijingdianEntity;
import com.cl.entity.view.ShoufeijingdianView;

import com.cl.service.ShoufeijingdianService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 收费景点
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-25 16:38:45
 */
@RestController
@RequestMapping("/shoufeijingdian")
public class ShoufeijingdianController {
    @Autowired
    private ShoufeijingdianService shoufeijingdianService;

    @Autowired
    private StoreupService storeupService;






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShoufeijingdianEntity shoufeijingdian,
                                                                                                                @RequestParam(required = false) Double menpiaojiagestart,
                    @RequestParam(required = false) Double menpiaojiageend,
                                                                                                                                                                    HttpServletRequest request){
                                    EntityWrapper<ShoufeijingdianEntity> ew = new EntityWrapper<ShoufeijingdianEntity>();
                                                                                                                                                                    if(menpiaojiagestart!=null) ew.ge("menpiaojiage", menpiaojiagestart);
                    if(menpiaojiageend!=null) ew.le("menpiaojiage", menpiaojiageend);
                                                                                                                                                                                                                                                                
        
        
        PageUtils page = shoufeijingdianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shoufeijingdian), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ShoufeijingdianEntity shoufeijingdian, 
                @RequestParam(required = false) Double menpiaojiagestart,
                @RequestParam(required = false) Double menpiaojiageend,
		HttpServletRequest request){
        EntityWrapper<ShoufeijingdianEntity> ew = new EntityWrapper<ShoufeijingdianEntity>();
                if(menpiaojiagestart!=null) ew.ge("menpiaojiage", menpiaojiagestart);
                if(menpiaojiageend!=null) ew.le("menpiaojiage", menpiaojiageend);

		PageUtils page = shoufeijingdianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shoufeijingdian), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShoufeijingdianEntity shoufeijingdian){
       	EntityWrapper<ShoufeijingdianEntity> ew = new EntityWrapper<ShoufeijingdianEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shoufeijingdian, "shoufeijingdian")); 
        return R.ok().put("data", shoufeijingdianService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShoufeijingdianEntity shoufeijingdian){
        EntityWrapper< ShoufeijingdianEntity> ew = new EntityWrapper< ShoufeijingdianEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shoufeijingdian, "shoufeijingdian")); 
		ShoufeijingdianView shoufeijingdianView =  shoufeijingdianService.selectView(ew);
		return R.ok("查询收费景点成功").put("data", shoufeijingdianView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShoufeijingdianEntity shoufeijingdian = shoufeijingdianService.selectById(id);
    if(null==shoufeijingdian.getClickNumber()){
        shoufeijingdian.setClickNumber(0);
    }
		shoufeijingdian.setClickNumber(shoufeijingdian.getClickNumber()+1);
		shoufeijingdianService.updateById(shoufeijingdian);
		shoufeijingdian = shoufeijingdianService.selectView(new EntityWrapper<ShoufeijingdianEntity>().eq("id", id));
        return R.ok().put("data", shoufeijingdian);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShoufeijingdianEntity shoufeijingdian = shoufeijingdianService.selectById(id);
		shoufeijingdian.setClickNumber(shoufeijingdian.getClickNumber()+1);
		shoufeijingdianService.updateById(shoufeijingdian);
		shoufeijingdian = shoufeijingdianService.selectView(new EntityWrapper<ShoufeijingdianEntity>().eq("id", id));
        return R.ok().put("data", shoufeijingdian);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        ShoufeijingdianEntity shoufeijingdian = shoufeijingdianService.selectById(id);
        if(type.equals("1")) {
        	shoufeijingdian.setThumbsupNumber(shoufeijingdian.getThumbsupNumber()+1);
        } else {
        	shoufeijingdian.setCrazilyNumber(shoufeijingdian.getCrazilyNumber()+1);
        }
        shoufeijingdianService.updateById(shoufeijingdian);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShoufeijingdianEntity shoufeijingdian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(shoufeijingdian);
        shoufeijingdianService.insert(shoufeijingdian);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShoufeijingdianEntity shoufeijingdian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(shoufeijingdian);
        shoufeijingdianService.insert(shoufeijingdian);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ShoufeijingdianEntity shoufeijingdian, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shoufeijingdian);
        shoufeijingdianService.updateById(shoufeijingdian);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ShoufeijingdianEntity> list = new ArrayList<ShoufeijingdianEntity>();
        for(Long id : ids) {
            ShoufeijingdianEntity shoufeijingdian = shoufeijingdianService.selectById(id);
            shoufeijingdian.setSfsh(sfsh);
            shoufeijingdian.setShhf(shhf);
            list.add(shoufeijingdian);
        }
        shoufeijingdianService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shoufeijingdianService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
