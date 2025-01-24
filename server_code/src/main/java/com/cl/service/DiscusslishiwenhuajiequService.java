package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscusslishiwenhuajiequEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusslishiwenhuajiequView;


/**
 * 历史文化街区评论表
 *
 * @author 
 * @email 
 * @date 2024-12-25 16:38:46
 */
public interface DiscusslishiwenhuajiequService extends IService<DiscusslishiwenhuajiequEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusslishiwenhuajiequView> selectListView(Wrapper<DiscusslishiwenhuajiequEntity> wrapper);
   	
   	DiscusslishiwenhuajiequView selectView(@Param("ew") Wrapper<DiscusslishiwenhuajiequEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusslishiwenhuajiequEntity> wrapper);
   	
   
}

