package com.ak.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ak.constants.AppConstants;
import com.ak.entity.Contact;
import com.ak.service.ContactService;

@Controller
public class ContactInfoController {
	@Autowired
	private ContactService contactService;

	@GetMapping("/loadForm")
	public String loadForm(Model model) {
		Contact cobj = new Contact();
		model.addAttribute(AppConstants.CONSTANT, cobj);
		return AppConstants.CONSTANT;
	}

	// saving contact infos
	@PostMapping("/saveContact")
	public String saveButtonHandler(Contact contact, Model model) {
		boolean isSaved = contactService.saveContact(contact);
		if (isSaved) {
			model.addAttribute("succMsg", "Your contact is saved successfully");
		} else {
			model.addAttribute("errMsg", "Faild to save your contact");
		}
		return AppConstants.CONSTANT;
	}

	// displaying all contacts info
	@GetMapping("/viewContacts")
	public ModelAndView viewAllContactsHandler(HttpServletRequest req) {
		Integer pageNo = 1;
		Integer pageSize = 3;
		String reqParam = req.getParameter("pno");
		if (reqParam != null && !"".equals(reqParam)) {
			pageNo = Integer.parseInt(reqParam);
		}

		Page<Contact> pagination = contactService.getAllContactsPagination(pageNo - 1, pageSize);
		int totalPage = pagination.getTotalPages();
		List<Contact> allContacts = pagination.getContent();

		ModelAndView mav = new ModelAndView();
		mav.addObject("contacts", allContacts);
		mav.addObject("tp", totalPage);
		mav.addObject("currentPno", pageNo);
		mav.setViewName("viewContacts");
		return mav;
	}

}
