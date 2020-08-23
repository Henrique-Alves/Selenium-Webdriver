package projetoteste;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteDesafioProfessorCadastro {
	
	public WebDriver driver;
	public DSL dsl;
	public CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		//driver.manage().window().setSize(new Dimension(1024, 768));
		driver.get("file:///"+System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finalizar() {
		driver.quit();
	}

	@Test
	public void cadastro() {
		inicializa();
	    page.setNome("Henrique");
		page.setSobreNome("Alves");
		page.setSexoMasculino();
		page.setComidaFavoritaCarne();
		page.setSelecionarEscolaridade("Superior");
		page.setEsportes("Futebol");
	    page.setClicarNoBotaoCadastrar();
		
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Henrique"));
		Assert.assertEquals("Sobrenome: Alves", page.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Carne", page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Futebol", page.obterEsportes());
	}
	  
}
