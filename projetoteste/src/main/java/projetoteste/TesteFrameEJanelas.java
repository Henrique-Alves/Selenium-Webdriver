package projetoteste;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteFrameEJanelas {

	@Test
	public void deveInteragirComFrames() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///"+System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		driver.switchTo().frame("frame1");//leva o foco para o frame;
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
		
		driver.switchTo().defaultContent();//tras de volta pra fora do frame;
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
	}
	
	@Test
	public void deveInteragirComJanelas() {
		WebDriver driver01 = new ChromeDriver();
		driver01.get("file:///"+System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		driver01.findElement(By.id("buttonPopUpEasy")).click();
		driver01.switchTo().window("Popup");
		driver01.findElement(By.tagName("textarea")).sendKeys("deu certo");
		driver01.close();
		driver01.switchTo().window("");
		driver01.findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		WebDriver driver02 = new ChromeDriver();
		//driver02.manage().window().setSize(new Dimension(1024, 768));
		driver02.get("file:///"+System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		driver02.findElement(By.id("buttonPopUpEasy")).click();
		System.out.println(driver02.getWindowHandle());
		System.out.println(driver02.getWindowHandles());
		driver02.switchTo().window((String) driver02.getWindowHandles().toArray()[1]);
		driver02.findElement(By.tagName("textarea")).sendKeys("deu certo?");
		driver02.switchTo().window((String) driver02.getWindowHandles().toArray()[0]);
		driver02.findElement(By.tagName("textarea")).sendKeys("e agora?");
		
		
	}
}