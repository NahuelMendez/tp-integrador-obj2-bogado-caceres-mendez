package ar.edu.unq.TPIntegrador;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;

class MuestraTest {
	private Muestra muestra;
	private Muestra muestra1;
	private Usuario userPropietario;
	private Usuario gonzaBasico_propietario;
	private Usuario usuarioBasico;
	private Usuario nahueExperto;
	private Usuario ximeExperto;
	private Usuario usuarioExperto;
	private Opinion opinionVinchucaSordida;
	private Opinion opinionVinchucaSordida2;
	private Opinion opinionChincheFoliada;
	private BufferedImage fotoVinchuca;
	private Ubicacion ubicacionDeLaMuestra;
	private ZonaDeCobertura zona;
	private AplicacionWeb app;

	@BeforeEach
	void setUp() throws Exception {
		userPropietario = mock(Usuario.class);
		fotoVinchuca = mock(BufferedImage.class);
		opinionChincheFoliada = mock(Opinion.class);
		zona = mock(ZonaDeCobertura.class);
		app = mock(AplicacionWeb.class);
		
		gonzaBasico_propietario = new Usuario("Gonza", app);
		gonzaBasico_propietario.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
		nahueExperto = new Usuario("Nahue", app);
		nahueExperto.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		ximeExperto = new Usuario("Xime", app);
		ximeExperto.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		usuarioExperto = new Usuario("User", app);
		usuarioExperto.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		usuarioBasico = new Usuario("Pepite", app);
		usuarioBasico.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
		opinionVinchucaSordida = new Opinion(Descripcion.VINCHUCA_SORDIDA);
		opinionVinchucaSordida2 = new Opinion(Descripcion.VINCHUCA_SORDIDA);
		opinionChincheFoliada = new Opinion(Descripcion.CHINCHE_FOLIADA);
		
		ubicacionDeLaMuestra = new Ubicacion(1.5, 3.0);
	
		muestra = new Muestra(gonzaBasico_propietario, opinionVinchucaSordida, fotoVinchuca, ubicacionDeLaMuestra , LocalDate.of(2020,12,01));
		muestra1 = new Muestra(userPropietario, opinionChincheFoliada, fotoVinchuca, ubicacionDeLaMuestra , LocalDate.of(2020,05,05));
	}

	@Test
	void test_UnaMuestraTieneUnaEspecieDeVinchucaVINCHUCA_SORDIDA() {
		assertEquals("VINCHUCA_SORDIDA", muestra.getEspecieDeVinchuca());
	}

	@Test
	void test_UnaMuestraTieneUnaFoto() {
		assertEquals(fotoVinchuca, muestra.getFotoDeLaVinchuca());
	}
	
	@Test
	void test_UnaMuestraTieneUnaUbicacion() {
		assertEquals(ubicacionDeLaMuestra, muestra.getUbicacion());
	}
	
	@Test
	void test_UnaMuestraTieneUnPropietarioDeNombreUsuario() {
		when(muestra1.getIdentificacionPropietarioDeLaMuestra()).thenReturn("NombreUsuario");
		assertEquals("NombreUsuario", muestra1.getIdentificacionPropietarioDeLaMuestra());
	}

	@Test
	void test_UnaMuestraTieneUnaFechaDeCreacion2020_12_01() {
		assertEquals(LocalDate.of(2020,12,01), muestra.getFechaDeCreacion());
	}
	
	@Test
	void test_cuandoUnaMuestraAgregaUnaOpinionSeActualizaLaUltimaFechaDeVotacion() throws Exception {
		assertEquals(LocalDate.of(2020,12,01), muestra.getFechaUltimaVotacion());
		muestra.agregarOpinion(opinionChincheFoliada, usuarioBasico);
		assertEquals(LocalDate.now(), muestra.getFechaUltimaVotacion());
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
		assertTrue(muestra.contieneLaOpinion(opinionVinchucaSordida));
	}
	
	@Test
	void test_UnaMuestraCreadaPorUnUsuarioConEstadoBasicoTieneUnEstadoVotada() {
		assertEquals("votada", muestra.getEstadoActual());
	}

	@Test
	void test_UnaMuestraCreadaPorUnUsuarioConEstadoExpertoTieneUnEstadoVotadaPorExperto() throws Exception {
		muestra = new Muestra(ximeExperto, opinionVinchucaSordida, fotoVinchuca, ubicacionDeLaMuestra , LocalDate.of(2020,12,01));
		
		assertEquals("votadaPorExperto", muestra.getEstadoActual());
	}

