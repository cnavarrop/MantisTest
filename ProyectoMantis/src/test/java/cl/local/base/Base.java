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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.externalconfig.model.Config;
import com.aventstack.extentreports.markuputils.Markup;

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

		if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
			browser = System.getenv("browser");
		} else {
			browser = prop.getProperty("navegador");
		}

		if (browser.equals("chrome")) {
			co = new ChromeOptions();
			try {
				driver = new RemoteWebDriver(new URL(prop.getProperty("urlNode")), co);
				log.debug("Inicialización de chrome");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ((browser.equals("firefox"))) {
			fo.setCapability("browserName", "Firefox");
			fo.setCapability("browserVersion", "107.0.1");
			fo = new FirefoxOptions();

			try {
				driver = new RemoteWebDriver(new URL(prop.getProperty("urlNode")), fo);
				log.debug("Inicialización de firefox");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			ExtentManager.test.log(Status.INFO, "click en: " + Locator, img);

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
			ExtentManager.test.log(Status.INFO, "Se ingresa el valor: " + Locator, img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
