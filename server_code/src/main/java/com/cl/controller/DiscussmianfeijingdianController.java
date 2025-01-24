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

import com.cl.entity.DiscussmianfeijingdianEntity;
import com.cl.entity.view.DiscussmianfeijingdianView;

import com.cl.service.DiscussmianfeijingdianService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 免费景点评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-25 16:38:46
 */
@RestController
@RequestMapping("/discussmianfeijingdian")
public class DiscussmianfeijingdianController {
    @Autowired
    private DiscussmianfeijingdianService discussmianfeijingdianService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussmianfeijingdianEntity discussmianfeijingdian,
                                                                                                                            HttpServletRequest request){
                                    EntityWrapper<DiscussmianfeijingdianEntity> ew = new EntityWrapper<DiscussmianfeijingdianEntity>();
                                                                                                                                                                                        
        
        
        PageUtils page = discussmianfeijingdianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussmianfeijingdian), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussmianfeijingdianEntity discussmianfeijingdian, 
		HttpServletRequest request){
        EntityWrapper<DiscussmianfeijingdianEntity> ew = new EntityWrapper<DiscussmianfeijingdianEntity>();

		PageUtils page = discussmianfeijingdianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussmianfeijingdian), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussmianfeijingdianEntity discussmianfeijingdian){
       	EntityWrapper<DiscussmianfeijingdianEntity> ew = new EntityWrapper<DiscussmianfeijingdianEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussmianfeijingdian, "discussmianfeijingdian")); 
        return R.ok().put("data", discussmianfeijingdianService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussmianfeijingdianEntity discussmianfeijingdian){
        EntityWrapper< DiscussmianfeijingdianEntity> ew = new EntityWrapper< DiscussmianfeijingdianEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussmianfeijingdian, "discussmianfeijingdian")); 
		DiscussmianfeijingdianView discussmianfeijingdianView =  discussmianfeijingdianService.selectView(ew);
		return R.ok("查询免费景点评论表成功").put("data", discussmianfeijingdianView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussmianfeijingdianEntity discussmianfeijingdian = discussmianfeijingdianService.selectById(id);
		discussmianfeijingdian = discussmianfeijingdianService.selectView(new EntityWrapper<DiscussmianfeijingdianEntity>().eq("id", id));
        return R.ok().put("data", discussmianfeijingdian);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussmianfeijingdianEntity discussmianfeijingdian = discussmianfeijingdianService.selectById(id);
		discussmianfeijingdian = discussmianfeijingdianService.selectView(new EntityWrapper<DiscussmianfeijingdianEntity>().eq("id", id));
        return R.ok().put("data", discussmianfeijingdian);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussmianfeijingdianEntity discussmianfeijingdian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussmianfeijingdian);
        discussmianfeijingdianService.insert(discussmianfeijingdian);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussmianfeijingdianEntity discussmianfeijingdian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussmianfeijingdian);
        discussmianfeijingdianService.insert(discussmianfeijingdian);
        return R.ok();
    }

     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussmianfeijingdianEntity discussmianfeijingdian = discussmianfeijingdianService.selectOne(new EntityWrapper<DiscussmianfeijingdianEntity>().eq("", username));
        return R.ok().put("data", discussmianfeijingdian);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussmianfeijingdianEntity discussmianfeijingdian, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussmianfeijingdian);
        discussmianfeijingdianService.updateById(discussmianfeijingdian);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussmianfeijingdianService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussmianfeijingdianEntity discussmianfeijingdian, HttpServletRequest request,String pre){
        EntityWrapper<DiscussmianfeijingdianEntity> ew = new EntityWrapper<DiscussmianfeijingdianEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = discussmianfeijingdianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussmianfeijingdian), params), params));
        return R.ok().put("data", page);
    }









}
