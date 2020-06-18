package ar.edu.unq.TPIntegrador.filtro;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public class FiltroPorNivelDeVerificacion extends FiltroPorString{

	public FiltroPorNivelDeVerificacion(String condicion) {
		super(condicion);
	}

	@Override
	protected boolean datoRequeridoDeLaMuestra(Muestra muestra) {
		return muestra.nivelDeVerificacion().equals(this.getCondicion());
	}

}
