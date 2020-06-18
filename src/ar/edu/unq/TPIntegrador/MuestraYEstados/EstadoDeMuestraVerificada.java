package ar.edu.unq.TPIntegrador.MuestraYEstados;

import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

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
	public void verificarMuestra(Muestra muestra){}  

	@Override
	public boolean sePuedeVerificarMuestra(Muestra muestra){
		return false;
	}

	@Override
	public String getEstadoDeMuestra(Muestra muestra) {
		return this.getNivelDeVerificacion(muestra);
	}

}
