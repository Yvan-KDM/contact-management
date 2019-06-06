package org.opendevup.web;

import java.util.List;
import java.util.Optional;

import org.opendevup.DAO.ContactRepository;
import org.opendevup.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ContactRestService {
	@Autowired
    private ContactRepository contactRepository;
	
	@RequestMapping(value="/contacts",method=RequestMethod.GET)
	public List<Contact> getContact(){
		return contactRepository.findAll();
	}
	
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.GET)
	public Optional<Contact> getContact(@PathVariable Long id){
		return contactRepository.findById(id);
	}
	
	@RequestMapping(value="/searchContacts",method=RequestMethod.GET)
	public Page<Contact> search(
			@RequestParam(name="mc", defaultValue="") String mc, 
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="10")int size){
		return contactRepository.chercher("%"+mc+"%",PageRequest.of(page,size));
	}
	
	@RequestMapping(value="/contacts/save",method=RequestMethod.POST)
	public Contact save(@RequestBody Contact c){
		return contactRepository.save(c);
	}
	
	
	@RequestMapping(value="/contacts/delete/{id}",method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id){
		 contactRepository.deleteById(id);
		 return true;
	}
	
	@RequestMapping(value="/contacts/update/{id}",method=RequestMethod.PUT)
	public Contact update(@PathVariable Long id, @RequestBody Contact c){
		c.setId(id);
		return  contactRepository.save(c);
	
	}
	
	
	

} 
