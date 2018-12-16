package com.gic.ittraining.ems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import com.gic.ittraining.ems.EmployeeForm;

public class EmployeeMapper implements RowMapper <List<EmployeeForm>> {
    public List<EmployeeForm> mapRow(ResultSet rs, int rowNum) throws SQLException {
    	System.out.println("* Employee Mapper function ***********");
        List<EmployeeForm> list = new ArrayList<EmployeeForm>();
        EmployeeForm tmp_emp = new EmployeeForm();
        tmp_emp.setId(rs.getInt("id"));
        tmp_emp.setEmployeeId(rs.getString("employeeId"));
        tmp_emp.setFirstName(rs.getString("firstName"));
        tmp_emp.setLastName(rs.getString("lastName"));
        tmp_emp.setFirstNameKana(rs.getString("firstNameKana"));
        tmp_emp.setLastNameKana(rs.getString("lastNameKana"));
        tmp_emp.setBirthDate(rs.getDate("birthDate"));
        tmp_emp.setGender(rs.getString("gender"));
        tmp_emp.setEmail(rs.getString("email"));
        list.add(tmp_emp);
        while (rs.next()) {
            EmployeeForm emp = new EmployeeForm();
            emp.setId(rs.getInt("id"));
            emp.setEmployeeId(rs.getString("employeeId"));
            emp.setFirstName(rs.getString("firstName"));
            emp.setLastName(rs.getString("lastName"));
            emp.setFirstNameKana(rs.getString("firstNameKana"));
            emp.setLastNameKana(rs.getString("lastNameKana"));
            emp.setBirthDate(rs.getDate("birthDate"));
            emp.setGender(rs.getString("gender"));
            emp.setEmail(rs.getString("email"));
            list.add(emp);
        }
        return list;
    }
}
