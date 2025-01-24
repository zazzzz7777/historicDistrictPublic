package com.cl.dao;

import com.cl.entity.DiscusslishiwenhuajiequEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusslishiwenhuajiequView;


/**
 * 历史文化街区评论表
 * 
 * @author 
 * @email 
 * @date 2024-12-25 16:38:46
 */
public interface DiscusslishiwenhuajiequDao extends BaseMapper<DiscusslishiwenhuajiequEntity> {
	
	List<DiscusslishiwenhuajiequView> selectListView(@Param("ew") Wrapper<DiscusslishiwenhuajiequEntity> wrapper);

	List<DiscusslishiwenhuajiequView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusslishiwenhuajiequEntity> wrapper);
	
	DiscusslishiwenhuajiequView selectView(@Param("ew") Wrapper<DiscusslishiwenhuajiequEntity> wrapper);


}
