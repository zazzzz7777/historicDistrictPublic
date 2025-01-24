package com.cl.dao;

import com.cl.entity.GoupiaoxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.GoupiaoxinxiView;


/**
 * 购票信息
 * 
 * @author 
 * @email 
 * @date 2024-12-25 16:38:45
 */
public interface GoupiaoxinxiDao extends BaseMapper<GoupiaoxinxiEntity> {
	
	List<GoupiaoxinxiView> selectListView(@Param("ew") Wrapper<GoupiaoxinxiEntity> wrapper);

	List<GoupiaoxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<GoupiaoxinxiEntity> wrapper);
	
	GoupiaoxinxiView selectView(@Param("ew") Wrapper<GoupiaoxinxiEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<GoupiaoxinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<GoupiaoxinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<GoupiaoxinxiEntity> wrapper);



}
