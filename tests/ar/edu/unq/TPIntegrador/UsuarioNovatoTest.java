package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.UsuarioNovato;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

class UsuarioNovatoTest {
	
	private UsuarioNovato usuarioN;
	private LocalDate fechaActual;
	private FixtureUsuarioNovatoTest fixture;
	private FixtureUsuarioNovatoParaBajarDeCategoriaTest fixture2;
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
		fixture2 = new FixtureUsuarioNovatoParaBajarDeCategoriaTest("23444555", sistema);
	}

	@Test
	void test_UnNuevoUsuarioNovatoNoTieneOpinionesEnLosUltimos30Dias() {
		Integer result = usuarioN.cantidadDeEnviosEnLosUltimos30Dias();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnUsuarioEmiteUnaOpinionYSuCantidadDeOpinionesEnLosUltimos30DiasEs1() throws Exception {
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
	void test_UnUsuarioNovatoQueEmitioUnaOpinion2MesesAtrasTieneCantidad0DeEnviosEnLosUltimos30Dias() throws Exception {
		LocalDate fechaAnterior = LocalDate.now().minusMonths(2);
		Opinion opinion2MesesAtras = mock(Opinion.class);
		when(opinion2MesesAtras.getFechaDeEmision()).thenReturn(fechaAnterior);
		usuarioN.opinarSobreMuestra(muestra1, opinion2MesesAtras);
		Integer result = usuarioN.cantidadDeOpinionesEnLosUltimos30Dias();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnUsuarioNovatoNoEsExpertoAlSerCreado() {
		Boolean result = usuarioN.esUsuarioExperto();
		assertFalse(result);
	}
	
	@Test
	void test_UnUsuarioNovatoSinCondicionesParaSerExpertoTieneEstadoDeUsuarioBasicoLuegoDeActualizarLaCategoria() throws Exception {
		usuarioN.actualizarCategoria();
		assertTrue(usuarioN.esUsuarioBasico());
	}
	
	@Test
	void test_UnUsuarioNovatoConCondicionesParaSerExpertoTieneEstadoDeUsuarioBasico() throws Exception {
		UsuarioNovato usuarioBasico = this.fixture.nuevoUsuarioListoParaActualizarCategoria();
		assertTrue(usuarioBasico.esUsuarioBasico());
	}
	
	@Test
	void test_UnUsuarioNovatoActualizaSuCategoriaDeUsuarioBasicoLuegoDeCumplirLosRequisitosParaSubirAExperto() throws Exception {
		UsuarioNovato usuarioNuevoExperto = this.fixture.nuevoUsuarioListoParaActualizarCategoria();
		usuarioNuevoExperto.actualizarCategoria();
		assertTrue(usuarioNuevoExperto.esUsuarioExperto());
	}
	
	@Test
	void test_UnUsuarioNovatoBasicoTieneRevisionesNecesariasPeroEnviosInsuficientesYNoCambiaDeCategoria() throws Exception {
		UsuarioNovato usuarioBasico = this.fixture.nuevoUsuarioNovatoQueCumpleRevisionesPeroNoEnvios();
		assertTrue(usuarioBasico.esUsuarioBasico());
	}
	
	@Test
	void test_UnUsuarioNovatoBasicoTieneEnviosNecesariosPeroRevisionesInsuficientesYNoCambiaDeCategoria() throws Exception {
		UsuarioNovato usuarioBasico = this.fixture.nuevoUsuarioNovatoQueCumpleConEnviosPeroNoConRevisiones();
		assertTrue(usuarioBasico.esUsuarioBasico());
	}
	
	@Test
	void test_UnUsuarioNovatoQueTieneCategoriaExpertoBajaACategoriaBasicoPorNoCumplirLosRequisitos() {
		UsuarioNovato usuarioNuevoBasico = this.fixture2;
		Boolean estadoAnteriorEraExperto = usuarioNuevoBasico.esUsuarioExperto();
		usuarioNuevoBasico.actualizarCategoria();
		Boolean estadoNuevoEsBasico = usuarioNuevoBasico.esUsuarioBasico();
		assertTrue(estadoAnteriorEraExperto);
		assertTrue(estadoNuevoEsBasico);
	}

}
