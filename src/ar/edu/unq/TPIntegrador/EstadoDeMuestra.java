package ar.edu.unq.TPIntegrador;

public interface EstadoDeMuestra {

	abstract boolean sePuedeVerificarMuestra(Muestra muestra) throws Exception;
	
	abstract boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra);

	abstract void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception;
	
}
