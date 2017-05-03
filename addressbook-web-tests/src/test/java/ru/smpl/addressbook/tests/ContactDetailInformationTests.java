package ru.smpl.addressbook.tests;

import org.testng.annotations.Test;
import ru.smpl.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDetailInformationTests extends TestBase {

    @Test
    public void testContactDetailInformation() {

        app.goTo().gotoHomePage();
        ContactData contact = app.db().contacts().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        String contactDetailsForm = app.contact().DetailsForm(contact).getDetailContact();
        String HomePhone = "";
        String MobilePhone = "";
        String WorkPhone = "";

        if (! contactInfoFromEditForm.getHomePhone().equals("")) {
            HomePhone = "H: " + contactInfoFromEditForm.getHomePhone();
        }
        if (! contactInfoFromEditForm.getMobilePhone().equals("")) {
            MobilePhone = "M: " + contactInfoFromEditForm.getMobilePhone();
        }
        if (! contactInfoFromEditForm.getWorkPhone().equals("")) {
            WorkPhone = "W: " + contactInfoFromEditForm.getWorkPhone();
        }
        String contactEditForm = contactInfoFromEditForm.getFirstname() + contactInfoFromEditForm.getLastname() +
                contactInfoFromEditForm.getAddress() + HomePhone + MobilePhone + WorkPhone +
                contactInfoFromEditForm.getEmail() + contactInfoFromEditForm.getEmail2() + contactInfoFromEditForm.getEmail3();

        assertThat(cleaned(contactEditForm), equalTo(cleaned(contactDetailsForm)));
    }

    private static String cleaned(String contactDetailsForm) {
        return contactDetailsForm.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
