package jsp_exam.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jsp_exam.JdbcUtil;
import jsp_exam.dao.impl.EmployeeDaoImpl;
import jsp_exam.dto.Department;
import jsp_exam.dto.Employee;
import jsp_exam.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	private static Connection con;
	private static EmployeeDaoImpl dao = EmployeeDaoImpl.getInstance();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		con = JdbcUtil.getConnection();
		dao.setCon(con);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		con.close();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	
	@Test
	public void test01selectEmployeeByall() {
		System.out.printf("%s()%n", "test01selectEmployeeByAll");
		List<Employee> list = dao.selectEmployeeByAll();
		Assert.assertNotNull(list);
		
		dao.selectEmployeeByAll().stream().forEach(System.out::println);
	}

	@Test
	public void test04selectEmployeeByNo() {
		System.out.printf("%s()%n", "test04selectEmployeeByNo");
		Employee emp = new Employee(2106);
		Employee selEmp = dao.selectEmployeeByNo(emp);
		Assert.assertNotNull(selEmp);
		System.out.println(selEmp);
	}

	@Test
	public void test02InsertEmployee() {
		System.out.printf("%s()%n", "test02InsertEmployee");
		Employee newEmp = new Employee(1004, "박규영", new Title(5), new Employee(1003), 2000000, new Department(3), new Date());	
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmployeeByNo(new Employee(1004)));
	}

	@Test
	public void test03UpdateEmployee() {
		System.out.printf("%s()%n", "test03UpdateEmployee");
		Employee newEmp = new Employee(1004, "천사", new Title(5), new Employee(4377), 2000000, new Department(3), new Date());	
		int res = dao.updateEmployee(newEmp);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmployeeByNo(new Employee(1004)));
	}

	@Test
	public void test05DeleteEmployee() {
		System.out.printf("%s()%n", "testDeleteEmployee");
		Employee newEmp = new Employee(1004);
		int res = dao.deleteEmployee(newEmp);
		Assert.assertEquals(1, res);
		dao.selectEmployeeByAll().stream().forEach(System.out::println);
	}

}
