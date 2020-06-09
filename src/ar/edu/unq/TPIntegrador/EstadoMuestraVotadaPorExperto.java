package ar.edu.unq.TPIntegrador;

public class EstadoMuestraVotadaPorExperto implements EstadoDeMuestra {

	public EstadoMuestraVotadaPorExperto() {
	}

	@Override
	public boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra) {
		if(usuario.esUsuarioExperto() &&
			muestra.noContieneLaOpinionDelUsuario(usuario)){
				return true;
			}
			else {
				return false;
			}
		}

	@Override
	public boolean sePuedeVerificarMuestra(Muestra muestra) {
		return muestra.coincidenDosExpertosEnSuOpinion();
	}

	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception {
		if(usuarioAptoParaVotar(usuario, muestra)) {
			muestra.add(opinionAAgregar, usuario);
			muestra.verificarMuestra();
		}else {
			throw new Exception("El usuario no puede opinar sobre la muestra.");
		}
	}
}
