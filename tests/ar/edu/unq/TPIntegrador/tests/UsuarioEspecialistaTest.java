package ar.edu.unq.TPIntegrador.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.TPIntegrador.UsuarioEspecialista;

class UsuarioEspecialistaTest {
	
	private UsuarioEspecialista usuarioE;
	
	@BeforeEach
	public void setUp() {
		usuarioE = new UsuarioEspecialista("36001002");
	}
	
	@Test
	void test_UnUsuarioEspecialistaNuevoTieneIdentificacion36001002() {
		String result = usuarioE.getIdentificacion();
		assertEquals("36001002", result);
	}

	@Test
	void test_UnUsuarioEspecialistaAlQuererActualizarSuCategoriaNoHaceNada() {
		String categoria = usuarioE.getClass().getSimpleName();
		usuarioE.actualizarCategoria();
		String result = usuarioE.getClass().getSimpleName();
		assertEquals(categoria, result);
	}
	
	@Test
	void test_ElEstadoDeUsuarioDeUnUsuarioEspecialistaEsEstadoExperto() {
		String result = usuarioE.getEstadoDeUsuario().getClass().getSimpleName();
		assertEquals("EstadoDeUsuarioExperto", result);
	}

}
