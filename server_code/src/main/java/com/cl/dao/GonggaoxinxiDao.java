package com.cl.dao;

import com.cl.entity.GonggaoxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.GonggaoxinxiView;


/**
 * 公告信息
 * 
 * @author 
 * @email 
 * @date 2024-12-25 16:38:46
 */
public interface GonggaoxinxiDao extends BaseMapper<GonggaoxinxiEntity> {
	
	List<GonggaoxinxiView> selectListView(@Param("ew") Wrapper<GonggaoxinxiEntity> wrapper);

	List<GonggaoxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<GonggaoxinxiEntity> wrapper);
	
	GonggaoxinxiView selectView(@Param("ew") Wrapper<GonggaoxinxiEntity> wrapper);


}
