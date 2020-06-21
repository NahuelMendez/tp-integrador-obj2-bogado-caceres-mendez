package ar.edu.unq.TPIntegrador;

import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.EstadoDeUsuario;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.EstadoDeUsuarioExperto;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

public class FixtureUsuarioNovatoParaBajarDeCategoriaTest extends Usuario{
	
	/* 
	 * La creacion de esta clase fue unicamente con motivo de test
	 * plantea un escenario en el que un usuario experto baje de categoria a basico
	 * por no cumplir con los requerimientos
	 * los metodos quedaron en package para usarlos solo en los test
	*/

	FixtureUsuarioNovatoParaBajarDeCategoriaTest(String identificacion, AplicacionWeb aplicacionWeb) {
		super(identificacion, aplicacionWeb);
		this.setearEstadoDeUsuarioParaTest(new EstadoDeUsuarioExperto());
	}
	
	void setearEstadoDeUsuarioParaTest(EstadoDeUsuario estadoDeUsuario) {
		this.setEstadoDeUsuario(estadoDeUsuario);
	}
	
}
