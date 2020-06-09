package ar.edu.unq.TPIntegrador;

public abstract class EstadoDeMuestra {


	protected abstract boolean sePuedeVerificarMuestra(Muestra muestra) throws Exception;
	
	protected abstract boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra);

	protected abstract void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception;
	
}
