package ar.edu.unq.TPIntegrador.filtro;

import java.time.LocalDate;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public class FiltroFechaCreacion extends FiltroPorFecha {
    
    public FiltroFechaCreacion(LocalDate condicion, IEstrategiaDeFiltrado estrategia){
    	super(condicion, estrategia);
    }

	public LocalDate datoRequeridoDeLaMuestra(Muestra muestra) {
		return muestra.getFechaDeCreacion();
	}
}
