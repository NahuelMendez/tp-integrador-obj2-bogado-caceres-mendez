package ar.edu.unq.TPIntegrador;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

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
	private Muestra muestra1;
	private Usuario gonzaBasico_propietario;
	private Usuario usuarioBasico;
	private Usuario nahueExperto;
	private Usuario ximeExperto;
	private Usuario usuarioExperto;
	
	private Opinion opinionChincheFoliada;
	private BufferedImage fotoVinchuca;
	private Ubicacion ubicacionDeLaMuestra;
	private ZonaDeCobertura zona;
	private Opinion opinionVinchucaGuasayana;
	private Opinion opinionChincheFoliada2;
	private Opinion opinionVinchucaGuasayana2;
	
	@BeforeEach
	void setUp() throws Exception {
		
		fotoVinchuca = mock(BufferedImage.class);
		opinionChincheFoliada = mock(Opinion.class);
		opinionChincheFoliada2 = mock(Opinion.class);
		opinionVinchucaGuasayana = mock(Opinion.class);
		opinionVinchucaGuasayana2 = mock(Opinion.class);
		opinionChincheFoliada = mock(Opinion.class);
		opinionChincheFoliada2 = mock(Opinion.class);
		zona = mock(ZonaDeCobertura.class);
		mock(AplicacionWeb.class);
		usuarioBasico = mock(Usuario.class);
		usuarioExperto = mock(Usuario.class);
		nahueExperto = mock(Usuario.class);
		ximeExperto = mock(Usuario.class);
		gonzaBasico_propietario = mock(Usuario.class);
		ubicacionDeLaMuestra = mock(Ubicacion.class);
	
		muestra = new Muestra(gonzaBasico_propietario, opinionVinchucaGuasayana, fotoVinchuca, ubicacionDeLaMuestra , LocalDate.of(2020,12,01));
		muestra1 = new Muestra(usuarioBasico, opinionChincheFoliada, fotoVinchuca, ubicacionDeLaMuestra , LocalDate.of(2020,05,05));
		
		when(nahueExperto.esUsuarioExperto()).thenReturn(true);
		when(ximeExperto.esUsuarioExperto()).thenReturn(true);
		when(usuarioBasico.esUsuarioBasico()).thenReturn(true);
		when(usuarioExperto.esUsuarioExperto()).thenReturn(true);
		
		when(opinionVinchucaGuasayana.getDescripcion()).thenReturn("VINCHUCA_GUAYASANA");
		when(opinionChincheFoliada2.getDescripcion()).thenReturn("CHINCHE_FOLIADA");
		when(opinionChincheFoliada.getDescripcion()).thenReturn("CHINCHE_FOLIADA");
		when(opinionVinchucaGuasayana.getFechaDeEmision()).thenReturn(LocalDate.now());
	}

	@Test
	void test_UnaMuestraTieneUnaEspecieDeVinchucaVINCHUCA_GUAYASANA() {
		assertEquals("VINCHUCA_GUAYASANA", muestra.getEspecieDeVinchuca());
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
		
		muestra.agregarOpinion(opinionVinchucaGuasayana, nahueExperto);
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
		assertTrue(muestra.contieneLaOpinion(opinionVinchucaGuasayana));
	}
	
	@Test
	void test_UnaMuestraCreadaPorUnUsuarioConEstadoBasicoTieneUnEstadoVotada() throws Exception {
		muestra = new Muestra(usuarioBasico, opinionChincheFoliada, fotoVinchuca, ubicacionDeLaMuestra , LocalDate.of(2020,12,01));
		
		assertEquals("votada", muestra.getEstadoActual());
	}

	@Test
	void test_UnaMuestraCreadaPorUnUsuarioConEstadoExpertoTieneUnEstadoVotadaPorExperto() throws Exception {
		muestra = new Muestra(nahueExperto, opinionChincheFoliada, fotoVinchuca, ubicacionDeLaMuestra , LocalDate.of(2020,12,01));
		
		assertEquals("votadaPorExperto", muestra.getEstadoActual());
	}
	
	@Test
	void test_UnaMuestraNoPuedeVolverASerOpinadaPorSuPropietario() throws Exception {
		try {
			muestra.agregarOpinion(opinionChincheFoliada, usuarioBasico);
	    }
	    catch (Exception exception){
	    	assertEquals(exception.getMessage(), "El usuario no puede opinar sobre la muestra.");
	     } 
	}
	
	@Test
	void test_UnaMuestraNoContieneLaOpinionDeUnUsuarioQueNoOpino() {
		assertTrue(muestra.noContieneLaOpinionDelUsuario(ximeExperto));
	}
	
	@Test
	void test_UnaMuestraCuandoAgregaUnaOpinionSeLeAgregaAsuHistorialDeOpiniones() throws Exception {
		muestra.agregarOpinion(opinionChincheFoliada, ximeExperto);
		
		assertTrue(muestra.contieneLaOpinion(opinionChincheFoliada)); 
	}
	
	@Test
	void test_laCantidadDeOpinionesEnElHistorialDeOpinionesEs2() throws Exception {
		muestra.agregarOpinion(opinionChincheFoliada, nahueExperto);
		muestra.agregarOpinion(opinionChincheFoliada2, ximeExperto);
		
		assertEquals(2, muestra.getHistorialDeOpiniones().size());
	}
	
	@Test
	void test_unaVezQueEntranLosExpertos_LasOpinionesQueValeSonLasDeLosExpertos() throws Exception {
		assertTrue(muestra.contieneLaOpinion(opinionVinchucaGuasayana));
		
		muestra.agregarOpinion(opinionChincheFoliada, nahueExperto);
		
		assertFalse(muestra.contieneLaOpinion(opinionVinchucaGuasayana));
	}
	/*
	@Test
	void test_CuandoUnaMuestraEstaEnEstadoVotadaPorExpertoNoPuedeVotarUnUsuarioBasico() throws Exception {
		muestra.agregarOpinion(opinionChincheFoliada, nahueExperto);
		
		assertFalse(muestra.usuarioAptoParaVotar(usuarioBasico));
	}
	*/
	@Test
	void test_cuandoUnUsuarioExpertoOpinaSobreUnaMuestraEstaQuedaVotadaPorExperto() throws Exception {
		muestra.agregarOpinion(opinionChincheFoliada, ximeExperto);
		
		assertEquals("votadaPorExperto", muestra.getEstadoActual());
	}
	
	@Test
	void test_cuandoDosUsuariosExpertosCoincidenEnLaOpinionSobreUnaMuestraEstaQuedaVerificada() throws Exception {
		muestra.agregarOpinion(opinionChincheFoliada, nahueExperto);
		muestra.agregarOpinion(opinionChincheFoliada2, ximeExperto);
		
		assertEquals("verificada", muestra.getEstadoActual());
		assertTrue(muestra.coincidenDosExpertosEnSuOpinion());
	}
	
	@Test
	void test_cuandoDosUsuariosExpertosNOCoincidenEnLaOpinionSobreUnaMuestraEstaNOQuedaVerificada() throws Exception {
		muestra.agregarOpinion(opinionChincheFoliada, nahueExperto);
		muestra.agregarOpinion(opinionVinchucaGuasayana2, ximeExperto);
		
		assertEquals("votadaPorExperto", muestra.getEstadoActual());
		assertFalse(muestra.coincidenDosExpertosEnSuOpinion());
	}
	
	@Test
	void test_cuandoUnUsuarioExpertoTrataDeVotarUnaMuestraVerificadaSuOpinionNoSeAgrega() throws Exception {
		try {
			muestra.agregarOpinion(opinionChincheFoliada, nahueExperto);
			muestra.agregarOpinion(opinionChincheFoliada2, ximeExperto);
			muestra.agregarOpinion(opinionVinchucaGuasayana, usuarioExperto);
		}
		catch (Exception exception){
            assertEquals(exception.getMessage(), "Nadie puede opinar sobre muestras verificadas");
		}
		assertFalse(muestra.contieneLaOpinion(opinionVinchucaGuasayana));
		assertFalse(muestra.getEstadoDeMuestra().sePuedeVerificarMuestra(muestra));
		//assertFalse(muestra.getEstadoDeMuestra().usuarioAptoParaVotar(usuarioExperto, muestra));
	} 
	
	@Test
	void test_unaMuestraTieneUnNivelDeVerificacionVotadaSiNoOpinoNingunExperto() {
		assertEquals("votada", muestra.nivelDeVerificacion());
	}
	
	@Test
	void test_unaMuestraTieneUnNivelDeVerificacionVotadaPorExpertoSiOpinoAlMenosUnExperto() throws Exception {
		muestra.agregarOpinion(opinionChincheFoliada, usuarioExperto);
		
		assertEquals("votada", muestra.nivelDeVerificacion());
	}
	
	@Test
	void test_unaMuestraTieneUnNivelDeVerificacionVerificadaSiDosExpertosConcidieronEnSuOpinion() throws Exception {
		muestra.agregarOpinion(opinionChincheFoliada, nahueExperto);
		muestra.agregarOpinion(opinionChincheFoliada2, ximeExperto);
		
		assertEquals("verificada", muestra.nivelDeVerificacion());
	}
	/*
	@Test
	void test_unUsuarioExpertoPuedeVotarUnaMuestraVotadaPorExperto() throws Exception {
		muestra.agregarOpinion(opinionVinchucaGuasayana, nahueExperto);
	
		assertTrue(muestra.usuarioAptoParaVotar(ximeExperto));
	}
	*/ 
	@Test
	void test_unUsuarioExpertoNoPuedeVotarUnaMuestraVerificada() throws Exception {
		assertFalse(muestra.getEstadoDeMuestra().sePuedeVerificarMuestra(muestra));
	}
	
	@Test
	void test_cuandoSeLePideAUnaMuestraSuResultadoActualRetornaLaOpinionMasVotada() throws Exception {
		muestra.agregarOpinion(opinionChincheFoliada, usuarioBasico);
		muestra.agregarOpinion(opinionChincheFoliada2, usuarioExperto);
		muestra.agregarOpinion(opinionChincheFoliada2, ximeExperto);
		
		assertEquals("CHINCHE_FOLIADA", muestra.getResultadoActual());
	}
	
	@Test
	void test_cuandoUnaMuestraSeVerificaSeLeAvisaASusZonasDeCobertura() throws Exception {
		muestra.agregarZonaDeCobertura(zona);
		muestra.agregarOpinion(opinionChincheFoliada, nahueExperto);
		muestra.agregarOpinion(opinionChincheFoliada2, ximeExperto);
	
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
		Muestra muestraLejana1 = new Muestra(gonzaBasico_propietario, opinionChincheFoliada, fotoVinchuca, ubicacionLejana1 , LocalDate.of(2020,05,05));
		
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

	
	
	
	
	
	
	
