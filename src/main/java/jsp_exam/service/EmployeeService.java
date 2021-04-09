package jsp_exam.service;

import java.sql.Connection;
import java.util.List;

import jsp_exam.dao.impl.EmployeeDaoImpl;
import jsp_exam.ds.JndiDS;
import jsp_exam.dto.Employee;

public class EmployeeService {
	private Connection con = JndiDS.getConnection();
	private EmployeeDaoImpl dao = EmployeeDaoImpl.getInstance();

	public EmployeeService() {
		dao.setCon(con);
	}

	public List<Employee> showEmployees() {
		return dao.selectEmployeeByAll();
	}
	
	public void addEmployee (Employee employee) {
		dao.insertEmployee(employee);
	}
	
	public void removeEmployee(Employee employee) {
		dao.deleteEmployee(employee);
	}
	
	public void modifyEmployee (Employee employee) {
		dao.updateEmployee(employee);
	}	
}
