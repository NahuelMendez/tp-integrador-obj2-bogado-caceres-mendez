package ar.edu.unq.TPIntegrador;

public class EstadoMuestraVotadaPorExperto implements EstadoDeMuestra {

	public EstadoMuestraVotadaPorExperto() {
	}

	@Override
	public String getNivelDeVerificacion(Muestra muestra) {
		return "votada";
	}
	
	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception {
		if(usuarioAptoParaVotar(usuario, muestra)) {
			muestra.agregarOpinionDeUsuario(opinionAAgregar, usuario);
			muestra.verificarMuestra();
		}
	}

	@Override
	public boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra) {
		boolean retorno = false;
		if(usuario.esUsuarioExperto() &&
			muestra.noContieneAlUsuario(usuario)){
			retorno = true;
			}
		return retorno;
		}

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
