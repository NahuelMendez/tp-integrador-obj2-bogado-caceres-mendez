package ar.edu.unq.TPIntegrador;

public abstract class EstadoDeUsuario {
	
	public abstract void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario);
	
	public abstract Boolean esUsuarioBasico();
	
	public abstract Boolean esUsuarioExperto();

	protected abstract void actualizarCateogiriaDeUsuarioNovato(UsuarioNovato usuarioNovato);

}
