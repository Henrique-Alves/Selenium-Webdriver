package projetoteste;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

@RunWith(Parameterized.class)
public class TesteRegraCadastro {

	public CampoTreinamentoPage page;
	public DSL dsl;
	public WebDriver driver;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidasFavoritas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String msg;
	
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
	
	@Parameters
	public static Collection<Object[]> getCollObjects(){
		return Arrays.asList(new Object[][] {
			{"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
			{"Henrique", "", "", Arrays.asList(), new String[]{}, "Sobreno eh obrigatorio"},
			{"Henrique", "Alves", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			{"Henrique", "Alves", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Henrique", "Alves", "Masculino", Arrays.asList("Carne"), new String[]{"Futebol"}, "voce faz esporte ou nao?"},
			
		});
	}
	
	@Test
	public void deveValidarRegras() {
		page.setNome(nome);
		page.setSobreNome(sobrenome);
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		}
		if(sexo.equals("Feminino")){
			page.setSexoFeminino();
		}
		if(comidasFavoritas.contains("Carne")) page.setComidaFavoritaCarne();
		if(comidasFavoritas.contains("Pizza")) page.setComidaFavoritaPizza();
		if(comidasFavoritas.contains("Vegetariano")) page.setComidaFavoritaVegetariano();
		page.setEsportes(esportes);
		page.setClicarNoBotaoCadastrar();
		System.out.println(msg);
		//Assert.assertEquals(msg, dsl.alertaOberTextoEAceita());
	}
	
}
