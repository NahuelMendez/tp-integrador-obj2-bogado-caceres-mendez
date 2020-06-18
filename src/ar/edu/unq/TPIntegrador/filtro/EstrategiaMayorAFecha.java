package ar.edu.unq.TPIntegrador.filtro;

import java.util.Set;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public class EstrategiaMayorAFecha implements IEstrategiaDeFiltrado {

	@Override
	public void agregarMuestraSiPasaElFiltro(FiltroPorFecha filtro, Set<Muestra> listaFiltrada, Muestra muestra) {
		if(filtro.datoRequeridoDeLaMuestra(muestra).isAfter(filtro.getCondicion())) {
			listaFiltrada.add(muestra);
		}
	}

}
