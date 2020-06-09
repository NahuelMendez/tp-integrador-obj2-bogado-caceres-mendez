package ar.edu.unq.TPIntegrador;

public class EstadoDeMuestraVerificada extends EstadoDeMuestra {

	public EstadoDeMuestraVerificada() {
	}

	@Override
	public void comprobarSiSePuedeOpinar(Muestra muestra, Usuario usuario) throws Exception{
		throw new Exception("Nadie puede opinar sobre muestras verificadas");
	}

	@Override
	protected boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra) {
		return false;
	}

	@Override
	protected boolean sePuedeVerificarMuestra(Muestra muestra){
		return false;
	}
}
