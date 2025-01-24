package com.cl.dao;

import com.cl.entity.MianfeijingdianEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.MianfeijingdianView;


/**
 * 免费景点
 * 
 * @author 
 * @email 
 * @date 2024-12-25 16:38:45
 */
public interface MianfeijingdianDao extends BaseMapper<MianfeijingdianEntity> {
	
	List<MianfeijingdianView> selectListView(@Param("ew") Wrapper<MianfeijingdianEntity> wrapper);

	List<MianfeijingdianView> selectListView(Pagination page,@Param("ew") Wrapper<MianfeijingdianEntity> wrapper);
	
	MianfeijingdianView selectView(@Param("ew") Wrapper<MianfeijingdianEntity> wrapper);


}
