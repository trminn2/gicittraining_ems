package com.gic.ittraining.ems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author thaemyatnoelwin
 * @version 1.0 12/08/2018
 */
public class EmployeeListMapper implements RowMapper<List<EmployeeList>> {
	public List<EmployeeList> mapRow(ResultSet rs, int rowNum) throws SQLException {
		List<EmployeeList> list = new ArrayList<EmployeeList>();
		EmployeeList tmp_emp = new EmployeeList();
        //tmp_emp.setEmployeeId(rs.getString("employeeId"));
		tmp_emp.setEmployeeId(rs.getString("employeeId"));
        tmp_emp.setFirstName(rs.getString("firstName"));
        tmp_emp.setLastName(rs.getString("lastName"));
       // tmp_emp.setFirstNameKana(rs.getString("firstNameKana"));
        //tmp_emp.setLastNameKana(rs.getString("lastNameKana"));
        tmp_emp.setGender(rs.getString("gender"));
        tmp_emp.setName(rs.getString("name"));
        tmp_emp.setEmail(rs.getString("email"));
        list.add(tmp_emp);
        while (rs.next()) {
        	EmployeeList employeeList = new EmployeeList();
        	employeeList.setEmployeeId(rs.getString("employeeId"));
        	employeeList.setFirstName(rs.getString("firstName"));
        	employeeList.setLastName(rs.getString("lastName"));
        	//employeeList.setFirstNameKana(rs.getString("firstNameKana"));
        	//employeeList.setLastNameKana(rs.getString("lastNameKana"));
        	employeeList.setGender(rs.getString("gender"));
        	employeeList.setName(rs.getString("name"));
        	System.out.println("GENDER is " + rs.getString("gender"));
        	employeeList.setEmail(rs.getString("email"));
            list.add(employeeList);
        }
        return list;
    }
}
