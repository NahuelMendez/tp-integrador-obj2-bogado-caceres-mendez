package ar.edu.unq.TPIntegrador;

public class EstadoDeMuestraVerificada implements EstadoDeMuestra {

	public EstadoDeMuestraVerificada() {
	}

	@Override
	public String getNivelDeVerificacion(Muestra muestra) {
		return "verificada";
	}

	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception{
		throw new Exception("Nadie puede opinar sobre muestras verificadas");
	}
	
	@Override
	public boolean usuarioAptoParaVotar(Usuario usuario, Muestra muestra) {
		return false;
	}
	
	@Override
	public void verificarMuestra(Muestra muestra){ 
	}  

	@Override
	public boolean sePuedeVerificarMuestra(Muestra muestra){
		return false;
	}

}
