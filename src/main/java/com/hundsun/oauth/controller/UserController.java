package com.hundsun.oauth.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hundsun.oauth.domain.Pagination;
import com.hundsun.oauth.domain.Privilege;
import com.hundsun.oauth.domain.Tag;
import com.hundsun.oauth.domain.User;
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
	 * @return View page 加入page分页
	 * 
	 *         这个功能集成了分页+ 查询的功能， 采用不同sql 来实现
	 */
	@RequestMapping(value = "/overview")
	public String overview(Integer pageNo, UserOverviewDto overviewDto, Model model) {
		if (null == pageNo) {
			pageNo = Pagination.PAGENO;
		}
		model.addAttribute("pageNo", pageNo);
		// 显示记录的条数
		overviewDto = userService.loadUserOverviewDto(overviewDto);
		model.addAttribute("overviewDto", overviewDto);
		String countSql = null, pageListSql = null;

		if (null == overviewDto || StringUtils.isBlank(overviewDto.getUsername())) {
			countSql = "select count(*) from user_";
			pageListSql = "SELECT * from user_ ORDER BY create_time ASC";
		} else {
			countSql = "select count(*) from user_ where username like '%" + overviewDto.getUsername().trim() + "%' ";
			pageListSql = "SELECT * from user_ where username like '%" + overviewDto.getUsername().trim()+ "%' ORDER BY create_time ASC";
		}
		Pagination<User> pagination = new Pagination<User>(pageNo, Pagination.PAGESIZE);
		pagination = userService.paginationBySql(countSql, pageListSql, pagination);
		if(null != pagination && null != pagination.getPageList()&& pagination.getPageList().size()>0){
			for(User user :pagination.getPageList()){
				user.getPrivileges().addAll(userService.findPrivilege(user.getId()));
			}
		}
		model.addAttribute("pagination", pagination);

		return "user_overview";
	}

	@RequestMapping(value = "/form/plus", method = RequestMethod.GET)
	public String showForm(Model model) {
		model.addAttribute("formDto", new UserFormDto());
		// add role choose selections
		List<Tag> tags = new ArrayList<>();
		for (Privilege p : Privilege.values()) {
			Tag tag = new Tag(p.getName(), p.getSourceId());
			tags.add(tag);
		}
		model.addAttribute("tags", tags);
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
	public String tabDesc() {
		return "table_description";
	}

	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		UserFormDto formDto = userService.loadUserFormDtoById(id);
		model.addAttribute("formDto", formDto);
		// add role choose selections
		List<Tag> tags = new ArrayList<>();
		for (Privilege p : Privilege.values()) {
			Tag tag = new Tag(p.getName(), p.getSourceId());
			tags.add(tag);
		}
		model.addAttribute("tags", tags);
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

	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") Long id) {
		UserFormDto userFrom = userService.loadUserFormDtoById(id);
		if (null != userFrom) {
			try {
				userService.deleteUserById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/user/overview";
	}

}
