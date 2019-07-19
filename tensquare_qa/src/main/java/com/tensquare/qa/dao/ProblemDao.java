package com.tensquare.qa.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.tensquare.qa.pojo.Problem;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
	
	
	/**
	 * 最新问答列表
	 * @param labelid
	 * @param pageable
	 * @return
	 */
	@Query(value="SELECT * from tb_problem as pro ,tb_pl as pl where pro.id = pl.problemid and pl.labelid =? ORDER BY pro.replytime DESC"
			, nativeQuery=true)
	public Page<Problem> newlist(String labelid,Pageable pageable);
	
	/**
	 * 热门问答列表
	 * @return
	 */
	@Query(value="SELECT * from tb_problem as pro ,tb_pl as pl where pro.id = pl.problemid and pl.labelid =? ORDER BY pro.reply DESC"
			, nativeQuery=true)
	public Page<Problem> hotlist(String labelid,Pageable pageable);
	
	/**
	 * 等待回答列表
	 * @return
	 */
	@Query(value="SELECT * from tb_problem as pro ,tb_pl as pl where pro.id = pl.problemid and pl.labelid =? and replay=0 ORDER BY pro.createtim DESC"
			, nativeQuery=true)
	public Page<Problem> waitlist(String labelid,Pageable pageable);
}
