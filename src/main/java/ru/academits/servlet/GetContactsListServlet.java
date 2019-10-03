package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.service.ContactService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GetContactsListServlet extends HttpServlet {
    private ContactService contactService = PhoneBook.contactService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filterString = req.getParameter("s");
        if (filterString != null && !filterString.isEmpty()) {
            req.setAttribute("contactList", contactService.getFilteredContacts(filterString));
        } else {
            req.setAttribute("contactList", contactService.getAllContacts());
        }

        req.setAttribute("contactValidation", contactService.getLastContactValidation());
        req.setAttribute("currentContact", contactService.getLastContact());
        req.getRequestDispatcher("phonebook.jsp").forward(req, resp);
    }
}
