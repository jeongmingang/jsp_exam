package jsp_exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jsp_exam.dao.EmployeeDao;
import jsp_exam.dto.Department;
import jsp_exam.dto.Employee;
import jsp_exam.dto.Title;

public class EmployeeDaoImpl implements EmployeeDao {
	private static EmployeeDaoImpl instance = new EmployeeDaoImpl();
	private Connection con;

	public static EmployeeDaoImpl getInstance() {
		if (instance == null) {
			instance = new EmployeeDaoImpl();
		}
		return instance;
	}

	private EmployeeDaoImpl() {
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql = "select emp_no, emp_name, title_no, title_name, manager_no, manager_name, " + 
					 "	salary, dept_No, dept_Name, floor, hiredate " + 
					 " from vw_full_employee";
		try (PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Employee> list = new ArrayList<>();
				do {
					list.add(getEmployee(rs));
				} while (rs.next());
				return list;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		int No = rs.getInt("emp_No");
		String Name = rs.getString("emp_Name");
		Title title = new Title(rs.getInt("title_no"));
		Employee manager = new Employee(rs.getInt("manager_no"));
		int salary = rs.getInt("salary");
		Department dept = new Department(rs.getInt("dept_No"));
		Date hireDate = rs.getDate("hireDate");
	
		try {
			title.setName(rs.getString("title_name"));
		}catch (SQLException e) {}
		
		try {
			manager.setName(rs.getString("manager_name"));
		} catch(SQLException e) {}
		
		try {
			dept.setName(rs.getString("dept_Name"));
			dept.setFloor(rs.getInt("Floor"));
		} catch(SQLException e) {}		
		
		return new Employee(No, Name, title, manager, salary, dept, hireDate);
}

	@Override
	public Employee selectEmployeeByNo(Employee employee) {
		String sql = "select emp_no, emp_name, title_no, title_name, manager_no, manager_name, " + 
					 "	salary, dept_No, dept_Name, floor, hiredate " + 
					 "    from vw_full_employee " + 
					 " where emp_no = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, employee.getNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee employee) {
		String sql = "insert into employee values(?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, employee.getNo());
			pstmt.setString(2, employee.getName());
			pstmt.setInt(3, employee.getTitle().getNo());
			pstmt.setInt(4, employee.getManager().getNo());
			pstmt.setInt(5, employee.getSalary());
			pstmt.setInt(6, employee.getDept().getNo());
			pstmt.setTimestamp(7, new Timestamp(employee.getHireDate().getTime()));
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int updateEmployee(Employee employee) {
		String sql = "update employee " +
					 "	set emp_name = ?, tno = ?, manager = ?, salary = ?, dno = ?, hiredate = ? " +
					 " where emp_no = ?";
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, employee.getName());
			pstmt.setInt(2, employee.getTitle().getNo());	   //Title객체의 tNo를 불러옴
			pstmt.setInt(3, employee.getManager().getNo()); //Employee객체의 EmpNo를 불러옴
			pstmt.setInt(4, employee.getSalary());
			pstmt.setInt(5, employee.getDept().getNo());   //Department객체의 DeptNo를 불러옴
			pstmt.setTimestamp(6, new Timestamp(employee.getHireDate().getTime()));
			pstmt.setInt(7, employee.getNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(Employee employee) {
		String sql = "delete from employee where emp_no = ?";
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, employee.getNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
