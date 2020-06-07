package ar.edu.unq.TPIntegrador;

import java.time.LocalDate;

public class FechaCreacion extends FiltroPorFecha {
    
    public FechaCreacion(LocalDate condicion, IEstrategiaDeFiltrado estrategia){
    	super(condicion, estrategia);
    }

	public LocalDate datoRequeridoDeLaMuestra(Muestra muestra) {
		return muestra.getFechaDeCreacion();
	}
}
