package projetoteste;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import org.junit.Assert;


public class TesteDesafio {
	
	@Test
	public void cadastro() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///"+System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Henrique");
		Assert.assertEquals("Henrique", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");
		Assert.assertEquals("Alves", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Superior");
		Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());
		
		List<WebElement> options = combo.getOptions();
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Futebol")) {
				encontrou = true;
				break;
			}
		}
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
	}
}
