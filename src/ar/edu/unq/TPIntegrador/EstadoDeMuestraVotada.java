package ar.edu.unq.TPIntegrador;

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
		if((usuarioAptoParaVotar(usuario, muestra)) && (!usuario.esUsuarioExperto())) {
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
}
