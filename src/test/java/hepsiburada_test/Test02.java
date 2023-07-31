package hepsiburada_test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Test02 {
    @Test
    public void test() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //https://www.hepsiburada.com sitesine giriş yapılır.
        driver.get("https://www.trendyol.com");

        //Kullanıcı login işlemi yapılır ve kontrol edilir.
        Actions actions = new Actions(driver);
      //  driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        driver.findElement(By.xpath("//*[.='Giriş Yap']"));

        WebElement emailBox = driver.findElement(By.id("login-email"));
        emailBox.sendKeys("nttaskan@gmail.com" + Keys.ENTER);
        Thread.sleep(5000);
        WebElement passwordBox = driver.findElement(By.id("login-password-input"));
        passwordBox.sendKeys("b2691210");
        Thread.sleep(5000);
        //JavascriptExecutor jse= (JavascriptExecutor) driver;
        //jse.executeScript("arguments[0].click();", driver.findElement(By.id("btnEmailSelect")));
        driver.findElement(By.xpath("//*[@class='q-primary q-fluid q-button-medium q-button submit']")).click();
        Thread.sleep(5000);

        //Assert.assertTrue(driver.findElement(By.xpath("//*[@id='myAccount']")).getText().contains("Hesabım"));

        //Arama çubuğuna "Telefon" kelimesi aratılır.
        WebElement searchBox = driver.findElement(By.id("sfx-discovery-search-suggestions"));
        searchBox.sendKeys("Telefon" + Keys.ENTER);

        //Kullanıcı fiyat aralığını  20.500-46.300 seçeneğini filtreler.
        WebElement fiyat = driver.findElement(By.xpath("(//div[@class='fltr-cntnr-ttl-area'])[4]"));
        actions.moveToElement(fiyat).
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
        WebElement urunSayfaFiyati = driver.findElement(By.xpath("//div[@class='product-price-and-ratings']"));
        driver.findElement(By.xpath("//*[.='Sepete git']")).click();
        WebElement urunSepetFiyati = driver.findElement(By.id("basket_payedPrice"));

        Assert.assertTrue(urunSayfaFiyati.getText().contains(urunSepetFiyati.getText()));

        //Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.
        driver.findElement(By.xpath("//a[@class='delete_button_1lHhf']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='content_Z9h8v']")).getText().contains("Sepetin şu an boş"));

        //driver.close();

    }
}
