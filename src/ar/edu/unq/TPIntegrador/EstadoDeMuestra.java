package ar.edu.unq.TPIntegrador;

public abstract class EstadoDeMuestra {
 
	public abstract void comprobarSiSePuedeOpinar(Muestra muestra, Usuario usuario) throws Exception;

	protected abstract boolean sePuedeVerificarMuestra(Muestra muestra) throws Exception;
	
	protected abstract boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra);
	
}
