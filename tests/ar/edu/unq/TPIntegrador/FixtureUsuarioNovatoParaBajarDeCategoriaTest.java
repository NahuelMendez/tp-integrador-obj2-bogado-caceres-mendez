package ar.edu.unq.TPIntegrador;

public class FixtureUsuarioNovatoParaBajarDeCategoriaTest extends UsuarioNovato{

	public FixtureUsuarioNovatoParaBajarDeCategoriaTest(String identificacion, AplicacionWeb aplicacionWeb) {
		super(identificacion, aplicacionWeb);
		this.setearEstadoDeUsuarioParaTest(new EstadoDeUsuarioExperto());
	}
	
	public void setearEstadoDeUsuarioParaTest(EstadoDeUsuario estadoDeUsuario) {
		this.setEstadoDeUsuario(estadoDeUsuario);
	}
	
	

}
