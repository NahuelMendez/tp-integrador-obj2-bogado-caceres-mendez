package ar.edu.unq.TPIntegrador.MuestraYEstados;

import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

public interface EstadoDeMuestra {

	abstract void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception;

	abstract String getNivelDeVerificacion(Muestra muestra);

	abstract void actualizarEstado(Muestra muestra) throws Exception;
	
}
