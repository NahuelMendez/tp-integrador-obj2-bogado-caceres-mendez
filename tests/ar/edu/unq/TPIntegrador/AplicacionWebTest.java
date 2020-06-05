package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AplicacionWebTest {

	private AplicacionWeb aplicacionWeb;

	@BeforeEach
	public void setUp() {
		aplicacionWeb = new AplicacionWeb();
	}
	
	@Test
	void test_AplicacionWebNoTieneMuestrasRegistradasYSuCantidadDeMuestrasEsCero() {
		
		assertEquals(0, aplicacionWeb.muestrasRegistradas().size());
	}
	
	@Test
	void test_AplicacionWebTieneUnaMuestraRegistradaYSuCantidadDeMuestrasEsUno() {
		Muestra muestra = mock(Muestra.class);
		
		aplicacionWeb.registrarMuestra(muestra);
		
		assertEquals(1, aplicacionWeb.muestrasRegistradas().size());
	}
	
	@Test
	void test_AplicacionWebNoTieneUsuarioRegistradosYSuCantidadDeUsuariosEsCero() {
		
		assertEquals(0, aplicacionWeb.usuariosRegistrados().size());
	}
	
	@Test
	void test_AplicacionWebTieneUnUsuarioRegistradoYSuCantidadDeUsuariosEsUno() {
		Usuario usuario = mock(Usuario.class);
		
		aplicacionWeb.registrarUsuario(usuario);
		
		assertEquals(1, aplicacionWeb.usuariosRegistrados().size());
	}
	
	@Test
	void test_AplicacionWebNoTieneZonasDeCoberturaRegistradasYSuCantidadDeZonasDeCoberturaEsCero() {
		
		assertEquals(0, aplicacionWeb.zonasDeCobertura().size());
	}
	
	@Test
	void test_AplicacionWebTieneUnaZonaDeCoberturaRegistradaYSuCantidadDeZonasDeCoberturaEsUna() {
		ZonaDeCobertura zonaDeCobertura = mock(ZonaDeCobertura.class);
		
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura);
		
		assertEquals(1, aplicacionWeb.zonasDeCobertura().size());
	}
	
	@Test
	void test_AplicacionWebBuscaCualesSonLasMuestrasQueSeRegistraronCercaDe1KilometroDeUnaMuestraDadaYLeDelegaLaResponsabilidadALaMuestra() {
		Muestra muestra1 = mock(Muestra.class);
		Muestra muestra2 = mock(Muestra.class);
		Muestra muestra3 = mock(Muestra.class);
		Set<Muestra> listaDeMuestras = new HashSet<Muestra>();
		
		listaDeMuestras.add(muestra2);
		listaDeMuestras.add(muestra3);
		aplicacionWeb.registrarMuestra(muestra1);
		aplicacionWeb.registrarMuestra(muestra2);
		aplicacionWeb.registrarMuestra(muestra3);
		aplicacionWeb.muestrasCercanas(muestra1, 1);
		
		
		verify(muestra1).muestrasCercanas(listaDeMuestras,1);
	}
	
	@Test
	void test_AplicacionWebBuscaCualesSonLasMuestrasQueSeRegistraronCercaDe1KilometroDeUnaMuestraDadaYLaCantidadDeMuestrasCercasEsCero() {
		Muestra muestra1 = mock(Muestra.class);
		Muestra muestra2 = mock(Muestra.class);
		Muestra muestra3 = mock(Muestra.class);
		Set<Muestra> listaDeMuestras = new HashSet<Muestra>();
		Set<Muestra> listaVacia = new HashSet<Muestra>();
		
		listaDeMuestras.add(muestra2);
		listaDeMuestras.add(muestra3);
		aplicacionWeb.registrarMuestra(muestra1);
		aplicacionWeb.registrarMuestra(muestra2);
		aplicacionWeb.registrarMuestra(muestra3);
		aplicacionWeb.muestrasCercanas(muestra1,1);
		when(muestra1.muestrasCercanas(listaDeMuestras,1)).thenReturn(listaVacia);
		
		assertEquals(0, aplicacionWeb.muestrasCercanas(muestra1, 1).size());
	}
	
	@Test
	void test_AplicacionWebRegistraUnaNuevaMuestraYLeAvisaALasZonasDeCoberturaRegistradas() {
		ZonaDeCobertura zonaDeCobertura1 = mock(ZonaDeCobertura.class);
		ZonaDeCobertura zonaDeCobertura2 = mock(ZonaDeCobertura.class);
		Muestra muestra = mock(Muestra.class);
		
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura1);
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura2);
		aplicacionWeb.registrarMuestra(muestra);
		
		verify(zonaDeCobertura1).agregarNuevaMuestra(muestra);
		verify(zonaDeCobertura2).agregarNuevaMuestra(muestra);
	}
	

}
