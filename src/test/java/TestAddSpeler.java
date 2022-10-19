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

public class TestAddSpeler {
    WebDriver driver;
    String url = "http://localhost:8080/Smeyers_Tom_war_exploded/";
    @Before
    public void setUp() throws Exception{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url+"Controller?command=VoegToe");
    }
    @After
    public void clean(){
        driver.quit();
    }
    @Test
    public void test_Form_is_Shown_again_with_error_messages_If_all_fields_are_empty(){
        voegItemToe("",0,0,"");

        assertEquals("Voeg Toe", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Geen geldige naam!"));
        assertTrue(containsWebElementsWithText(lis, "Geen geldige club!"));
        assertTrue(containsWebElementsWithText(lis, "Vul een nummer in voor doelpunten."));
        assertTrue(containsWebElementsWithText(lis, "Vul een nummer in voor wedstrijden."));
    }
    @Test
    public void test_Form_is_Shown_again_with_error_messages_If_name_field_is_empty(){
        voegItemToe("",35,20,"Anderlecht");

        assertEquals("Voeg Toe", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Geen geldige naam!"));
    }
    @Test
    public void test_Form_is_Shown_again_with_error_messages_If_doelpunten_field_is_zero(){
        voegItemToe("Tom Smeyers",0,20,"Anderlecht");

        assertEquals("Voeg Toe", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Vul een nummer in voor doelpunten."));
    }
    @Test
    public void test_Form_is_Shown_again_with_error_messages_If_doelpunten_field_is_negative(){
        voegItemToe("Tom Smeyers",-5,20,"Anderlecht");

        assertEquals("Voeg Toe", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Geen geldig aantal doelpunten!"));
    }
    @Test
    public void test_Form_is_Shown_again_with_error_messages_If_wedstrijden_field_is_negative(){
        voegItemToe("Tom Smeyers",35,-5,"Anderlecht");

        assertEquals("Voeg Toe", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Geen geldig aantal wedstrijden!"));
    }
    @Test
    public void test_Form_is_Shown_again_with_error_messages_If_wedstrijden_field_is_zero(){
        voegItemToe("Tom Smeyers",35,0,"Anderlecht");

        assertEquals("Voeg Toe", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Vul een nummer in voor wedstrijden."));
    }
    @Test
    public void test_Form_is_Shown_again_with_error_messages_If_club_field_is_empty(){
        voegItemToe("Tom Smeyers",35,20,"");

        assertEquals("Voeg Toe", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Geen geldige club!"));
    }
    @Test
    public void test_Result_is_shown_if_all_fields_are_filled()
    {
        voegItemToe("Tom Smeyers",35,20,"Anderlecht");
        assertEquals("Overzicht", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"Tom Smeyers"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"35"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"20"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"Anderlecht"));
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
    private boolean containsWebElementsWithText(List<WebElement> elements, String text) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equals(text)) {
                return true;
            }
        }
        return false;
    }
}