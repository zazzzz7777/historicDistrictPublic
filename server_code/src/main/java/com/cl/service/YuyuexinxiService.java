package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YuyuexinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YuyuexinxiView;


/**
 * 预约信息
 *
 * @author 
 * @email 
 * @date 2024-12-25 16:38:45
 */
public interface YuyuexinxiService extends IService<YuyuexinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YuyuexinxiView> selectListView(Wrapper<YuyuexinxiEntity> wrapper);
   	
   	YuyuexinxiView selectView(@Param("ew") Wrapper<YuyuexinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YuyuexinxiEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<YuyuexinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<YuyuexinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<YuyuexinxiEntity> wrapper);



}

