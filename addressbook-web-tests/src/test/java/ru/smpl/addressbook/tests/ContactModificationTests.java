package ru.smpl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.Contacts;
import ru.smpl.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensureReconditions() {
        app.goTo().gotoHomePage();
        if (app.contact().all().size() == 0){
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
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        app.contact().modify(modifiedContact);
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Nikolay").withLastname("Petrov").withEmail("nikolay.petrov@newmymail.ru");
        app.contact().fillForm(contact, false);
        app.contact().update();
        app.goTo().gotoHomePage();
        Assert.assertEquals(app.contact().count(), before.size());
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
