package projetoteste;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {
	
	@Test
	public void DeveInteragirComAlertSimples() {
		WebDriver driver10 = new ChromeDriver();
		//driver10.manage().window().setSize(new Dimension(1024, 768));
		driver10.get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		driver10.findElement(By.id("alert")).click();
		Alert alert = driver10.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();
		driver10.findElement(By.id("elementosForm:nome")).sendKeys(texto);
	}
	
	@Test
	public void DeveInteragirComAlertConfirme() {
		WebDriver driver01 = new ChromeDriver();
		//driver01.manage().window().setSize(new Dimension(1024, 768));
		driver01.get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		driver01.findElement(By.id("confirm")).click();
		Alert alerta = driver01.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		Assert.assertEquals("Confirmado", alerta.getText());
		alerta.accept();
		
		driver01.findElement(By.id("confirm")).click();
		alerta = driver01.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss(); //dismiss() é para cancelar 
		Assert.assertEquals("Negado", alerta.getText());
		alerta.accept();
	}
	
	@Test
	public void n() {
		WebDriver driver02 = new ChromeDriver();
		//driver02.manage().window().setSize(new Dimension(1024, 768));
		driver02.get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		driver02.findElement(By.id("prompt")).click();
		Alert alerta = driver02.switchTo().alert();
		Assert.assertEquals("Digite um Numero", alerta.getText());
		alerta.sendKeys("12");//escreve no input
		alerta.accept();
		Assert.assertEquals("Era 12?", alerta.getText());
		alerta.accept();
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
	}

}
