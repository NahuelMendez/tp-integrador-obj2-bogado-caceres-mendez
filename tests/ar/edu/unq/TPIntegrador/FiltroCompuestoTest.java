package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;
import ar.edu.unq.TPIntegrador.filtro.*;

class FiltroCompuestoTest {

	private FiltroCompuesto filtroCompuestoAnd;
	private FiltroCompuesto filtroCompuestoOr;
	private FiltroPorFecha filtroSimple1;
	private FiltroPorString filtroSimple2;
	private Set<Muestra> listaDeMuestras;
	private Muestra muestra1;
	private Muestra muestra2;
	
	

	@BeforeEach
	public void setUp() {
		filtroCompuestoAnd = new FiltroCompuestoAnd();
		filtroCompuestoOr = new FiltroCompuestoOr();
		filtroSimple1 = mock(FiltroPorFecha.class);
		filtroSimple2 = mock(FiltroPorString.class);
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		listaDeMuestras = new HashSet<Muestra>();
		
		listaDeMuestras.add(muestra1);
		listaDeMuestras.add(muestra2);
	}
	
	@Test
	void test_FiltroCompuestoNoTieneFiltrosYSuCantidadDeFiltroEsCero() {
		
		assertEquals(0, filtroCompuestoAnd.getFiltros().size());
	}
	
	@Test
	void test_FiltroCompuestoTiene1FiltroYSuCantidadDeFiltroEsUno() {
		
		filtroCompuestoAnd.agregarFiltro(filtroSimple1);
		
		assertEquals(1, filtroCompuestoAnd.getFiltros().size());
	}
	
	@Test
	void test_FiltroCompuestoRemueveElUnicoElementoQueTieneYLaCantidadDeFiltrosEsCero() {
		
		filtroCompuestoAnd.agregarFiltro(filtroSimple1);
		filtroCompuestoAnd.removerFiltro(filtroSimple1);
		
		assertEquals(0, filtroCompuestoAnd.getFiltros().size());
	}
	
	@Test
	void test_FiltroCompuestoAndFiltraDosMuestrasQueCumplanConAmbosFiltrosSimplesYLasMuestrasObtenidasEsUna() {
		Set<Muestra> listaFiltrada = new HashSet<Muestra>();
		listaFiltrada.add(muestra1);
		
		filtroCompuestoAnd.agregarFiltro(filtroSimple1);
		filtroCompuestoAnd.agregarFiltro(filtroSimple2);
		
		when(filtroSimple1.filtrar(listaDeMuestras)).thenReturn(listaDeMuestras);
		when(filtroSimple2.filtrar(listaDeMuestras)).thenReturn(listaFiltrada);
		
		assertEquals(1, filtroCompuestoAnd.filtrar(listaDeMuestras).size());
		verify(filtroSimple1).filtrar(listaDeMuestras);
		verify(filtroSimple2).filtrar(listaDeMuestras);
	}
	
	@Test
	void test_FiltroCompuestoOrFiltraDosMuestrasQueCumplanConAlMenosUnFiltroSimpleYLasMuestrasObtenidadSonDos() {
		Set<Muestra> listaFiltrada1 = new HashSet<Muestra>();
		listaFiltrada1.add(muestra1);
		Set<Muestra> listaFiltrada2 = new HashSet<Muestra>();
		listaFiltrada2.add(muestra2);
		
		filtroCompuestoOr.agregarFiltro(filtroSimple1);
		filtroCompuestoOr.agregarFiltro(filtroSimple2);
		
		when(filtroSimple1.filtrar(listaDeMuestras)).thenReturn(listaFiltrada1);
		when(filtroSimple2.filtrar(listaDeMuestras)).thenReturn(listaFiltrada2);
		
		assertEquals(2, filtroCompuestoOr.filtrar(listaDeMuestras).size());
		verify(filtroSimple1).filtrar(listaDeMuestras);
		verify(filtroSimple2).filtrar(listaDeMuestras);
	}
	
	@Test
	void test_FiltroCompuestoOrFiltraDosMuestrasConUnFiltroSimpleYUnFiltroCompuestoAndConDosFiltroSimplesYLaCantidadDeMuestrasEsUno() {
		Set<Muestra> listaFiltrada1 = new HashSet<Muestra>();
		listaFiltrada1.add(muestra1);
		Set<Muestra> listaFiltrada2 = new HashSet<Muestra>();
		listaFiltrada2.add(muestra2);
		Set<Muestra> listaVacia = new HashSet<Muestra>();
		
		filtroCompuestoAnd.agregarFiltro(filtroSimple1);
		filtroCompuestoAnd.agregarFiltro(filtroSimple2);
		filtroCompuestoOr.agregarFiltro(filtroCompuestoAnd);
		filtroCompuestoOr.agregarFiltro(filtroSimple2);
		
		when(filtroSimple1.filtrar(listaDeMuestras)).thenReturn(listaFiltrada1);
		when(filtroSimple2.filtrar(listaDeMuestras)).thenReturn(listaVacia);
		when(filtroSimple2.filtrar(listaDeMuestras)).thenReturn(listaFiltrada2);
		
		assertEquals(1, filtroCompuestoOr.filtrar(listaDeMuestras).size());
		verify(filtroSimple1).filtrar(listaDeMuestras);
		verify(filtroSimple2, times(2)).filtrar(listaDeMuestras);
	}

}
