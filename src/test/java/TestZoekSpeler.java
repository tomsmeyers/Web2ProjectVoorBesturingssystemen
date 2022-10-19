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

public class TestZoekSpeler {
    WebDriver driver;
    String url = "http://localhost:8080/Smeyers_Tom_war_exploded/";
    @Before
    public void setUp() throws Exception{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url+"Controller?command=Zoek");
    }
    @After
    public void clean(){
        driver.quit();
    }
    @Test
    public void speler_gevonden(){
        zoekSpeler("Deniz Undav", "Union");
        assertEquals("Gevonden", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")),"Naam: Deniz Undav"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")),"Doelpunten: 20"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")),"Wedstrijden: 28"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")),"Club: Union"));
    }
    @Test
    public void speler_niet_gevonden(){
        zoekSpeler("ABC", "Anderlecht");
        assertEquals("Gevonden", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("p")),"Helaas, we konden geen speler met naam ABC vinden."));
    }
    @Test
    public void speler_naam_leeg_geeft_foutmelding(){
        zoekSpeler("", "Anderlecht");
        assertEquals("Zoek", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Naam mag niet leeg zijn!"));
    }
    @Test
    public void speler_club_leeg_geeft_foutmelding(){
        zoekSpeler("ABC", "");
        assertEquals("Zoek", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Club mag niet leeg zijn!"));
    }
    @Test
    public void speler_naam_en_club_leeg_geeft_foutmelding(){
        zoekSpeler("", "");
        assertEquals("Zoek", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Naam en Club mogen niet leeg zijn!"));
    }
    private boolean containsWebElementsWithText(List<WebElement> elements, String text) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equals(text)) {
                return true;
            }
        }
        return false;
    }
    private void zoekSpeler(String string1, String string2){
        driver.findElement(By.id("Name")).sendKeys(string1);
        driver.findElement(By.id("Club")).sendKeys(string2);
        driver.findElement(By.id("button")).click();
    }
}
