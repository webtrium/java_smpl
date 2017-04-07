package ru.smpl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensureReconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0){
            app.group().create(new GroupData().withName("test3"));
        }
    }

    @Test
    public void testContactCreation() {
        app.goTo().gotoHomePage();
        List<ContactData> before = app.contact().list();
        app.goTo().gotoContactsAddPage();
        ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Ivanov").withEmail("ivan.ivanov@newmymail.ru").withGroup("test3" );
        app.contact().fillForm(contact, true);
        app.contact().submitCreation();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
