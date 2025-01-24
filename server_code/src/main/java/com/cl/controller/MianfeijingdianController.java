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

import com.cl.entity.MianfeijingdianEntity;
import com.cl.entity.view.MianfeijingdianView;

import com.cl.service.MianfeijingdianService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 免费景点
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-25 16:38:45
 */
@RestController
@RequestMapping("/mianfeijingdian")
public class MianfeijingdianController {
    @Autowired
    private MianfeijingdianService mianfeijingdianService;

    @Autowired
    private StoreupService storeupService;






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,MianfeijingdianEntity mianfeijingdian,
                                                                                                                                                                                                                                        HttpServletRequest request){
                                    EntityWrapper<MianfeijingdianEntity> ew = new EntityWrapper<MianfeijingdianEntity>();
                                                                                                                                                                                                                                                                                                                                                                            
        
        
        PageUtils page = mianfeijingdianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, mianfeijingdian), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,MianfeijingdianEntity mianfeijingdian, 
		HttpServletRequest request){
        EntityWrapper<MianfeijingdianEntity> ew = new EntityWrapper<MianfeijingdianEntity>();

		PageUtils page = mianfeijingdianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, mianfeijingdian), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( MianfeijingdianEntity mianfeijingdian){
       	EntityWrapper<MianfeijingdianEntity> ew = new EntityWrapper<MianfeijingdianEntity>();
      	ew.allEq(MPUtil.allEQMapPre( mianfeijingdian, "mianfeijingdian")); 
        return R.ok().put("data", mianfeijingdianService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(MianfeijingdianEntity mianfeijingdian){
        EntityWrapper< MianfeijingdianEntity> ew = new EntityWrapper< MianfeijingdianEntity>();
 		ew.allEq(MPUtil.allEQMapPre( mianfeijingdian, "mianfeijingdian")); 
		MianfeijingdianView mianfeijingdianView =  mianfeijingdianService.selectView(ew);
		return R.ok("查询免费景点成功").put("data", mianfeijingdianView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        MianfeijingdianEntity mianfeijingdian = mianfeijingdianService.selectById(id);
    if(null==mianfeijingdian.getClickNumber()){
        mianfeijingdian.setClickNumber(0);
    }
		mianfeijingdian.setClickNumber(mianfeijingdian.getClickNumber()+1);
		mianfeijingdianService.updateById(mianfeijingdian);
		mianfeijingdian = mianfeijingdianService.selectView(new EntityWrapper<MianfeijingdianEntity>().eq("id", id));
        return R.ok().put("data", mianfeijingdian);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        MianfeijingdianEntity mianfeijingdian = mianfeijingdianService.selectById(id);
		mianfeijingdian.setClickNumber(mianfeijingdian.getClickNumber()+1);
		mianfeijingdianService.updateById(mianfeijingdian);
		mianfeijingdian = mianfeijingdianService.selectView(new EntityWrapper<MianfeijingdianEntity>().eq("id", id));
        return R.ok().put("data", mianfeijingdian);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        MianfeijingdianEntity mianfeijingdian = mianfeijingdianService.selectById(id);
        if(type.equals("1")) {
        	mianfeijingdian.setThumbsupNumber(mianfeijingdian.getThumbsupNumber()+1);
        } else {
        	mianfeijingdian.setCrazilyNumber(mianfeijingdian.getCrazilyNumber()+1);
        }
        mianfeijingdianService.updateById(mianfeijingdian);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MianfeijingdianEntity mianfeijingdian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(mianfeijingdian);
        mianfeijingdianService.insert(mianfeijingdian);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody MianfeijingdianEntity mianfeijingdian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(mianfeijingdian);
        mianfeijingdianService.insert(mianfeijingdian);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody MianfeijingdianEntity mianfeijingdian, HttpServletRequest request){
        //ValidatorUtils.validateEntity(mianfeijingdian);
        mianfeijingdianService.updateById(mianfeijingdian);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<MianfeijingdianEntity> list = new ArrayList<MianfeijingdianEntity>();
        for(Long id : ids) {
            MianfeijingdianEntity mianfeijingdian = mianfeijingdianService.selectById(id);
            mianfeijingdian.setSfsh(sfsh);
            mianfeijingdian.setShhf(shhf);
            list.add(mianfeijingdian);
        }
        mianfeijingdianService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        mianfeijingdianService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
