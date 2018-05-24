package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import es.codeurjc.ais.tictactoe.Board;
import es.codeurjc.ais.tictactoe.*;


public class BoardTest {

	Board board;
	
	@Before
	public void setUp() {
		board = new Board();
	}
	
	@Test
	public void testCheckDraw1() {
		//En este primer test de checkDraw() probamos si hay empate en un tablero vacio
		//por lo que el resultado debe ser False
		assertEquals(false, board.checkDraw());
	}
	
	@Test
	public void testCheckDraw2() {
		//En este segundo test de checkDraw() probamos si hay empate en un tablero lleno
		//por lo que el resultado debe ser True
		
		for(int i=0;i<9;i++) {
			if((i%2)==0) {
				board.getCell(i).value="x";
			}
			else {
				board.getCell(i).value="o";
			}
		}	
	
		assertEquals(true, board.checkDraw());
	}
	
	@Test
	public void testCheckDraw3() {
		//En este tercer test de checkDraw() probamos si hay empate en un tablero con
		//una fila ganadora llena por lo que el resultado debe ser False.
		
		board.getCell(0).value="x";	
		board.getCell(3).value="x";
		board.getCell(6).value="x";
	
		assertEquals(false, board.checkDraw());
	}
	
	@Test
	public void testGetCellsIfWinner1() {
		//En este primer test de getCellsIfWinner(String label) vamos a tener un tablero vacio 
		//por lo que el resultado si le pasamos una etiqueta cualquiera debe ser null.
		
		int[] aux = board.getCellsIfWinner("x");
		
		assertNull(aux);
		
	}
	
	@Test
	public void testGetCellsIfWinner2() {
		//En este segundo test de getCellsIfWinner(String label) vamos a tener un tablero con la 
		//fila ganadora {0, 3, 6} con la etiqueta 'x' por lo que el resultado si le pasamos la 
		//etiqueta 'x' debe ser {0, 3, 6}.
		
		int[] res = {0, 3, 6};
		
		board.getCell(0).value="x";	
		board.getCell(3).value="x";
		board.getCell(6).value="x";
		
		int[] aux = board.getCellsIfWinner("x");
		
		assertArrayEquals(res, aux);		
		
	}
	
	@Test
	public void testGetCellsIfWinner3() {
		//En este tercer test de getCellsIfWinner(String label) vamos a tener un tablero con la 
		//fila ganadora {0, 3, 6} con la etiqueta 'x' en la posicion 0 y 6 pero en la posicion 3 
		//tendremos una 'o' por lo que no es ganadora, por lo que el resultado si le pasamos la 
		//etiqueta 'x' el resultado sera null.
		
		
		board.getCell(0).value="x";	
		board.getCell(3).value="o";
		board.getCell(6).value="x";
		
		int[] aux = board.getCellsIfWinner("x");
		
		assertNull(aux);		
		
	}	
	
	@Test
	public void testGetCellsIfWinner4() {
		//En este cuarto test de getCellsIfWinner(String label) vamos a tener un tablero con
		//dos filas ganadoras:  { 0, 1, 2 } y { 2, 5, 8 }. En este caso si le pasamos la 
		//etiqueta 'x' deberia devolver la primera fila que encuentre que es { 0, 1, 2 }.
		
		/*
		 * 
		 *   x | x | x
		 *	 o | o | x
		 * 	 o | o | x
		 *
		 */
		
		for(int i=0; i<9; i++) {
			if(i==3 || i==4 || i==6 || i==7) {
				board.getCell(i).value="o";
			}
			else {
				board.getCell(i).value="x";
			}
		}
		
		int[] res = { 0, 1, 2 };
		
		int[] aux = board.getCellsIfWinner("x");
		
		assertArrayEquals(res, aux);		
		
	}	
	@Test
	public void testGetCellsIfWinner5() {
		//En este cuarto test de getCellsIfWinner(String label) vamos a ver si colocando una fila ganadora con 
		//'o', si al pasarle 'x' nos devuelve o no ganador a 'x'. El resultado debe ser Null.
		
		board.getCell(0).value="o";	
		board.getCell(3).value="o";
		board.getCell(6).value="o";
		
		int[] aux = board.getCellsIfWinner("x");
		
		assertNull(aux);		
		
	}	
	
	

}
