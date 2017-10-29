package com.wl.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wl.employee.dao.EmployeeDao;
import com.wl.employee.domain.Employee;
import com.wl.employee.domain.PageBean;
import com.wl.employee.service.EmployeeService;
/**
 * Ա�������ҵ���ʵ����
 */
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	//ҵ����½�ķ���
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
		//��װ��ǰ��ҳ��
		pageBean.setCurrentPage(currentPage);
		//��װÿҳ��ʾ�ļ�¼��
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		//��װ��ҳ��
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		//��װÿҳ��ʾ������
		int begin = (currentPage - 1) * pageSize;
		List<Employee> list = employeeDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	//ҵ��㱣��Ա���ķ���
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	//ҵ������Ա��ID��ѯԱ���ķ���
	public Employee findById(Integer eid) {
		Employee employee = employeeDao.findById(eid);
		return employee;
	}

	@Override
	//ҵ����޸�Ա���ķ���
	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	@Override
	//ҵ���ɾ��Ա���ķ���
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}
	
}
