import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;

import java.util.*;

public class MainTest {

    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        // Настроим WebDriverManager для автоматической установки и инициализации ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test1() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1050, 740));

        // Ожидание до загрузки элементов
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Вводим данные для авторизации
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

        // Ожидание, пока появится элемент после авторизации
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-bike-light\"]")));

        // Добавляем товары в корзину
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-bike-light\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-fleece-jacket\"]")).click();

        // Убираем товары из корзины
        driver.findElement(By.cssSelector("*[data-test=\"remove-sauce-labs-fleece-jacket\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"remove-sauce-labs-bolt-t-shirt\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"remove-sauce-labs-bike-light\"]")).click();

        // Добавляем другие товары
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-onesie\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-fleece-jacket\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")).click();

        // Убираем товары из корзины
        driver.findElement(By.cssSelector("*[data-test=\"remove-test.allthethings()-t-shirt-(red)\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"remove-sauce-labs-onesie\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"remove-sauce-labs-fleece-jacket\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"remove-sauce-labs-bolt-t-shirt\"]")).click();

        // Нажимаем на сортировку товаров
        driver.findElement(By.cssSelector("*[data-test=\"product-sort-container\"]")).click();
    }
}
