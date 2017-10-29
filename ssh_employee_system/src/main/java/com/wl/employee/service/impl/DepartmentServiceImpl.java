package com.wl.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wl.employee.dao.DepartmentDao;
import com.wl.employee.domain.Department;
import com.wl.employee.domain.PageBean;
import com.wl.employee.service.DepartmentService;
/**
 * 部门管理的业务层的实现类
 */
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDao departmentDao;

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	//分页查询部分的方法
	public PageBean<Department> findByPage(Integer currentPage) {
		PageBean<Department> pageBean = new PageBean<Department>();
		//封装当前页数
		pageBean.setCurrentPage(currentPage);
		//封装每页显示数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = departmentDao.findCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize); //Math.ceil()浮点数向上取整
		pageBean.setTotalPage(num.intValue());
		//封装每页显示的数据
		int begin = (currentPage - 1) * pageSize;
		List<Department> list = departmentDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	//业务层保存部门的方法
	public void save(Department department) {
		departmentDao.save(department);
	}

	@Override
	//业务层根据部门ID查询部门的方法
	public Department findById(Integer did) {
		Department department = departmentDao.findById(did);
		return department;
	}

	@Override
	//业务层修改部门方法
	public void update(Department department) {
		departmentDao.update(department);
	}

	@Override
	//业务层删除部门的方法
	public void delete(Department department) {
		departmentDao.delete(department);
	}

	@Override
	//查询所有部门的方法
	public List<Department> findAll() {
		List<Department> list = departmentDao.findAll();
		return list;
	}
	
}
