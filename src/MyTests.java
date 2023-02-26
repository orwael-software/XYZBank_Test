import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;

public class MyTests {
	WebDriver driver;

	@BeforeTest
	public void myFirst() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
	}

	@Test(priority = 1, groups = "Customer")
	public void loginAsCustomer() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[1]/button")).click();
	}

	@Test(priority = 1, groups = "Manager")
	public void loginAsManager() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/button")).click();
	}

	@Test(priority = 2, groups = "Manager")
	public void Add_customer() throws InterruptedException {

		int userID = (int) (Math.random() * 100);
		StringBuilder userName = new StringBuilder();
		userName.append(userID);
		String UserIDAsString = userName.toString();
		System.out.println(UserIDAsString);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[1]")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[1]/input"))
				.sendKeys("customer");
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[2]/input"))
				.sendKeys("user" + UserIDAsString);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[3]/input"))
				.sendKeys(UserIDAsString);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/button")).click();
		Thread.sleep(3000);
		String myTextintheAlert = driver.switchTo().alert().getText();
		System.out.println(myTextintheAlert);
		boolean mycheck = myTextintheAlert.contains("successfully");
		SoftAssert mysoft = new SoftAssert();
		mysoft.assertEquals(mycheck, true, "customer add successfully");
		driver.switchTo().alert().accept();
		driver.navigate().to("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/list");
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/form/div/div/input"))
				.sendKeys("user" + UserIDAsString);
		List<WebElement> myUsers = driver.findElements(By.xpath("//tbody/tr"));
		int myActualUsers = myUsers.size();
		mysoft.assertEquals(myUsers.size(),1);
		mysoft.assertAll();
	}
	
	@Test()
	public void testOne () throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[1]/button")).click();
		WebElement myMenu = driver.findElement(By.xpath("//*[@id=\"userSelect\"]"));
		Select MySelect = new Select(myMenu);
		MySelect.selectByVisibleText("Harry Potter");	
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/form/button")).click();
		Thread.sleep(2000);
		String MyBiggestAmount = "1000";
		String MySmallestAmount = "500";
		int small = Integer.parseInt(MySmallestAmount);
		int Big = Integer.parseInt(MyBiggestAmount);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[2]")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")).sendKeys(MyBiggestAmount);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")).sendKeys(MySmallestAmount);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button")).click();
		Thread.sleep(2000);
		String myBalance = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")).getText();
		System.out.println(myBalance);
		int myNewBalance = Integer.parseInt(myBalance);
		SoftAssert MySoft = new SoftAssert();
		MySoft.assertEquals(myNewBalance, Big-small , "check the withdraw process");
		MySoft.assertAll();
		
	}
	
	
}