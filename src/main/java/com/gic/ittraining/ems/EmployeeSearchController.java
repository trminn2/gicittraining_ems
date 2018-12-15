package com.gic.ittraining.ems;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author thaemyatnoelwin
 * @version 1.0 12/07/2018
 */
@SpringBootApplication
@Controller
@ComponentScan(basePackages = { "com.gic.ittraining.ems" })
public class EmployeeSearchController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/gicEMS", method = RequestMethod.GET)
	String search(Model model, @ModelAttribute EmployeeList employeeForm) {
		if (employeeForm.getEmployeeId() != null || employeeForm.getFirstName() != null || employeeForm.getGender() != null || 
				employeeForm.getEmail() != null) {
			System.out.println("Employee Name is*********" + employeeForm.getFirstName());
			List<EmployeeList> list = null;
			String sql = "select employee.employeeId,employee.firstName,employee.lastName,employee.email,`group`.name,employee.gender from employee LEFT join `group` ON employee.groupId = `group`.id where ";
			if (!employeeForm.getEmployeeId().equals("") && !employeeForm.getFirstName().equals("")) {
				System.out.println("1111111111");
				list = jdbcTemplate.queryForObject(sql + " employeeId = '" + employeeForm.getEmployeeId()
						+ "' and firstName like '%" + employeeForm.getFirstName() + "%'", new EmployeeListMapper());
			} else if (employeeForm.getEmployeeId().equals("") && !employeeForm.getFirstName().equals("")) {
				System.out.println("22222222");
				list = jdbcTemplate.queryForObject(sql +"firstName like '%" + employeeForm.getFirstName() + "%'"
						+ " or lastName like '%" + employeeForm.getLastName() + "%'" + " or firstNameKana like '%"
						+ employeeForm.getFirstNameKana() + "%'" + " or lastNameKana like '%"
						+ employeeForm.getLastNameKana() + "%'", new EmployeeListMapper());
			} else if (employeeForm.getEmployeeId().equals("") && employeeForm.getFirstName().equals("")
					&& !employeeForm.getGender().equals("")) {
				System.out.println("33333333333");
				list = jdbcTemplate.queryForObject(sql +"gender ='" + employeeForm.getGender() + "'", new EmployeeListMapper());
			} else if (employeeForm.getEmployeeId().equals("") && employeeForm.getFirstName().equals("")
					&& employeeForm.getGender().equals("") && !employeeForm.getEmail().equals("") ) {
				System.out.println("44444444444");
				list = jdbcTemplate.queryForObject(sql +"email ='" + employeeForm.getEmail() + "'", new EmployeeListMapper());			
			}
			model.addAttribute("list", list);
		}
		return "gicEMS/empSearchList";
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSearchController.class, args);
	}
}
