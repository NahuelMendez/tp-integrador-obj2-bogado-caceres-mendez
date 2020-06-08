package ar.edu.unq.TPIntegrador;

import java.util.Set;

public interface IEstrategiaDeFiltrado {
	
	public void agregarMuestraSiPasaElFiltro(FiltroPorFecha filtro, Set<Muestra> listaFiltrada, Muestra muestra);
	
}
