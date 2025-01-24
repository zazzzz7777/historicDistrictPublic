package com.cl.dao;

import com.cl.entity.DiscussshoufeijingdianEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussshoufeijingdianView;


/**
 * 收费景点评论表
 * 
 * @author 
 * @email 
 * @date 2024-12-25 16:38:46
 */
public interface DiscussshoufeijingdianDao extends BaseMapper<DiscussshoufeijingdianEntity> {
	
	List<DiscussshoufeijingdianView> selectListView(@Param("ew") Wrapper<DiscussshoufeijingdianEntity> wrapper);

	List<DiscussshoufeijingdianView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussshoufeijingdianEntity> wrapper);
	
	DiscussshoufeijingdianView selectView(@Param("ew") Wrapper<DiscussshoufeijingdianEntity> wrapper);


}
