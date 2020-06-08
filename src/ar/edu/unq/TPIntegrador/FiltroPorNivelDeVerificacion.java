package ar.edu.unq.TPIntegrador;

public class FiltroPorNivelDeVerificacion extends FiltroPorString{

	public FiltroPorNivelDeVerificacion(String condicion) {
		super(condicion);
	}

	@Override
	protected boolean datoRequeridoDeLaMuestra(Muestra muestra) {
		return muestra.nivelDeVerificacion().equals(super.getCondicion());
	}

}
