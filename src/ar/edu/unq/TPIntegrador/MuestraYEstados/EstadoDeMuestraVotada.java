package ar.edu.unq.TPIntegrador.MuestraYEstados;

import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

public class EstadoDeMuestraVotada implements EstadoDeMuestra {

	public EstadoDeMuestraVotada() {
		
	}
	
	@Override
	public boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra) {
		return muestra.noContieneAlUsuario(usuario);
	}

	@Override
	public boolean sePuedeVerificarMuestra(Muestra muestra) {
		return false;
	}

	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception {
		if(!usuarioAptoParaVotar(usuario, muestra)) {
			throw new Exception("El usuario no puede opinar sobre la muestra.");
		}
		else if(!usuario.esUsuarioExperto()) {
			muestra.agregarOpinionDeUsuario(opinionAAgregar, usuario);
		}else{
			muestra.cerrarOpinionesParaUsuariosBasicos();
			muestra.agregarOpinionDeUsuario(opinionAAgregar, usuario);	
		}
	}

	@Override
	public String getNivelDeVerificacion(Muestra muestra) {
		return "votada";
	}

	@Override
	public void verificarMuestra(Muestra muestra) {}

	@Override
	public String getEstadoDeMuestra(Muestra muestra) {
		return this.getNivelDeVerificacion(muestra);
	}
}
