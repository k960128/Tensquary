package com.tensquare.recruit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
	
	/**
	 * 推荐职位查询
	 */
	public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);
	
	/**
	* 最新职位列表
	* @param state
	* @return
	*/
	public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
