package com.tensquare.spit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

@Controller
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

	@Autowired
	private SpitService spitService;

	/**
	 * 查询评论
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功", spitService.findAll());
	}

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Result findOne(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",spitService.findById(id));
	}

	/**
	 * 增加
	 * @param spit
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Spit spit ){
		spitService.save(spit);
		return new Result(true,StatusCode.OK,"增加成功");
	}

	/* 修改
	 * @param spit
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public Result update(@RequestBody Spit spit,@PathVariable String id )
	{
		spit.set_id(id);
		spitService.update(spit);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public Result deleteById(@PathVariable String id ){
		spitService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	/**
	 * 根据上级ID查询吐槽分页数据
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value="/comment/{parentId}/{page}/{size}",method=RequestMethod.GET)
	public Result findByParentid(@PathVariable String parentId,
			@PathVariable int page,@PathVariable int size){
		Page<Spit> pageList = spitService.findByParentid(parentId,page,size);
		return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(pageList.getTotalElements(), pageList.getContent() ) );
	}

	
	/**
	 * 
	 */

}
