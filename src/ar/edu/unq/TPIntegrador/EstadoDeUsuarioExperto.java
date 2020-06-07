package ar.edu.unq.TPIntegrador;

public class EstadoDeUsuarioExperto implements EstadoDeUsuario{

	@Override
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario) throws Exception {
		if (muestra.usuarioEsAptoParaVotar(usuario)) {
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
	public void actualizarCateogiriaDeUsuarioNovato(UsuarioNovato usuarioNovato) {
		if (!usuarioNovato.cumpleConRevisionesNecesarias()
				&& !usuarioNovato.cumpleConEnviosNecesarios()) {
			usuarioNovato.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
		}
	}
}
