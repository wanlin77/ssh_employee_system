package com.wl.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wl.employee.dao.DepartmentDao;
import com.wl.employee.domain.Department;
import com.wl.employee.domain.PageBean;
import com.wl.employee.service.DepartmentService;
/**
 * ���Ź����ҵ����ʵ����
 */
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDao departmentDao;

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	//��ҳ��ѯ���ֵķ���
	public PageBean<Department> findByPage(Integer currentPage) {
		PageBean<Department> pageBean = new PageBean<Department>();
		//��װ��ǰҳ��
		pageBean.setCurrentPage(currentPage);
		//��װÿҳ��ʾ��
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = departmentDao.findCount();
		pageBean.setTotalCount(totalCount);
		//��װ��ҳ��
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize); //Math.ceil()����������ȡ��
		pageBean.setTotalPage(num.intValue());
		//��װÿҳ��ʾ������
		int begin = (currentPage - 1) * pageSize;
		List<Department> list = departmentDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	//ҵ��㱣�沿�ŵķ���
	public void save(Department department) {
		departmentDao.save(department);
	}

	@Override
	//ҵ�����ݲ���ID��ѯ���ŵķ���
	public Department findById(Integer did) {
		Department department = departmentDao.findById(did);
		return department;
	}

	@Override
	//ҵ����޸Ĳ��ŷ���
	public void update(Department department) {
		departmentDao.update(department);
	}

	@Override
	//ҵ���ɾ�����ŵķ���
	public void delete(Department department) {
		departmentDao.delete(department);
	}

	@Override
	//��ѯ���в��ŵķ���
	public List<Department> findAll() {
		List<Department> list = departmentDao.findAll();
		return list;
	}
	
}
