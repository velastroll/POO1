package uva.poo.entrega1;

public class Producto {

	private String nombre;
	private String UPC;
	private double precio = 0;
	private int unidades = 0;

	/**
	 * Implementation of a new product.
	 * 
	 * @param code:
	 *            Necessary to calculate UPC code.
	 * @param nombre:
	 *            product name, can not be changed.
	 * @throws IllegalArgumentException
	 *             The code must be 11 digits.
	 */
	public Producto(String code, String nombre) throws IllegalArgumentException {
		if (code.length() != 11) {
			throw new IllegalArgumentException("El codigo debe ser de 11 digitos.");
		}
		for (int c = 0; c < 11; c++) {
			if (code.charAt(c) < 48 || code.charAt(c) > 57) {
				throw new IllegalArgumentException("El codigo debe ser de 11 digitos.");
			}
		}
		this.UPC = setUPC(code);
		this.nombre = nombre;
	}

	/**
	 * 
	 * @param code:
	 *            Necessary to make UPC code
	 * @param nombre:
	 *            Product name.
	 * @param precio:
	 *            Product price.
	 * @param unidades:
	 *            Available stock.
	 * @throws IllegalArgumentException
	 *             non valid data.
	 */
	public Producto(String code, String nombre, double precio, int unidades) throws IllegalArgumentException {

		if (code.length() != 11) {
			throw new IllegalArgumentException("El codigo debe ser de 11 digitos.");
		}
		if (precio < 0) {
			throw new IllegalArgumentException("El precio no puede ser negativo.");
		}
		if (unidades < 0) {
			throw new IllegalArgumentException("Las unidades no pueden ser negativas.");
		}

		this.UPC = setUPC(code);
		this.nombre = nombre;
		this.precio = precio;
		this.unidades = unidades;
	}

	/**
	 * Function that creates UPC code.
	 * 
	 * @param code
	 *            Necessary to calculate UPC code.
	 * @return UPC code.
	 * @throws IllegalArgumentException
	 *             Code must be 11 digits and numbers.
	 * 		 
	 * 				
	 */
	private String setUPC(String code) throws IllegalArgumentException {

		if (code.length() != 11) {
			throw new IllegalArgumentException("El codigo debe ser de 11 digitos.");
		}
		for(int i=0;i<11;i++){
			if(code.charAt(i)<48 || code.charAt(i)>57){
				throw new IllegalArgumentException("El codigo tiene que ser numeros.");
			}
		}

		String strUPC = code;
		int last_digito = ((strUPC.charAt(0) - 48) * 3) + (strUPC.charAt(1) - 48) + ((strUPC.charAt(2) - 48) * 3)
				+ (strUPC.charAt(3) - 48) + ((strUPC.charAt(4) - 48) * 3) + (strUPC.charAt(5) - 48)
				+ ((strUPC.charAt(6) - 48) * 3) + (strUPC.charAt(7) - 48) + ((strUPC.charAt(8) - 48) * 3)
				+ (strUPC.charAt(9) - 48) + ((strUPC.charAt(10) - 48) * 3);
		int m = ((last_digito / 10) + 1) * 10;
		last_digito = m - last_digito;
		strUPC = strUPC + Integer.toString(last_digito);
		return strUPC;
	}

	/**
	 * 
	 * @return the UPC product code
	 */
	public String getUPC() {
		return this.UPC;
	}

	/**
	 * Set the available stock in a determinate field.
	 * 
	 * @param unidades:
	 *            new product units.
	 * @throws IllegalArgumentException
	 *             The units must be positives.
	 */
	public void setUnidades(int unidades) throws IllegalArgumentException {
		if (unidades < 0) {
			throw new IllegalArgumentException("Las unidades no pueden ser negativas.");
		}
		this.unidades = unidades;
	}

	/**
	 * 
	 * @return the available stock in a determinate field
	 */
	public int getUnidades() {
		return this.unidades;
	}

	/**
	 * @param change
	 *            is the number of units changed.
	 * @throws IllegalArgumentException
	 *             Units + the units changed can not be less than zero.
	 */
	public void changeUnidades(int change) throws IllegalArgumentException {
		if (change + unidades < 0) {
			throw new IllegalArgumentException("No puedes quitar mas de las que hay.");
		}
		unidades += change;
	}

	/**
	 * 
	 * Change product price
	 * 
	 * @param price
	 *            new product value
	 * @throws IllegalArgumentException
	 *             Price must be positive.
	 */
	public void setPrice(double price) throws IllegalArgumentException {
		if (price < 0) {
			throw new IllegalArgumentException("El precio no puede ser negativo.");
		}
		precio = price;
	}

	/**
	 * 
	 * @return Product price
	 */
	public double getPrice() {
		return this.precio;
	}

	/**
	 * 
	 * @return Product name in a String.
	 */
	public String getNombre() {
		return this.nombre;
	}

}
