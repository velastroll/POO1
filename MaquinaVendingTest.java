package uva.poo.entrega1;


import static org.junit.Assert.*;

import org.junit.Test;


import fabricante.externo.tarjetas.TarjetaMonedero;

public class MaquinaVendingTest {

	@Test
	public void testImplementacionMaquinaVendingValida() {
		MaquinaVending maq = new MaquinaVending(5, 5);
		
		
		
		maq.setProductoEnMaquina(1,"Lays", 5);
		maq.setProductoEnMaquina(1, 1, "66666666666", "Lays", 0.35, 7);
		maq.setProductoEnMaquina(1, 2, "66666666666", "Lays", 0.35, 3);
		assertEquals(maq.getUnidadesDisponiblesEnCasilla(1,1), 7);
		assertEquals(maq.getPrecioCasilla(1, 0), 0, 0.01);
		assertEquals(maq.getNombreProductoCasilla(1,0), "vacio");
	}
	@Test (expected = IllegalArgumentException.class)
	public void testGetStockConUPC_corto(){
		MaquinaVending maq = new MaquinaVending(5, 5);
		maq.setProductoEnMaquina(1, 3, "66666666666", "Lays", 0.35, 5);
		int stock = maq.getStockTotalDelProducto("12345678901");
	}
	@Test (expected = IllegalArgumentException.class)
	public void testGetStockConUPC_largo(){
		MaquinaVending maq = new MaquinaVending(5, 5);
		maq.setProductoEnMaquina(1, 3, "66666666666", "Lays", 0.35, 5);
		int stock = maq.getStockTotalDelProducto("1234567891012");
	}
	
	@Test
	public void testCompraValidaEnFilaLlena(){
		
		MaquinaVending maq = new MaquinaVending(1, 3);
		// Stock por ubicacion y en fila
		maq.setProductoEnMaquina(0, 0, "00000000000", "vacio", 0.50, 0);
		maq.setProductoEnMaquina(0, 1, "66666666666", "Lays", 0.35, 1);
		maq.setProductoEnMaquina(0, 2, "66666666666", "Lays", 0.35, 1);
		assertEquals(maq.getStockTotalDelProducto("666666666662"), 2);
		
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 0.35);
		assertTrue(maq.comprarProducto(0, tarjeta, "6Z1y00Nm31aA-571"));	// Compra
		
		assertEquals(maq.getStockTotalDelProducto("666666666662"), 1);		// El stock total desciende
		assertEquals(tarjeta.getSaldoActual(), 0, 0.01);  				// El dinero se descuenta de la tarjeta
		assertEquals(maq.getUnidadesDisponiblesEnCasilla(0, 0), 0);  		// El producto desciende
		assertEquals(maq.getUnidadesDisponiblesEnCasilla(0, 1), 0);  		// Al resto no afecta
		assertEquals(maq.getUnidadesDisponiblesEnCasilla(0, 2), 1);

	}	

	@Test
	public void testCompraValidaQuedandoSoloEnUlimaCasillaDeLaFila(){
		MaquinaVending maq = new MaquinaVending(1, 3);
		// Stock por ubicacion y en fila
		maq.setProductoEnMaquina(0, 0, "66666666666", "Lays", 0.35, 0);
		maq.setProductoEnMaquina(0, 1, "66666666666", "Lays", 0.35, 0);
		maq.setProductoEnMaquina(0, 2, "66666666666", "Lays", 0.35, 1);
		assertEquals(maq.getStockTotalDelProducto("666666666662"), 1);
		
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 0.35);
		assertTrue(maq.comprarProducto(0, tarjeta, "6Z1y00Nm31aA-571"));  	// Compra
		
		assertEquals(maq.getStockTotalDelProducto("666666666662"), 0);  	// No queda stock
		assertEquals(tarjeta.getSaldoActual(), 0, 0.01);  				// El dinero se descuenta de la tarjeta
		assertEquals(maq.getUnidadesDisponiblesEnCasilla(0, 0), 0);  		// Al resto no afecta
		assertEquals(maq.getUnidadesDisponiblesEnCasilla(0, 1), 0);
		assertEquals(maq.getUnidadesDisponiblesEnCasilla(0, 2), 0);  		// El producto desciende
	}
		
	@Test
	public void testCompraNoValidaPorNoProducto(){
		MaquinaVending maq = new MaquinaVending(1, 3);
		// Stock por ubicacion y en fila
		maq.setProductoEnMaquina(0, 0, "66666666666", "Lays", 0.35, 0);
		maq.setProductoEnMaquina(0, 1, "66666666666", "Lays", 0.35, 0);
		maq.setProductoEnMaquina(0, 2, "66666666666", "Lays", 0.35, 0);
		assertEquals(maq.getStockTotalDelProducto("666666666662"), 0);
		
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 0.35);
		assertFalse(maq.comprarProducto(0, tarjeta, "6Z1y00Nm31aA-571"));  	// Devuelve False = no compra
		assertEquals(maq.getStockTotalDelProducto("666666666662"), 0);  	// stock sigue igual
		assertEquals(tarjeta.getSaldoActual(), 0.35, 0.01);  				// no se descuenta dinero
		assertEquals(maq.getUnidadesDisponiblesEnCasilla(0, 0), 0);  		// Al resto no afecta
		assertEquals(maq.getUnidadesDisponiblesEnCasilla(0, 1), 0);
		assertEquals(maq.getUnidadesDisponiblesEnCasilla(0, 2), 0);  		
		
	}
	
	@Test
	public void testCompraNoValidaPorNoDinero(){
		MaquinaVending maq = new MaquinaVending(1, 3);
		// Stock por ubicacion y en fila
		maq.setProductoEnMaquina(0, 0, "66666666666", "Lays", 0.35, 0);
		maq.setProductoEnMaquina(0, 1, "66666666666", "Lays", 0.35, 0);
		maq.setProductoEnMaquina(0, 2, "66666666666", "Lays", 0.35, 1);
		assertEquals(maq.getStockTotalDelProducto("666666666662"), 1);
		
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 0.34);
		assertFalse(maq.comprarProducto(0, tarjeta, "6Z1y00Nm31aA-571"));  	// Devuelve False = no compra
		assertEquals(maq.getStockTotalDelProducto("666666666662"), 1);  	// stock sigue igual
		assertEquals(tarjeta.getSaldoActual(), 0.34, 0.01);  				// no se descuenta dinero
		assertEquals(maq.getUnidadesDisponiblesEnCasilla(0, 0), 0);  		// Al resto no afecta
		assertEquals(maq.getUnidadesDisponiblesEnCasilla(0, 1), 0);
		assertEquals(maq.getUnidadesDisponiblesEnCasilla(0, 2), 1); 
	}
	
	
	
}