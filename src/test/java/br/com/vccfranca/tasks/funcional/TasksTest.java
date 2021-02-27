package br.com.vccfranca.tasks.funcional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://192.168.0.121:8085/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {

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
	public void naoDeveSalvarTarefaSemDescricao() {
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
	public void naoDeveSalvarTarefaSemData() {

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
	public void naoDeveSalvarTarefaComDataAntiga() {

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
