package ar.edu.unq.TPIntegrador.filtro;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public class FiltroCompuestoOr extends FiltroCompuesto{

	@Override
	public Set<Muestra> filtrar(Set<Muestra> listaDeMuestras) {
		Set<Muestra> listaFiltrada = new HashSet<Muestra>();
		for (IFiltro filtro : this.getFiltros()) {
			listaFiltrada.addAll(filtro.filtrar(listaDeMuestras));
		}
		
		return listaFiltrada;
	}

}
