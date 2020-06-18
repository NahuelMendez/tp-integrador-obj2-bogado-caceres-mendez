package ar.edu.unq.TPIntegrador.filtro;

import java.util.Set;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public interface IEstrategiaDeFiltrado {
	
	public void agregarMuestraSiPasaElFiltro(FiltroPorFecha filtro, Set<Muestra> listaFiltrada, Muestra muestra);
	
}
