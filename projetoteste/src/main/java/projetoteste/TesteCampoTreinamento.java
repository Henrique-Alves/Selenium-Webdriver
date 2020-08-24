package projetoteste;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
//import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//simport org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class TesteCampoTreinamento {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		//driver.manage().window().setSize(new Dimension(1024, 768));
		driver.get("file:///"+System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		dsl = new DSL(driver);
	}
	
	@After
	public void finalizar() {
		//driver.quit();
	}

	@Test
	public void testeTextField() {
		dsl.escreve("elementosForm:nome", "Teste Texto");
		Assert.assertEquals("Teste Texto", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea() {
		dsl.escreve("elementosForm:sugestoes", "teste");
		Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));	
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		dsl.clickRadioButton("elementosForm:sexo:0");
		Assert.assertTrue(dsl.checandoSeORadioEstaMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void deveInteragirComcheckBox() {
		dsl.clickRadioButton("elementosForm:comidaFavorita:0");
		Assert.assertTrue(dsl.checandoSeORadioEstaMarcado("elementosForm:comidaFavorita:0"));
	}
	
	@Test
	public void DeveInteragirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		Assert.assertEquals("Mestrado", dsl.deveRetornarUmValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void DeveVerificarValoresCombo() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		java.util.List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}
	
	@Test
	public void DeveVerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.selecionarCombo("elementosForm:esportes", "Karate");
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement>allSelectdOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectdOptions.size());
	}
	
	@Test
	public void DeveInteragirComBotoes() {
		dsl.ClicarBotao("buttonSimple");
		
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
	}
	
	@Test
	@Ignore //Iguinora a execu��o do teste
	public void DeveInteragirComLinks() {
		dsl.clicarEmLink("voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void DeveVerificarOTituloDaPagina() {
		
		//Assert.assertTrue(driver9.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterNomeDoTexto(By.tagName("h3")));
		driver.findElement(By.tagName("h3")).getText();
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterNomeDoTexto(By.className("facilAchar")));
	}
	
	//***************JS*******************
	
	@Test
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escreva Meu nome � Henrique Alves'");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
}


