package ar.edu.unq.TPIntegrador;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public interface IObserver {
	
	//public void actualizar(ZonaDeCobertura zonaDeCobertura, Muestra muestra, String aviso);

	public void actualizarNuevaMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra);

	public void actualizarNuevaVerificacion(ZonaDeCobertura zonaDeCobertura, Muestra muestra);

}
