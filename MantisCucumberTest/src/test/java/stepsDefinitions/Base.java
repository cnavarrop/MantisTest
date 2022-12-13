package stepsDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.LoginPage;
import pages.PasswordPage;



public class Base {

	public static WebDriver driver;
	public static FileInputStream file;
	public static Properties po = new Properties();
	public static ChromeOptions co = new ChromeOptions();
    public static FirefoxOptions fo = new FirefoxOptions();
    String browser;
    Wait<WebDriver> wait;
    
    
    public void setWait() {
    	wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
    			.pollingEvery(Duration.ofSeconds(5))
    			.ignoring(NoSuchElementException.class);
    }
   
    @Before
	public  void setUpDriver() {
		if (driver == null) {
			if (po.getProperty("navegador") == null) {

				try {
					file = new FileInputStream(
							System.getProperty("user.dir") + "\\src\\test\\resources\\config\\config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					po.load(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(System.getenv("navegador")!=null && !System.getenv().isEmpty()) {
			browser = System.getenv("navegador");
		}else {
			browser = po.getProperty("navegador");
		}
		
		System.setProperty("navegador", browser);

			if (po.getProperty("navegador").equalsIgnoreCase("chrome")) {
				co = new ChromeOptions();
				try {
					driver = new RemoteWebDriver(new URL(po.getProperty("urlnode")), co);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (po.getProperty("navegador").equalsIgnoreCase("firefox")) {
				System.setProperty("webdirver.gecko.driver", "C:\\Users\\cnavarrp\\Selenium\\Grid\\geckodriver.exe");
				fo = new FirefoxOptions();
				fo.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
				try {
					driver = new RemoteWebDriver(new URL(po.getProperty("urlnode")), fo);

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
	}
@After
public void tearDown() {
	if(driver!=null)
	driver.quit();
}

@Before("TetsPostLogin")
public void loginMantis() {
	LoginPage lp = new LoginPage();
	PasswordPage pp = new PasswordPage();
	lp.ingresarUrlMantis();
	lp.ingresarUsuario(po.getProperty("user"));
	lp.clickSubmit();
	pp.ingresarPassword(po.getProperty("pass"));
	pp.clickSubmit();
	
}

}
