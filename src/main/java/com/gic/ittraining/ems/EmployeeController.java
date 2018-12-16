package com.gic.ittraining.ems;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.gic.ittraining.ems.EmployeeForm;
import com.gic.ittraining.ems.EmployeeMapper;
import com.gic.ittraining.ems.EmployeeController;

/* 
 * 作成日：　2018年12月15日
 * 作成者：　モープィンピュー
 * 更新日：
 * 更新者：
 * 説明　：　Employeeコントローラクラス
 * 
 * */

@SpringBootApplication
@Controller
public class EmployeeController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	@RequestMapping(path = "/employee", method = RequestMethod.GET)
	String index(Model model) {
		System.out.println("Select table function");
	    List<EmployeeForm> list = jdbcTemplate.queryForObject("select * from employee", new EmployeeMapper());
	    model.addAttribute("list", list);
	    return "employee/employeeRegister";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeController.class, args);
	}

	@ModelAttribute
	EmployeeForm employeForm() {
	    return new EmployeeForm();
	}
	
	//	社員初期情報登録処理
	@RequestMapping(path = "/employee", method = RequestMethod.POST)
	String create(RedirectAttributes redirectAttributes, Model model, @ModelAttribute EmployeeForm empForm) {
	jdbcTemplate.update("INSERT INTO employee (employeeId, firstName, lastName, firstNameKana, LastNameKana, gender, email) "
			+ "values (?, ?, ?, ?, ?, ?, ?)", empForm.getEmployeeId(), empForm.getFirstName(), empForm.getLastName(), empForm.getFirstNameKana(),
			empForm.getLastNameKana(),empForm.getGender(), empForm.getEmail());
	redirectAttributes.addFlashAttribute("message", "社員情報を登録しました。");
	redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	return "redirect:/employee";
	}
}