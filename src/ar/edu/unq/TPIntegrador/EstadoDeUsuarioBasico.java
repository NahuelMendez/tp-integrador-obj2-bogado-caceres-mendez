package ar.edu.unq.TPIntegrador;

public class EstadoDeUsuarioBasico implements EstadoDeUsuario{

	@Override
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario) {
		if(muestra.usuarioAptoParaVotar(usuario)) {
			muestra.agregarOpinion(opinion, usuario);
			usuario.agregarOpinionEnviada(opinion);
		}
	}

	@Override
	public Boolean esUsuarioBasico() {
		return true;
	}

	@Override
	public Boolean esUsuarioExperto() {
		return false;
	}

	@Override
	public void actualizarCateogiriaDeUsuarioNovato(UsuarioNovato usuarioNovato) {
		if (usuarioNovato.cumpleConRevisionesNecesarias()
				&& usuarioNovato.cumpleConEnviosNecesarios()) {
			usuarioNovato.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		}
	}

}
