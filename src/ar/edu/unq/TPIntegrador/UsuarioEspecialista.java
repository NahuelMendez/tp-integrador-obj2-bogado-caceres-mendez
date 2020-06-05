package ar.edu.unq.TPIntegrador;

public class UsuarioEspecialista extends Usuario {

	public UsuarioEspecialista(String identificacion, AplicacionWeb aplicacionWeb) {
		super(identificacion, aplicacionWeb);
		super.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
	}

	public void actualizarCategoria() {
		
	}

}
