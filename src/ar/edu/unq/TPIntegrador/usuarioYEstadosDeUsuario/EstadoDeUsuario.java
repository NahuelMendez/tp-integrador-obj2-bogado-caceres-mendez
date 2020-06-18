package ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario;

import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public interface EstadoDeUsuario {
	
	public abstract void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario) throws Exception;
	
	public abstract Boolean esUsuarioBasico();
	
	public abstract Boolean esUsuarioExperto();

	public abstract void actualizarCateogiriaDeUsuarioNovato(UsuarioNovato usuarioNovato);

}
