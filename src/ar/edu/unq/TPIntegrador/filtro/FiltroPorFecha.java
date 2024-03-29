package ar.edu.unq.TPIntegrador.filtro;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public abstract class FiltroPorFecha implements IFiltro {
	
	private LocalDate fechaDeFiltro;
    private IEstrategiaDeFiltrado estrategia;
    
    public abstract LocalDate datoRequeridoDeLaMuestra(Muestra muestra);
    
    public FiltroPorFecha(LocalDate condicion, IEstrategiaDeFiltrado estrategia){
    	this.fechaDeFiltro = condicion;
    	this.estrategia = estrategia;
    }
	
	public Set<Muestra> filtrar(Set<Muestra> muestras){
		Set<Muestra> listaFiltrada = new HashSet<Muestra>();
		for (Muestra muestra : muestras) {
			this.estrategia.agregarMuestraSiPasaElFiltro(this, listaFiltrada, muestra);
		}
		return listaFiltrada;
	}

	public LocalDate getCondicion() {
		return fechaDeFiltro;
	}

}
