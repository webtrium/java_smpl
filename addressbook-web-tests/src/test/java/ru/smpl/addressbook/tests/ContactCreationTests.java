package ru.smpl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test3", null, null));
        }
        app.getNavigationHelper().gotoContactsAddPage();
        ContactData contact = new ContactData("Ivan", "Ivanov", "ivan.ivanov@newmymail.ru", "test3" );
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max(((o1, o2) -> Integer.compare(o1.getId(),o2.getId()))).get().getId());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
