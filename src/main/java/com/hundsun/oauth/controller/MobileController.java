package com.hundsun.oauth.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hundsun.oauth.domain.User;
import com.hundsun.oauth.dto.UserFormDto;
import com.hundsun.oauth.dto.UserJsonDto;
import com.hundsun.oauth.service.UserService;
import com.hundsun.oauth.utils.PasswordHandler;

@Controller
@RequestMapping("/mobile")
public class MobileController {

	@Autowired
	private UserService userService;

	@RequestMapping("dashboard")
	public String dashboard() {
		return "mobile/dashboard";
	}

	@RequestMapping("user_info")
	@ResponseBody
	public UserJsonDto userInfo() {
		return userService.loadCurrentUserJsonDto();
	}

	// **********************修改密码需要 mobile resources 权限*********
	// ***********************************************************
	@RequestMapping("/toUpdate")
	public String updateUser(HttpServletRequest request, Model model) {
		String guid = userService.loadCurrentUserJsonDto().getGuid();
		if (StringUtils.isNotBlank(guid)) {
			User user = userService.findUserByGuid(guid);
			if (null != user) {
				model.addAttribute("userForm", new UserFormDto(user));
			} else {
				return "redirect:/success";
			}
		}
		return "mobile/updateUser";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(@ModelAttribute("userForm") UserFormDto userForm, BindingResult result, Model model) {
		User user = userService.findUserById(userForm.getId());
		
		if(StringUtils.isBlank(userForm.getUsername())){
			result.rejectValue("username",null, "username is required");
		}
		
		if(StringUtils.isBlank(userForm.getPassword())){
			result.rejectValue("password", null, "old password is required");
		}
		else if(!PasswordHandler.md5(userForm.getPassword()).equals(user.getPassword())){
			result.rejectValue("password", null, "old password is mismatch");
		}
		
		if(StringUtils.isBlank(userForm.getRepassword())){
			result.rejectValue("repassword", null, "new password is required");
		}else if(userForm.getRepassword().length()<5){
			result.rejectValue("repassword", null, "new password's length must greater then 5");
		}
		if(result.getErrorCount()>0){
			model.addAttribute("userForm", userForm);
			return "mobile/updateUser";
			
		}
		try {
			userService.changePassword(userForm);
		} catch (Exception e) {
			model.addAttribute("userForm", userForm);
			return "mobile/updateUser";
		}
		return "success";
	}

}