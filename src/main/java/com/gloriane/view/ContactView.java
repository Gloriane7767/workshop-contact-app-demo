package com.gloriane.view;

import com.gloriane.model.Contact;

import java.util.List;
import java.util.Scanner;

public class ContactView {
    private Scanner scanner;

    public ContactView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n--- Contact Management System ---");
        System.out.println("1. Add Contact");
        System.out.println("2. Show All Contacts");
        System.out.println("3. Find Contact by Name");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public String promptForName() {
        System.out.print("Enter contact name: ");
        return scanner.nextLine();
    }

    public String promptForPhone() {
        System.out.print("Enter contact phone (10 digits): ");
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayErrorMessage(String error) {
        System.err.println("ERROR: " + error);
    }

    public void displayContacts(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("\n--- Contact List ---");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    public void displayContact(Contact contact) {
        if (contact == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Contact found: " + contact);
        }
    }
}
