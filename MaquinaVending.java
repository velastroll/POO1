package uva.poo.entrega1;
import fabricante.externo.tarjetas.TarjetaMonedero;

public class MaquinaVending {
	private int fila;
	private int columna;
	private Producto posicion [][];
	
	/**
	 * MaquinaVending CONSTRUCTOR
	 * @param fila max of rows
	 * @param columna max of columns
	 * @throws IllegalArgumentException Row and columns must be positives
	 */
	public MaquinaVending (int fila, int columna) throws IllegalArgumentException {
		if (fila<0||columna<0){ throw new IllegalArgumentException ("La filas y columnas minimas son 1.");}
		this.fila=fila;
		this.columna=columna;
		posicion = new Producto[fila][columna];
		for (int i = 0; i<(fila); i++){
			for (int j = 0; j<(columna); j++){
				posicion[i][j] = new Producto ("00000000000", "vacio", 0);
			}
		}
	}
	
	/**
	 * UPDATE the available stock
	 * @param fila selects the row. 0=all the row
	 * @param columna selects the column. 0=all the column
	 * @param code Need to implement the UPC code
	 * @param nombre The product name
	 * @param precio The product price
	 * @param unidades The old stock + the new units introduced
	 * @throws IllegalArgumentException Row and column must be in the domain.
	 */
	public void setProductoEnMaquina(int fila, int columna, String code, String nombre, double precio, int unidades) throws IllegalArgumentException {
		
		if (fila < 0 || fila > this.fila){ throw new IllegalArgumentException ("La fila seleccionada no esta en el dominio.");	}
		if (columna < 0 || columna > this.columna){ throw new IllegalArgumentException ("La columna seleccionada no esta en el dominio.");	}
		/*
		if (fila == 0 && columna!=0){
			for (int f=1;f<=this.fila; f++){
				posicion[f][columna] = new Producto (code, nombre, precio, unidades);
				posicion[f][columna].changeUnidades(unidades);
			}
		}
		if (columna == 0 && fila!=0){
			for (int c=1;c<=this.columna; c++){
				posicion[fila][c] = new Producto (code, nombre, precio, unidades);
				posicion[fila][c].changeUnidades(unidades);
			}
		}
		if (fila!=0 && columna !=0){
		*/
		posicion[fila][columna] = new Producto (code, nombre, precio, unidades);
		/*}*/
	}
	
	/**
	 * Remove one machine product.
	 * @param fila wanted row number
	 * @param columna wanted col number
	 * @throws IllegalArgumentException Column and row must be in the domain.
	 */
	public void sacarProducto(int fila, int columna) throws IllegalArgumentException {
		if (this.fila<=fila || this.columna<=columna || fila<0 || columna < 0){ throw new IllegalArgumentException ("La fila maxima es " + this.fila + " y la columna " + this.columna + ".\n");}
		posicion[fila][columna].changeUnidades(-1);
	}
	
	/**
	 * RETURN the available stock of a particular field.
	 * @param fila wanted row
	 * @param columna wanted column
	 * @return stock available stock in this field.
	 * @throws IllegalArgumentException The row and column must be in the domain
	 */
	public int getUnidadesDisponiblesEnCasilla(int fila, int columna) throws IllegalArgumentException {
		if (this.fila<=fila || this.columna<=columna || fila<0 || columna < 0){ throw new IllegalArgumentException ("La fila maxima es " + this.fila + " y la columna " + this.columna + ".\n");}
		int stock = posicion[fila][columna].getUnidades();
		return stock;
	}
	
	/**
	 * RETURN the available machine stock of a particular product.
	 * @param UPC Necessary to know the product
	 * @return stock available in the machine
	 */
	public int getStockTotalDelProducto(String UPC)throws IllegalArgumentException{
		    if (UPC.length()!=12){ throw new IllegalArgumentException("El UPC debe ser de 12 digitos");}
		int stock = 0;
		for (int j = 0; j<columna; j++){
			for (int i = 0; i<fila; i++){
				if (posicion[i][j].getUPC().equals(UPC)==true){
					stock += posicion[i][j].getUnidades();
				}
			}
		}
		return stock;
	}
	
