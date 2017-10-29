package com.wl.employee.dao;

import java.util.List;

import com.wl.employee.domain.Department;

/**
 * ���Ź����DAO��Ľӿ�
 */
public interface DepartmentDao {
	
	int findCount();
	
	List<Department> findByPage(int begin, int pageSize);
	
	void save(Department department);
	
	Department findById(Integer did);
	
	void update(Department department);
	
	void delete(Department department);
	
	List<Department> findAll();
}
