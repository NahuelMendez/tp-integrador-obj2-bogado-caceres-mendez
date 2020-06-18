package ar.edu.unq.TPIntegrador.filtro;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public abstract class FiltroPorString implements IFiltro {
	
	private String condicion;
	
	public FiltroPorString(String condicion) {
		this.condicion = condicion;
	}
	
	public Set<Muestra> filtrar(Set<Muestra> muestras){
		Set<Muestra> listaFiltrada = new HashSet<Muestra>();
		for (Muestra muestra : muestras) {
			this.agregarMuestraSiPasaElFiltro(listaFiltrada, muestra);
		}
		return listaFiltrada;
	}
	
	public void agregarMuestraSiPasaElFiltro(Set<Muestra> listaFiltrada, Muestra muestra) {
		if (datoRequeridoDeLaMuestra(muestra)) {
			listaFiltrada.add(muestra);
		}
	}

	protected String getCondicion() {
		return this.condicion;
	}
	
	protected abstract boolean datoRequeridoDeLaMuestra(Muestra muestra);
	
}
