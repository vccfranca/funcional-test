package br.com.vccfranca.tasks.funcional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() throws MalformedURLException {
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
//		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.121:4444/wd/hub"), cap );
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.163:4444/wd/hub"), cap );
		driver.navigate().to("http://192.168.0.121:8003/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();

		// clicar em add todo
		driver.findElement(By.id("addTodo")).click();

		// escrever a descricao
		driver.findElement(By.id("task")).sendKeys("Teste via selenium");

		// escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

		// clicar em salvar

		driver.findElement(By.id("saveButton")).click();

		// validar mensagem de sucesso

		String mensagem = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Sucess!", mensagem);

		// fechar o driver
		driver.quit();
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();

//			// escrever a descricao
//			driver.findElement(By.id("task")).sendKeys("Teste via selenium");

			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			// clicar em salvar

			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso

			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);
		} finally {
			// fechar o driver
			driver.quit();
		}

	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();
		try {
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();

			// escrever a descricao
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");

//			// escrever a data
//			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			// clicar em salvar

			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso

			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", mensagem);			
		} finally {
			// fechar o driver
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataAntiga() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();

		try {
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();

			// escrever a descricao
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");

			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

			// clicar em salvar

			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso

			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);
		}finally {
			// fechar o driver
			driver.quit();

		}		
	}

}
