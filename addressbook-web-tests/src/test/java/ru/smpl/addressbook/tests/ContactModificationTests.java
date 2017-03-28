package ru.smpl.addressbook.tests;

import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().modificationContact();
        app.getContactHelper().fillContactForm(new ContactData("Petr", "Petrov", "petr.petrov@newmymail.ru", null), false);
        app.getContactHelper().updateContacts();
        app.getNavigationHelper().gotoHomePage();
    }
}
