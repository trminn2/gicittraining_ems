package com.gic.ittraining.ems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {

	@RequestMapping("/")
	public String passwordchange(Model model) {
		return "PasswordChange";
	}
	
	/*@RequestMapping("/savepassword")
    public String savepassword(Model model) {
        return "PasswordChange";
    }*/
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ModelAttribute
	UserForm userForm() {
	    return new UserForm();
	}
	
	@RequestMapping(path = "/Save", method = RequestMethod.GET)
	public String update(Model model, @ModelAttribute UserForm userForm) {
		//System.out.println("update method.............................." + userForm.getNewpassword());
		String sql= "select password from user where employeeId = 'E0001' and roleId = 1 ";
		
		String currentpass = (String) jdbcTemplate.queryForObject(sql, new Object[] { }, String.class);
		
		//System.out.println(currentpass);
		if(currentpass.equals(userForm.getCurrentpassword())) {
			if (userForm.getNewpassword().equals(userForm.getConfirmpassword())){
				jdbcTemplate.update("UPDATE user SET password = '" +userForm.getNewpassword() + "' where employeeId = 'E0001' and roleId = 1");
				model.addAttribute("successful", true);
			    return "PasswordChange";
			 }
			else {
				model.addAttribute("notsamenewpassword", true);
			    return "PasswordChange";
			}
		}
		else if(currentpass!=userForm.getCurrentpassword())  {
			model.addAttribute("notsameoldpassword", true);
		    return "PasswordChange";
		}
		else {
			return "PasswordChange";
		}
		
	   
	
	  
	}
	
}
