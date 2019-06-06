package org.opendevup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.opendevup.DAO.ContactRepository;
import org.opendevup.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsApplication implements CommandLineRunner {
	@Autowired
	private ContactRepository contactRepository;

	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		contactRepository.save(new Contact());
		contactRepository.save(new Contact("KAMDEM", "Yvan", df.parse("17/12/1993"), "yvankamdem@yahoo.fr", (long) 074234567, "yvan.png"));
		contactRepository.save(new Contact("MBASAH", "Sheena", df.parse("23/04/1996"), "sheenamk@yahoo.fr", (long) 064234567, "yvan.png"));
		contactRepository.findAll().forEach(c->{
			System.out.println(c.getNom());
		});
		
		
	}

}
