package com.wl.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wl.employee.dao.EmployeeDao;
import com.wl.employee.domain.Employee;
import com.wl.employee.domain.PageBean;
import com.wl.employee.service.EmployeeService;
/**
 * 员工管理的业务层实现类
 */
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	//业务层登陆的方法
	@Override
	public Employee login(Employee employee) {
		if(employee.getUsername()!=null && employee.getPassword()!=null) {
			Employee existEmployee = employeeDao.findByUsernameAndPassWord(employee);
			return existEmployee;
		}
		return null;
	}

	@Override
	public PageBean<Employee> findByPage(Integer currentPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		//封装当前的页数
		pageBean.setCurrentPage(currentPage);
		//封装每页显示的记录数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每页显示的数据
		int begin = (currentPage - 1) * pageSize;
		List<Employee> list = employeeDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	//业务层保持员工的方法
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	//业务层根据员工ID查询员工的方法
	public Employee findById(Integer eid) {
		Employee employee = employeeDao.findById(eid);
		return employee;
	}

	@Override
	//业务层修改员工的方法
	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	@Override
	//业务层删除员工的方法
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}
	
}
