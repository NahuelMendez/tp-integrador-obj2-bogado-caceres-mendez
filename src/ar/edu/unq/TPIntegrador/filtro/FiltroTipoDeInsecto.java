package ar.edu.unq.TPIntegrador.filtro;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public class FiltroTipoDeInsecto extends FiltroPorString{
	
	public FiltroTipoDeInsecto(String condicion) {
		super(condicion);
	}

	protected boolean datoRequeridoDeLaMuestra(Muestra muestra) {
		return muestra.getEspecieDeVinchuca().equals(super.getCondicion());
	}

}
