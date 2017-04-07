package ru.smpl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

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
    public void testContactModification() {
        app.goTo().gotoHomePage();
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().modification(index);
        ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("Petr").withLastname("Petrov").withEmail("petr.petrov@newmymail.ru");
        app.contact().fillForm(contact, false);
        app.contact().update();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
