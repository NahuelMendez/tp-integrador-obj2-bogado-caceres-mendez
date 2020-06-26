package ar.edu.unq.TPIntegrador.MuestraYEstados;

import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

public class EstadoDeMuestraVerificada implements EstadoDeMuestra {

	@Override
	public String getNivelDeVerificacion(Muestra muestra) {
		return "verificada";
	}

	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinionAAgregar, Usuario usuario) throws Exception{
		throw new Exception("Nadie puede opinar sobre muestras verificadas"); 
	}

	@Override
	public void actualizarEstado(Muestra muestra) throws Exception{
		throw new Exception("La muestra ya ha sido verificada");
	}

}
