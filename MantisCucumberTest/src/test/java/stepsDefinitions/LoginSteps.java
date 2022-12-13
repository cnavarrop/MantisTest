package stepsDefinitions;



import static org.testng.Assert.fail;

import cucumber.api.java.en.And;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import pages.PasswordPage;

public class LoginSteps{
	


	
	LoginPage lp= new LoginPage();
	PasswordPage pp= new PasswordPage();
	HomePage hp = new HomePage();
	
	
	@Given("la url de acceso a Mantis")
	public void la_url_de_acceso_a_mantis() {
		lp.ingresarUrlMantis();
	}

	@When("^ingreso un usuario \"([^\"]*)\" valido$")
	public void ingreso_un_usuario_valido(String usuario) {
		
		lp.ingresarUsuario("cnavarrp");
		lp.clickSubmit();
	}

	@When("^ingreso una contraseña \"([^\"]*)\" valida$")
	public void ingreso_una_contraseña_valido(String pass) {
		pp.ingresarPassword("Metalero");
		pp.clickSubmit();
	}

	@Then("se ingresa y se muestra la pantalla home de mantis")
	public void se_ingresa_y_se_muestra_la_pantalla_home_de_mantis() {
	if(!hp.validarHomePage()) {
		fail("La pagina no corresponde a la pagina inicial");
	}
	 
	}
	
	@When("^no realizo el ingreso un usuario \"([^\"]*)\"$")
	public void ingreso_un_usuario_no_valido(String arg1) throws Throwable {
	   
	}
	
	@Then("^presiono la opcion submit$")
	public void presiono_la_opcion_submit() throws Throwable {
	   
		lp.clickSubmit();
	
	}

	@Then("^me muestra un mensaje de error$")
	public void se_muestra_el_mensaje_de_error() throws Throwable {
		if(!lp.mensajeDeError()) {
			fail("mensaje de error no corresponde");
		}
	   
	}
	
}

	