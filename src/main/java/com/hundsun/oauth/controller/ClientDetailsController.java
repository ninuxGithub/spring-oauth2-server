package com.hundsun.oauth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hundsun.oauth.domain.Privilege;
import com.hundsun.oauth.domain.Tag;
import com.hundsun.oauth.dto.OauthClientDetailsDto;
import com.hundsun.oauth.service.OauthService;

@Controller
public class ClientDetailsController {

	@Autowired
	private OauthService oauthService;

	@Autowired
	private OauthClientDetailsDtoValidator clientDetailsDtoValidator;

	@RequestMapping("client_details")
	public String clientDetails(Model model) {
		List<OauthClientDetailsDto> clientDetailsDtoList = oauthService.loadAllOauthClientDetailsDtos();
		model.addAttribute("clientDetailsDtoList", clientDetailsDtoList);

		return "clientdetails/client_details";
	}

	/*
	 * Logic delete
	 */
	@RequestMapping("archive_client/{clientId}")
	public String archiveClient(@PathVariable("clientId") String clientId) {
		oauthService.archiveOauthClientDetails(clientId);
		return "redirect:../client_details";
	}

	/*
	 * Test client
	 */
	@RequestMapping("test_client/{clientId}")
	public String testClient(@PathVariable("clientId") String clientId, Model model) {
		OauthClientDetailsDto clientDetailsDto = oauthService.loadOauthClientDetailsDto(clientId);
		model.addAttribute("clientDetailsDto", clientDetailsDto);
		return "clientdetails/test_client";
	}

	/*
	 * Register client
	 */
	@RequestMapping(value = "register_client", method = RequestMethod.GET)
	public String registerClient(Model model) {
		model.addAttribute("formDto", new OauthClientDetailsDto());
		// add role choose selections
		List<Tag> tags = new ArrayList<>();
		for (Privilege p : Privilege.values()) {
			Tag tag = new Tag(p.getName(), p.getSourceId());
			tags.add(tag);
		}
		model.addAttribute("tags", tags);

		return "clientdetails/register_client";
	}

	/*
	 * Submit register client
	 */
	@RequestMapping(value = "register_client", method = RequestMethod.POST)
	public String submitRegisterClient(@ModelAttribute("formDto") OauthClientDetailsDto formDto, BindingResult result) {
		clientDetailsDtoValidator.validate(formDto, result);
		if (result.hasErrors()) {
			return "clientdetails/register_client";
		}
		oauthService.registerClientDetails(formDto);
		return "redirect:client_details";
	}

	@RequestMapping("/updateResource/{clientId}")
	public String updateClientReource(@PathVariable("clientId") String clientId, Model model) {
		OauthClientDetailsDto clientDetailsDto = oauthService.loadOauthClientDetailsDto(clientId);
		// add role choose selections
		List<Tag> tags = new ArrayList<>();
		for (Privilege p : Privilege.values()) {
			Tag tag = new Tag(p.getName(), p.getSourceId());
			tags.add(tag);
		}
		model.addAttribute("tags", tags);
		model.addAttribute("formDto", clientDetailsDto);

		return "clientdetails/update_client";
	}

	@RequestMapping(value = "/submitUpdate", method = RequestMethod.POST)
	public String submitUpdate(@ModelAttribute("formDto") OauthClientDetailsDto formDto, BindingResult result,
			Model model) {
		clientDetailsDtoValidator.updateValidate(formDto, result);
		if (result.hasErrors()) {
			model.addAttribute("formDto", formDto);
			return "clientdetails/update_client";
		}
		oauthService.updateClientDetails(formDto);
		return "redirect:client_details";
	}

}