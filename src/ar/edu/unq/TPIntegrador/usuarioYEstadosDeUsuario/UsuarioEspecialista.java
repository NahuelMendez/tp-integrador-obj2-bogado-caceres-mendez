package ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario;

import ar.edu.unq.TPIntegrador.AplicacionWeb;

public class UsuarioEspecialista extends Usuario {

	public UsuarioEspecialista(String identificacion, AplicacionWeb aplicacionWeb) {
		super(identificacion, aplicacionWeb);
		super.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
	}

	public void actualizarCategoria() {
		
	}

}
