package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import pages.PasswordPage;

public class BuscarIncidenciaSteps {

	LoginPage lp = new LoginPage();
	HomePage hp = new HomePage();

	@Given("^Estando logeado en el sistema Mantis$")
	public void estando_logeado_en_el_sistema_Mantis() throws Throwable {

		lp.loginMantis();

	}

	@When("^voy a la opcion del menu ver incidencias$")
	public void voy_a_la_opcion_del_menu_ver_incidencias() throws Throwable {

		hp.buscarIncidencia("123");
	}

	@Then("^Me posiciono sobre el buscador de incidencias$")
	public void me_posiciono_sobre_el_buscador_de_incidencias() throws Throwable {

	}

	@Then("^escribo el numero Id de la incidencia$")
	public void escribo_el_numero_Id_de_la_incidencia() throws Throwable {

	}

	@Then("^realizo la busqueda$")
	public void realizo_la_busqueda() throws Throwable {

	}

	@Then("^Se muestra en la grilla la incidencia buscada$")
	public void se_muestra_en_la_grilla_la_incidencia_buscada() throws Throwable {

	}

}
