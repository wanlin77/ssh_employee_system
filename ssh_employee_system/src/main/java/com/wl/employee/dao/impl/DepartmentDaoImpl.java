package com.wl.employee.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.wl.employee.dao.DepartmentDao;
import com.wl.employee.domain.Department;
/**
 * ���Ź����DAO���ʵ����
 */
public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {

	@Override
	public int findCount() {
		String hql = "select count(*) from Department";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if(list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	//��ҳ��ѯ����
	public List<Department> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		List<Department> list = (List<Department>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	@Override
	//DAO�б��沿�ŵķ���
	public void save(Department department) {
		this.getHibernateTemplate().save(department);
	}

	@Override
	//DAO�и��ݲ��ŵ�ID��ѯ���ŵķ���
	public Department findById(Integer did) {
		return this.getHibernateTemplate().get(Department.class, did);
	}

	@Override
	//DAO���޸Ĳ��ŵķ���
	public void update(Department department) {
		this.getHibernateTemplate().update(department);
	}

	@Override
	//DAO��ɾ�����ŵķ���
	public void delete(Department department) {
		this.getHibernateTemplate().delete(department);
	}

	@Override
	//DAO�в�ѯ���в��ŵķ���
	public List<Department> findAll() {
		String hql = "from Department";
		List<Department> list = (List<Department>) this.getHibernateTemplate().find(hql);
		return list;
	}

}
