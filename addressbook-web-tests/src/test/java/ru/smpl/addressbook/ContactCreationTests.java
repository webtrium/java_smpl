package ru.smpl.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        gotoContactsAddPage();
        fillContactForm(new ContactData("Ivan", "Ivanov", "ivan.ivanov@newmymail.ru"));
        submitContactCreation();
        gotoHomePage();
    }

}
