package ru.smpl.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.Contacts;
import ru.smpl.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
        Contacts before = app.contact().all();
        app.goTo().gotoContactsAddPage();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Ivanov").withEmail("ivan.ivanov@newmymail.ru").withPhoto(photo);
        app.contact().fillForm(contact, true);
        app.contact().submitCreation();
        app.goTo().gotoHomePage();
        assertThat(app.contact().count(),equalTo(before.size()+1));
        Contacts after = app.contact().all();
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
    }

}
