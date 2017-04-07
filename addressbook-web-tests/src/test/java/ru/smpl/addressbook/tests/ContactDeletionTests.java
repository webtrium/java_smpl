package ru.smpl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensureReconditions() {
        app.goTo().gotoHomePage();
        if (app.contact().list().size() == 0){
            app.goTo().groupPage();
            if (app.group().list().size() == 0){
                app.group().create(new GroupData().withName("test3"));
            }
            app.goTo().gotoHomePage();
            app.goTo().gotoContactsAddPage();
            app.contact().fillForm(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withEmail("ivan.ivanov@newmymail.ru").withGroup("test3" ), true);
            app.contact().submitCreation();
        }
    }

    @Test
    public void testContactDeletion() {
        app.goTo().gotoHomePage();
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().select(index);
        app.contact().deleteSelection();
        app.contact().closeWindow();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
