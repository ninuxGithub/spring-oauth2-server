package com.hundsun.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hundsun.oauth.dto.UserFormDto;
import com.hundsun.oauth.dto.UserOverviewDto;
import com.hundsun.oauth.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserFormDtoValidator validator;

	/**
	 * @return View page
	 */
	@RequestMapping("/overview")
	public String overview(UserOverviewDto overviewDto, Model model) {
		overviewDto = userService.loadUserOverviewDto(overviewDto);
		model.addAttribute("overviewDto", overviewDto);
		return "user_overview";
	}

	@RequestMapping(value = "/form/plus", method = RequestMethod.GET)
	public String showForm(Model model) {
		model.addAttribute("formDto", new UserFormDto());
		return "user_form";
	}

	@RequestMapping(value = "form/plus", method = RequestMethod.POST)
	public String submitRegisterClient(@ModelAttribute("formDto") UserFormDto formDto, BindingResult result) {
		validator.validate(formDto, result);
		if (result.hasErrors()) {
			return "user_form";
		}
		try {
			userService.saveUser(formDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:../overview";
	}
	
	@RequestMapping("/table_description")
	public String tabDesc(){
		return "table_description";
	}
	
	
	@RequestMapping(value = "/toUpdate/{id}" , method=RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model){
		UserFormDto formDto =  userService.loadUserFormDtoById(id);
		model.addAttribute("formDto", formDto);
		return "user_update";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("formDto") UserFormDto formDto, BindingResult result) {
		validator.updateValidate(formDto, result);
		if (result.hasErrors()) {
			return "user_form";
		}
		try {
			userService.updateUser(formDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/user/overview";
	}
	
	

}
