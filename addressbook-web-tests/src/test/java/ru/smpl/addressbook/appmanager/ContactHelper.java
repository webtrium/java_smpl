package ru.smpl.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.smpl.addressbook.model.ContactData;
import ru.smpl.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
        contactCache = null;
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
        contactCache = null;
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

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache;

//    public Contacts all() {
//        if (contactCache != null) {
//            return new Contacts(contactCache);
//        }
//        contactCache = new Contacts();
//        List<WebElement> elements = wd.findElements(By.cssSelector("input[accept]"));
//        for (WebElement element : elements) {
//            String lastName = element.findElement(By.xpath("../../td[2]")).getText();
//            String firstName = element.findElement(By.xpath("../../td[3]")).getText();
//            String email = element.getAttribute("accept");
//            int id = Integer.parseInt(element.getAttribute("value"));
//            ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).withEmail(email);
//            contactCache.add(contact);
//        }
//        return new Contacts(contactCache);
//    }

    //HomePage
    public Contacts all() {
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String email = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withEmail(email).withAddress(address).withAllPhones(allPhones));

        }
        return new Contacts(contactCache);
    }

    //DetailsForm
    public ContactData DetailsForm(ContactData contact) {
        initContactDetailsById(contact.getId());
        WebElement tree = wd.findElement(By.xpath("//div[@id='content']"));
        String allDetails = tree.getText();
        return new ContactData().withDetailContact(allDetails);
    }

    //EditForm
    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withEmail(email).withEmail2(email2).withEmail3(email3).
                withAddress(address).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address);
    }

    public void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    public void initContactDetailsById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(6).findElement(By.tagName("a")).click();
    }
}
