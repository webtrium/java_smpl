package ru.smpl.addressbook.tests;

import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getContactHelper().isThereAContact()){
            app.getGroupHelper().createGroup(new GroupData("test3", null, null));
        }
        app.getNavigationHelper().gotoContactsAddPage();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "ivan.ivanov@newmymail.ru", "test3" ), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
    }

}
