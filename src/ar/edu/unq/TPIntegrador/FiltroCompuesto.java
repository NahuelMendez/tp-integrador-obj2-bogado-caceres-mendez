package ar.edu.unq.TPIntegrador;

import java.util.HashSet;
import java.util.Set;

public abstract class FiltroCompuesto implements IFiltro{

	private Set<IFiltro> filtros;

	public FiltroCompuesto() {
		super();
		filtros = new HashSet<IFiltro>();
	}

	public Set<IFiltro> getFiltros() {
		
		return filtros;
	}

	public void agregarFiltro(IFiltro filtroSimple) {
		filtros.add(filtroSimple);
	}

	public void removerFiltro(IFiltro filtroSimple1) {
		filtros.remove(filtroSimple1);
		
	}

	public abstract Set<Muestra> filtrar(Set<Muestra> listaDeMuestras);

}
