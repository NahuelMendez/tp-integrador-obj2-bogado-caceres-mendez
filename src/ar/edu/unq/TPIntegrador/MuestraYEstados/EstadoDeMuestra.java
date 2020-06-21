package ar.edu.unq.TPIntegrador.MuestraYEstados;

import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

public interface EstadoDeMuestra {

	abstract boolean sePuedeVerificarMuestra(Muestra muestra) throws Exception;

	abstract void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception;

	abstract String getNivelDeVerificacion(Muestra muestra);

	abstract String getEstadoDeMuestra(Muestra muestra);
	
}
