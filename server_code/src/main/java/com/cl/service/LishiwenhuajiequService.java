package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.LishiwenhuajiequEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.LishiwenhuajiequView;


/**
 * 历史文化街区
 *
 * @author 
 * @email 
 * @date 2024-12-25 16:38:46
 */
public interface LishiwenhuajiequService extends IService<LishiwenhuajiequEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<LishiwenhuajiequView> selectListView(Wrapper<LishiwenhuajiequEntity> wrapper);
   	
   	LishiwenhuajiequView selectView(@Param("ew") Wrapper<LishiwenhuajiequEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<LishiwenhuajiequEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<LishiwenhuajiequEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<LishiwenhuajiequEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<LishiwenhuajiequEntity> wrapper);



}

