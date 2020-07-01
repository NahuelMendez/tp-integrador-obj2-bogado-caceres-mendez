package ar.edu.unq.TPIntegrador;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public interface IObserver {

	public void actualizarNuevaMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra);

	public void actualizarNuevaVerificacion(ZonaDeCobertura zonaDeCobertura, Muestra muestra);

}
