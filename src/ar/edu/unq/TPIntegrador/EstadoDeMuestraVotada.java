package ar.edu.unq.TPIntegrador;

public class EstadoDeMuestraVotada extends EstadoDeMuestra {

	public EstadoDeMuestraVotada() {
		
	}
	
	@Override
	public boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra) {
		return muestra.noContieneLaOpinionDelUsuario(usuario);
	}

	@Override
	protected boolean sePuedeVerificarMuestra(Muestra muestra) {
		return false;
	}

	@Override
	protected void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception {
		if((usuarioAptoParaVotar(usuario, muestra)) && (!usuario.esUsuarioExperto())) {
			muestra.add(opinionAAgregar, usuario);
		}else{
			muestra.add(opinionAAgregar, usuario);
			muestra.cerrarOpinionesParaUsuariosBasicos();
		}
	}
}
