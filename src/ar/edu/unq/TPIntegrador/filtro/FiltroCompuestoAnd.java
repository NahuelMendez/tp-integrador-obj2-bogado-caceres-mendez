package ar.edu.unq.TPIntegrador.filtro;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public class FiltroCompuestoAnd extends FiltroCompuesto {

	@Override
	public Set<Muestra> filtrar(Set<Muestra> listaDeMuestras) {
		Set<Muestra> listaFiltrada = new HashSet<Muestra>();
		listaFiltrada.addAll(listaDeMuestras);
		for (IFiltro filtro : this.getFiltros()) {
			listaFiltrada.retainAll(filtro.filtrar(listaDeMuestras));
		}
		
		return listaFiltrada;
	}

}
