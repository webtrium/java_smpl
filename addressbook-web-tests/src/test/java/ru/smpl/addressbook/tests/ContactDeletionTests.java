package ru.smpl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoGroupPage();
            if (! app.getGroupHelper().isThereAGroup()){
                app.getGroupHelper().createGroup(new GroupData("test3", null, null));
            }
            app.getNavigationHelper().gotoHomePage();
            app.getNavigationHelper().gotoContactsAddPage();
            app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "ivan.ivanov@newmymail.ru", "test3" ), true);
            app.getContactHelper().submitContactCreation();
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContacts(before - 1);
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().closeWindow();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }

}
