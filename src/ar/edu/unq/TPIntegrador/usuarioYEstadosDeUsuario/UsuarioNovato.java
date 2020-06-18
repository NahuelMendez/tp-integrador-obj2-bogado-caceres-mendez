package ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario;

import ar.edu.unq.TPIntegrador.AplicacionWeb;

public class UsuarioNovato extends Usuario {
	
	public UsuarioNovato(String identificacion, AplicacionWeb aplicacionWeb) {
		super(identificacion, aplicacionWeb);
		super.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
	}
	
	public void actualizarCategoria() {
		super.getEstadoDeUsuario().actualizarCateogiriaDeUsuarioNovato(this);
	}
	
	protected Boolean cumpleConRevisionesNecesarias() {
		return this.cantidadDeOpinionesEnLosUltimos30Dias() >= 20;
	}
	

	protected Boolean cumpleConEnviosNecesarios() {
		return this.cantidadDeEnviosEnLosUltimos30Dias() >= 10;
	}

}
