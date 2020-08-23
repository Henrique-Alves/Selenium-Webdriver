package projetoteste;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TesteDesafioProfessorCadastro.class,
	TesteCampoTreinamento.class,
	TesteRegraCadastro.class
})
public class SuiteTeste {

}
