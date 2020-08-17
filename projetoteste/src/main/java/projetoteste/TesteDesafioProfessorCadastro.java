package projetoteste;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;


public class TesteDesafioProfessorCadastro {
	
	public WebDriver driver;
	public DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		//driver.manage().window().setSize(new Dimension(1024, 768));
		driver.get("file:///"+System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@Test
	public void cadastro() {
		inicializa();
	    
		dsl.escreve("elementosForm:nome", "Henrique");
		dsl.escreve("elementosForm:sobrenome", "Alves");
		
		dsl.ClicarBotao("elementosForm:sexo:0");
		dsl.ClicarBotao("elementosForm:comidaFavorita:0");
		dsl.selecionarCombo("elementosForm:escolaridade","Superior");
	    dsl.selecionarCombo("elementosForm:esportes","Futebol");
		
		dsl.ClicarBotao("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Henrique"));
		Assert.assertEquals("Sobrenome: Alves", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Carne", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Futebol", dsl.obterTexto("descEsportes"));
	}
}
