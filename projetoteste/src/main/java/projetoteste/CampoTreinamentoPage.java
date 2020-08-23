package projetoteste;

import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	
	private DSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}

	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);
	}
	
	public void setSobreNome(String sobrenome) {
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino() {
		dsl.ClicarBotao("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino() {
		dsl.ClicarBotao("elementosForm:sexo:1");
	}
	
	public void setComidaFavoritaCarne() {
		dsl.ClicarBotao("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaFavoritaPizza() {
		dsl.ClicarBotao("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaFavoritaVegetariano() {
		dsl.ClicarBotao("elementosForm:comidaFavorita:3");
	}
	
	public void setSelecionarEscolaridade(String valor) {
		dsl.selecionarCombo("elementosForm:escolaridade", valor);
	}
	
	public void setEsportes(String... valores) {
		for(String valor: valores)
		dsl.selecionarCombo("elementosForm:esportes", valor);
	}
	
	public void setClicarNoBotaoCadastrar() {
		dsl.ClicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro() {
		return dsl.obterTexto("resultado");
	}
	
	public String obterNomeCadastro() {
		return dsl.obterTexto("descNome");
	}
	
	public String obterSobrenomeCadastro() {
		return dsl.obterTexto("descSobrenome");
	}
	
	public String obterSexoCadastro() {
		return dsl.obterTexto("descSexo");
	}
	
	public String obterComidaCadastro() {
		return dsl.obterTexto("descComida");
	}
	
	public String obterEscolaridadeCadastro() {
		return dsl.obterTexto("descEscolaridade");
	}
	
	public String obterEsportes() {
		return dsl.obterTexto("descEsportes");
	}
}
