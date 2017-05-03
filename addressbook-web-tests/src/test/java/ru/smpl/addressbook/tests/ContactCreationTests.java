package ru.smpl.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.smpl.addressbook.generators.FileDeserializer;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.Contacts;
import ru.smpl.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensureReconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("test3"));
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson;

            gson = new GsonBuilder()
                    .registerTypeAdapter(File.class, new FileDeserializer()).create();

            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactFromJson")
    public void testContactCreation(ContactData contact) {
        app.goTo().gotoHomePage();
        Contacts before = app.db().contacts();
        app.goTo().gotoContactsAddPage();
//        File photo = new File("src/test/resources/stru.png");
//        ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Ivanov").withEmail("ivan.ivanov@newmymail.ru").withPhoto(photo);
        app.contact().fillForm(contact, true);
        app.contact().submitCreation();
        app.goTo().gotoHomePage();
        assertThat(app.contact().count(),equalTo(before.size()+1));
        Contacts after = app.db().contacts();
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
    }

}
