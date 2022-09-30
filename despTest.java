package despegar.avance;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import despegar.avance.PO.HomePage;
import despegar.avance.PO.ResultsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


//agregar class
public class despTest{
	public WebDriver driver;
	public HomePage inicio;
	
		
@BeforeMethod (alwaysRun=true)
	public void setUp(){
	System.setProperty("webdriver.chrome.driver", "src/primerProyecto/chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://www.despegar.com.ar/");
	inicio = new HomePage(driver); }
		
		
		
		 //data provider 
		@DataProvider
		public Object[][] dataprov(){
		return new Object [][] {
			{"Brasil"},
			{"Bariloche"}
		}
		;
		}
		
			
			@Test (groups="PrimerGroup",dataProvider="dataprov")
		public void BuscarAlojamiento(String pais) throws Exception {
			System.out.println("Validacion de busqueda en despegar.com");
	
			
			  inicio.cerrarFace();
			  inicio.aceptarCookies(); 
			  inicio.irAlojamientos();
			  //inicio.openMenu();
			  inicio.elegirDestino();
			  inicio.ingresarDestino(pais);
			  inicio.ingresarFechas();
			  inicio.cantidadPersonas("dos");
			 
			  
			   ResultsPage primerAlojamiento = inicio.buscar();
			   
			   Assert.assertFalse(primerAlojamiento.returnResult().isEmpty(),"Not available "); 
			   
		}
		@AfterMethod
		public void afterMethod() {
		driver.quit();
		}
			}
			




		
