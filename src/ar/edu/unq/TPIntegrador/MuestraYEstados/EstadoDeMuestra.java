package ar.edu.unq.TPIntegrador.MuestraYEstados;

import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

public interface EstadoDeMuestra {

	abstract boolean sePuedeVerificarMuestra(Muestra muestra) throws Exception;
	
	//abstract boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra);

	abstract void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception;

	abstract String getNivelDeVerificacion(Muestra muestra);

	abstract void verificarMuestra(Muestra muestra) throws Exception;

	abstract String getEstadoDeMuestra(Muestra muestra);
	
}
