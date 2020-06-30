package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;
import ar.edu.unq.TPIntegrador.filtro.IFiltro;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

class AplicacionWebTest {

	private AplicacionWeb aplicacionWeb;
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;
	private ZonaDeCobertura zonaDeCobertura1;
	private ZonaDeCobertura zonaDeCobertura2;
	private ZonaDeCobertura zonaDeCobertura3;

	@BeforeEach
	public void setUp() {
		aplicacionWeb = new AplicacionWeb();
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
	    zonaDeCobertura1 = mock(ZonaDeCobertura.class);
		zonaDeCobertura2 = mock(ZonaDeCobertura.class);
		zonaDeCobertura3 = mock(ZonaDeCobertura.class);
	}
	
	@Test
	void test_AplicacionWebNoTieneMuestrasRegistradasYSuCantidadDeMuestrasEsCero() {
		
		assertEquals(0, aplicacionWeb.muestrasRegistradas().size());
	}
	
	@Test
	void test_AplicacionWebTieneUnaMuestraRegistradaYSuCantidadDeMuestrasEsUno() {
		
		
		aplicacionWeb.registrarMuestra(muestra1);
		
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
		
		
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura1);
		
		assertEquals(1, aplicacionWeb.zonasDeCobertura().size());
	}
	
	@Test
	void test_AplicacionWebBuscaCualesSonLasMuestrasQueSeRegistraronCercaDe1KilometroDeUnaMuestraDadaYLeDelegaLaResponsabilidadALaMuestra() {
		
		Set<Muestra> listaDeMuestras = new HashSet<Muestra>();
		
		listaDeMuestras.add(muestra2);
		listaDeMuestras.add(muestra3);
		aplicacionWeb.registrarMuestra(muestra1);
		aplicacionWeb.registrarMuestra(muestra2);
		aplicacionWeb.registrarMuestra(muestra3);
		aplicacionWeb.muestrasCercanas(muestra1, 1d);
		
		
		verify(muestra1).muestrasCercanas(listaDeMuestras,1);
	}
	
	@Test
	void test_AplicacionWebBuscaCualesSonLasMuestrasQueSeRegistraronCercaDe1KilometroDeUnaMuestraDadaYLaCantidadDeMuestrasCercaEsCero() {
		Set<Muestra> listaDeMuestras = new HashSet<Muestra>();
		Set<Muestra> listaVacia = new HashSet<Muestra>();
		
		listaDeMuestras.add(muestra2);
		listaDeMuestras.add(muestra3);
		aplicacionWeb.registrarMuestra(muestra1);
		aplicacionWeb.registrarMuestra(muestra2);
		aplicacionWeb.registrarMuestra(muestra3);
		aplicacionWeb.muestrasCercanas(muestra1,1d);
		when(muestra1.muestrasCercanas(listaDeMuestras,1)).thenReturn(listaVacia);
		
		assertEquals(0, aplicacionWeb.muestrasCercanas(muestra1, 1d).size());
	}
	
	@Test
	void test_AplicacionWebRegistraUnaNuevaMuestraYLeAvisaALasZonasDeCoberturaRegistradas() {
		
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura1);
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura2);
		aplicacionWeb.registrarMuestra(muestra1);
		
		verify(zonaDeCobertura1).agregarNuevaMuestra(muestra1);
		verify(zonaDeCobertura2).agregarNuevaMuestra(muestra1);
	}
	
	@Test
	void test_AplicacionWebFiltraSuListraDeMuestraYLeDelegaLaResponsabilidadAlFiltro() {
		Set<Muestra> listaDeMuestras = new HashSet<Muestra>();
		IFiltro filtro = mock(IFiltro.class);
		
		listaDeMuestras.add(muestra1);
		listaDeMuestras.add(muestra2);
		listaDeMuestras.add(muestra3);
		aplicacionWeb.registrarMuestra(muestra1);
		aplicacionWeb.registrarMuestra(muestra2);
		aplicacionWeb.registrarMuestra(muestra3);
		aplicacionWeb.filtrarMuestras(filtro);
		
		
		verify(filtro).filtrar(listaDeMuestras);
	}
	
	@Test
	void test_AplicacionWebFiltraSuListraDeMuestraYElFiltroLeDevuelveLaMismaListaDeMuestras() {
		Set<Muestra> listaDeMuestras = new HashSet<Muestra>();
		IFiltro filtro = mock(IFiltro.class);
		
		listaDeMuestras.add(muestra1);
		listaDeMuestras.add(muestra2);
		listaDeMuestras.add(muestra3);
		aplicacionWeb.registrarMuestra(muestra1);
		aplicacionWeb.registrarMuestra(muestra2);
		aplicacionWeb.registrarMuestra(muestra3);
		when(filtro.filtrar(listaDeMuestras)).thenReturn(listaDeMuestras);
		
		assertEquals(listaDeMuestras, aplicacionWeb.filtrarMuestras(filtro));
	}
	
	@Test
	void test_AplicacionWebQuiereSaberCualesSonLasZonasQueSeSolapanConUnaZonaDeCoberturaYLeDelegaLaResponsabilidadALaZonaDecobertura() {
		
		Set<ZonaDeCobertura> zonasDeCobertura = new HashSet<ZonaDeCobertura>();
		
		zonasDeCobertura.add(zonaDeCobertura2);
		zonasDeCobertura.add(zonaDeCobertura3);
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura1);
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura2);
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura3);
		aplicacionWeb.zonasQueSeSolapanCon(zonaDeCobertura1);
		
		verify(zonaDeCobertura1).zonasQueSolapan(zonasDeCobertura);
	}
	
	@Test
	void test_AplicacionWebQuiereSaberCualesSonLasZonasQueSeSolapanConUnaZonaDeCoberturaYLaZonaDeCoberturaLeRetornaUnaLista() {
		
		Set<ZonaDeCobertura> zonasDeCobertura = new HashSet<ZonaDeCobertura>();
		
		zonasDeCobertura.add(zonaDeCobertura2);
		zonasDeCobertura.add(zonaDeCobertura3);
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura1);
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura2);
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura3);
		when(zonaDeCobertura1.zonasQueSolapan(zonasDeCobertura)).thenReturn(zonasDeCobertura);
		
		assertEquals(zonasDeCobertura,aplicacionWeb.zonasQueSeSolapanCon(zonaDeCobertura1));
	}
	
	@Test
	void test_AplicacionWebActualizaLaCategoriaDeTodosLosUsuarios() {
		Usuario usuario1 = mock(Usuario.class);
		Usuario usuario2 = mock(Usuario.class);
		
		aplicacionWeb.registrarUsuario(usuario1);
		aplicacionWeb.registrarUsuario(usuario2);
		aplicacionWeb.actualizarCategoriaDeUsuariosALaFecha();
		
		verify(usuario1).actualizarCategoria();
		verify(usuario2).actualizarCategoria();
	}

}
