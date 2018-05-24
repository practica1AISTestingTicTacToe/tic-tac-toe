package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;

import static org.mockito.Mockito.*;


public class TicTacToeGameTest {
	
	TicTacToeGame game;
	Connection c1;
	Connection c2;
	Player p1;
	Player p2;
	
	@Before
	public void setUp() {
		game = new TicTacToeGame();
		
		c1 = mock(Connection.class);
		c2 = mock(Connection.class);
		
		p1 = new Player(1, "x", "Jugador 1");
		p2 = new Player(2, "o", "Jugador 2");
		
	}
	@Test
	public void testP1Win() {
		/*
		 * Vamos a comprobar que todo transcurre como debe cuando  
		 * el jugador(P1) que empieza moviendo ficha gana. 
		 *  
		 *   x | o | o
		 *	   | x |  
		 * 	   |   | x
		 * 
		 */
		
		//Añadimos las conexiones al juego
		game.addConnection(c1);
		game.addConnection(c2);
		
		//Añadimos los jugadores al juego
		game.addPlayer(p1);
		game.addPlayer(p2);
		
		verify(c1, times(2)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers()); //Verificamos la conexion 1
		verify(c2, times(2)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers()); //Verificamos la conexion 2
		
		reset(c1); //Reseteamos la cuenta de llamadas
		reset(c2);
		
		//Marcamos el tablero con las fichas y el orden correspondiente
		this.game.mark(0);
		this.game.mark(1);
		this.game.mark(4);
		this.game.mark(2);
		this.game.mark(8);
		
		verify(c1, times(2)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0)); //Verificamos los turnos la conexion 1
		verify(c1, times(2)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		verify(c2, times(2)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0)); //Verificamos los turnos la conexion 2
		verify(c2, times(2)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		
		reset(c1);
		reset(c2);
		
		int[] exp = { 0, 4, 8 }; //Valor esperado
		int[] res = this.game.checkWinner().pos; //Resultado
		
		assertArrayEquals(exp, res); //Comprobamos si es correcta la fila ganadora
		
	}
	
	@Test
	public void testP1Lose() {
		/*
		 * Vamos a comprobar que todo transcurre como debe cuando  
		 * el jugador(P1) que empieza moviendo ficha pierde. 
		 *  
		 *   x |   | o
		 *	   | x | o 
		 * 	 x |   | o
		 * 
		 */
		
		//Añadimos las conexiones al juego
		game.addConnection(c1);
		game.addConnection(c2);
		
		//Añadimos los jugadores al juego
		game.addPlayer(p1);
		game.addPlayer(p2);
		
		verify(c1, times(2)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers()); //Verificamos la conexion 1
		verify(c2, times(2)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers()); //Verificamos la conexion 2
		
		reset(c1); //Reseteamos la cuenta de llamadas
		reset(c2);
		
		//Marcamos el tablero con las fichas y el orden correspondiente
		this.game.mark(0);
		this.game.mark(2);
		this.game.mark(4);
		this.game.mark(5);
		this.game.mark(6);
		this.game.mark(8);
		
		verify(c1, times(2)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0)); //Verificamos los turnos la conexion 1
		verify(c1, times(3)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		verify(c2, times(2)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0)); //Verificamos los turnos la conexion 2
		verify(c2, times(3)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		
		reset(c1);
		reset(c2);
		
		
		int[] exp = { 2, 5, 8 };	//Valor esperado
		int[] res = this.game.checkWinner().pos;	//Resultado
		
		assertArrayEquals(exp, res); //Comprobamos si es correcta la fila ganadora
		
	}
	
	@Test
	public void testDraw() {
		/*
		 * Vamos a comprobar que todo transcurre como debe cuando  
		 * se produce un empate. 
		 *  
		 *   x | o | x
		 *	 x | x | o 
		 * 	 o | x | o
		 * 
		 */
		
		//Añadimos las conexiones al juego
		game.addConnection(c1);
		game.addConnection(c2);
		
		//Añadimos los jugadores al juego
		game.addPlayer(p1);
		game.addPlayer(p2);
		
		verify(c1, times(2)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers()); //Verificamos la conexion 1
		verify(c2, times(2)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers()); //Verificamos la conexion 2
		
		reset(c1); //Reseteamos la cuenta de llamadas
		reset(c2);
		
		//Marcamos el tablero con las fichas y el orden correspondiente
		this.game.mark(0);
		this.game.mark(1);
		this.game.mark(4);
		this.game.mark(8);
		this.game.mark(2);
		this.game.mark(6);
		this.game.mark(3);
		this.game.mark(5);
		this.game.mark(7);
		
		verify(c1, times(4)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0)); //Verificamos los turnos la conexion 1
		verify(c1, times(4)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		verify(c2, times(4)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0)); //Verificamos los turnos la conexion 2
		verify(c2, times(4)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		
		reset(c1);
		reset(c2);
		
		boolean exp = true;	//Valor esperado
		boolean res = this.game.checkDraw();	//Resultado

		assertEquals(exp, res); //Comprobamos si corresponde el valor esperado con el resultado
		
	}


}
