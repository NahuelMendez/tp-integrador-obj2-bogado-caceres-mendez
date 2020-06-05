package ar.edu.unq.TPIntegrador;

public class EstadoDeUsuarioExperto extends EstadoDeUsuario{

	@Override
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario) {
		if (muestra.usuarioAptoParaVotar(usuario)) {
			muestra.agregarOpinion(opinion, usuario);
			usuario.agregarOpinionEnviada(opinion);
		}
	}

	@Override
	public Boolean esUsuarioBasico() {
		return false;
	}

	@Override
	public Boolean esUsuarioExperto() {
		return true;
	}

	@Override
	protected void actualizarCateogiriaDeUsuarioNovato(UsuarioNovato usuarioNovato) {
		if (!usuarioNovato.cumpleConRevisionesNecesarias()
				&& !usuarioNovato.cumpleConEnviosNecesarios()) {
			usuarioNovato.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
		}
	}
}
