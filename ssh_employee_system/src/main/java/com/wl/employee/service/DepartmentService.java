package com.wl.employee.service;

import java.util.List;

import com.wl.employee.domain.Department;
import com.wl.employee.domain.PageBean;

/**
 * ���Ź����ҵ���Ľӿ�
 */
public interface DepartmentService {
	
	PageBean<Department> findByPage(Integer currentPage);
	
	void save(Department department);
	
	Department findById(Integer did);
	
	void update(Department department);
	
	void delete(Department department);
	
	List<Department> findAll();
}
