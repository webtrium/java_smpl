package ru.smpl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.GroupData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensureReconditions() {
        app.goTo().gotoHomePage();
        if (app.contact().list().size() == 0){
            app.goTo().groupPage();
            if (app.group().all().size() == 0){
                app.group().create(new GroupData().withName("test3"));
            }
            app.goTo().gotoHomePage();
            app.goTo().gotoContactsAddPage();
            app.contact().fillForm(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withEmail("ivan.ivanov@newmymail.ru").withGroup("test3" ), true);
            app.contact().submitCreation();
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().gotoHomePage();
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        app.contact().modify(modifiedContact);
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Nikolay").withLastname("Petrov").withEmail("nikolay.petrov@newmymail.ru");
        app.contact().fillForm(contact, false);
        app.contact().update();
        app.goTo().gotoHomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
