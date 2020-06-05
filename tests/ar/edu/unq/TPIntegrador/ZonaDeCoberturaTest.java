package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		
		zonaDeCobertura1 = new ZonaDeCobertura("Zona uno", 5, epicentro1);
		zonaDeCobertura2 = new ZonaDeCobertura("Zona dos", 8, epicentro2);
		zonaDeCobertura3 = new ZonaDeCobertura("Zona tres", 12, epicentro3);
	}

	@Test
	void test_ZonaDeCobertura1TieneDeNombreZonaUno() {
		
		assertEquals("Zona uno", zonaDeCobertura1.getNombre());
	}
	
	@Test
	void test_ZonaDeCobertura2TieneDeNombreZonaDos() {
		
		assertEquals("Zona dos", zonaDeCobertura2.getNombre());
	}
	
	@Test
	void test_ZonaDeCobertura1TieneRadioCinco() {
		
		assertEquals(5 , zonaDeCobertura1.getRadio());
	}
	
	@Test
	void test_ZonaDeCobertura2TieneRadioOcho() {
		
		assertEquals(8 , zonaDeCobertura2.getRadio());
	}
	
	@Test
	void test_ZonaDeCobertura1TieneEpicentro() {
		
		assertEquals(epicentro1 , zonaDeCobertura1.getEpicentro());
	}
	
	@Test
	void test_ZonaDeCobertura1NoSeSolapaConNingunaZonaDeCobertura() {
		List<ZonaDeCobertura> listaDeZonas = new ArrayList<ZonaDeCobertura>();
		listaDeZonas.add(zonaDeCobertura2);
		listaDeZonas.add(zonaDeCobertura3);
		
		when(epicentro1.medirDistancias(epicentro2)).thenReturn(15);
		when(epicentro1.medirDistancias(epicentro3)).thenReturn(21);
		
		assertEquals(0 , zonaDeCobertura1.zonasQueSolapan(listaDeZonas).size());
	}
	
	@Test
	void test_ZonaDeCobertura1SeSolapaConUnaZonaDeCobertura() {
		List<ZonaDeCobertura> listaDeZonas = new ArrayList<ZonaDeCobertura>();
		listaDeZonas.add(zonaDeCobertura2);
		listaDeZonas.add(zonaDeCobertura3);
		
		when(epicentro1.medirDistancias(epicentro2)).thenReturn(6);
		when(epicentro1.medirDistancias(epicentro3)).thenReturn(21);
		
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
		when(epicentro1.medirDistancias(ubicacionMuestra)).thenReturn(3);
		zonaDeCobertura1.agregarNuevaMuestra(muestra);
		
		verify(organizacion1).actualizar(zonaDeCobertura1, muestra, "Nueva muestra");
		verify(organizacion2).actualizar(zonaDeCobertura1, muestra, "Nueva muestra");
		assertEquals(1, zonaDeCobertura1.muestrasRegistradas().size());
	}
	
	@Test
	void test_ZonaDeCoberturaIntentaAgregarUnaMuestraPeroNoPerteneceASuZonaDeCoberturaYSuCantidadDeMuestrasEsCero() {
		Muestra muestra = mock(Muestra.class);
		Ubicacion ubicacionMuestra = mock(Ubicacion.class);
		
		when(muestra.getUbicacion()).thenReturn(ubicacionMuestra);
		when(epicentro1.medirDistancias(ubicacionMuestra)).thenReturn(6);
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
		
		verify(organizacion1).actualizar(zonaDeCobertura1, muestra, "Nueva verificacion");
		verify(organizacion2).actualizar(zonaDeCobertura1, muestra, "Nueva verificacion");
	}

}
