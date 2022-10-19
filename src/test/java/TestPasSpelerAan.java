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

public class TestPasSpelerAan {
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
    public void test_Alles_Correct_ingevuld(){
        driver.findElement(By.id("PasaanJoshua Zirkzee")).click();
        LeegAlles();
        voegItemToe("Joshua Zirkzee",15,28,"Anderlecht");
        assertEquals("Overzicht", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"Joshua Zirkzee"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"15"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"28"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"Anderlecht"));
    }
    @Test
    public void test_alles_leeg_ingevuld(){
        driver.findElement(By.id("PasaanJoshua Zirkzee")).click();
        LeegAlles();
        voegItemToe("",0,0,"");
        assertEquals("Pas aan", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Geen geldige naam!"));
        assertTrue(containsWebElementsWithText(lis, "Geen geldige club!"));
        assertTrue(containsWebElementsWithText(lis, "Vul een nummer in voor doelpunten."));
        assertTrue(containsWebElementsWithText(lis, "Vul een nummer in voor wedstrijden."));
    }
    private void voegItemToe(String string1, int int1, int int2, String string2){
        String string3, string4;
        if(int1 == 0){
            string3 = "";
        }else {
            string3 = String.valueOf(int1);
        }
        if(int2 == 0){
            string4 = "";
        }else {
            string4 = String.valueOf(int2);
        }
        driver.findElement(By.id("Name")).sendKeys(string1);
        driver.findElement(By.id("Doelpunten")).sendKeys(string3);
        driver.findElement(By.id("Wedstrijden")).sendKeys(string4);
        driver.findElement(By.id("Club")).sendKeys(string2);
        driver.findElement(By.id("button")).click();
    }
    private void LeegAlles(){
        driver.findElement(By.id("Name")).clear();
        driver.findElement(By.id("Doelpunten")).clear();
        driver.findElement(By.id("Wedstrijden")).clear();
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
