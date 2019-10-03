package ru.academits.dao;

import ru.academits.model.Contact;
import ru.academits.service.ContactValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Anna on 17.06.2017.
 */
public class ContactDao {
    private List<Contact> contactList = new ArrayList<>();
    private AtomicInteger idSequence = new AtomicInteger(0);
    private Contact lastContact = new Contact();
    private ContactValidation lastContactValidation = new ContactValidation();

    public ContactDao() {
        Contact contact = new Contact();
        contact.setId(getNewId());
        contact.setFirstName("Иван");
        contact.setLastName("Иванов");
        contact.setPhone("9123456789");
        contactList.add(contact);
    }

    private int getNewId() {
        return idSequence.addAndGet(1);
    }

    /**
     * Get filtered contacts list where firstName or lastName or phone contains
     * specified string.
     *
     * @param filterString - string to filter with.
     * @return filtered contacts list.
     */
    public List<Contact> getFilteredContacts(String filterString) {
        if (filterString.isEmpty()) {
            return contactList;
        }

        String lowerCaseFilterString = filterString.toLowerCase();
        return contactList
                .stream()
                .filter(c -> (c.getFirstName().toLowerCase().contains(lowerCaseFilterString) ||
                        c.getLastName().toLowerCase().contains(lowerCaseFilterString) ||
                        c.getPhone().toLowerCase().contains(lowerCaseFilterString)))
                .collect(Collectors.toList());
    }

    public void add(Contact contact) {
        contact.setId(getNewId());
        contactList.add(contact);
    }

    /**
     * Deletes all contacts in contactList which id contains in specified idsList.
     *
     * @param idsList list of contacts identifier to delete.
     * @return number of deleted contacts.
     */
    public int deleteContacts(List<Integer> idsList) {
        int sizeBefore = contactList.size();

        contactList = contactList.stream()
                .filter(c -> !idsList.contains(c.getId()))
                .collect(Collectors.toList());
        return sizeBefore - contactList.size();
    }

    public void saveLastContact(Contact contact) {
        this.lastContact = contact;
    }

    public Contact getLastContact() {
        return lastContact;
    }

    public void saveLastContactValidation(ContactValidation contactValidation) {
        this.lastContactValidation = contactValidation;
    }

    public ContactValidation getLastContactValidation() {
        return lastContactValidation;
    }
}
