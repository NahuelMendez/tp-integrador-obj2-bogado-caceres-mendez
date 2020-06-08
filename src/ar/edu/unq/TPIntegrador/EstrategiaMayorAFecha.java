package ar.edu.unq.TPIntegrador;

import java.util.Set;

public class EstrategiaMayorAFecha implements IEstrategiaDeFiltrado {

	@Override
	public void agregarMuestraSiPasaElFiltro(FiltroPorFecha filtro, Set<Muestra> listaFiltrada, Muestra muestra) {
		if(filtro.datoRequeridoDeLaMuestra(muestra).isAfter(filtro.getCondicion())) {
			listaFiltrada.add(muestra);
		}
	}

}
