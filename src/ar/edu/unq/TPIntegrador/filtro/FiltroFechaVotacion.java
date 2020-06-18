package ar.edu.unq.TPIntegrador.filtro;

import java.time.LocalDate;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public class FiltroFechaVotacion extends FiltroPorFecha{

	public FiltroFechaVotacion(LocalDate condicion, IEstrategiaDeFiltrado estrategia) {
		super(condicion, estrategia);
	}

	@Override
	public LocalDate datoRequeridoDeLaMuestra(Muestra muestra) {
		return muestra.getFechaUltimaVotacion();
	}

}
