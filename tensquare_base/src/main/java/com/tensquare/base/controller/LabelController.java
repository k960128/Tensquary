package com.tensquare.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/label")
public class LabelController {
	
	@Autowired
	private LabelService labelService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll(){
		
		return new Result(true,StatusCode.OK,"查询成功",labelService.findAll());
	}
	
	@RequestMapping( value="/{lableId}",method = RequestMethod.GET)
	public Result findById(@PathVariable String lableId){
		return new Result(true,StatusCode.OK,"查询成功",labelService.findById(lableId));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Label label){
		labelService.add(label);
		return new Result(true,StatusCode.OK,"添加成功");
	}
	@RequestMapping(value="/{lableId}",method = RequestMethod.PUT)
	public Result update(@PathVariable String labelId,@RequestBody Label label){
		labelService.findById(labelId);
		labelService.update(label);
		return new Result(true,StatusCode.OK,"更新成功");
	}
	@RequestMapping(value="/{lableId}",method = RequestMethod.DELETE)
	public Result del(@PathVariable String labelId){
		labelService.deleteById(labelId);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
	@RequestMapping(value="/search",method = RequestMethod.POST)
	public Result search(@RequestBody Label label){
		
		List<Label> list = labelService.findSearch(label);
		return new Result(true,StatusCode.OK,"查询成功",list);
	}
	
	
	@RequestMapping(value="/search/{page}/{size}",method = RequestMethod.POST)
	public Result pageQuery(@RequestBody Label label ,@PathVariable int page , @PathVariable int size){
		
		Page<Label> pageData =  labelService.pageQuery(label,page,size);
		return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(pageData.getTotalElements(), pageData.getContent()));
	}
}
