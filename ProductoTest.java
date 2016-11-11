package uva.poo.entrega1;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductoTest {
	
	@Test
	public void testInicializacionArgumentoValido() {
		Producto p = new Producto ("66666666666", "Lays", 0.35);
		assertEquals(p.getUPC(), "666666666662");
		assertEquals(p.getNombre(), "Lays");
		assertEquals(p.getPVP(), 0.35, 0.01);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testInicializacionArgumentoCodeNoValidoPrincipio(){
		Producto p = new Producto ("/666666666", "Pringles", 0.35);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInicializacionArgumentoCodeNoValidoFinal(){
		Producto p = new Producto ("6666666666:", "Pringles", 0.35);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testInicializacion2ArgumentoNoValidoPrecioNegativo(){
		Producto p = new Producto ("6666666666", "Pringles", -0.01);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testCambiarPVP_ArgumentoNegativo(){
		Producto p = new Producto ("66666666666", "Lays", 0.35);
		p.setPVP(-0.01);
	}
}
