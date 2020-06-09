package ar.edu.unq.TPIntegrador;

public class EstadoDeMuestraVerificada extends EstadoDeMuestra {

	public EstadoDeMuestraVerificada() {
	}

	
	protected boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra) {
		return false;
	}

	
	protected boolean sePuedeVerificarMuestra(Muestra muestra){
		return false;
	}

	@Override
	protected void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception{
		throw new Exception("Nadie puede opinar sobre muestras verificadas");
	}
	
	
	
	
	
	
	
}
