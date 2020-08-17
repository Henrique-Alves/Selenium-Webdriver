package projetoteste;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestDesafioRegraDeNegocios {
	//esse � do professor;
	//validar campos obrigatorios;
	
	private WebDriver driver;
	private DSL dsl;
	
	//forma de inicializar chamando o metodo em outros testes
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		//driver.manage().window().setSize(new Dimension(1024, 768));
		driver.get("file:///"+System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		dsl = new DSL(driver);
	}
	
	@After
	public void quit() {
		driver.quit();
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		inicializa();
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		inicializa();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Henrique");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		inicializa();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Henrique");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarComidaFavoritaObrigatorio() {
		inicializa();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Henrique");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() {
		inicializa();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Henrique");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		alert.accept();
	}

}
