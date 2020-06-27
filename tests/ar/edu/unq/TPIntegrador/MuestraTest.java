package ar.edu.unq.TPIntegrador;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;

class MuestraTest {
	
	private Muestra muestra;
	
	@Mock private BufferedImage fotoVinchuca;
	@Mock private Ubicacion ubicacionDeLaMuestra;
	@Mock private ZonaDeCobertura zona;
	@Mock private Usuario gonzaBasico;
	@Mock private Usuario usuarioBasico;
	@Mock private Usuario nahueExperto;
	@Mock private Usuario ximeExperto;
	@Mock private Usuario usuarioExperto;
	@Mock private Opinion opinionChincheFoliada;
	@Mock private Opinion opinionChincheFoliada2;
	@Mock private Opinion opinionVinchucaGuasayana;
	@Mock private Opinion opinionVinchucaGuasayana2;
	
	@BeforeEach
	void setUp() throws Exception {

		fotoVinchuca = mock(BufferedImage.class);
		ubicacionDeLaMuestra = mock(Ubicacion.class);
		
		zona = mock(ZonaDeCobertura.class);
		
		usuarioBasico = mock(Usuario.class);
		usuarioExperto = mock(Usuario.class);
		nahueExperto = mock(Usuario.class);
		ximeExperto = mock(Usuario.class);
		gonzaBasico = mock(Usuario.class);
		opinionChincheFoliada = mock(Opinion.class);
		opinionChincheFoliada2 = mock(Opinion.class);
		opinionVinchucaGuasayana = mock(Opinion.class);
		opinionVinchucaGuasayana2 = mock(Opinion.class);
		
		muestra = new Muestra(gonzaBasico, opinionVinchucaGuasayana, fotoVinchuca, ubicacionDeLaMuestra , LocalDate.of(2020,12,01));

		when(usuarioBasico.esUsuarioBasico()).thenReturn(true);
		when(usuarioExperto.esUsuarioExperto()).thenReturn(true);
		when(nahueExperto.esUsuarioExperto()).thenReturn(true);
		when(ximeExperto.esUsuarioExperto()).thenReturn(true);
		when(gonzaBasico.esUsuarioBasico()).thenReturn(true);
		
		when(opinionVinchucaGuasayana.getDescripcion()).thenReturn("VINCHUCA_GUAYASANA");
		when(opinionVinchucaGuasayana.getFechaDeEmision()).thenReturn(LocalDate.now());
		when(opinionVinchucaGuasayana2.getDescripcion()).thenReturn("VINCHUCA_GUAYASANA");
		when(opinionVinchucaGuasayana2.getFechaDeEmision()).thenReturn(LocalDate.now());
		when(opinionChincheFoliada.getDescripcion()).thenReturn("CHINCHE_FOLIADA");
		when(opinionChincheFoliada.getFechaDeEmision()).thenReturn(LocalDate.now());
		when(opinionChincheFoliada2.getDescripcion()).thenReturn("CHINCHE_FOLIADA");
		when(opinionChincheFoliada2.getFechaDeEmision()).thenReturn(LocalDate.now());
	}

	@Test
	void test_Constructor() {
		assertEquals("VINCHUCA_GUAYASANA", muestra.getEspecieDeVinchuca());
		assertTrue(muestra.contieneLaOpinion(opinionVinchucaGuasayana));
		assertEquals(fotoVinchuca, muestra.getFotoDeLaVinchuca());
		assertEquals(ubicacionDeLaMuestra, muestra.getUbicacion());
		when(muestra.getIdentificacionPropietarioDeLaMuestra()).thenReturn("Gonza");
		assertEquals("Gonza", muestra.getIdentificacionPropietarioDeLaMuestra());
		assertEquals(LocalDate.of(2020,12,01), muestra.getFechaDeCreacion());
		assertTrue(muestra.getZonasDeCobertura().isEmpty());	
		assertEquals(1, muestra.getHistorialDeOpiniones().size());
	}
	
	@Test
	void test_cuandoUnaMuestraAgregaUnaOpinionSeActualizaLaUltimaFechaDeVotacion() throws Exception {
		assertEquals(LocalDate.of(2020,12,01), muestra.getFechaUltimaVotacion());
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada2, nahueExperto);
		
