package com.wl.employee.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wl.employee.domain.Department;
import com.wl.employee.domain.PageBean;
import com.wl.employee.service.DepartmentService;

public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {
	private Department department = new Department();
	@Override
	public Department getModel() {
		return department;
	}
	
	private Integer currentPage = 1;
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	//ע�벿�Ź����Service
	private DepartmentService departmentService;

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	//�ṩһ����ѯ�ķ���
	public String findAll() {
		PageBean<Department> pageBean = departmentService.findByPage(currentPage);
		//��pageBean���뵽ֵջ��
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	//��ת����Ӳ��ŵ�ҳ��ķ���
	public String saveUI() {
		return "saveUI";
	}
	
	//��Ӳ��ŵ�ִ�з���
	public String save() {
		departmentService.save(department);
		return "saveSuccess";
	}
	
	//�༭���ŵ�ִ�еķ�����
	public String edit() {
		department = departmentService.findById(department.getDid());
		return "editSuccess";
	}
	
	//�޸Ĳ��ŵ�ִ�з���
	public String update() {
		departmentService.update(department);
		return "updateSuccess";
	}
	
	//ɾ�����ŵ�ִ�еķ���
	public String delete() {
		department = departmentService.findById(department.getDid());
		departmentService.delete(department);
		return "deleteSuccess";
	}

}
