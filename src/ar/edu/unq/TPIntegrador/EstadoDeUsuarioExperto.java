package ar.edu.unq.TPIntegrador;

public class EstadoDeUsuarioExperto extends EstadoDeUsuario{

	@Override
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario) {
		if (muestra.usuarioAptoParaVotar(usuario)) {
			muestra.agregarOpinion(opinion, usuario);
			usuario.agregarOpinionEnviada(opinion);
		}
		
	}

}
