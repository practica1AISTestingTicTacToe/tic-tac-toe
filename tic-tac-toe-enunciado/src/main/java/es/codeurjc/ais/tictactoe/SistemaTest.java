package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;

import java.util.concurrent.Delayed;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class SistemaTest {

	private WebDriver driver1;
	private WebDriver driver2;
	String nuevoJugador1;
	String nuevoJugador2;

	@BeforeClass
	public static void setupClass() {
		ChromeDriverManager.getInstance().setup();
		WebApp.start();
	}
	
	@AfterClass
		public static void teardownClass() {
		WebApp.stop();
	}

	
	@Before
	public void setupTest() throws Exception {
		//Creamos los navegadores
		driver1 = new ChromeDriver();
		driver2 = new ChromeDriver();
		
	    //Hacemos a cada navegador de ir a la pagina a evaluar
		driver1.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");
		
		//Creamos una variable con los nombres de los jugadores
		nuevoJugador1 = "jugador1";
		nuevoJugador2 = "jugador2";
		
		//Introducimos los nombres de los jugadores en sus respectivos campos del navegador
		driver1.findElement(By.id("nickname")).sendKeys(nuevoJugador1);
		Thread.sleep(2000);
		driver2.findElement(By.id("nickname")).sendKeys(nuevoJugador2);
		Thread.sleep(2000);
		
		//Tras introducir el nombre hacemos que hagan click en "Play!"
		driver1.findElement(By.id("startBtn")).click();
		Thread.sleep(2000);
		driver2.findElement(By.id("startBtn")).click();
		Thread.sleep(2000);
	}

	@After
	public void teardown() {
		if (driver1 != null) {
			driver1.quit();
		}
		if (driver2 != null) {
			driver2.quit();
		}
	}
	
	@Test
	public void testPrimerJugadorGana() throws Exception {
		/*
		 * Vamos a comprobar que todo transcurre como debe cuando  
		 * el jugador(nuevoJugador1) que empieza moviendo ficha gana. 
		 *  
		 *   x | o | o
		 *	   | x |  
		 * 	   |   | x
		 * 
		 */
		
		//Marcamos el tablero, es decir, jugamos la partida
		driver1.findElement(By.id("cell-0")).click();
		Thread.sleep(2000);
		driver2.findElement(By.id("cell-1")).click();
		Thread.sleep(2000);
		driver1.findElement(By.id("cell-4")).click();
		Thread.sleep(2000);
		driver2.findElement(By.id("cell-2")).click();
		Thread.sleep(2000);
		driver1.findElement(By.id("cell-8")).click();
		Thread.sleep(2000);
		
		//Obtenemos las alertas del navegador al finalizar el juego
		String alert1 =  driver1.switchTo().alert().getText();
		Thread.sleep(2000);
		String alert2 =  driver2.switchTo().alert().getText();
		Thread.sleep(2000);
		
		//Esto solo es usado para ver por consola el 'alert' del navegador. Se puede omitir
		System.out.println("Esta es la alerta del driver1" + alert1);
		System.out.println("Esta es la alerta del driver2" + alert2);
		
		//Almacenamos en una variable el valor esperado de la alerta del navegador
		String exp = nuevoJugador1 + " wins! " + nuevoJugador2 + " looses.";
		
		//Comparamos los valores obtenidos con los esperados
		assertEquals(exp, alert1);
		assertEquals(exp, alert2);
		
		
		Thread.sleep(2000);
			
	}
	
	@Test
	public void testPrimerJugadorPierde() throws Exception {
		/*
		 * Vamos a comprobar que todo transcurre como debe cuando  
		 * el jugador(nuevoJugador1) que empieza moviendo ficha pierde. 
		 *  
		 *   x |   | o
		 *	   | x | o 
		 * 	 x |   | o
		 * 
		 */		
		
		//Marcamos el tablero, es decir, jugamos la partida
		driver1.findElement(By.id("cell-0")).click();
		Thread.sleep(2000);
		driver2.findElement(By.id("cell-2")).click();
		Thread.sleep(2000);
		driver1.findElement(By.id("cell-4")).click();
		Thread.sleep(2000);
		driver2.findElement(By.id("cell-5")).click();
		Thread.sleep(2000);
		driver1.findElement(By.id("cell-6")).click();
		Thread.sleep(2000);
		driver2.findElement(By.id("cell-8")).click();
		Thread.sleep(2000);
		
		//Obtenemos las alertas del navegador al finalizar el juego
		String alert1 =  driver1.switchTo().alert().getText();
		Thread.sleep(2000);
		String alert2 =  driver2.switchTo().alert().getText();
		Thread.sleep(2000);
		
		//Esto solo es usado para ver por consola el 'alert' del navegador. Se puede omitir
		System.out.println("Esta es la alerta del driver1: " + alert1);
		System.out.println("Esta es la alerta del driver2: " + alert2);
		
		//Almacenamos en una variable el valor esperado de la alerta del navegador
		String exp = nuevoJugador2 + " wins! " + nuevoJugador1 + " looses.";
		
		//Comparamos los valores obtenidos con los esperados
		assertEquals(exp, alert1);
		assertEquals(exp, alert2);
		
		
		Thread.sleep(2000);	
			
	}
	
	@Test
	public void testEmpate() throws Exception {
		/*
		 * Vamos a comprobar que todo transcurre como debe cuando  
		 * se produce un empate. 
		 *  
		 *   x | o | x
		 *	 x | x | o 
		 * 	 o | x | o
		 * 
		 */
		
		//Marcamos el tablero, es decir, jugamos la partida
		driver1.findElement(By.id("cell-0")).click();
		Thread.sleep(2000);
		driver2.findElement(By.id("cell-1")).click();
		Thread.sleep(2000);
		driver1.findElement(By.id("cell-4")).click();
		Thread.sleep(2000);
		driver2.findElement(By.id("cell-8")).click();
		Thread.sleep(2000);
		driver1.findElement(By.id("cell-2")).click();
		Thread.sleep(2000);
		driver2.findElement(By.id("cell-6")).click();
		Thread.sleep(2000);
		driver1.findElement(By.id("cell-3")).click();
		Thread.sleep(2000);
		driver2.findElement(By.id("cell-5")).click();
		Thread.sleep(2000);
		driver1.findElement(By.id("cell-7")).click();
		Thread.sleep(2000);
		
		//Obtenemos las alertas del navegador al finalizar el juego
		String alert1 =  driver1.switchTo().alert().getText();
		Thread.sleep(2000);
		String alert2 =  driver2.switchTo().alert().getText();
		Thread.sleep(2000);
		
		//Esto solo es usado para ver por consola el 'alert' del navegador. Se puede omitir
		System.out.println("Esta es la alerta del driver1: " + alert1);
		System.out.println("Esta es la alerta del driver2: " + alert2);
		
		//Almacenamos en una variable el valor esperado de la alerta del navegador
		String exp = "Draw!";
		
		//Comparamos los valores obtenidos con los esperados
		assertEquals(exp, alert1);
		assertEquals(exp, alert2);
		
		
		Thread.sleep(2000);	
			
	}

}
