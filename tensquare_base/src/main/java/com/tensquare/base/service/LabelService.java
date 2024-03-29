package com.tensquare.base.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;

import entity.PageResult;
import util.IdWorker;

@Service

public class LabelService {

	@Autowired
	private LabelDao labelDao;
	@Autowired
	private IdWorker idWorker;
	/**
	 * 查询全部标签
	 * @return
	 */
	public List<Label> findAll(){
		return labelDao.findAll();
	}
	/**
	 * 根据ID查询标签
	 * @return
	 */
	public Label findById(String id){
		return labelDao.findById(id).get();
	}
	/**
	 * 增加标签
	 * @param label
	 */
	public void add(Label label){
		label.setId( idWorker.nextId()+"" );//设置ID
		labelDao.save(label);
	}
	/**
	 * 修改标签
	 * @param label
	 */
	public void update(Label label){
		labelDao.save(label);
	}
	/**
	 * 删除标签
	 * @param id
	 */
	public void deleteById(String id){
		labelDao.deleteById(id);
	}

	/**
	 * 分页查询
	 */
	public List<Label> findSearch(Label label){
		return labelDao.findAll(new Specification<Label>() {

			@Override
			/**
			 * root：根对象，把需要的条件封装到哪个对象中
			 * cq：封装查询关键字
			 * cb：封装条件对象，如何直接返回null ，表示不需要任何条件 
			 */
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				//创建一个集合来存放所有条件
				List<Predicate> predicateList = new ArrayList<>();
				if(label.getLabelname()!=null&& !"".equals(label.getLabelname())){
					Predicate predicate = cb.like(root.get("labelname").as(String.class), "%"+label.getLabelname()+"%");
					predicateList.add(predicate);
				}
				if(label.getState()!=null&& !"".equals(label.getState())){
					Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
					predicateList.add(predicate);
				}

				//new一个数组作为最终返回值
				Predicate[] parr = new Predicate[predicateList.size()];
				//把list的数据转乘数组
				parr = predicateList.toArray(parr);
				return cb.and(parr);
			}
		});
	}


	/**
	 * 分页查询
	 * @param label
	 * @return
	 */
	public Page<Label> pageQuery(Label label,int page,int size){
		Pageable pageable = PageRequest.of(page-1, size);
		return (Page<Label>) labelDao.findAll(new Specification<Label>() {

			@Override
			/**
			 * root：根对象，把需要的条件封装到哪个对象中
			 * cq：封装查询关键字
			 * cb：封装条件对象，如何直接返回null ，表示不需要任何条件 
			 */
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				//创建一个集合来存放所有条件
				List<Predicate> predicateList = new ArrayList<>();
				if(label.getLabelname()!=null&& !"".equals(label.getLabelname())){
					Predicate predicate = cb.like(root.get("labelname").as(String.class), "%"+label.getLabelname()+"%");
					predicateList.add(predicate);
				}
				if(label.getState()!=null&& !"".equals(label.getState())){
					Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
					predicateList.add(predicate);
				}

				//new一个数组作为最终返回值
				Predicate[] parr = new Predicate[predicateList.size()];
				//把list的数据转乘数组
				parr = predicateList.toArray(parr);
				return cb.and(parr);
			}
		},pageable);
	}

}
