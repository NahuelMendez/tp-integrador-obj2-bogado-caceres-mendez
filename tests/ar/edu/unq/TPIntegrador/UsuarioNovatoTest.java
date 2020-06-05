package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

class UsuarioNovatoTest {
	
	private UsuarioNovato usuarioN;
	private LocalDate fechaActual;
	private FixtureUsuarioNovatoTest fixture;
	@Mock private AplicacionWeb sistema;
	@Mock private Opinion opinion1;
	@Mock private Opinion opinion2;
	@Mock private Muestra muestra1;
	
	@BeforeEach
	public void setUp() {
		sistema = mock(AplicacionWeb.class);
		usuarioN = new UsuarioNovato("30112113", sistema);
		fechaActual = LocalDate.now();
		opinion1 = mock(Opinion.class);
		when(opinion1.getFechaDeEmision()).thenReturn(fechaActual);
		opinion2 = mock(Opinion.class);
		when(opinion2.getFechaDeEmision()).thenReturn(fechaActual);
		muestra1 = mock(Muestra.class);
		when(muestra1.getFechaDeCreacion()).thenReturn(fechaActual);
		when(muestra1.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		fixture = new FixtureUsuarioNovatoTest();
	}

	@Test
	void test_UnNuevoUsuarioNovatoNoTieneOpinionesEnLosUltimos30Dias() {
		Integer result = usuarioN.cantidadDeEnviosEnLosUltimos30Dias();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnUsuarioEmiteUnaOpinionYSuCantidadDeOpinionesEnLosUltimos30DiasEs1() {
		usuarioN.opinarSobreMuestra(muestra1, opinion1);
		Integer result = usuarioN.cantidadDeOpinionesEnLosUltimos30Dias();
		assertEquals(1, result);
	}
	
	@Test
	void test_UnUsuarioNuevoQueNoEnvioNingunaMuestraTiene0CantidadDeEnviosEnLosUltimos30Dias() {
		Integer result = usuarioN.cantidadDeEnviosEnLosUltimos30Dias();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnUsuarioNovatoEnviaUnaMuestraYTiene1CantidadDeEnviosEnLosUltimos30Dias() {
		usuarioN.enviarMuestra(muestra1);
		Integer result = usuarioN.cantidadDeEnviosEnLosUltimos30Dias();
		assertEquals(1, result);
	}
	
	@Test
	void test_UnUsuarioNovatoQueEmitioUnaOpinion2MesesAtrasTieneCantidad0DeEnviosEnLosUltimos30Dias() {
		LocalDate fechaAnterior = LocalDate.now().minusMonths(2);
		Opinion opinion2MesesAtras = mock(Opinion.class);
		when(opinion2MesesAtras.getFechaDeEmision()).thenReturn(fechaAnterior);
		usuarioN.opinarSobreMuestra(muestra1, opinion2MesesAtras);
		Integer result = usuarioN.cantidadDeOpinionesEnLosUltimos30Dias();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnUsuarioNovatoActualizaSuCategoriaDeUsuarioBasicoLuegoDeCumplirLosRequisitosParaSubirAExperto() {
		UsuarioNovato usuarioNuevoExperto = this.fixture.nuevoUsuarioListoParaActualizarCategoria();
		String result = usuarioNuevoExperto.getEstadoDeUsuario().getClass().getSimpleName();
		System.out.println(usuarioNuevoExperto.cantidadDeEnviosEnLosUltimos30Dias());
		System.out.println(usuarioNuevoExperto.cantidadDeOpinionesEnLosUltimos30Dias());
		assertEquals("EstadoDeUsuarioExperto", result);
	}

}
