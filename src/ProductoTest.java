package uva.poo.entrega1;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductoTest {

	@Test
	public void testInicializacionArgumentoValido() {
		Producto p = new Producto ("666666666662", "Lays");
		assertEquals(p.getUPC(), "666666666662");
		assertEquals(p.getNombre(), "Lays");
		assertEquals(p.getPrice(), 0, 0.01);
		assertEquals(p.getUnidades(), 0);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testInicializacionArgumentoCodeNoValidoPrincipio(){
		Producto p = new Producto ("/6666666666", "Pringles");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInicializacionArgumentoCodeNoValidoFinal(){
		Producto p = new Producto ("66666666666:", "Pringles");
	}
	
	@Test
	public void testInicializacion2ArgumentoValido() {
		Producto p = new Producto ("666666666662", "Lays", 12, 5);
		assertEquals(p.getUPC(), "666666666662");
		assertEquals(p.getNombre(), "Lays");
		assertEquals(p.getPrice(), 12, 0.01);
		assertEquals(p.getUnidades(), 5);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testInicializacion2ArgumentoNoValidoPrecioNegativo(){
		Producto p = new Producto ("66666666662", "Pringles", -0.01, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInicializacion2ArgumentoNoValidoUnidadesNegativas(){
		Producto p = new Producto ("666666666662", "Pringles", 3, -1);
	}

	
}
