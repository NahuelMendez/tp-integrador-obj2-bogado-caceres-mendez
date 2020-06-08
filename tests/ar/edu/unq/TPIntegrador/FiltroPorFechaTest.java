package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

class FiltroPorFechaTest {
	
	private FixtureListasDeMuestrasParaFiltros lista;
	private FixtureFiltrosPorFechaConEstrategias filtros;
	private FechaCreacion filtroFechaCreacionIgual;
	private FechaCreacion filtroFechaCreacionMayor;
	private FechaCreacion filtroFechaCreacionMenor;
	private FechaVotacion filtroFechaVotacionIgual;
	private FechaVotacion filtroFechaVotacionMayor;
	private FechaVotacion filtroFechaVotacionMenor;
	
	
	@BeforeEach
	public void setUp() {
		lista = new FixtureListasDeMuestrasParaFiltros();
		filtros = new FixtureFiltrosPorFechaConEstrategias();
		filtroFechaCreacionIgual = filtros.nuevoFiltroFechaCreacionConEstrategiaIgual();
		filtroFechaCreacionMayor = filtros.nuevoFiltroFechaCreacionConEstrategiaMayor();
		filtroFechaCreacionMenor = filtros.nuevoFiltroFechaCreacionConEstrategiaMenor();
		filtroFechaVotacionIgual = filtros.nuevoFiltroFechaVotacionConEstrategiaIgual();
		filtroFechaVotacionMayor = filtros.nuevoFiltroFechaVotacionConEstrategiaMayor();
		filtroFechaVotacionMenor = filtros.nuevoFiltroFechaVotacionConEstrategiaMenor();
	}
	
	@Test
	void test_UnFiltroFechaDeCreacionFiltraSobreUnaListaVaciaYTiene0CantidadDeElementosFiltrados() {
		Set<Muestra> listaParaFiltrar = new HashSet<Muestra>();
		Integer result = filtroFechaCreacionIgual.filtrar(listaParaFiltrar).size();
		assertEquals(0, result);
	}

	@Test
	void test_UnFiltroObtieneUnaListaDe3MuestrasAlFiltrarPorFechaDeCreacion() {
		Set<Muestra> listaParaFiltrar = this.lista.listaDeMuestrasParaFiltrarPorFechaDeCreacion();
		Integer result = filtroFechaCreacionIgual.filtrar(listaParaFiltrar).size();
		assertEquals(3, result);
	}
	
	@Test
	void test_UnFiltroDeFechaDeCreacionFechaActualObtieneUnaListaConUnSoloElementoDespuesDeFiltrar() {
		Set<Muestra> listaParaFiltrar = this.lista.listaConUnSoloElementoDeFiltroPorFechaDeCreacion();
		Integer result = filtroFechaCreacionIgual.filtrar(listaParaFiltrar).size();
		assertEquals(1, result);
	}
	
	@Test
	void test_UnFiltroFechaDeCreacionConEstrategiaMayorObtiene0CantidadDeElementosAlRecorrerUnaListaVacia() {
		Set<Muestra> listaParaFiltrar = new HashSet<Muestra>();
		Integer result = filtroFechaCreacionMayor.filtrar(listaParaFiltrar).size();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnFiltroFechaDeCreacionConEstrategiaMayorObtiene2CantidadDeElementosAlFiltrarPorFechasMayoresALaSolicitada() {
		Set<Muestra> listaParaFiltrar = this.lista.listaConUnSoloElementoDeFiltroPorFechaDeCreacion();
		Integer result = filtroFechaCreacionMayor.filtrar(listaParaFiltrar).size();
		assertEquals(2, result);
	}
	
	@Test
	void test_UnFiltroFechaDeCreacionConEstrategiaMayorObtiene0CantidadDeElementosAlFiltrarPorLaFechaSolicitada() {
		Set<Muestra> listaParaFiltrar = this.lista.listaConNingunaFechaDeCreacionMayorANowY2UltimaOpinionAntesDeNow();
		Integer result = filtroFechaCreacionMayor.filtrar(listaParaFiltrar).size();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnFiltroFechaDeCreacionConEstrategiaMenorObtiene0ElementosAlFiltrarUnaListaVacia() {
		Set<Muestra> listaParaFiltrar = new HashSet<Muestra>();
		Integer result = filtroFechaCreacionMenor.filtrar(listaParaFiltrar).size();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnFiltroFechaDeCreacionConEstrategiaMenorObtiene1ElementosAlFiltrar() {
		Set<Muestra> listaParaFiltrar = this.lista.listaConNingunaFechaDeCreacionMayorANowY2UltimaOpinionAntesDeNow();
		Integer result = filtroFechaCreacionMenor.filtrar(listaParaFiltrar).size();
		assertEquals(1, result);
	}
	
	@Test
	void test_UnFiltroFechaDeCreacionConEstrategiaMenorObtiene2ElementosAlFiltrar() {
		Set<Muestra> listaParaFiltrar = this.lista.listaCon2ElementosConFechaMenorANow();
		Integer result = filtroFechaCreacionMenor.filtrar(listaParaFiltrar).size();
		assertEquals(2, result);
	}
	
	@Test
	void test_UnFiltroFechaDeVotacionConEstrategiaIgualObtiene0ElementosAlFiltrarUnaListaVacia() {
		Set<Muestra> listaParaFiltrar = new HashSet<Muestra>();
		Integer result = filtroFechaVotacionIgual.filtrar(listaParaFiltrar).size();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnFiltroFechaDeVotacionConEstrategiaIgualObtiene() {
		Set<Muestra> listaParaFiltrar = this.lista.listaDeMuestrasParaFiltrarPorFechaDeCreacion();
		Integer result = filtroFechaVotacionIgual.filtrar(listaParaFiltrar).size();
		assertEquals(2, result);
	}
	
	@Test
	void test_UnFiltroFechaDeVotacionConEstrategiaMayorObtiene0CantidadDeElementosAlRecorrerUnaListaVacia() {
		Set<Muestra> listaParaFiltrar = new HashSet<Muestra>();
		Integer result = filtroFechaVotacionMayor.filtrar(listaParaFiltrar).size();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnFiltroFechaDeVotacionConEstrategiaMayorObtiene2CantidadDeElementosAlFiltrarPorFechasMayoresALaSolicitada() {
		Set<Muestra> listaParaFiltrar = this.lista.listaConUnSoloElementoDeFiltroPorFechaDeCreacion();
		Integer result = filtroFechaVotacionMayor.filtrar(listaParaFiltrar).size();
		assertEquals(3, result);
	}
	
	@Test
	void test_UnFiltroFechaDeVotacionConEstrategiaMenorObtiene0ElementosAlFiltrarUnaListaVacia() {
		Set<Muestra> listaParaFiltrar = new HashSet<Muestra>();
		Integer result = filtroFechaVotacionMenor.filtrar(listaParaFiltrar).size();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnFiltroFechaDeVotacionConEstrategiaMenorObtiene1ElementosAlFiltrar() {
		Set<Muestra> listaParaFiltrar = this.lista.listaConNingunaFechaDeCreacionMayorANowY2UltimaOpinionAntesDeNow();
		Integer result = filtroFechaVotacionMenor.filtrar(listaParaFiltrar).size();
		assertEquals(1, result);
	}
	
	@Test
	void test_UnFiltroFechaDeVotacionConEstrategiaMenorObtiene2ElementosAlFiltrar() {
		Set<Muestra> listaParaFiltrar = this.lista.listaCon2ElementosConFechaMenorANow();
		Integer result = filtroFechaVotacionMenor.filtrar(listaParaFiltrar).size();
		assertEquals(2, result);
	}

}
