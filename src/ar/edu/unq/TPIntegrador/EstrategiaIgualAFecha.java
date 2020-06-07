package ar.edu.unq.TPIntegrador;

import java.util.ArrayList;

public class EstrategiaIgualAFecha implements IEstrategiaDeFiltrado{

	@Override
	public void agregarMuestraSiPasaElFiltro(FiltroPorFecha filtro, ArrayList<Muestra> listaFiltrada, Muestra muestra) {
		if(filtro.datoRequeridoDeLaMuestra(muestra).isEqual(filtro.getCondicion())) {
			listaFiltrada.add(muestra);
		}
	}

}
