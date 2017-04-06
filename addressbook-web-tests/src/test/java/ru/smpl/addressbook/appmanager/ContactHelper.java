package ru.smpl.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.smpl.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContacts(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath(".//*[@value='Delete']"));
    }

    public void modificationContact(int index) {
        wd.findElements(By.xpath("(.//*[@src='icons/pencil.png'])")).get(index).click();
//        click(By.xpath("(.//*[@src='icons/pencil.png'])[1]"));
    }

    public void updateContacts() {
        click(By.name("update"));
    }

    public void closeWindow(){
        wd.switchTo().alert().accept();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("input[accept]"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.xpath("../../td[2]")).getText();
            String firstName = element.findElement(By.xpath("../../td[3]")).getText();
            String email = element.getAttribute("accept");
            String id = element.getAttribute("value");
            ContactData contact = new ContactData(id,firstName, lastName, email, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
