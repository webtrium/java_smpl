package ru.smpl.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.smpl.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("email"), contactData.getEmail());
    }

    public void selectContacts() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContacts() {
        click(By.xpath(".//*[@value='Delete']"));
    }

    public void modificationContact() {
        click(By.xpath("(.//*[@src='icons/pencil.png'])[1]"));
    }

    public void updateContacts() {
        click(By.name("update"));
    }

    public void closeWindow(){
        wd.switchTo().alert().accept();
    }
}
