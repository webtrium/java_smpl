package ru.smpl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.GroupData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensureReconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test3"));
        }
    }

    @Test
    public void testContactCreation() {
        app.goTo().gotoHomePage();
        Set<ContactData> before = app.contact().all();
        app.goTo().gotoContactsAddPage();
        ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Ivanov").withEmail("ivan.ivanov@newmymail.ru").withGroup("test3" );
        app.contact().fillForm(contact, true);
        app.contact().submitCreation();
        app.goTo().gotoHomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
