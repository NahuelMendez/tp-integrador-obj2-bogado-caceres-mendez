package ar.edu.unq.TPIntegrador;

public class EstadoDeUsuarioBasico implements EstadoDeUsuario{

	@Override
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario) throws Exception {
		try {
			if(muestra.usuarioEsAptoParaVotar(usuario)) {
				muestra.agregarOpinion(opinion, usuario);
				usuario.agregarOpinionEnviada(opinion);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
