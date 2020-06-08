package ar.edu.unq.TPIntegrador;

import java.util.Set;

public class EstrategiaMenorAFecha implements IEstrategiaDeFiltrado {

	@Override
	public void agregarMuestraSiPasaElFiltro(FiltroPorFecha filtro, Set<Muestra> listaFiltrada, Muestra muestra) {
		if(filtro.datoRequeridoDeLaMuestra(muestra).isBefore(filtro.getCondicion())) {
			listaFiltrada.add(muestra);
		}
	}

}
