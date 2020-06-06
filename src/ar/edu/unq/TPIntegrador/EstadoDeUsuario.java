package ar.edu.unq.TPIntegrador;

public interface EstadoDeUsuario {
	
	public abstract void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario);
	
	public abstract Boolean esUsuarioBasico();
	
	public abstract Boolean esUsuarioExperto();

	public abstract void actualizarCateogiriaDeUsuarioNovato(UsuarioNovato usuarioNovato);

}
