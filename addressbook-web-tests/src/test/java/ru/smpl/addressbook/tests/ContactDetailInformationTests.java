package ru.smpl.addressbook.tests;

import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDetailInformationTests extends TestBase {

    @Test
    public void testContactDetailInformation() {

        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        String contactDetailsForm = app.contact().DetailsForm(contact).getDetailContact();
        String contactEditForm = contactInfoFromEditForm.getFirstname() + contactInfoFromEditForm.getLastname() +
                contactInfoFromEditForm.getAddress() + contactInfoFromEditForm.getHomePhone() +
                contactInfoFromEditForm.getMobilePhone() + contactInfoFromEditForm.getWorkPhone() +
                contactInfoFromEditForm.getEmail() + contactInfoFromEditForm.getEmail2() + contactInfoFromEditForm.getEmail3();

        assertThat(contactEditForm, equalTo(cleaned(contactDetailsForm)));
    }

    private static String cleaned(String contactDetailsForm) {
        return contactDetailsForm.replaceAll("\\s", "").replaceAll("[-()]", "").replaceAll(".:", "");
    }

}