	@Test
	void test_UnaMuestraNoPuedeVolverASerOpinadaPorSuPropietario() throws Exception {
		try {
			muestra.agregarOpinion(opinionChincheFoliada, gonzaBasico_propietario);
	    }
	    catch (Exception exception){
	    	assertEquals(exception.getMessage(), "El usuario no puede opinar sobre la muestra.");
	     } 
	}
	
	@Test
	void test_UnaMuestraNoContieneLaOpinionDeUnUsuarioQueNoOpino() {
		assertTrue(muestra.noContieneAlUsuario(ximeExperto));
	}
	
	@Test
	void test_UnaMuestraCuandoAgregaUnaOpinionSeLeAgregaAsuHistorialDeOpiniones() throws Exception {
		muestra.agregarOpinion(opinionChincheFoliada, ximeExperto);
		
		assertTrue(muestra.contieneLaOpinion(opinionChincheFoliada)); 
	}

	@Test
	void test_cuandoSeLePideAUnaMuestraSuHistorialDeOpinionesLasRetorna() throws Exception {
		ArrayList<String> opiniones = new ArrayList<String>();
		opiniones.add("VINCHUCA_SORDIDA");
		opiniones.add("VINCHUCA_SORDIDA");
		
		muestra.agregarOpinion(opinionVinchucaSordida, nahueExperto);
		muestra.agregarOpinion(opinionVinchucaSordida2, ximeExperto);
		assertEquals(opiniones, muestra.getOpiniones());
	}
	
	@Test
	void test_laCantidadDeOpinionesEnElHistorialDeOpinionesEs2() throws Exception {
		muestra.agregarOpinion(opinionVinchucaSordida, nahueExperto);
		muestra.agregarOpinion(opinionVinchucaSordida2, ximeExperto);
		
		assertEquals(2, muestra.getHistorialDeOpiniones().size());
	}
	
	@Test
	void test_unaVezQueEntranLosExpertos_LasOpinionesQueValeSonLasDeLosExpertos() throws Exception {
		assertTrue(muestra.contieneLaOpinion(opinionVinchucaSordida));
		
		muestra.agregarOpinion(opinionChincheFoliada, nahueExperto);
		muestra.agregarOpinion(opinionChincheFoliada, ximeExperto);
		
		assertFalse(muestra.contieneLaOpinion(opinionVinchucaSordida));
	}
	
	@Test
	void test_CuandoUnaMuestraEstaEnEstadoVotadaPorExpertoNoPuedeVotarUnUsuarioBasico() throws Exception {
		muestra.agregarOpinion(opinionVinchucaSordida, nahueExperto);
		assertFalse(muestra.usuarioAptoParaVotar(usuarioBasico));
	}
	
	@Test
	void test_cuandoUnUsuarioExpertoOpinaSobreUnaMuestraEstaQuedaVotadaPorExperto() throws Exception {
		muestra.agregarOpinion(opinionVinchucaSordida, ximeExperto);
		
		assertEquals("votadaPorExperto", muestra.getEstadoActual());
	}

	@Test
	void test_cuandoDosUsuariosExpertosCoincidenEnLaOpinionSobreUnaMuestraEstaQuedaVerificada() throws Exception {
		muestra.agregarOpinion(opinionVinchucaSordida, nahueExperto);
		muestra.agregarOpinion(opinionVinchucaSordida2, ximeExperto);
		
		assertEquals("verificada", muestra.getEstadoActual());
		assertTrue(muestra.coincidenDosExpertosEnSuOpinion());
	}

	@Test
	void test_cuandoDosUsuariosExpertosNOCoincidenEnLaOpinionSobreUnaMuestraEstaNOQuedaVerificada() throws Exception {
		muestra.agregarOpinion(opinionVinchucaSordida, nahueExperto);
		muestra.agregarOpinion(opinionChincheFoliada, ximeExperto);
		
		assertEquals("votadaPorExperto", muestra.getEstadoActual());
		assertFalse(muestra.coincidenDosExpertosEnSuOpinion());
	}

	@Test
	void test_cuandoUnUsuarioExpertoTrataDeVotarUnaMuestraVerificadaSuOpinionNoSeAgrega() throws Exception {
		try {
			muestra.agregarOpinion(opinionVinchucaSordida, nahueExperto);
			muestra.agregarOpinion(opinionVinchucaSordida, ximeExperto);
			muestra.agregarOpinion(opinionChincheFoliada, usuarioExperto);
		}
		catch (Exception exception){
            assertEquals(exception.getMessage(), "Nadie puede opinar sobre muestras verificadas");
		}
		assertFalse(muestra.contieneLaOpinion(opinionChincheFoliada));
	} 
	
	
	@Test
	void test_unaMuestraVerificadaNoSePuedeVolverAVerificar() throws Exception {
		muestra.agregarOpinion(opinionVinchucaSordida, nahueExperto);
		muestra.agregarOpinion(opinionVinchucaSordida, ximeExperto);
		assertFalse(muestra.getEstadoDeMuestra().sePuedeVerificarMuestra(muestra));
		assertFalse(muestra.getEstadoDeMuestra().usuarioAptoParaVotar(usuarioExperto, muestra));
	}
	
