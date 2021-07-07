package com.ak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ak.entity.Contact;
import com.ak.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public boolean saveContact(Contact contact) {
		contact.setActiveSw("Y");
		Contact saveEntity = contactRepository.save(contact);
		if (saveEntity != null && saveEntity.getContactId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Contact> getAllContacts() {

		Contact contactFilter = new Contact();
		contactFilter.setActiveSw("Y");
		Example<Contact> example = Example.of(contactFilter);
		 return contactRepository.findAll(example);
		
	}

	@Override
	public Contact getContactById(Integer contactId) {
		Optional<Contact> findById = contactRepository.findById(contactId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public Page<Contact> getAllContactsPagination(Integer pageNo, Integer pageSize) {

		Contact contactFilter = new Contact();
		contactFilter.setActiveSw("Y");
		Example<Contact> example = Example.of(contactFilter);
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		 return contactRepository.findAll(example, pageRequest);

		 
	}

	@Override
	public boolean delContactById(Integer contactId) {
		// Hard Delete fuvvtionality
		/*
		 * boolean existsById = ContactRepository.existsById(contactId); if (existsById)
		 * { ContactRepository.deleteById(contactId); } return false;
		 */

		// Soft DELETE functionality
		Optional<Contact> findById = contactRepository.findById(contactId);
		if (findById.isPresent()) {
			Contact contact = findById.get();
			contact.setActiveSw("N");
			contactRepository.save(contact);
			return true;
		} else {
			return false;
		}

	}

}
