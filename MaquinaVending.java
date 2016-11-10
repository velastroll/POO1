package uva.poo.entrega1;
import fabricante.externo.tarjetas.TarjetaMonedero;

public class MaquinaVending {
	
	private int num_filas;
	private Producto productoDeLaFila[];
	private int unidadesDeLaFila[];
	
	
	public MaquinaVending (int num_filas) throws IllegalArgumentException {
		if (num_filas<1){ throw new IllegalArgumentException ("La filas minimas son 1.");}
		this.num_filas=num_filas;
		productoDeLaFila = new Producto[num_filas];
		unidadesDeLaFila = new int [num_filas];
		for (int i = 0; i<(num_filas); i++){
				productoDeLaFila[i] = new Producto ("00000000000", "vacio", 0);
				unidadesDeLaFila[i] = 0;
		}
	}

	
	public void setProductosEnFila (int fila, Producto producto, int unidades) throws IllegalArgumentException{
		if (fila>=num_filas || fila<0){	throw new IllegalArgumentException ("Las filas posibles estan en [0, " + num_filas + "].\n");	}
		if (unidades < 0){			throw new IllegalArgumentException ("Debe introducir alguna unidad entera positiva.\n");		}
		unidadesDeLaFila[fila] = unidades;
		productoDeLaFila[fila] = producto;		
	}
	
	
	public void setUnidades(int fila, int unidades) throws IllegalArgumentException {
		if (num_filas<=fila || fila<0){ 	throw new IllegalArgumentException ("Las filas posibles estan en [0, " + num_filas + "].\n");	}
		unidadesDeLaFila[fila] = unidades;
	}

	
	public int getUnidadesDisponiblesDeLaFila(int fila) throws IllegalArgumentException {
		if (num_filas<=fila || fila<0){ 	throw new IllegalArgumentException ("Las filas posibles estan en [0, " + num_filas + "].\n");	}
		return unidadesDeLaFila[fila];
	}
	
	
	public void setPrecio (int fila, double precio) throws IllegalArgumentException {
		if (num_filas<=fila || fila<0){ 	throw new IllegalArgumentException ("Las filas posibles estan en [0, " + num_filas + "].\n");	}
		if (precio<0) { 					throw new IllegalArgumentException ("El precio no puede ser negativo");							}
		
		productoDeLaFila[fila].setPVP(precio);
	}
	
	public double getPrecio (int fila)  throws IllegalArgumentException {
		if (num_filas<=fila || fila<0){ 	throw new IllegalArgumentException ("Las filas posibles estan en [0, " + num_filas + "].\n");	}
		return productoDeLaFila[fila].getPVP();
	}
	
	public String getNombreProductoDeLaFila(int fila)  throws IllegalArgumentException {
		if (num_filas<=fila || fila<0){ 	throw new IllegalArgumentException ("Las filas posibles estan en [0, " + num_filas + "].\n");	}
		return productoDeLaFila[fila].getNombre();
	}
	

	public boolean comprarProductoDeLaFila(int fila, TarjetaMonedero tarjeta, String credencial)   throws IllegalArgumentException {
		if (num_filas<=fila || fila<0){ 	throw new IllegalArgumentException ("Las filas posibles estan en [0, " + num_filas + "].\n");	}
		boolean compra = false;
		if (unidadesDeLaFila[fila]>0){
			if (productoDeLaFila[fila].getPVP() <= tarjeta.getSaldoActual()){
				unidadesDeLaFila[fila]-=1;
				tarjeta.descontarDelSaldo(credencial, productoDeLaFila[fila].getPVP());
				compra = true;
			}
		}
		return compra;
	}		
}
