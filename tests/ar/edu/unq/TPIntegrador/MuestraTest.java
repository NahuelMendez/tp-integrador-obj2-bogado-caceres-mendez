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
	private Usuario gonzaBasico_propietario;
	private Usuario usuarioBasico;
	private Usuario nahueExperto;
	private Usuario ximeExperto;
	private Usuario userExperto;
	private Opinion opinion;
	
	private Muestra muestra1;
	private Usuario userPropietario;
	private BufferedImage fotoVinchuca;
	private Ubicacion ubicacionVinchuca;
	public Opinion opinion2;
	private ZonaDeCobertura zona;
	private AplicacionWeb app;


	@BeforeEach
	void setUp() throws Exception {
		userPropietario = mock(Usuario.class);
		fotoVinchuca = mock(BufferedImage.class);
		
		opinion2 = mock(Opinion.class);
		zona = mock(ZonaDeCobertura.class);
		app = mock(AplicacionWeb.class);
		
		gonzaBasico_propietario = new Usuario("Gonza", app);
		gonzaBasico_propietario.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
		nahueExperto = new Usuario("Nahue", app);
		nahueExperto.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		ximeExperto = new Usuario("Xime", app);
		ximeExperto.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		userExperto = new Usuario("User", app);
		userExperto.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		usuarioBasico = new Usuario("Pepite", app);
		usuarioBasico.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
		opinion = new Opinion(Descripcion.VINCHUCA_SORDIDA);
		
		ubicacionVinchuca = new Ubicacion(1.5, 3.0);
	
		//Muestra con instancias de clases concretas
		muestra = new Muestra(gonzaBasico_propietario, opinion, fotoVinchuca, ubicacionVinchuca , LocalDate.of(2020,12,01));
		
		//Muestra con dummy objects.
		muestra1 = new Muestra(userPropietario, opinion2, fotoVinchuca, ubicacionVinchuca , LocalDate.of(2020,05,05));
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
		assertEquals(ubicacionVinchuca, muestra.getUbicacion());
	}
	
	@Test
	void test_UnaMuestraTieneUnPropietarioDeNombreJuan() {
		when(muestra1.getIdentificacionPropietarioDeLaMuestra()).thenReturn("juan");
		assertEquals("juan", muestra1.getIdentificacionPropietarioDeLaMuestra());
	}

	@Test
	void test_UnaMuestraTieneUnPropietarioDeNombreGonza() {
		assertEquals("Gonza", muestra.getIdentificacionPropietarioDeLaMuestra());
	}
	
	@Test
	void test_UnaMuestraTieneUnaFechaDeCreacion2020_12_01() {
		assertEquals(LocalDate.of(2020,12,01), muestra.getFechaDeCreacion());
	}
	
	@Test
	void test_UnaMuestraTieneUnaFechaDeUltimaVotacion2020_06_06() {
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
		assertTrue(muestra.getHistorialDeOpiniones().containsValue(opinion));
	}
	
	@Test
	void test_UnaMuestraCreadaPorUnUsuarioConEstadoBasicoTieneUnEstadoVotada() {
		assertEquals(new EstadoDeMuestraVotada().getClass(), muestra.getEstadoDeMuestra().getClass());
	}

	@Test
	void test_UnaMuestraNoContieneLaOpinionDeUnUsuarioQueNoOpino() {
		assertTrue(muestra.noContieneLaOpinionDelUsuario(ximeExperto));
	}
	
	@Test
	void test_UnaMuestraTieneUnEstadoAcordeASuPropietario_MuestraVotada_UsuarioBasico() {
		assertEquals(new EstadoDeMuestraVotada().getClass(), muestra.getEstadoSegunEstadoDeUsuario(gonzaBasico_propietario).getClass());
	}
	
	@Test
	void test_UnaMuestraNoPuedeVolverASerComentadaPorSuPropietario() throws Exception {
		try {
			muestra.agregarOpinion(opinion2, gonzaBasico_propietario);
	    }
	    catch (Exception exception){
	               assertEquals(exception.getMessage(), "El usuario no puede opinar sobre la muestra.");
	     } 
	}
	
	@Test
	void test_UnaMuestraCuandoAgregaUnaOpinionSeLeAgregaAsuHistorialDeOpiniones() throws Exception {
		muestra.agregarOpinion(opinion2, usuarioBasico);
		assertTrue(muestra.getHistorialDeOpiniones().containsKey(usuarioBasico)); 
		assertEquals(new EstadoDeMuestraVotada().getClass(), muestra.getEstadoDeMuestra().getClass());
	}
	
	@Test
	void test_UnaMuestraEstaEnEstadoVotadaPorExperto() throws Exception {
		muestra.cerrarOpinionesParaUsuariosBasicos();
		assertEquals(new EstadoMuestraVotadaPorExperto().getClass(), muestra.getEstadoDeMuestra().getClass());
	}
	
	@Test
	void test_CuandoUnaMuestraEstaEnEstadoVotadaPorExpertoNoPuedeVotarUnUsuarioBasico() throws Exception {
		muestra.cerrarOpinionesParaUsuariosBasicos();
		assertFalse(muestra.usuarioAptoParaVotar(usuarioBasico));
	}
	
	@Test
	void test_cuandoUnUsuarioExpertoOpinaSobreUnaMuestraEstaQuedaVotadaPorExperto() throws Exception {
		muestra.agregarOpinion(opinion, ximeExperto);
		assertEquals(new EstadoMuestraVotadaPorExperto().getClass(), muestra.getEstadoDeMuestra().getClass());
		assertEquals(LocalDate.now(), muestra.getFechaUltimaVotacion());
		assertTrue(muestra.getHistorialDeOpiniones().containsKey(ximeExperto));
	}

	@Test
	void test_cuandoDosUsuariosExpertosCoincidenEnLaOpinionSobreUnaMuestraEstaQuedaVerificada() throws Exception {
		muestra.agregarOpinion(opinion, nahueExperto);
		muestra.agregarOpinion(opinion, ximeExperto);
		assertEquals(new EstadoDeMuestraVerificada().getClass(), muestra.getEstadoDeMuestra().getClass());
		assertEquals(LocalDate.now(), muestra.getFechaUltimaVotacion());
		assertTrue(muestra.getHistorialDeOpiniones().containsKey(nahueExperto));
	}
	
	@Test
	void test_CuandoUnUsuarioExpertoTrataDeVotarUnaMuestraVerificadaLaOpinionNoSeAgrega() throws Exception {
		try {
			muestra.agregarOpinion(opinion, nahueExperto);
			muestra.agregarOpinion(opinion, ximeExperto);
			muestra.agregarOpinion(opinion2, userExperto);
		}
		catch (Exception exception){
            assertEquals(exception.getMessage(), "Nadie puede opinar sobre muestras verificadas");
			
		}
		assertEquals(new EstadoDeMuestraVerificada().getClass(), muestra.getEstadoDeMuestra().getClass());
		assertFalse(muestra.getHistorialDeOpiniones().containsKey(userExperto));
		assertFalse(muestra.getHistorialDeOpiniones().containsValue(opinion2));
	}

	@Test
	void test_unUsuarioExpertoPuedeVotarUnaMuestraVotadaPorExperto() throws Exception {
		muestra.agregarOpinion(opinion, nahueExperto);
		
		assertTrue(muestra.usuarioAptoParaVotar(ximeExperto));
	}
	
	
	@Test
	void test_cuandoSeLePideAUnaMuestraSuResultadoActualRetornaSusOpiniones() throws Exception {
		ArrayList<String> opiniones = new ArrayList<String>();
		opiniones.add("VINCHUCA_SORDIDA");
		opiniones.add("VINCHUCA_SORDIDA");
		opiniones.add("VINCHUCA_SORDIDA");
		
		muestra.agregarOpinion(opinion, nahueExperto);
		muestra.agregarOpinion(opinion, ximeExperto);
		assertEquals(opiniones, muestra.getOpiniones());
	}
	
	@Test
	void test_cuandoSeLePideAUnaMuestraSuResultadoActualRetornaLaOpinionMasOpinada() throws Exception {
		muestra.agregarOpinion(opinion, nahueExperto);
		muestra.agregarOpinion(opinion, ximeExperto);
		assertEquals("VINCHUCA_SORDIDA", muestra.getResultadoActual());
	}
	
	@Test
	void test_unaMuestraSeVerificaSeLeAvisaASusZonasDeCobertura() throws Exception {
		muestra.agregarZonaDeCobertura(zona);
		muestra.agregarOpinion(opinion, nahueExperto);
		muestra.agregarOpinion(opinion, ximeExperto);
		
		verify(zona, atLeastOnce()).muestraVerificada(muestra);
	}
	
	@Test
	void test_cuandoUnaMuestraSeVerificaCambiaSuNivelDeVerificacion() throws Exception {
		muestra.agregarZonaDeCobertura(zona);
		muestra.agregarOpinion(opinion, nahueExperto);
		muestra.agregarOpinion(opinion, ximeExperto);
		
		assertEquals("verificada", muestra.nivelDeVerificacion());
	}
	
	@Test
	void test_UnaMuestraSinVerificarTieneNivelDeVerificacionVotada() throws Exception {
		assertEquals("votada", muestra.nivelDeVerificacion());
	}

	@Test
	void test_unaMuestraRetornaSuListaDeMuestrasCercanas(){
		Ubicacion ubicacionCercana1 = new Ubicacion(1.5, 3.0);
		Ubicacion ubicacionCercana2 = new Ubicacion(2.1, 1.0);   
		Ubicacion ubicacionLejana1 = new Ubicacion(120.0, 343.7);    
		Ubicacion ubicacionLejana2 = new Ubicacion(243.0, 123.0);
		
		Muestra muestraCercana1 = new Muestra(userPropietario, opinion2, fotoVinchuca, ubicacionCercana1 , LocalDate.of(2020,05,05));
		Muestra muestraCercana2 = new Muestra(userPropietario, opinion2, fotoVinchuca, ubicacionCercana2 , LocalDate.of(2020,05,05));
		Muestra muestraLejana1 = new Muestra(userPropietario, opinion2, fotoVinchuca, ubicacionLejana1 , LocalDate.of(2020,05,05));
		Muestra muestraLejana2 = new Muestra(userPropietario, opinion2, fotoVinchuca, ubicacionLejana2 , LocalDate.of(2020,05,05));
		
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
	
	
	
	
	
	
	
	
