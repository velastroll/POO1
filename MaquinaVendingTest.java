package uva.poo.entrega1;
import static org.junit.Assert.*;
import org.junit.Test;
import fabricante.externo.tarjetas.TarjetaMonedero;

public class MaquinaVendingTest {

	@Test
	public void testImplementacionMaquinaVendingValida() {
		MaquinaVending maq = new MaquinaVending (2);
		System.out.print("Goodbay World!");
		// Implementamos MaqVen
		// getunidades
		// getPrecio
		// GetNombreProducto
		// Compra Valida (true)
	}
	
	
	/*
	 * Implementacion no valida: [excepcion] num filas = 0 o menor
	 */
	
	/*	
	 * Prueba Compra valida pero false: no hay producto
	 * 
	 */
	
	/*	
	 * Prueba Compra valida pero false: no saldo
	 * 
	 */
	
	
	//----------------------------------------------
	
	/*
	 * Prueba compra no valida: [excepcion] Mala fila (por debajo)
	 */
	
	/*
	 * Prueba compra no valida: [excepcion] Mala fila (por arriba)
	 */
	
	//-----------------------------------------------
	
	/*
	 * Prueba setProductos no valida: [excepcion] Mala fila (por debajo)
	 */
	
	/*
	 * Prueba setProductos no valida: [excepcion] Mala fila (por arriba)
	 */
	
	/*
	 * Prueba setUnidades no valida: [excepcion] Mala fila (por debajo)
	 */
	
	/*
	 * Prueba setUnidades no valida:[excepcion] Mala fila (por arriba)
	 */
	
	/**
	 * Prueba setUnidades no valida: [excepcion] Precio <= 0
	 */
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetStockConUPC_corto(){
	}

}