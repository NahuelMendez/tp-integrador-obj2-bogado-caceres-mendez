package ar.edu.unq.TPIntegrador;

public class EstadoDeMuestraVotada extends EstadoDeMuestra {

	public EstadoDeMuestraVotada() {
		
	}

	@Override
	public void comprobarSiSePuedeOpinar(Muestra muestra, Usuario usuario) throws Exception{
		if(!usuarioAptoParaVotar(usuario, muestra)) {
		throw new Exception("El usuario no puede opinar sobre la muestra.");
		}else if(usuario.esUsuarioExperto()) {
			muestra.cerrarOpinionesParaUsuariosBasicos();
		}
	}

	@Override
	public boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra) {
		return muestra.noContieneLaOpinionDelUsuario(usuario);
	}

	@Override
	protected boolean sePuedeVerificarMuestra(Muestra muestra) {
		return false;
	}
}
