package com.cl.dao;

import com.cl.entity.ShoufeijingdianEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ShoufeijingdianView;


/**
 * 收费景点
 * 
 * @author 
 * @email 
 * @date 2024-12-25 16:38:45
 */
public interface ShoufeijingdianDao extends BaseMapper<ShoufeijingdianEntity> {
	
	List<ShoufeijingdianView> selectListView(@Param("ew") Wrapper<ShoufeijingdianEntity> wrapper);

	List<ShoufeijingdianView> selectListView(Pagination page,@Param("ew") Wrapper<ShoufeijingdianEntity> wrapper);
	
	ShoufeijingdianView selectView(@Param("ew") Wrapper<ShoufeijingdianEntity> wrapper);


}
