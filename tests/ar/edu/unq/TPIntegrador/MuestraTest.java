package ar.edu.unq.TPIntegrador;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MuestraTest {
	
	//usuarios concretos para testear el pasaje entre estados de Muestra
	
	
	private Usuario propietario;
	private Usuario usuarioAjeno;
	private BufferedImage fotoVinchuca;
	private Ubicacion ubicacionVinchuca;
	private Muestra muestra;
	public Opinion opinionDelPropietario;
	private Opinion opinion2;
	private ZonaDeCobertura zona;
		
	@BeforeEach
	void setUp() throws Exception {
	
		propietario = mock(Usuario.class);
		usuarioAjeno = mock(Usuario.class);
		fotoVinchuca = mock(BufferedImage.class);
		ubicacionVinchuca = mock(Ubicacion.class);
		opinionDelPropietario = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		zona = mock(ZonaDeCobertura.class);
		muestra = new Muestra(propietario, opinionDelPropietario, fotoVinchuca, ubicacionVinchuca , LocalDate.of(2020,12,01));
		Usuario userExperto = new Usuario("Juan", null);
		userExperto.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		Usuario userExperto2 = new Usuario("Pepe", null);
		userExperto2.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
	}

	@Test
	void test_UnaMuestraTieneUnaEspecieDeVinchucaPtiaChinche() {
		when(muestra.getEspecieDeVinchuca()).thenReturn("Ptia Chinche");
		assertEquals("Ptia Chinche", muestra.getEspecieDeVinchuca());
	}
	
	@Test
	void test_UnaMuestraTieneUnaFoto() {
		assertEquals(fotoVinchuca, muestra.getFotoDeLaVinchuca());
	}
	
	@Test
	void test_UnaMuestraTieneUnaUbicacion() {
		assertEquals(ubicacionVinchuca, muestra.getUbicacion());
	}
	
	@Test
	void test_UnaMuestraTieneUnPropietarioDeNombreJuan() {
		when(muestra.getIdentificacionPropietarioDeLaMuestra()).thenReturn("cazador de vinchucas");
		assertEquals("cazador de vinchucas", muestra.getIdentificacionPropietarioDeLaMuestra());
	}
	
	@Test
	void test_UnaMuestraTieneUnaFechaDeCreacion2020_12_01() {
		assertEquals(LocalDate.of(2020,12,01), muestra.getFechaDeCreacion());
	}
	
	@Test
	void test_UnaMuestraTieneUnaFechaDeUltimaVotacion2020_12_01() {
		when(muestra.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2020,12,01));
		assertEquals(LocalDate.of(2020,12,01), muestra.getFechaUltimaVotacion());
	}
	
	@Test
	void test_unaMuestraQueNoAgregoUnaZonaDeCoberturaTieneSuListaDeZonasVacia() {
		assertTrue(muestra.getZonasDeCobertura().isEmpty());
	}
	
	@Test
	void test_unaMuestraQueAgregoUnaZonaDeCoberturaLATieneEnSuListaDeZonas() {
		muestra.agregarZonaDeCobertura(zona);
		assertTrue(muestra.getZonasDeCobertura().contains(zona));
	}
	
	@Test
	void test_UnaMuestraCuandoSeCreaTienelaOpinionDelPropietario() {
		assertTrue(muestra.getHistorialDeOpiniones().containsValue(opinionDelPropietario));
	}
	
	@Test
	void test_UnaMuestraCreadaPorUnUsuarioConEstadoBasicoTieneUnEstadoVotada() {
		assertEquals(new EstadoDeMuestraVotada().getClass(), muestra.getEstadoDeMuestra().getClass());
	}

	@Test
	void test_UnaMuestraNoContieneLaOpinionDelUsuarioAjeno() {
		assertTrue(muestra.noContieneLaOpinionDelUsuario(usuarioAjeno));
	}
	
	@Test
	void test_UnaMuestraTieneUnEstadoAcordeASuPropietario_MuestraVotada_UsuarioBasico() {
		assertEquals(new EstadoDeMuestraVotada().getClass(), muestra.getEstadoSegunEstadoDeUsuario(propietario).getClass());
	}
	
	@Test
	void test_UnaMuestraNoPuedeVolverASerComentadaPorSuPropietario() throws Exception {
		try {
			muestra.agregarOpinion(opinion2, propietario);
	    }
	    catch (Exception exception){
	               assertEquals(exception.getMessage(), "El usuario no puede opinar sobre la muestra.");
	     } 
	}
	
	@Test
	void test_UnaMuestraCuandoAgregaUnaOpinionSeLeAgregaAsuHistorialDeOpiniones() throws Exception {
		
		try {
			muestra.agregarOpinion(opinion2, usuarioAjeno);
	    }
	    catch (Exception exception){
	               assertEquals(exception.getMessage(), "La Muestra no puede verificarse aun");
	     } 
		assertTrue(muestra.getHistorialDeOpiniones().containsKey(usuarioAjeno)); 
		assertEquals(new EstadoDeMuestraVotada().getClass(), muestra.getEstadoDeMuestra().getClass());
	}
	
	
	@Test
	void test_CuandoSeIntentaVerificarUnaMuestraVotadaSeArrojaUnaExcepcion() throws Exception {
		try {
		muestra.verificarMuestra();
		 }
	    catch (Exception exception){
	               assertEquals(exception.getMessage(), "La Muestra no puede verificarse aun");
	    }
		assertNotEquals(new EstadoDeMuestraVerificada().getClass(), muestra.getEstadoDeMuestra().getClass());
	}
	
	@Test
	void test_UnaMuestraEstaEnEstadoVotadaPorExperto() throws Exception {
		muestra.cerrarOpinionesParaUsuariosBasicos();
		assertEquals(new EstadoMuestraVotadaPorExperto().getClass(), muestra.getEstadoDeMuestra().getClass());
		assertNotEquals(new EstadoDeMuestraVerificada().getClass(), muestra.getEstadoDeMuestra().getClass());
		assertNotEquals(new EstadoDeMuestraVotada().getClass(), muestra.getEstadoDeMuestra().getClass());
	}
	
	@Test
	void test_CuandoUnaMuestraEstaEnEstadoVotadaPorExpertoNoPuedeVotarUnUsuarioBasico() throws Exception {
		muestra.cerrarOpinionesParaUsuariosBasicos();
		assertFalse(muestra.usuarioEsAptoParaVotar(usuarioAjeno));
	}
	
	@Test
	void test_cuandoUnUsuarioExpertoOpinaSobreUnaMuestraEstaQuedaVotadaPorExperto() throws Exception {
		Usuario userNovato = new Usuario("Juan", null);
		userNovato.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
		Usuario userExperto = new Usuario("Pepe", null);
		userExperto.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		Opinion opinion = new Opinion(Descripcion.VINCHUCA_SORDIDA);
		Muestra muestraNovato = new Muestra(userNovato, opinion, fotoVinchuca, ubicacionVinchuca , LocalDate.of(2020,12,01));
		muestraNovato.agregarOpinion(opinion, userExperto);
		
		assertEquals(new EstadoMuestraVotadaPorExperto().getClass(), muestraNovato.getEstadoDeMuestra().getClass());
		assertEquals(LocalDate.now(), muestraNovato.getFechaUltimaVotacion());
		assertTrue(muestraNovato.getHistorialDeOpiniones().containsKey(userExperto));
		assertTrue(muestraNovato.getHistorialDeOpiniones().containsKey(userNovato));
	}

	@Test
	void test_cuandoDosUsuariosExpertosCoincidenEnLaOpinionSobreUnaMuestraEstaQuedaVerificada() throws Exception {
		Usuario userExperto = new Usuario("Juan", null);
		userExperto.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		Usuario userExperto2 = new Usuario("Pepe", null);
		userExperto2.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		Opinion opinion = new Opinion(Descripcion.VINCHUCA_SORDIDA);
		Muestra muestraExperto = new Muestra(userExperto, opinion, fotoVinchuca, ubicacionVinchuca , LocalDate.of(2020,12,01));
		
		muestraExperto.agregarOpinion(opinion, userExperto2);
		
		assertEquals(new EstadoDeMuestraVerificada().getClass(), muestraExperto.getEstadoDeMuestra().getClass());
		assertEquals(LocalDate.now(), muestraExperto.getFechaUltimaVotacion());
		assertTrue(muestraExperto.getHistorialDeOpiniones().containsKey(userExperto));
		assertTrue(muestraExperto.getHistorialDeOpiniones().containsKey(userExperto2));
	}
	
	
	@Test
	void test_CuandoUnUsuarioExpertoTrataDeVotarUnaMuestraVerificadaSeArrojaUnaExcepcion() throws Exception {
		Usuario userExperto = new Usuario("Juan", null);
		userExperto.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
		Usuario userExperto2 = new Usuario("Pepe", null);
		userExperto2.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
		Usuario userExperto3 = new Usuario("Pepe", null);
		userExperto3.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
		Opinion opinionDeExperto = new Opinion(Descripcion.VINCHUCA_SORDIDA);
		Muestra muestraExperto = new Muestra(userExperto, opinionDeExperto, fotoVinchuca, ubicacionVinchuca , LocalDate.of(2020,12,01));
		
		try {
			muestraExperto.agregarOpinion(opinionDeExperto, userExperto3);
			muestraExperto.agregarOpinion(opinionDeExperto, userExperto2);			
		 }
	    catch (Exception exception){
	               assertEquals(exception.getMessage(), "La Muestra no puede verificarse aun");
	    }
		//assertEquals(new EstadoDeMuestraVotada().getClass(), muestra.getEstadoDeMuestra().getClass());
	}
	
}
	
	
	
	
	
	
	
	