		assertEquals(LocalDate.now(), muestra.getFechaUltimaVotacion());
	}
	
	@Test
	void test_unaMuestraQueAgregoUnaZonaDeCoberturaLATieneEnSuListaDeZonas() {
		muestra.agregarZonaDeCobertura(zona);
		
		assertTrue(muestra.getZonasDeCobertura().contains(zona));
	}

	@Test
	void test_UnaMuestraNoPuedeVolverASerOpinadaPorSuPropietario() throws Exception {
		assertThrows(Exception.class, () -> muestra.agregarOpinion(opinionChincheFoliada, gonzaBasico));
	}
	
	@Test
	void test_UnaMuestraNoContieneLaOpinionDeUnUsuarioQueNoOpino() {
		assertFalse(muestra.contieneAlUsuario(ximeExperto));
	}
	
	@Test
	void test_UnaMuestraCuandoAgregaUnaOpinionSeLeAgregaAsuHistorialDeOpiniones() throws Exception {
		assertFalse(muestra.contieneLaOpinion(opinionChincheFoliada)); 
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada, ximeExperto); 
		assertTrue(muestra.contieneLaOpinion(opinionChincheFoliada)); 
	}
	
	@Test
	void test_laCantidadDeOpinionesEnElHistorialDeOpinionesEs2() throws Exception {
		muestra.agregarOpinion(opinionChincheFoliada, nahueExperto);
		
		assertEquals(2, muestra.getHistorialDeOpiniones().size());
	}

	@Test
	void test_unaMuestraEsComentadaPorUnUsuarioExperto() throws Exception {
		muestra.verificarMuestra();
		muestra.agregarOpinion(opinionChincheFoliada, nahueExperto);
		
		verify(nahueExperto).agregarOpinionAMuestraVotadaPorExperto(muestra, opinionChincheFoliada);
	}
	
	
	@Test
	void test_unaVezQueEntranLosExpertos_LasOpinionesQueValeSonLasDeLosExpertos() throws Exception {
		assertTrue(muestra.contieneLaOpinion(opinionVinchucaGuasayana));
		
		muestra.verificarMuestra();
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada, nahueExperto);
		
		assertFalse(muestra.contieneLaOpinion(opinionVinchucaGuasayana));
		assertTrue(muestra.contieneLaOpinion(opinionChincheFoliada));
	}

	@Test
	void test_cuandoDosUsuariosExpertosCoincidenEnLaOpinionSobreUnaMuestraEstaQuedaVerificada() throws Exception {
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada, nahueExperto);
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada2, ximeExperto);
		
		assertTrue(muestra.coincidenDosExpertosEnSuOpinion());
	}
	
	@Test
	void test_cuandoDosUsuariosExpertosNOCoincidenEnLaOpinionSobreUnaMuestraEstaNOQuedaVerificada() throws Exception {
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada, gonzaBasico);
		muestra.agregarOpinionDeUsuario(opinionVinchucaGuasayana2, ximeExperto);
		
		assertFalse(muestra.coincidenDosExpertosEnSuOpinion());
	}
	
	@Test
	void test_cuandoUnUsuarioExpertoTrataDeVotarUnaMuestraVerificadaSuOpinionNoSeAgrega() throws Exception {
		muestra.verificarMuestra();
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada, nahueExperto);
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada2, ximeExperto);
		muestra.verificarMuestra();
		
		assertThrows(Exception.class, () -> muestra.agregarOpinion(opinionVinchucaGuasayana2, usuarioExperto));
	} 
		
	@Test
	void test_cuandoUnUsuarioExpertoTrataDeVotarUnaMuestraVotadaPorExpertoQueYaHaVotadoSuOpinionNoSeAgrega() throws Exception {
		muestra.verificarMuestra();
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada, nahueExperto);
		
		assertThrows(Exception.class, () -> muestra.agregarOpinion(opinionChincheFoliada2, nahueExperto));
	}
	
	@Test
	void test_unaMuestraTieneUnNivelDeVerificacionVotadaSiNoOpinoNingunExperto() {
		assertEquals("votada", muestra.nivelDeVerificacion());
	}
	
	@Test
	void test_unaMuestraTieneUnNivelDeVerificacionVotadaPorExpertoSiOpinoAlMenosUnExperto() throws Exception {
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada, usuarioExperto);
		muestra.verificarMuestra();
		
		assertEquals("votada", muestra.nivelDeVerificacion());
	}
	
	@Test
	void test_unaMuestraTieneUnNivelDeVerificacionVerificadaSiDosExpertosConcidieronEnSuOpinion() throws Exception {
		muestra.verificarMuestra();
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada, nahueExperto);
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada2, ximeExperto);
		muestra.verificarMuestra(); 
		
		assertEquals("verificada", muestra.nivelDeVerificacion());
	}
	
	@Test
	void test_unaMuestraVerificadaNoSePuedeVolverAVerificar() throws Exception {
		muestra.verificarMuestra();
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada, nahueExperto);
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada2, ximeExperto);
		muestra.verificarMuestra(); 
		
		assertThrows(Exception.class, () -> muestra.verificarMuestra());
	}
	
	@Test
	void test_cuandoSeLePideAUnaMuestraSuResultadoActualRetornaLaOpinionMasVotada() throws Exception {
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada, usuarioBasico);
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada2, usuarioExperto);
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada2, ximeExperto);
		
		assertEquals("CHINCHE_FOLIADA", muestra.getResultadoActual());
	}

	@Test
	void test_cuandoUnaMuestraSeVerificaSeLeAvisaASusZonasDeCobertura() throws Exception {
		muestra.agregarZonaDeCobertura(zona);
		muestra.verificarMuestra();
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada, nahueExperto);
		muestra.agregarOpinionDeUsuario(opinionChincheFoliada2, ximeExperto);
		
		muestra.verificarMuestra();
		verify(zona, atLeastOnce()).muestraVerificada(muestra);
		
		assertEquals("verificada", muestra.nivelDeVerificacion());
	}
	
	@Test
	void test_unaMuestraRetornaSuListaDeMuestrasCercanas() throws Exception{
		Ubicacion ubicacionCercana1 = mock(Ubicacion.class);
		Ubicacion ubicacionCercana2 = mock(Ubicacion.class); 
		Ubicacion ubicacionLejana1 = mock(Ubicacion.class);    
		Ubicacion ubicacionLejana = mock(Ubicacion.class);
		
		Muestra muestraCercana1 = new Muestra(usuarioBasico, opinionChincheFoliada, fotoVinchuca, ubicacionCercana1 , LocalDate.of(2020,05,05));
		Muestra muestraCercana2 = new Muestra(nahueExperto, opinionChincheFoliada, fotoVinchuca, ubicacionCercana2 , LocalDate.of(2020,05,05));
		Muestra muestraLejana = new Muestra(ximeExperto, opinionChincheFoliada, fotoVinchuca, ubicacionLejana , LocalDate.of(2020,05,05));
		Muestra muestraLejana1 = new Muestra(gonzaBasico, opinionChincheFoliada, fotoVinchuca, ubicacionLejana1 , LocalDate.of(2020,05,05));
		
		Set<Muestra> muestrasAComparar = new HashSet<Muestra>();
		Set<Muestra> muestrasEnCercania = new HashSet<Muestra>();
		
		muestrasAComparar.add(muestraCercana1);
		muestrasAComparar.add(muestraCercana2);
		muestrasAComparar.add(muestraLejana);
		muestrasAComparar.add(muestraLejana1);
		
		muestrasEnCercania.add(muestraCercana1);
		muestrasEnCercania.add(muestraCercana2);
		
		when(ubicacionDeLaMuestra.muestrasCercanas(muestrasAComparar, 10.0)).thenReturn(muestrasEnCercania);
		assertEquals(muestrasEnCercania, muestra.muestrasCercanas(muestrasAComparar, 10.0));
		verify(ubicacionDeLaMuestra).muestrasCercanas(muestrasAComparar, 10.0);
	}
}

	
	
	
	
	
	
	
