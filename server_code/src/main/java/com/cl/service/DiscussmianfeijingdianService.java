package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscussmianfeijingdianEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussmianfeijingdianView;


/**
 * 免费景点评论表
 *
 * @author 
 * @email 
 * @date 2024-12-25 16:38:46
 */
public interface DiscussmianfeijingdianService extends IService<DiscussmianfeijingdianEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussmianfeijingdianView> selectListView(Wrapper<DiscussmianfeijingdianEntity> wrapper);
   	
   	DiscussmianfeijingdianView selectView(@Param("ew") Wrapper<DiscussmianfeijingdianEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussmianfeijingdianEntity> wrapper);
   	
   
}

