package com.cl.dao;

import com.cl.entity.YuyuexinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YuyuexinxiView;


/**
 * 预约信息
 * 
 * @author 
 * @email 
 * @date 2024-12-25 16:38:45
 */
public interface YuyuexinxiDao extends BaseMapper<YuyuexinxiEntity> {
	
	List<YuyuexinxiView> selectListView(@Param("ew") Wrapper<YuyuexinxiEntity> wrapper);

	List<YuyuexinxiView> selectListView(Pagination page,@Param("ew") Wrapper<YuyuexinxiEntity> wrapper);
	
	YuyuexinxiView selectView(@Param("ew") Wrapper<YuyuexinxiEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuyuexinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuyuexinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuyuexinxiEntity> wrapper);



}
