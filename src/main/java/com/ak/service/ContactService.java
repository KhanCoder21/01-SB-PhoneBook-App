package com.ak.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ak.entity.Contact;

public interface ContactService {
	public boolean saveContact(Contact contact);

	public List<Contact> getAllContacts();

	public Page<Contact> getAllContactsPagination(Integer pageNo,Integer pageSize);

	public Contact getContactById(Integer contactId);

	public boolean delContactById(Integer contactid);
}
