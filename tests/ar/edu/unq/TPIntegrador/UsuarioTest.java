package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.EstadoDeUsuario;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.EstadoDeUsuarioBasico;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

class UsuarioTest {
	
	private Usuario usuario;
	private LocalDate fechaActual;
	private FixtureUsuarioNovatoTest fixture;
	private FixtureUsuarioNovatoParaBajarDeCategoriaTest fixture2;
	private Usuario usuarioConCondicionesDeEstadoExperto;
	
	@Mock private AplicacionWeb sistema;
	@Mock private Muestra muestra;
	@Mock private Opinion opinion;
	@Mock private Opinion opinion1;
	@Mock private Opinion opinion2;
	@Mock private Muestra muestra1;
	
	
	@BeforeEach
	public void setUp() {
		
		sistema = mock(AplicacionWeb.class);
		
		fixture = new FixtureUsuarioNovatoTest();
		fixture2 = new FixtureUsuarioNovatoParaBajarDeCategoriaTest("23444555", sistema);
		
		usuario = new Usuario("30120240", sistema);
		usuarioConCondicionesDeEstadoExperto = fixture.nuevoUsuarioListoParaActualizarCategoria();
		
		fechaActual = LocalDate.now();
		
		muestra = mock(Muestra.class);
		opinion = mock(Opinion.class);
		opinion1 = mock(Opinion.class);
		when(opinion1.getFechaDeEmision()).thenReturn(fechaActual);
		opinion2 = mock(Opinion.class);
		when(opinion2.getFechaDeEmision()).thenReturn(fechaActual);
		muestra1 = mock(Muestra.class);
		when(muestra1.getFechaDeCreacion()).thenReturn(fechaActual);
		
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
	void test_UnUsuarioAgregaUnaOpinionEnviadaCuandoRecibeElMensajeAgregarAOpinionesEnviadas() {
		usuario.agregarOpinionEnviada(opinion);
		Integer result = usuario.getRevisiones();
		assertEquals(1, result);
	}
	
	@Test
	void test_UnUsuarioNoEstaAptoParaEmitirUnaOpinionYTiene0CantidadDeOpinionesLuegoDeOpinar() throws Exception {
		usuario.opinarSobreMuestra(muestra, opinion);
		Integer result = usuario.getRevisiones();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnUsuarioQueNoEmitioOpinionesTieneUnaListaVaciaDeOpinionesEnviadas() {
		Integer result = usuario.getOpinionesEnviadas().size();
		assertEquals(0, result);
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
	
	@Test
	void test_UnUsuarioQueEnviaUnaMuestraLeEnviaElMensajeALaAplicionWebParaRegistrarla() {
		usuario.enviarMuestra(muestra);
		verify(sistema).registrarMuestra(muestra);
	}
	
	@Test
	void test_UnUsuarioEmiteUnaOpinionYLeEnviaElMensajeDeAgregarOpinionALaMuestra() throws Exception {
		usuario.opinarSobreMuestra(muestra, opinion);
		verify(muestra).agregarOpinion(opinion, usuario);
	}
	
	@Test
	void test_UnUsuarioNuevoTieneUnEstadoDeUsuarioBasico() {
		Class<? extends EstadoDeUsuario> result = usuario.getEstadoDeUsuario().getClass();
		assertEquals(EstadoDeUsuarioBasico.class, result);
	}
	
	@Test
	void test_UnUsuarioNovatoSiempreComienzaSiendoBasico() {
		assertTrue(usuario.esUsuarioBasico());
	}
	
	@Test
	void test_UnUsuarioAlEmpezarSiempreSiendoBasicoNoPuedeSerExpertoAlSerCreado() {
		assertFalse(usuario.esUsuarioExperto());
	}

	@Test
	void test_UnUsuarioEspecialistaAlQuererActualizarSuCategoriaNoHaceNada() {
		usuarioConCondicionesDeEstadoExperto.cambiarAUsuarioEspecialista();
		String categoria = usuarioConCondicionesDeEstadoExperto.getClass().getSimpleName();
		usuarioConCondicionesDeEstadoExperto.actualizarCategoria();
		String result = usuarioConCondicionesDeEstadoExperto.getClass().getSimpleName();
		assertEquals(categoria, result);
	}
	
	@Test
	void test_ElEstadoDeUsuarioDeUnUsuarioConEstadoExpertoEsEstadoExperto() throws Exception {
		Usuario usuarioExpertoNuevo = fixture.nuevoUsuarioListoParaActualizarCategoria();
		usuarioExpertoNuevo.actualizarCategoria();
		assertTrue(usuarioExpertoNuevo.esUsuarioExperto());
	}
	
	@Test
	void test_UnUsuarioEspecialistaSiempretieneEstadoExperto() {
		usuario.cambiarAUsuarioEspecialista();
		assertFalse(usuario.esUsuarioBasico());
	}
	
	@Test
	void test_CuandoUnUsuarioExpertoEmiteUnaOpinionSobreUnaMuestraLaMuestraRecibeElMensajeAgregarOpinion() throws Exception {
		usuarioConCondicionesDeEstadoExperto.opinarSobreMuestra(muestra, opinion);
		verify(muestra).agregarOpinion(opinion, usuarioConCondicionesDeEstadoExperto);
	}
	
	@Test
	void test_UnNuevoUsuarioNovatoNoTieneOpinionesEnLosUltimos30Dias() {
		Integer result = usuario.cantidadDeEnviosEnLosUltimos30Dias();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnUsuarioEmiteUnaOpinionYSuCantidadDeOpinionesEnLosUltimos30DiasEs1() throws Exception {
		usuario.agregarOpinionEnviada(opinion1);
		Integer result = usuario.cantidadDeOpinionesEnLosUltimos30Dias();
		assertEquals(1, result);
	}
	
	@Test
	void test_UnUsuarioBasicoQueNoEnvioNingunaMuestraTiene0CantidadDeEnviosEnLosUltimos30Dias() {
		Integer result = usuario.cantidadDeEnviosEnLosUltimos30Dias();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnUsuarioBasicoEnviaUnaMuestraYTiene1CantidadDeEnviosEnLosUltimos30Dias() {
		usuario.enviarMuestra(muestra1);
		Integer result = usuario.cantidadDeEnviosEnLosUltimos30Dias();
		assertEquals(1, result);
	}
	
	@Test
	void test_UnUsuarioBasicoQueEmitioUnaOpinion2MesesAtrasTieneCantidad0DeEnviosEnLosUltimos30Dias() throws Exception {
		LocalDate fechaAnterior = LocalDate.now().minusMonths(2);
		Opinion opinion2MesesAtras = mock(Opinion.class);
		when(opinion2MesesAtras.getFechaDeEmision()).thenReturn(fechaAnterior);
		usuario.agregarOpinionEnviada(opinion2MesesAtras);
		Integer result = usuario.cantidadDeOpinionesEnLosUltimos30Dias();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnUsuarioNoEsExpertoAlSerCreado() {
		Boolean result = usuario.esUsuarioExperto();
		assertFalse(result);
	}
	
	@Test
	void test_UnUsuarioBasicoSinCondicionesParaSerExpertoTieneEstadoDeUsuarioBasicoLuegoDeActualizarLaCategoria() throws Exception {
		usuario.actualizarCategoria();
		assertTrue(usuario.esUsuarioBasico());
	}
	
	@Test
	void test_UnUsuarioBasicoConCondicionesParaSerExpertoTieneEstadoDeUsuarioBasico() throws Exception {
		Usuario usuarioBasico = this.fixture.nuevoUsuarioListoParaActualizarCategoria();
		assertTrue(usuarioBasico.esUsuarioBasico());
	}
	
	@Test
	void test_UnUsuarioBasicoActualizaSuCategoriaDeUsuarioBasicoLuegoDeCumplirLosRequisitosParaSubirAExperto() throws Exception {
		Usuario usuarioNuevoExperto = this.fixture.nuevoUsuarioListoParaActualizarCategoria();
		usuarioNuevoExperto.actualizarCategoria();
		assertTrue(usuarioNuevoExperto.esUsuarioExperto());
	}
	
	@Test
	void test_UnUsuarioBasicoTieneRevisionesNecesariasPeroEnviosInsuficientesYNoCambiaDeCategoria() throws Exception {
		Usuario usuarioBasico = this.fixture.nuevoUsuarioBasicoQueCumpleRevisionesPeroNoEnvios();
		usuarioBasico.actualizarCategoria();
		assertTrue(usuarioBasico.esUsuarioBasico());
	}
	
	@Test
	void test_UnUsuarioNovatoTieneEnviosNecesariosPeroRevisionesInsuficientesYNoCambiaDeCategoria() throws Exception {
		Usuario usuarioBasico = this.fixture.nuevoUsuarioBasicoQueCumpleConEnviosPeroNoConRevisiones();
		usuarioBasico.actualizarCategoria();
		assertTrue(usuarioBasico.esUsuarioBasico());
	}
	
	@Test
	void test_UnUsuarioBasicoQueTieneCategoriaExpertoBajaACategoriaBasicoPorNoCumplirLosRequisitos() {
		Usuario usuarioNuevoBasico = this.fixture2;
		Boolean estadoAnteriorEraExperto = usuarioNuevoBasico.esUsuarioExperto();
		usuarioNuevoBasico.actualizarCategoria();
		Boolean estadoNuevoEsBasico = usuarioNuevoBasico.esUsuarioBasico();
		assertTrue(estadoAnteriorEraExperto);
		assertTrue(estadoNuevoEsBasico);
	}

	@Test
	void test_UnUsuarioEspecialistaNuncaBajaDeCategoria() {
		usuario.cambiarAUsuarioEspecialista();
		assertFalse(usuario.esUsuarioBasico());
	}
	
	@Test
	void test_UnUsuarioAlAgregarOpinionSobreMuestraLeEnviaElMensajeAgregarOpinionAMuestra() throws Exception {
		usuario.agregarOpinionAMuestraVotada(muestra1, opinion);
		verify(muestra1).agregarOpinionDeUsuario(opinion, usuario);
	}
	
	@Test
	void test_UnUsuarioAlAgregarUnaOpinionSobreMuestraLeEnviaElMensajeCerrarOpinionesParaTodosLosUsuariosBasicos() {
		usuarioConCondicionesDeEstadoExperto.actualizarCategoria();
		usuarioConCondicionesDeEstadoExperto.agregarOpinionAMuestraVotada(muestra1, opinion);
		verify(muestra1).cerrarOpinionesParaUsuariosBasicos();
		verify(muestra1).agregarOpinionDeUsuario(opinion, usuarioConCondicionesDeEstadoExperto);
	}
	
	@Test
	void test_UnUsuarioConEstadoDeUsuarioBasicoLanzaUnErrorAlAgregarUnaOpinionSobreUnaMuestraVotadaPorExperto() {
		assertThrows(Exception.class, () -> usuario.agregarOpinionAMuestraVotadaPorExperto(muestra, opinion));
	}
	
	@Test
	void test_UnUsuarioConEstadoDeUsuarioExpertoAlAgregarUnaOpinionAUnaMuestraVotadaPorExpertoLeEnviaElMensajeVerificarMuestra() throws Exception {
		usuarioConCondicionesDeEstadoExperto.actualizarCategoria();
		usuarioConCondicionesDeEstadoExperto.agregarOpinionAMuestraVotadaPorExperto(muestra1, opinion);
		verify(muestra1).verificarMuestra();
	}
	
	@Test
	void test_UnUsuarioConEstadoExpertoAlOpinarSobreMuestraLeEnviaElMensajeAgregarOpinionAMuestra() throws Exception {
		usuarioConCondicionesDeEstadoExperto.actualizarCategoria();
		usuarioConCondicionesDeEstadoExperto.opinarSobreMuestra(muestra, opinion);
		verify(muestra).agregarOpinion(opinion, usuarioConCondicionesDeEstadoExperto);
	}
	
	@Test
	void test_UnUsuarioQueTieneEstadoExpertoAlActualizarCategoriaBajoLasMismasCondicionesSigueSiendoExperto() {
		usuarioConCondicionesDeEstadoExperto.actualizarCategoria();
		usuarioConCondicionesDeEstadoExperto.actualizarCategoria();
		assertTrue(usuarioConCondicionesDeEstadoExperto.esUsuarioExperto());
	}
	
	@Test
	void test_UnUsuarioEnviaUnaMuestra2MesesAtrasDelaFechaActualYNoEstaEnSusEnviosDeLosUltimos30Dias() {
		LocalDate fechaAnterior = LocalDate.now().minusMonths(2);
		Muestra muestra2MesesAtras = mock(Muestra.class);
		when(muestra2MesesAtras.getFechaDeCreacion()).thenReturn(fechaAnterior);
		usuario.enviarMuestra(muestra2MesesAtras);
		Integer result = usuario.cantidadDeEnviosEnLosUltimos30Dias();
		assertEquals(0, result);
	}
}
