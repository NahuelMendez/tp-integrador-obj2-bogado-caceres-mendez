package ar.edu.unq.TPIntegrador;

public class UsuarioEspecialista extends Usuario {

	public UsuarioEspecialista(String identificacion) {
		super(identificacion);
		super.setEstadoDeUsuario(new EstadoDeUsuarioExperto());;
	}

	public void actualizarCategoria() {
		
	}

}
