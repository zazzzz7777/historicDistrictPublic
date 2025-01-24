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

import com.cl.entity.DiscusslishiwenhuajiequEntity;
import com.cl.entity.view.DiscusslishiwenhuajiequView;

import com.cl.service.DiscusslishiwenhuajiequService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 历史文化街区评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-25 16:38:46
 */
@RestController
@RequestMapping("/discusslishiwenhuajiequ")
public class DiscusslishiwenhuajiequController {
    @Autowired
    private DiscusslishiwenhuajiequService discusslishiwenhuajiequService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscusslishiwenhuajiequEntity discusslishiwenhuajiequ,
                                                                                                                            HttpServletRequest request){
                                    EntityWrapper<DiscusslishiwenhuajiequEntity> ew = new EntityWrapper<DiscusslishiwenhuajiequEntity>();
                                                                                                                                                                                        
        
        
        PageUtils page = discusslishiwenhuajiequService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusslishiwenhuajiequ), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscusslishiwenhuajiequEntity discusslishiwenhuajiequ, 
		HttpServletRequest request){
        EntityWrapper<DiscusslishiwenhuajiequEntity> ew = new EntityWrapper<DiscusslishiwenhuajiequEntity>();

		PageUtils page = discusslishiwenhuajiequService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusslishiwenhuajiequ), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscusslishiwenhuajiequEntity discusslishiwenhuajiequ){
       	EntityWrapper<DiscusslishiwenhuajiequEntity> ew = new EntityWrapper<DiscusslishiwenhuajiequEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discusslishiwenhuajiequ, "discusslishiwenhuajiequ")); 
        return R.ok().put("data", discusslishiwenhuajiequService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscusslishiwenhuajiequEntity discusslishiwenhuajiequ){
        EntityWrapper< DiscusslishiwenhuajiequEntity> ew = new EntityWrapper< DiscusslishiwenhuajiequEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discusslishiwenhuajiequ, "discusslishiwenhuajiequ")); 
		DiscusslishiwenhuajiequView discusslishiwenhuajiequView =  discusslishiwenhuajiequService.selectView(ew);
		return R.ok("查询历史文化街区评论表成功").put("data", discusslishiwenhuajiequView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscusslishiwenhuajiequEntity discusslishiwenhuajiequ = discusslishiwenhuajiequService.selectById(id);
		discusslishiwenhuajiequ = discusslishiwenhuajiequService.selectView(new EntityWrapper<DiscusslishiwenhuajiequEntity>().eq("id", id));
        return R.ok().put("data", discusslishiwenhuajiequ);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscusslishiwenhuajiequEntity discusslishiwenhuajiequ = discusslishiwenhuajiequService.selectById(id);
		discusslishiwenhuajiequ = discusslishiwenhuajiequService.selectView(new EntityWrapper<DiscusslishiwenhuajiequEntity>().eq("id", id));
        return R.ok().put("data", discusslishiwenhuajiequ);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscusslishiwenhuajiequEntity discusslishiwenhuajiequ, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusslishiwenhuajiequ);
        discusslishiwenhuajiequService.insert(discusslishiwenhuajiequ);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscusslishiwenhuajiequEntity discusslishiwenhuajiequ, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusslishiwenhuajiequ);
        discusslishiwenhuajiequService.insert(discusslishiwenhuajiequ);
        return R.ok();
    }

     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscusslishiwenhuajiequEntity discusslishiwenhuajiequ = discusslishiwenhuajiequService.selectOne(new EntityWrapper<DiscusslishiwenhuajiequEntity>().eq("", username));
        return R.ok().put("data", discusslishiwenhuajiequ);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscusslishiwenhuajiequEntity discusslishiwenhuajiequ, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusslishiwenhuajiequ);
        discusslishiwenhuajiequService.updateById(discusslishiwenhuajiequ);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discusslishiwenhuajiequService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscusslishiwenhuajiequEntity discusslishiwenhuajiequ, HttpServletRequest request,String pre){
        EntityWrapper<DiscusslishiwenhuajiequEntity> ew = new EntityWrapper<DiscusslishiwenhuajiequEntity>();
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
		PageUtils page = discusslishiwenhuajiequService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusslishiwenhuajiequ), params), params));
        return R.ok().put("data", page);
    }









}
