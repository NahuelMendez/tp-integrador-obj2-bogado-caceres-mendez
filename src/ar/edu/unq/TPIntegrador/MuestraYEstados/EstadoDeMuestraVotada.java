package ar.edu.unq.TPIntegrador.MuestraYEstados;

import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

public class EstadoDeMuestraVotada implements EstadoDeMuestra {

	@Override
	public String getNivelDeVerificacion(Muestra muestra) {
		return "votada";
	}

	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception {
		if(!muestra.contieneAlUsuario(usuario)) {
			usuario.agregarOpinionAMuestraVotada(muestra, opinionAAgregar);
		}
		else {
			throw new Exception("El usuario ya ha opinado sobre la muestra"); 
		}
	}

	@Override
	public void actualizarEstado(Muestra muestra) {
		muestra.historialDeOpiniones.clear(); 
		muestra.setEstadoDeMuestra(new EstadoMuestraVotadaPorExperto());
		
	}
}
