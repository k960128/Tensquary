package com.tensquare.spit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;

import util.IdWorker;

@Service
@Transactional
public class SpitService {


	@Autowired
	private SpitDao spitDao;
	@Autowired
	private IdWorker idWorker;

	public List<Spit> findAll(){
		return spitDao.findAll();
	}

	public Spit findById(String id){
		return spitDao.findById(id).get();
	}

	public void save(Spit spit){
		spit.set_id(idWorker.nextId()+"");
		spitDao.save(spit);
	}

	public void update(Spit spit){
		spitDao.save(spit);
	}

	public void deleteById(String id){
		spitDao.deleteById(id);;
	}


	/**
	 * 根据上级ID查询吐槽列表
	 * @param parentid
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Spit> findByParentid(String parentid,int page, int size){
		PageRequest pageRequest = PageRequest.of(page-1, size);
		return spitDao.findByParentid(parentid, pageRequest);
	}
}