	@Test
	void test_unaMuestraTieneUnNivelDeVerificacionVotadaSiNoOpinoNingunExperto() {
		assertEquals("votada", muestra.nivelDeVerificacion());
	}
	
	@Test
	void test_unaMuestraTieneUnNivelDeVerificacionVotadaSiOpinoAlMenosUnExperto() throws Exception {
		muestra.agregarOpinion(opinionVinchucaSordida, nahueExperto);
		
		assertEquals("votada", muestra.nivelDeVerificacion());
	}
	
	@Test
	void test_unaMuestraTieneUnNivelDeVerificacionVerificadaSiDosExpertosConcidieronEnSuOpinion() throws Exception {
		muestra.agregarOpinion(opinionVinchucaSordida, nahueExperto);
		muestra.agregarOpinion(opinionVinchucaSordida2, ximeExperto);
		
		assertEquals("verificada", muestra.nivelDeVerificacion());
	}
	
	@Test
	void test_unUsuarioExpertoPuedeVotarUnaMuestraVotadaPorExperto() throws Exception {
		muestra.agregarOpinion(opinionVinchucaSordida, nahueExperto);
		
		assertTrue(muestra.usuarioAptoParaVotar(ximeExperto));
	}
	
	@Test
	void test_unUsuarioExpertoNoPuedeVotarUnaMuestraVerificada() throws Exception {
		assertFalse(muestra.getEstadoDeMuestra().sePuedeVerificarMuestra(muestra));
	}
	
	@Test
	void test_cuandoSeLePideAUnaMuestraSuResultadoActualRetornaLaOpinionMasVotada() throws Exception {
		muestra.agregarOpinion(opinionChincheFoliada, usuarioBasico);
		muestra.agregarOpinion(opinionVinchucaSordida, nahueExperto);
		muestra.agregarOpinion(opinionVinchucaSordida2, ximeExperto);
		
		assertEquals("VINCHUCA_SORDIDA", muestra.getResultadoActual());
	}
	
	@Test
	void test_cuandoUnaMuestraSeVerificaSeLeAvisaASusZonasDeCobertura() throws Exception {
		muestra.agregarZonaDeCobertura(zona);
		muestra.agregarOpinion(opinionVinchucaSordida, nahueExperto);
		muestra.agregarOpinion(opinionVinchucaSordida, ximeExperto);
		
		verify(zona, atLeastOnce()).muestraVerificada(muestra);
	}
	
	@Test
	void test_unaMuestraRetornaSuListaDeMuestrasCercanas() throws Exception{
		Ubicacion ubicacionCercana1 = new Ubicacion(1.5, 3.0);
		Ubicacion ubicacionCercana2 = new Ubicacion(2.1, 1.0);   
		Ubicacion ubicacionLejana1 = new Ubicacion(120.0, 343.7);    
		Ubicacion ubicacionLejana2 = new Ubicacion(243.0, 123.0);
		
		Muestra muestraCercana1 = new Muestra(userPropietario, opinionChincheFoliada, fotoVinchuca, ubicacionCercana1 , LocalDate.of(2020,05,05));
		Muestra muestraCercana2 = new Muestra(nahueExperto, opinionChincheFoliada, fotoVinchuca, ubicacionCercana2 , LocalDate.of(2020,05,05));
		Muestra muestraLejana1 = new Muestra(ximeExperto, opinionChincheFoliada, fotoVinchuca, ubicacionLejana1 , LocalDate.of(2020,05,05));
		Muestra muestraLejana2 = new Muestra(gonzaBasico_propietario, opinionChincheFoliada, fotoVinchuca, ubicacionLejana2 , LocalDate.of(2020,05,05));
		
		Set<Muestra> muestrasAComparar = new HashSet<Muestra>();
		Set<Muestra> muestrasCercanas = new HashSet<Muestra>();
		
		muestrasAComparar.add(muestraCercana1);
		muestrasAComparar.add(muestraCercana2);
		muestrasAComparar.add(muestraLejana1);
		muestrasAComparar.add(muestraLejana2);
		
		muestrasCercanas.add(muestraCercana1);
		muestrasCercanas.add(muestraCercana2);
		
		assertEquals(muestrasCercanas, muestra.muestrasCercanas(muestrasAComparar, 342.0));
	}
	
}
	
	
	
	
	
	
	
	
