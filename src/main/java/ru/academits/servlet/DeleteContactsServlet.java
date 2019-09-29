package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.coverter.ContactConverter;
import ru.academits.service.ContactService;
import ru.academits.service.ContactsDeletion;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteContactsServlet extends HttpServlet {
    private ContactService contactService = PhoneBook.contactService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteParamsString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        List<Integer> contactsIds = contactConverter.convertIdsFromStringParam(deleteParamsString);

        ContactsDeletion contactsDeletion = contactService.deleteContacts(contactsIds);
        resp.sendRedirect("/phonebook");
    }
}
