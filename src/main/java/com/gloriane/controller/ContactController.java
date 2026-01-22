package com.gloriane.controller;

import com.gloriane.data.ContactDao;
import com.gloriane.exception.ContactStorageException;
import com.gloriane.exception.DuplicateContactException;
import com.gloriane.model.Contact;
import com.gloriane.view.ContactView;

public class ContactController {
    private ContactDao dao;
    private ContactView view;

    public ContactController(ContactDao dao, ContactView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean running = true;
        while (running) {
            view.displayMenu();
            String choice = view.getUserInput();

            try {
                switch (choice) {
                    case "1":
                        addContact();
                        break;
                    case "2":
                        showAllContacts();
                        break;
                    case "3":
                        findContact();
                        break;
                    case "4":
                        running = false;
                        view.displayMessage("Exiting application. Goodbye!");
                        break;
                    default:
                        view.displayMessage("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                view.displayErrorMessage("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void addContact() {
        try {
            String name = view.promptForName();
            String phone = view.promptForPhone();
            Contact contact = new Contact(name, phone);
            dao.save(contact);
            view.displayMessage("Contact added successfully!");
        } catch (IllegalArgumentException e) {
            view.displayErrorMessage("Validation failed: " + e.getMessage());
        } catch (DuplicateContactException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (ContactStorageException e) {
            view.displayErrorMessage("Storage error: " + e.getMessage());
        }
    }

    private void showAllContacts() {
        try {
            view.displayContacts(dao.findAll());
        } catch (ContactStorageException e) {
            view.displayErrorMessage("Failed to retrieve contacts: " + e.getMessage());
        }
    }

    private void findContact() {
        try {
            String name = view.promptForName();
            Contact contact = dao.findByName(name);
            view.displayContact(contact);
        } catch (ContactStorageException e) {
            view.displayErrorMessage("Search error: " + e.getMessage());
        }
    }
}

