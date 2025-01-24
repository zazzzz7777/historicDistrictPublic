package com.cl.dao;

import com.cl.entity.LishiwenhuajiequEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.LishiwenhuajiequView;


/**
 * 历史文化街区
 * 
 * @author 
 * @email 
 * @date 2024-12-25 16:38:46
 */
public interface LishiwenhuajiequDao extends BaseMapper<LishiwenhuajiequEntity> {
	
	List<LishiwenhuajiequView> selectListView(@Param("ew") Wrapper<LishiwenhuajiequEntity> wrapper);

	List<LishiwenhuajiequView> selectListView(Pagination page,@Param("ew") Wrapper<LishiwenhuajiequEntity> wrapper);
	
	LishiwenhuajiequView selectView(@Param("ew") Wrapper<LishiwenhuajiequEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<LishiwenhuajiequEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<LishiwenhuajiequEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<LishiwenhuajiequEntity> wrapper);



}
