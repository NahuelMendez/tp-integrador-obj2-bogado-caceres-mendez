package ar.edu.unq.TPIntegrador;

import java.util.Set;

public class FiltroTipoDeInsecto extends FiltroPorString{
	
	public FiltroTipoDeInsecto(String condicion) {
		super(condicion);
	}

	protected boolean datoRequeridoDeLaMuestra(Muestra muestra) {
		return muestra.getEspecieDeVinchuca().equals(super.getCondicion());
	}

}
