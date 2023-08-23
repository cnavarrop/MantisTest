package cl.local.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import utils.ExtentManager;

public class Base {

	public static WebDriver driver;
	private static FileInputStream fis;
	protected static Properties prop = new Properties();
	private ChromeOptions co;
	private FirefoxOptions fo;
	public Wait<WebDriver> wait;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public String browser;
	
	//variables para actualización de Test en Testlink
	public static String DEVKEY="d2afc5b3d973e3a6abb4c3a94f9a7d83";
	public static String URL="http://18.206.238.171/lib/api/xmlrpc/v1/xmlrpc.php";
	

	public WebDriver getDriver() {
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
		return driver;
	}

	@BeforeSuite
	public void IniReport() {
		ExtentManager.getIntance();
	}

	@BeforeMethod
	public void setUp() {
		if (driver == null)
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\propiedades\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			prop.load(fis);
			log.debug("Carga de archivo property");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (System.getenv("navegador") != null && !System.getenv("navegador").isEmpty()) {
			browser = System.getenv("navegador");
		} else {
			browser = prop.getProperty("navegador");
		}
		
		System.setProperty("navagador", browser);

		if (browser.equals("chrome")) {
			co = new ChromeOptions();
			try {
				System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver-win64\\chromedriver.exe");
				driver = new RemoteWebDriver(new URL(prop.getProperty("urlNode")), co);
				log.debug("Inicialización de chrome");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ((browser.equals("firefox"))) {
			System.setProperty("webdriver.gecko.driver", "D:\\Selenium\\Grid\\geckodriver.exe");
			fo = new FirefoxOptions();
			fo.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

			try {
				driver = new RemoteWebDriver(new URL(prop.getProperty("urlNode")), fo);
				log.debug("Inicialización de firefox");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			LogoutMantis();
			driver.quit();
		}
	}

	@AfterSuite
	public void endReport() {
		ExtentManager.endReport();
		
	}
	
	
	 
	public static void reportResult(String TestProject,String TestPlan,String Testcase,String Build,String Notes,String Result) throws TestLinkAPIException{
		
	TestLinkAPIClient api=new TestLinkAPIClient(DEVKEY, URL);
	api.reportTestCaseResult(TestProject, TestPlan, Testcase, Build, Notes, Result);
	
	}

	public static void LogoutMantis() {
		driver.findElement(By.xpath(prop.getProperty("xpathMiUsuario"))).click();
		driver.findElement(By.xpath(prop.getProperty("xpathSalir"))).click();
		log.debug("Logout satisfactorio");
	}

	public void LogInMantis(String user, String pass) {
		driver.get(prop.getProperty("urlAcceso"));
		driver.manage().window().maximize();
		Type("xpathUser", user);
		ClickOn("submit_login");
		Type("xpathPassw", pass);
		ClickOn("submit_pass");
		log.debug("Ingreso satisfactorio a la url: " + prop.getProperty("urlAcceso"));
	}

	public void probarLinks() {
		List<WebElement> tagA = driver.findElements(By.tagName("a"));
		Iterator<WebElement> ite = tagA.iterator();
		HttpURLConnection url = null;
		ArrayList<String> linksOK = new ArrayList<String>();
		ArrayList<String> linksFails = new ArrayList<String>();

		while (ite.hasNext()) {
			String href = ite.next().getAttribute("href");
			if (href == null || href.isEmpty()) {
				System.out.println("Link Vacio o erroneo");
			}

			try {
				url = (HttpURLConnection) new URL(href).openConnection();
				url.setRequestMethod("HEAD");
				url.connect();
				if (url.getResponseCode() > 400) {
					linksFails.add(href);
				} else {
					linksOK.add(href);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("Links con errores");
		for (String fail : linksFails) {
			System.out.println(fail);
		}

		System.out.println("Links correctos");
		for (String OK : linksOK) {
			System.out.println(OK);
		}

	}

	public void ClickOn(String Locator) {
		String path = System.getProperty("user.dir") + prop.getProperty("PathImagenes") + Locator + ".png";
		try {
			driver.findElement(By.xpath(prop.getProperty(Locator))).click();
			utils.ScreenShot.screenShot(Locator);
			MediaEntityModelProvider img = MediaEntityBuilder.createScreenCaptureFromPath(path).build();
			//ExtentManager.test.log(Status.INFO, "click en: " + Locator, img);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Type(String Locator, String value) {
		String path = System.getProperty("user.dir") + prop.getProperty("PathImagenes") + Locator + ".png";
		try {
			driver.findElement(By.xpath(prop.getProperty(Locator))).sendKeys(value);
			utils.ScreenShot.screenShot(Locator);
			MediaEntityModelProvider img = MediaEntityBuilder.createScreenCaptureFromPath(path).build();
			//ExtentManager.test.log(Status.INFO, "Se ingresa el valor: " + Locator, img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
