import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;


public class TestVerwijderSpeler {
    WebDriver driver;
    String url = "http://localhost:8080/Smeyers_Tom_war_exploded/";
    @Before
    public void setUp() throws Exception{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url+"Controller?command=Overzicht");
    }
    @After
    public void clean(){
        driver.quit();
    }

    @Test
    public void Verwijder_Speler_Antwoord_Nee(){
        driver.findElement(By.id("VerwijderJoshua Zirkzee")).click();
        assertEquals("Verwijder", driver.getTitle());
        driver.findElement(By.id("Nee")).click();
        assertEquals("Overzicht", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"Joshua Zirkzee"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"12"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"25"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"Anderlecht"));
    }

    @Test
    public void verwijder_Speler_Antwoord_Ja(){
        driver.findElement(By.id("VerwijderJoshua Zirkzee")).click();
        assertEquals("Verwijder", driver.getTitle());
        driver.findElement(By.id("Ja")).click();
        assertEquals("Overzicht", driver.getTitle());
        assertFalse(containsWebElementsWithText(driver.findElements(By.tagName("td")),"Joshua Zirkzee"));
        assertFalse(containsWebElementsWithText(driver.findElements(By.tagName("td")),"12"));
        assertFalse(containsWebElementsWithText(driver.findElements(By.tagName("td")),"25"));
        assertFalse(containsWebElementsWithText(driver.findElements(By.tagName("td")),"Anderlecht"));
    }
    private boolean containsWebElementsWithText(List<WebElement> elements, String text) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equals(text)) {
                return true;
            }
        }
        return false;
    }
}
