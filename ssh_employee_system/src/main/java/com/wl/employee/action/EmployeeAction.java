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
 * 员工管理的Action类
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee> {
	//模型驱动使用的对象
	private Employee employee = new Employee();
	
	@Override
	public Employee getModel() {
		return employee;
	}
	
	//接受当前页数
	private Integer currentPage = 1;
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	//注入业务层
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//登陆执行的方法
	public String login() {
		System.out.println("login执行了");
		Employee existEmployee = employeeService.login(employee);
		//System.out.println("zheshi" + existEmployee.getEname());
		if(existEmployee == null) {
			//登陆失败
			this.addActionError("用户名或密码错误！");
			return INPUT;
		} else {
			//登陆成功
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return SUCCESS;
		}
	}
	
	//分页查询员工的执行的方法
	public String findAll() {
		PageBean<Employee> pageBean = employeeService.findByPage(currentPage);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	//跳转到添加员工页面执行的方法
	public String saveUI() {
		//查询所有部门
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	
	//保存员工的执行方法
	public String save() {
		employeeService.save(employee);
		return "saveSuccess";
	}
	
	//编辑员工的执行的方法
	public String edit() {
		//根据员工ID查询员工
		employee = employeeService.findById(employee.getEid());
		//查询所有的部门
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "editSuccess";
	}
	
	//修改员工的执行的方法
	public String update() {
		employeeService.update(employee);
		return "updateSuccess";
	}
	
	//删除员工的执行方法
	public String delete() {
		employee = employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}
	
}
