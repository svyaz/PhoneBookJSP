package ru.academits.coverter;

import ru.academits.model.Contact;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactConverter {

    public Contact convertContactFormStringParam(String contactParams) throws UnsupportedEncodingException {
        Map<String, String> map = splitQuery(contactParams);
        Contact contact = new Contact();
        contact.setLastName(map.get("lastName"));
        contact.setFirstName(map.get("firstName"));
        contact.setPhone(map.get("phone"));
        return contact;
    }

    public List<Integer> convertIdsFromStringParam(String deleteParamsString) {
        List<Integer> ids = new ArrayList<>();
        String[] pairs = deleteParamsString.split("&");

        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            try {
                String key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
                int value = Integer.parseInt(URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                if (key.equals("contactId") && value != 0) {
                    ids.add(value);
                }
            } catch (Exception e) {
                System.out.println("error in ContactConverter convertIdsFromStringParam method: ");
                e.printStackTrace();
            }
        }
        return ids;
    }

    private static Map<String, String> splitQuery(String params) throws UnsupportedEncodingException {
        Map<String, String> queryPairs = new HashMap<>();
        String[] pairs = params.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            queryPairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return queryPairs;
    }
}
