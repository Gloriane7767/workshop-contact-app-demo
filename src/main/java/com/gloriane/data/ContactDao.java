package com.gloriane.data;

import com.gloriane.exception.ContactStorageException;
import com.gloriane.exception.DuplicateContactException;
import com.gloriane.model.Contact;

import java.util.List;

public interface ContactDao {
    List<Contact> findAll();
    void save(Contact contact) ;
    Contact findByName(String name);
}

