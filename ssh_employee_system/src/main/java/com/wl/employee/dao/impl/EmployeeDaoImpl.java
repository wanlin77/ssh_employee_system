package com.wl.employee.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.wl.employee.dao.EmployeeDao;
import com.wl.employee.domain.Employee;
/**
 * Ա�������DAO��ʵ����
 */
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {

	@Override
	//DAO�и����û����������ѯ�û��ķ���
	public Employee findByUsernameAndPassWord(Employee employee) {
		String hql = "from Employee where username = ? and password = ?";
		List<Employee> list = (List<Employee>) this.getHibernateTemplate().find(hql, employee.getUsername(), employee.getPassword());
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int findCount() {
		String hql = "select count(*) from Employee";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if(list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Employee> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		List<Employee>  list = (List<Employee>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	@Override
	//DAO�б���Ա���ķ���
	public void save(Employee employee) {
		this.getHibernateTemplate().save(employee);
	}

	@Override
	//DAO�и���Ա��ID��ѯԱ���ķ���
	public Employee findById(Integer eid) {
		Employee employee = this.getHibernateTemplate().get(Employee.class, eid);
		return employee;
	}

	@Override
	//DAO�����޸�Ա���ķ���
	public void update(Employee employee) {
		this.getHibernateTemplate().update(employee);
	}

	@Override
	//DAO����ɾ��Ա���ķ���
	public void delete(Employee employee) {
		this.getHibernateTemplate().delete(employee);
	}

	
}
