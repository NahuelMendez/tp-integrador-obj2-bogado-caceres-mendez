package ar.edu.unq.TPIntegrador;

import java.time.LocalDate;

public class FechaVotacion extends FiltroPorFecha{

	public FechaVotacion(LocalDate condicion, IEstrategiaDeFiltrado estrategia) {
		super(condicion, estrategia);
	}

	@Override
	public LocalDate datoRequeridoDeLaMuestra(Muestra muestra) {
		return muestra.getFechaUltimaVotacion();
	}

}
