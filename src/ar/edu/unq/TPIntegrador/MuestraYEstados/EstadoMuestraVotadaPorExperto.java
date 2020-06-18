package ar.edu.unq.TPIntegrador.MuestraYEstados;

import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

public class EstadoMuestraVotadaPorExperto implements EstadoDeMuestra {

	@Override
	public String getNivelDeVerificacion(Muestra muestra) {
		return "votada";
	}
	
	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception {
		if(muestra.noContieneLaOpinionDelUsuario(usuario)) { 
			usuario.agregarOpinionAMuestraVotadaPorExperto(muestra, opinionAAgregar); 
		} 
		else {
			throw new Exception("El usuario ya ha opinado sobre la muestra");
		}
	}

/*
	@Override
	public boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra) {
		boolean retorno = false; 
		if(muestra.noContieneLaOpinionDelUsuario(usuario)){
			retorno = true;
			}
		return retorno;
		}
*/
	@Override
	public void verificarMuestra(Muestra muestra){
		if(sePuedeVerificarMuestra(muestra)) {
			muestra.cerrarOpinionesParaTodosLosUsuarios(); 
			muestra.avisarVerificacionAZonasDeCobertura(); 
		}
	}

	@Override
	public boolean sePuedeVerificarMuestra(Muestra muestra) {
		return muestra.coincidenDosExpertosEnSuOpinion();
	}

	@Override
	public String getEstadoDeMuestra(Muestra muestra) {
		return "votadaPorExperto";
	}
	
}
