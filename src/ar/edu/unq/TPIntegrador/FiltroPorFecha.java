package ar.edu.unq.TPIntegrador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class FiltroPorFecha {
	
	private LocalDate condicion;
    private IEstrategiaDeFiltrado estrategia;
    
    public abstract LocalDate datoRequeridoDeLaMuestra(Muestra muestra);
    
    public FiltroPorFecha(LocalDate condicion, IEstrategiaDeFiltrado estrategia){
    	this.condicion = condicion;
    	this.estrategia = estrategia;
    }
	
	public List<Muestra> filtrar(List<Muestra> muestras){
		ArrayList<Muestra> listaFiltrada = new ArrayList<Muestra>();
		for (Muestra muestra : muestras) {
			this.estrategia.agregarMuestraSiPasaElFiltro(this,listaFiltrada, muestra);
		}
		return listaFiltrada;
	}

	public LocalDate getCondicion() {
		return condicion;
	}

}
