package ar.edu.unq.TPIntegrador;

public abstract class EstadoDeMuestra {
 

	public void comprobarSiSePuedeOpinar(Muestra muestra, Usuario usuario) throws Exception{
		throw new Exception();
	}

	public void comprobarSiSePuedeVerificarMuestra (Muestra muestra) throws Exception{
		throw new Exception();
	}
	
	
	protected abstract boolean sePuedeVerificarMuestra(Muestra muestra) throws Exception;
	
	protected abstract boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra);
	
}
