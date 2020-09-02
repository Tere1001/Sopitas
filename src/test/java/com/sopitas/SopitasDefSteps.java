package com.sopitas;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import java.util.List;

import static org.junit.Assert.*;

public class SopitasDefSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUpTest() {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--disable-notifications");
        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        wait= new WebDriverWait(driver,10 );
    }

    @After
    public void tearDownTest() {
        driver.quit();
    }

    @When("I am in the Sopitas main page")
    public void DefSopitas() throws Exception {
        assertNotNull("El browser no esta inicializador",driver); // verifica navegador esta presente
        assertTrue("Hay mas de  una ventana abierta",  driver.getWindowHandles().size() ==1); // validar una venta abierta de explorador

        driver.get("https://www.sopitas.com");
        String titulo= driver.getTitle();
        assertEquals("Se muestra incocorrectamente la pagina","Sopitas.com", titulo);
        WebElement logo= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1 .text-hide.logo-sopitas")));
       // assertTrue(elementIsVisible(By.cssSelector("h1 .text-hide.logo-sopitas")));
        assertTrue("El logo no se muestra correctamente",logo.isDisplayed());
        System.out.println("Estamos en la pagina de sopitas");
      //  if(elementIsVisible(By.cssSelector("#trending h5"))){
       //     System.out.println("El elemento hashtag es visible");
        //} else throw new Exception("El elemento hashtag no es visible");
    }


    @Then("I see the hashtags")
    public void iSeeTheHashtags() {
        List<WebElement> listahashtag = driver.findElements(By.cssSelector("#trending ol li"));
       assertNotNull(listahashtag);
       assertTrue(listahashtag.size() > 3);
        System.out.println(listahashtag.size());

        for(WebElement element:listahashtag ){
            System.out.println(element.getText());
        }
    }

    public boolean elementIsVisible(By descripcion) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(descripcion));
            return true;
        } catch(Exception ex) {
            return false;
        }

    }
}
