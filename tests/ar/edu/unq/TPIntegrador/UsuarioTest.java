package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.TPIntegrador.Muestra;
import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.Usuario;

import static org.mockito.Mockito.*;

class UsuarioTest {
	
	private Usuario usuario;
	@Mock private Muestra muestra;
	@Mock private Opinion opinion;
	
	@BeforeEach
	public void setUp() {
		usuario = new UsuarioNovato("30120240");
		muestra = mock(Muestra.class);
		opinion = mock(Opinion.class);
	}

	@Test
	void test_UnNuevoUsuarioTieneIdentificacion30120240() {
		String result = usuario.getIdentificacion();
		assertEquals("30120240", result);
	}
	
	@Test
	void test_UnUsuarioNuevoQueNoEmitioOpinionesTiene0CantidadDeOpiniones() {
		Integer result = usuario.getRevisiones();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnUsuarioEmiteUnaOpinionYTiene1CantidadDeOpiniones() {
		when(muestra.usuarioAptoParaVotar(usuario)).thenReturn(true);
		usuario.opinarSobreMuestra(muestra, opinion);
		Integer result = usuario.getRevisiones();
		assertEquals(1, result);
	}
	
	@Test
	void test_UnUsuarioQueNoEnvioNingunaMuestraTiene0CantidadDeEnvios() {
		Integer result = usuario.getEnvios();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnUsuarioQueEnviaUnaMuestraTiene1CantidadDeEnvios() {
		usuario.enviarMuestra(muestra);
		Integer result = usuario.getEnvios();
		assertEquals(1, result);
	}
	
	

}
