package ar.edu.unq.TPIntegrador;

import java.util.ArrayList;

public interface IEstrategiaDeFiltrado {
	
	public void agregarMuestraSiPasaElFiltro(FiltroPorFecha filtro, ArrayList<Muestra> listaFiltrada, Muestra muestra);
	
}
