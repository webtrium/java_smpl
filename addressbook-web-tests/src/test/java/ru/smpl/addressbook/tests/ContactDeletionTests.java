package ru.smpl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.Contacts;
import ru.smpl.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensureReconditions() {
        app.goTo().gotoHomePage();
        if (app.db().contacts().size() == 0){
            app.goTo().groupPage();
            if (app.db().groups().size() == 0){
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
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.contact().closeWindow();
        app.goTo().gotoHomePage();
        Assert.assertEquals(app.contact().count(), before.size() - 1);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
        assertThat(after.size(), equalTo(before.size() - 1));

    }

}
