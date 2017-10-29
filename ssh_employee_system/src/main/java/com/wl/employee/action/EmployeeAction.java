package com.wl.employee.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wl.employee.domain.Department;
import com.wl.employee.domain.Employee;
import com.wl.employee.domain.PageBean;
import com.wl.employee.service.DepartmentService;
import com.wl.employee.service.EmployeeService;
/**
 * Ա�������Action��
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee> {
	//ģ������ʹ�õĶ���
	private Employee employee = new Employee();
	
	@Override
	public Employee getModel() {
		return employee;
	}
	
	//���ܵ�ǰҳ��
	private Integer currentPage = 1;
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	//ע��ҵ���
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//��½ִ�еķ���
	public String login() {
		System.out.println("loginִ����");
		Employee existEmployee = employeeService.login(employee);
		//System.out.println("zheshi" + existEmployee.getEname());
		if(existEmployee == null) {
			//��½ʧ��
			this.addActionError("�û������������");
			return INPUT;
		} else {
			//��½�ɹ�
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return SUCCESS;
		}
	}
	
	//��ҳ��ѯԱ����ִ�еķ���
	public String findAll() {
		PageBean<Employee> pageBean = employeeService.findByPage(currentPage);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	//��ת�����Ա��ҳ��ִ�еķ���
	public String saveUI() {
		//��ѯ���в���
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	
	//����Ա����ִ�з���
	public String save() {
		employeeService.save(employee);
		return "saveSuccess";
	}
	
	//�༭Ա����ִ�еķ���
	public String edit() {
		//����Ա��ID��ѯԱ��
		employee = employeeService.findById(employee.getEid());
		//��ѯ���еĲ���
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "editSuccess";
	}
	
	//�޸�Ա����ִ�еķ���
	public String update() {
		employeeService.update(employee);
		return "updateSuccess";
	}
	
	//ɾ��Ա����ִ�з���
	public String delete() {
		employee = employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}
	
}
