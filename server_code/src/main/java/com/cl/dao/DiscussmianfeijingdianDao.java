package com.cl.dao;

import com.cl.entity.DiscussmianfeijingdianEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussmianfeijingdianView;


/**
 * 免费景点评论表
 * 
 * @author 
 * @email 
 * @date 2024-12-25 16:38:46
 */
public interface DiscussmianfeijingdianDao extends BaseMapper<DiscussmianfeijingdianEntity> {
	
	List<DiscussmianfeijingdianView> selectListView(@Param("ew") Wrapper<DiscussmianfeijingdianEntity> wrapper);

	List<DiscussmianfeijingdianView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussmianfeijingdianEntity> wrapper);
	
	DiscussmianfeijingdianView selectView(@Param("ew") Wrapper<DiscussmianfeijingdianEntity> wrapper);


}
