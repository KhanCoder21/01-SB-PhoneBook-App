package com.ak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ak.entity.Contact;
import com.ak.service.ContactService;

@Controller
public class ViewContactsController {
	@Autowired
	private ContactService contactService;

	@GetMapping("editContact")
	public ModelAndView editClickHandler(@RequestParam("cid") Integer contactId) {
		ModelAndView mav = new ModelAndView();
		Contact cobj = contactService.getContactById(contactId);
		mav.addObject("contact", cobj);
		mav.setViewName("contact");
		return mav;
	}

	@GetMapping("deleteContact")
	public String deleteClickHandler(@RequestParam("cid") Integer contactId) {
		contactService.delContactById(contactId);

		return "redirect:viewContacts";
	}

}
