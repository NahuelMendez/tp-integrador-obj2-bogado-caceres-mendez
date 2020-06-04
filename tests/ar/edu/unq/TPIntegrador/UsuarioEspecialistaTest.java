package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.TPIntegrador.UsuarioEspecialista;

class UsuarioEspecialistaTest {
	
	private UsuarioEspecialista usuarioE;
	@Mock private Muestra muestra;
	@Mock private Opinion opinion;
	
	@BeforeEach
	public void setUp() {
		usuarioE = new UsuarioEspecialista("36001002");
		muestra = mock(Muestra.class);
		opinion = mock(Opinion.class);
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
	
	@Test
	void test_CuandoUnUsuarioEspecialistaEmiteUnaOpinionSobreUnaMuestraLaMuestraRecibeElMensajeAgregarOpinion() {
		when(muestra.usuarioAptoParaVotar(usuarioE)).thenReturn(true);
		usuarioE.opinarSobreMuestra(muestra, opinion);
		verify(muestra).agregarOpinion(opinion, usuarioE);
	}
	
	@Test
	void test_UnUsuarioQuiereOpinarSobreUnaMuestraYaVerificadaPorLoTantoNoEmiteOpinion() {
		when(muestra.usuarioAptoParaVotar(usuarioE)).thenReturn(false);
		usuarioE.opinarSobreMuestra(muestra, opinion);
		Integer result = usuarioE.getRevisiones();
		assertEquals(0, result);
	}

}
