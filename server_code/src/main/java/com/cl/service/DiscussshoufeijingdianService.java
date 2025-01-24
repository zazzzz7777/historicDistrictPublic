package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscussshoufeijingdianEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussshoufeijingdianView;


/**
 * 收费景点评论表
 *
 * @author 
 * @email 
 * @date 2024-12-25 16:38:46
 */
public interface DiscussshoufeijingdianService extends IService<DiscussshoufeijingdianEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussshoufeijingdianView> selectListView(Wrapper<DiscussshoufeijingdianEntity> wrapper);
   	
   	DiscussshoufeijingdianView selectView(@Param("ew") Wrapper<DiscussshoufeijingdianEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussshoufeijingdianEntity> wrapper);
   	
   
}

