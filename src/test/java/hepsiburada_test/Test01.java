package hepsiburada_test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Test01 {
    @Test
    public void test() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //https://www.hepsiburada.com sitesine giriş yapılır.
        driver.get("https://www.hepsiburada.com");

        //Kullanıcı login işlemi yapılır ve kontrol edilir.
        Actions actions = new Actions(driver);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        actions.moveToElement(driver.findElement(By.xpath("//*[.='Giriş Yap']"))).perform();
        driver.findElement(By.id("login")).click();

        WebElement emailBox = driver.findElement(By.id("txtUserName"));
        emailBox.sendKeys("nttaskan@gmail.com" + Keys.ENTER);
        Thread.sleep(5000);
        WebElement passwordBox = driver.findElement(By.id("txtPassword"));
        passwordBox.sendKeys("b2691210");
        Thread.sleep(5000);
        driver.findElement(By.id("btnEmailSelect")).click();
        Thread.sleep(5000);

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='myAccount']")).getText().contains("Hesabım"));

        //Arama çubuğuna "Telefon" kelimesi aratılır.
        WebElement searchBox = driver.findElement(By.xpath("//*[@class='searchBoxOld-M1esqHPyWSuRUjMCALPK']"));
        searchBox.sendKeys("Telefon" + Keys.ENTER);

        //Kullanıcı fiyat aralığını  20.500-46.300 seçeneğini filtreler.
        WebElement enAzFiyat = driver.findElement(By.xpath("//input[@placeholder='En az']"));
        actions.moveToElement(enAzFiyat).
                sendKeys("20500").
                sendKeys(Keys.TAB).
                sendKeys("46300").
                sendKeys(Keys.TAB).
                sendKeys(Keys.ENTER).
                build().
                perform();

        //Rastgele bir telefon seçer.
        driver.findElement(By.xpath("(//*[@data-test-id='carousel-grid-item'])[1]")).click();

        //Seçilen ürün sepete eklenir.
        driver.findElement(By.xpath("//button[@id='addToCart']")).click();

        //Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.
        WebElement urunSayfaFiyati = driver.findElement(By.xpath("//span[@class='price merchant price-new-old']"));
        driver.findElement(By.xpath("//*[.='Sepete git']")).click();
        WebElement urunSepetFiyati = driver.findElement(By.xpath("//*[@class='total_price_3V-CM']"));

        Assert.assertEquals(urunSayfaFiyati.getText(), urunSepetFiyati.getText());

        //Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.
        driver.findElement(By.xpath("//a[@class='delete_button_1lHhf']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='content_Z9h8v']")).getText().contains("Sepetin şu an boş"));

        //driver.close();

    }
}
