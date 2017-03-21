package ru.smpl.addressbook.tests;

import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.gotoContactsAddPage();
        app.fillContactForm(new ContactData("Ivan", "Ivanov", "ivan.ivanov@newmymail.ru"));
        app.submitContactCreation();
        app.gotoHomePage();
    }

}
