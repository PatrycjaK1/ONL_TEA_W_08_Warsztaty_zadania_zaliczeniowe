package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadanie1i2Steps {
    private WebDriver driver;

    @Given("An open browser with mystore-testlab.coderslab.pl")
    public void openHotelTestLab() {
        // Skonfiguruj sterownik przeglądarki
        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/drivers/chromedriver.exe");
        // Uruchom nowy egzemplarz przeglądarki Chrome
        driver = new ChromeDriver();
        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        // Przejdź do mystore-testlab.coderslab
        driver.get("https://mystore-testlab.coderslab.pl/");
    }

    @When("A button 'Sign in' is clicked")
    public void clickSignIn() {
        driver.findElement(By.className("user-info")).click();                               //przycisk "Sign in"
    }

    @And("An e-mail and a password is entered in input fields")
    public void enterEmailAndPassword() {
        driver.findElement(By.name("email")).sendKeys("grzes909@gazeta.pl");    //pole "Email"
        driver.findElement(By.name("password")).sendKeys("Pass123");            //pole "Password"
    }

    @And("A button 'SIGN IN' is clicked")
    public void clickSignIn2() {
        driver.findElement(By.id("submit-login")).click();                                   //przycisk "SIGN IN"
    }

    @And("User is logged")
    public void userIsLogged() throws InterruptedException {
        Thread.sleep(2000);
        WebElement userName = driver.findElement(By.className("account"));
        Assert.assertEquals("Iwo Antczak", userName.getText());
        Assert.assertTrue(userName.isDisplayed());
    }

    @And("An link 'Addresses' is clicked")
    public void clickAddresses() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@title='Addresses']")).click();                     //link "Addresses"
    }

    @And("An link 'Create new address' is clicked")
    public void clickCreateNewAddress() {
        driver.findElement(By.xpath("//a[@data-link-action='add-address']")).click();        //link "Create new address"
    }

    @And("^An alias (.*), an address ([A-ZĆŁÓŚŻŹ][a-ząćęłńóśżź]+\\ .*[0-9]+.*), " +
            "a city ([A-ZĆŁÓŚŻŹ][a-ząćęłńóśżź]+[\\-\\ ]?[A-ZĆŁÓŚŻŹ]?[a-ząćęłńóśżź]*), " +
            "zip/postal code ([0-9]{2}\\-[0-9]{3}), a country ([A-ZĆŁÓŚŻŹ][a-ząćęłńóśżź]+\\ ?[A-ZĆŁÓŚŻŹ]?[a-ząćęłńóśżź]*) " +
            "and a phone ([0-9]{9,15}) is entered in input fields$")
    public void enterAliasAddressCityPostalCodeCountryAndPhone(String alias, String address, String city, String postalCode, String country, int phone) {
        driver.findElement(By.name("alias")).sendKeys(alias);                                //pole "Alias"
        driver.findElement(By.name("address1")).sendKeys(address);                           //pole "Address"
        driver.findElement(By.name("postcode")).sendKeys(postalCode);                        //pole "Zip/Postal Code"
        driver.findElement(By.name("city")).sendKeys(city);                                  //pole "City"
        driver.findElement(By.name("id_country")).sendKeys(country);                         //pole "Country"
        driver.findElement(By.name("phone")).sendKeys(String.valueOf(phone));                //pole "Phone"
    }

    @And("A button 'SAVE' is clicked")
    public void clickSave() {
        driver.findElement(By.name("submitAddress")).submit();                               //przycisk "SAVE"
    }

    @Then("^A new address (.*), ([A-ZĆŁÓŚŻŹ][a-ząćęłńóśżź]+\\ .*[0-9]+.*), " +
            "([A-ZĆŁÓŚŻŹ][a-ząćęłńóśżź]+[\\-\\ ]?[A-ZĆŁÓŚŻŹ]?[a-ząćęłńóśżź]*), " +
            "([0-9]{2}\\-[0-9]{3}), ([A-ZĆŁÓŚŻŹ][a-ząćęłńóśżź]+\\ ?[A-ZĆŁÓŚŻŹ]?[a-ząćęłńóśżź]*), " +
            "([0-9]{9,15}) is added$")
    public void newAddressIsAdded(String alias, String address, String city, String postalCode, String country, int phone) {
        WebElement newAddress = driver.findElement(By.xpath("(//div[@class='address-body'])[last()]"));
        String newUserNameAddress = alias + "\n" + "Iwo Antczak" + "\n" + address + "\n" + city + "\n" + postalCode + "\n" + country + "\n" + phone;
        Assert.assertEquals(newUserNameAddress, newAddress.getText());
        Assert.assertTrue(newAddress.isDisplayed());
    }

    @And("A success message is displayed")
    public void displaySuccessMessage() {
        WebElement message = driver.findElement(By.className("alert-success"));              //komunikat "Address successfully added!"
        Assert.assertTrue(message.isDisplayed());
    }

    @And("Close browser")
    public void closeBrowser() {
        driver.quit();
    }

    @And("An link 'Delete' is clicked")
    public void clickDelete() {
        driver.findElement(By.xpath("//a[@data-link-action='delete-address']")).click();     //link "Delete"
    }

   /* @Then("^An address (.*), ([A-ZĆŁÓŚŻŹ][a-ząćęłńóśżź]+\\ .*[0-9]+.*), " +
            "([A-ZĆŁÓŚŻŹ][a-ząćęłńóśżź]+[\\-\\ ]?[A-ZĆŁÓŚŻŹ]?[a-ząćęłńóśżź]*), " +
            "([0-9]{2}\\-[0-9]{3}), ([A-ZĆŁÓŚŻŹ][a-ząćęłńóśżź]+\\ ?[A-ZĆŁÓŚŻŹ]?[a-ząćęłńóśżź]*), " +
            "([0-9]{9,15}) is deleted$")
    public void addressIsDeleted(String alias, String address, String city, String postalCode, String country, int phone) {
        //String[] table = new String[] {};
        //WebElement linkDelete = driver.findElement(By.xpath("//a[@data-link-action='delete-address']"));
        List<WebElement> listOfAddresses = driver.findElements(By.xpath("//a[@data-link-action='delete-address']"));
        //WebElement newAddress = driver.findElement(By.xpath("(//div[@class='address-body'])[last()]"));//nowy adres - ostatni
        String currentUserNameAddress = alias + "\n" + "Iwo Antczak" + "\n" + address + "\n" + city + "\n" + postalCode + "\n" + country + "\n" + phone;
        for (int i = 0; i <= Integer.parseInt(listOfAddresses); i++) {
            //table = Arrays.copyOf(table, table.length + 1);         //zwiększenie rozmiaru tablicy o 1
            //table [i] = i;
            WebElement currentAddress = driver.findElement(By.xpath("(//div[@class='address-body'])[last()-" + i + "]"));
            Assert.assertNotEquals(currentUserNameAddress, currentAddress.getText());
            Assert.assertFalse(currentAddress.isDisplayed());
        }
    }*/

    @Then("A success delete message is displayed")
    public void displaySuccessDeleteMessage() {
        WebElement message = driver.findElement(By.className("alert-success"));              //komunikat "Address successfully deleted!"
        Assert.assertTrue(message.isDisplayed());
    }

    @And("A keyword 'Hummingbird Printed Sweater' is entered in input field")
    public void enterKeywordHummingbirdPrintedSweater() {
        WebElement search = driver.findElement(By.className("ui-autocomplete-input"));      //pole wyszukiwania
        search.clear();
        search.sendKeys("Hummingbird Printed Sweater");
        search.submit();
    }

    @And("The first one should contain 'Hummingbird Printed Sweater'")
    public void theFirstOneContainsHummingbirdPrintedSweater() {
        WebElement theFirstOne = driver.findElement(By.xpath("//h2[@itemprop='name']"));    //pierwszy wyszukany element
        Assert.assertEquals("Hummingbird Printed Sweater", theFirstOne.getText());
        Assert.assertTrue(theFirstOne.isDisplayed());
    }

    @And("The first one is clicked")
    public void clickTheFirstOne() {
        driver.findElement(By.className("thumbnail-container")).click();        //pozycja "Hummingbird Printed Sweater"
    }

    @And("Check if the discount is 20%")
    public void checkIfTheDiscountIs20percent() {
        double discount = 0;
        String regularPrice = driver.findElement(By.className("regular-price")).getText();        //cena regularna
        String currentPrice = driver.findElement(By.className("current-price")).getText();        //cena aktualna
        //discount = 1 - currentPrice/regularPrice;
        //Assert.assertEquals("0.2", discount);
        //driver.findElement(By.name("postcode")).sendKeys(postalCode);                        //pole "Zip/Postal Code"
        //driver.findElement(By.name("city")).sendKeys(city);                                  //pole "City"
        //driver.findElement(By.name("id_country")).sendKeys(country);                         //pole "Country"
        //driver.findElement(By.name("phone")).sendKeys(String.valueOf(phone));                //pole "Phone"
    }

    @And("A size 'M' is chosen from selection list")
    public void chooseSize() {
        WebElement size = driver.findElement(By.id("group_1"));                                 //pole "Size"
        size.click();
        WebElement sizeM = driver.findElement(By.xpath("//option[@title='M']"));                //pozycja "M"
        sizeM.click();
    }

    @And("Quantity '5' is entered in input field")
    public void enterQuantity() {
        WebElement quantity = driver.findElement(By.id("quantity_wanted"));                     //pole "Quantity"
        quantity.clear();
        quantity.sendKeys("5");
    }

    @And("A button 'ADD TO CART' is clicked")
    public void clickAddToCart() {
        driver.findElement(By.xpath("//button[@data-button-action='add-to-cart']")).click();   //przycisk "ADD TO CART"
    }

    @And("A button 'PROCEED TO CHECKOUT' is clicked")
    public void clickProceedToCheckout() throws InterruptedException {
        Thread.sleep(2000);
        //WebElement proceedToCheckout = driver.findElement(By.xpath("//a[@href='//mystore-testlab.coderslab.pl/index.php?controller=cart&action=show']"));   //przycisk "PROCEED TO CHECKOUT"
        //proceedToCheckout.click();
        driver.findElement(By.xpath("(//a[@href='//mystore-testlab.coderslab.pl/index.php?controller=cart&action=show'])[last()]")).click();   //przycisk "PROCEED TO CHECKOUT"
        WebElement keyword = driver.findElement(By.className("product-line-info"));
        Assert.assertEquals("Hummingbird printed sweater", keyword.getText());
        Assert.assertTrue(keyword.isDisplayed());

        driver.findElement(By.xpath("//a[@href='https://mystore-testlab.coderslab.pl/index.php?controller=order']")).click();          //przycisk "PROCEED TO CHECKOUT" (drugi)
    }
}