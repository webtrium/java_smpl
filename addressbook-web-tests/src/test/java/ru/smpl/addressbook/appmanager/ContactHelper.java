package ru.smpl.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void deleteSelection() {
        click(By.xpath(".//*[@value='Delete']"));
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelection();
    }

    public void modification(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void modify(ContactData contact) {
        int id = contact.getId();
        modification(id);
    }

    public void update() {
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

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("input[accept]"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.xpath("../../td[2]")).getText();
            String firstName = element.findElement(By.xpath("../../td[3]")).getText();
            String email = element.getAttribute("accept");
            int id = Integer.parseInt(element.getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).withEmail(email);
            contacts.add(contact);
        }
        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("input[accept]"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.xpath("../../td[2]")).getText();
            String firstName = element.findElement(By.xpath("../../td[3]")).getText();
            String email = element.getAttribute("accept");
            int id = Integer.parseInt(element.getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).withEmail(email);
            contacts.add(contact);
        }
        return contacts;
    }
}
