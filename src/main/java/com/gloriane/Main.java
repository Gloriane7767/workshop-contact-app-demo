package com.gloriane;

import com.gloriane.controller.ContactController;
import com.gloriane.data.ContactDao;
import com.gloriane.data.FileContactDaoImpl;
import com.gloriane.exception.ContactStorageException;
import com.gloriane.exception.DuplicateContactException;
import com.gloriane.model.Contact;
import com.gloriane.view.ContactView;

import java.util.List;

public class Main {
    public static void main(String[] args) throws DuplicateContactException, ContactStorageException {

        // Initialize the Data Layer (DAO)
        ContactDao dao = new FileContactDaoImpl();
         /*
        System.out.println("--- Saving new contacts ---");
        Contact c1 = new Contact("John Doe", "1234567890");
        Contact c2 = new Contact("Jane Smith", "0987654321");

        dao.save(c1);
        System.out.println("Saved: " + c1);
        dao.save(c2);
        System.out.println("Saved: " + c2);
         */

        // Initialize the View
        ContactView view = new ContactView();

        // Initialize the Controller with its dependencies
        ContactController controller = new ContactController(dao, view);

        // Start the application
        controller.run();
    }
}
