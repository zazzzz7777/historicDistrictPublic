package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ShoufeijingdianEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ShoufeijingdianView;


/**
 * 收费景点
 *
 * @author 
 * @email 
 * @date 2024-12-25 16:38:45
 */
public interface ShoufeijingdianService extends IService<ShoufeijingdianEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ShoufeijingdianView> selectListView(Wrapper<ShoufeijingdianEntity> wrapper);
   	
   	ShoufeijingdianView selectView(@Param("ew") Wrapper<ShoufeijingdianEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ShoufeijingdianEntity> wrapper);
   	
   
}

