package com.wl.employee.service;

import com.wl.employee.domain.Employee;
import com.wl.employee.domain.PageBean;

/**
 * Ա������ҵ���Ľӿ�
 */
public interface EmployeeService {
	
	Employee login(Employee employee);
	
	PageBean<Employee> findByPage(Integer currentPage);
	
	void save(Employee employee);
	
	Employee findById(Integer eid);
	
	void update(Employee employee);
	
	void delete(Employee employee);
	
}
