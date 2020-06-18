package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

class ZonaDeCoberturaTest {
	
	private ZonaDeCobertura zonaDeCobertura1;
	private Ubicacion epicentro1;
	private ZonaDeCobertura zonaDeCobertura2;
	private ZonaDeCobertura zonaDeCobertura3;
	private Ubicacion epicentro2;
	private Ubicacion epicentro3;

	@BeforeEach
	public void setUp() {
		epicentro1 = mock(Ubicacion.class);
		epicentro2 = mock(Ubicacion.class);
		epicentro3 = mock(Ubicacion.class);
		
		zonaDeCobertura1 = new ZonaDeCobertura("Zona uno", 5d, epicentro1);
		zonaDeCobertura2 = new ZonaDeCobertura("Zona dos", 8d, epicentro2);
		zonaDeCobertura3 = new ZonaDeCobertura("Zona tres", 12d, epicentro3);
	}

	
	@Test
	void test_Constructor() {
		
		assertEquals("Zona uno", zonaDeCobertura1.getNombre());
		assertEquals("Zona dos", zonaDeCobertura2.getNombre());
		assertEquals(5 , zonaDeCobertura1.getRadio());
		assertEquals(8 , zonaDeCobertura2.getRadio());
		assertEquals(epicentro1 , zonaDeCobertura1.getEpicentro());
	}
	
	@Test
	void test_ZonaDeCobertura1NoSeSolapaConNingunaZonaDeCobertura() {
		Set<ZonaDeCobertura> listaDeZonas = new HashSet<ZonaDeCobertura>();
		listaDeZonas.add(zonaDeCobertura2);
		listaDeZonas.add(zonaDeCobertura3);
		
		when(epicentro1.medirDistancias(epicentro2)).thenReturn(15d);
		when(epicentro1.medirDistancias(epicentro3)).thenReturn(21d);
		
		assertEquals(0 , zonaDeCobertura1.zonasQueSolapan(listaDeZonas).size());
	}
	
	@Test
	void test_ZonaDeCobertura1SeSolapaConUnaZonaDeCobertura() {
		Set<ZonaDeCobertura> listaDeZonas = new HashSet<ZonaDeCobertura>();
		listaDeZonas.add(zonaDeCobertura2);
		listaDeZonas.add(zonaDeCobertura3);
		
		when(epicentro1.medirDistancias(epicentro2)).thenReturn(6d);
		when(epicentro1.medirDistancias(epicentro3)).thenReturn(21d);
		
		assertEquals(1 , zonaDeCobertura1.zonasQueSolapan(listaDeZonas).size());
	}
	
	@Test
	void test_ZonaDeCobertura1NoTieneOrganizacionesRegistradas() {
		
		assertEquals(0 , zonaDeCobertura1.observerRegistrados().size());
	}
	
	@Test
	void test_ZonaDeCoberturaTieneUnaOrganizacionRegistrada() {
		Organizacion organizacion = mock(Organizacion.class);
		
		zonaDeCobertura1.agregarObserver(organizacion);
		
		assertEquals(1 , zonaDeCobertura1.observerRegistrados().size());
	}
	
	@Test
	void test_ZonaDeCoberturaQuitoLaUnicaOrganizacionQueTeniaYAhoraTieneCeroOrganizaciones() {
		Organizacion organizacion = mock(Organizacion.class);
		
		zonaDeCobertura1.agregarObserver(organizacion);
		zonaDeCobertura1.quitarObserver(organizacion);
		
		assertEquals(0 , zonaDeCobertura1.observerRegistrados().size());
	}
	
	
	@Test
	void test_ZonaDeCoberturaNoTieneMuestrasRegistradasYSuCantidadDeMuestrasEsCero() {
		
		assertEquals(0, zonaDeCobertura1.muestrasRegistradas().size());
	}
	
	@Test
	void test_ZonaDeCoberturaAgregarUnaNuevaMuestraASuListaDeMuestrasYNotificaALasOrganizaciones() {
		Organizacion organizacion1 = mock(Organizacion.class);
		Organizacion organizacion2 = mock(Organizacion.class);
		Ubicacion ubicacionMuestra = mock(Ubicacion.class);
		Muestra muestra = mock(Muestra.class);
		
		zonaDeCobertura1.agregarObserver(organizacion1);
		zonaDeCobertura1.agregarObserver(organizacion2);
		when(muestra.getUbicacion()).thenReturn(ubicacionMuestra);
		when(epicentro1.medirDistancias(ubicacionMuestra)).thenReturn(3d);
		zonaDeCobertura1.agregarNuevaMuestra(muestra);
		
		verify(organizacion1).actualizarNuevaMuestra(zonaDeCobertura1, muestra);
		verify(organizacion2).actualizarNuevaMuestra(zonaDeCobertura1, muestra);
		assertEquals(1, zonaDeCobertura1.muestrasRegistradas().size());
	}
	
	@Test
	void test_ZonaDeCoberturaIntentaAgregarUnaMuestraPeroNoPerteneceASuZonaDeCoberturaYSuCantidadDeMuestrasEsCero() {
		Muestra muestra = mock(Muestra.class);
		Ubicacion ubicacionMuestra = mock(Ubicacion.class);
		
		when(muestra.getUbicacion()).thenReturn(ubicacionMuestra);
		when(epicentro1.medirDistancias(ubicacionMuestra)).thenReturn(6d);
		zonaDeCobertura1.agregarNuevaMuestra(muestra);
		
		assertEquals(0, zonaDeCobertura1.muestrasRegistradas().size());
	}
	
	@Test
	void test_EnZonaDeCoberturaSeVerificaUnaMuestraYLeAvisaALasOrganizaciones() {
		Organizacion organizacion1 = mock(Organizacion.class);
		Organizacion organizacion2 = mock(Organizacion.class);
		Muestra muestra = mock(Muestra.class);
		
		zonaDeCobertura1.agregarObserver(organizacion1);
		zonaDeCobertura1.agregarObserver(organizacion2);
		zonaDeCobertura1.muestraVerificada(muestra);
		
		verify(organizacion1).actualizarNuevaVerificacion(zonaDeCobertura1, muestra);
		verify(organizacion2).actualizarNuevaVerificacion(zonaDeCobertura1, muestra);
	}

}
