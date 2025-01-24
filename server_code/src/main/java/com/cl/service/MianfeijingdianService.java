package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.MianfeijingdianEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.MianfeijingdianView;


/**
 * 免费景点
 *
 * @author 
 * @email 
 * @date 2024-12-25 16:38:45
 */
public interface MianfeijingdianService extends IService<MianfeijingdianEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<MianfeijingdianView> selectListView(Wrapper<MianfeijingdianEntity> wrapper);
   	
   	MianfeijingdianView selectView(@Param("ew") Wrapper<MianfeijingdianEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<MianfeijingdianEntity> wrapper);
   	
   
}