	/**
	 * CHANGE the price of all the same products.
	 * @param UPC Necessary to know the wanted product.
	 * @param precio New product price.
	 * @throws IllegalArgumentException Price can not be less than 0.
	 */
	public void setPrecioProducto(String UPC, double precio) throws IllegalArgumentException{
		if (precio<0){ throw new IllegalArgumentException ("El precio no puede ser negativo");}
		for (int j = 0; j<columna; j++){
			for (int i = 0; i<fila; i++){
				if (posicion[i][j].getUPC() == UPC){
					posicion[i][j].setPrice(precio);
				}
			}
		}
	}
	
	/**
	 * RETURN the price of a particular field.
	 * @param fila wanted row.
	 * @param columna wanted column.
	 * @return precio price of a particular field
	 * @throws IllegalArgumentException
	 */
	public double getPrecioCasilla(int fila, int columna) throws IllegalArgumentException {
		if (this.fila<=fila || this.columna<=columna || fila<0 || columna < 0){ throw new IllegalArgumentException ("La fila maxima es " + this.fila + " y la columna " + this.columna + ".\n");}
		double precio = posicion[fila][columna].getPrice();
		return precio; 
	}
	
	/**
	 * SELECT the PRICE of this field.
	 * @param fila wanted row
	 * @param columna wanted column
	 * @param precio new product price
	 * @throws IllegalArgumentException row/column must be in the domain and the price<0
	 */
	public void setPrecioCasilla(int fila, int columna, double precio) throws IllegalArgumentException{
		if (this.fila<=fila || this.columna<=columna || fila<0 || columna < 0){ throw new IllegalArgumentException ("La fila maxima es " + this.fila + " y la columna " + this.columna + ".\n");}
		if (precio<0){ throw new IllegalArgumentException ("El precio no puede ser negativo");}
		posicion[fila][columna].setPrice(precio);
	}

	/**
	 * RETURN the product name of this field.
	 * @param fila wanted row
	 * @param columna wanted column
	 * @return nombre product name
	 * @throws IllegalArgumentException row/column must be in the domain.
	 */
	public String getNombreProductoCasilla(int fila, int columna) throws IllegalArgumentException{
		if (this.fila<=fila || this.columna<=columna || fila<0 || columna < 0){ throw new IllegalArgumentException ("La fila maxima es " + this.fila + " y la columna " + this.columna + ".\n");}
		String nombre = posicion[fila][columna].getNombre();
		return nombre;
	}
	/**
	 * Return TRUE or FALSE if can buy or not.
	 * @param fila is the wanted product row.
	 * @param columna is the wanted product column.
	 * @param tarjeta is the card used.
	 * @param credencial Is the tarjeta credential number
	 * @return TRUE if can buy or FALSE if not.
	 * @throws IllegalArgumentException The row and column must be in the domain.
	 */
	public boolean comprarProducto(int fila, TarjetaMonedero tarjeta, String credencial) throws IllegalArgumentException {  
		if (fila < 0 || fila >= this.fila){ throw new IllegalArgumentException ("La fila seleccionada no esta en el dominio.");	}
		//if (columna < 0 || columna > this.columna){ throw new IllegalArgumentException ("La columna seleccionada no esta en el dominio.");	}
		
		boolean compra = false;
		double saldo = tarjeta.getSaldoActual();
		for (int c = 0; c<this.columna; c++){
			if (posicion[fila][c].getPrice()<=saldo){
				if (posicion[fila][c].getUnidades()>0){
					compra = true;
					posicion[fila][c].changeUnidades(-1);
					tarjeta.descontarDelSaldo(credencial, posicion[fila][c].getPrice());
					c=this.columna;
				}
			}
		}
		return compra;
	}		
}
