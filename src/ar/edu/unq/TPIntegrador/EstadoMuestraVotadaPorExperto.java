package ar.edu.unq.TPIntegrador;

public class EstadoMuestraVotadaPorExperto extends EstadoDeMuestra {

	public EstadoMuestraVotadaPorExperto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void comprobarSiSePuedeOpinar(Muestra muestra, Usuario usuario) throws Exception{
		if(!usuarioAptoParaVotar(usuario, muestra)) {
			//throw new Exception("El usuario no puede opinar sobre la muestra.");
		}
	}

	@Override
	protected boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra) {
		if(usuario.esUsuarioExperto() && 
			muestra.noContieneLaOpinionDelUsuario(usuario)){
				return true;
			}
			else {
				return false;
			}
		}

	@Override
	protected boolean sePuedeVerificarMuestra(Muestra muestra) {
		return muestra.coincidenDosExpertosEnSuOpinion();
	}


}
