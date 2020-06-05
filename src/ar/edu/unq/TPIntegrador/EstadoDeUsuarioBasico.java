package ar.edu.unq.TPIntegrador;

public class EstadoDeUsuarioBasico extends EstadoDeUsuario{

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
	protected void actualizarCateogiriaDeUsuarioNovato(UsuarioNovato usuarioNovato) {
		if (usuarioNovato.cumpleConRevisionesNecesarias()
				&& usuarioNovato.cumpleConEnviosNecesarios()) {
			usuarioNovato.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		}
	}

}
