package com.wl.employee.dao;

import java.util.List;

import com.wl.employee.domain.Employee;

/**
 * 员工管理的DAO的接口
 */
public interface EmployeeDao {
	
	Employee findByUsernameAndPassWord(Employee employee);
	
	int findCount();
	
	List<Employee> findByPage(int begin, int pageSize);
	
	void save(Employee employee);
	
	Employee findById(Integer eid);
	
	void update(Employee employee);
	
	void delete(Employee employee);
	
}
