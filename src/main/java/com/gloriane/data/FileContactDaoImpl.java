package com.gloriane.data;

import com.gloriane.exception.*;
import com.gloriane.model.Contact;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileContactDaoImpl implements ContactDao {
    private Path path = Path.of("contacts.txt");

    public void save(Contact contact) throws ContactStorageException, DuplicateContactException {
        try {
            if (contactExists(contact.getName())) throw new DuplicateContactException("Duplicate name!");
            String data = contact.getName() + "," + contact.getPhoneNumber() + "\n";
            Files.write(path, data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) { throw new ContactStorageException("Save failed", e); }
    }

    public List<Contact> findAll() throws ContactStorageException {
        try {
            if (!Files.exists(path)) return new ArrayList<>();
            List<Contact> contacts = new ArrayList<>();
            for (String line : Files.readAllLines(path)) {
                String[] parts = line.split(",");
                if (parts.length == 2) contacts.add(new Contact(parts[0], parts[1]));
            }
            return contacts;
        } catch (IOException e) { throw new ContactStorageException("Read failed", e); }
    }

    @Override
    public Contact findByName(String name) throws ContactStorageException {
        try {
            if (!Files.exists(path)) return null;
            for (String line : Files.readAllLines(path)) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equalsIgnoreCase(name)) {
                    return new Contact(parts[0], parts[1]);
                }
            }
            return null;
        } catch (IOException e) {
            throw new ContactStorageException("Search failed", e);
        }
    }

    private boolean contactExists(String name) throws ContactStorageException {
        return findByName(name) != null;
    }
}