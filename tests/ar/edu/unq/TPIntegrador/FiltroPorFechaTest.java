package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
		List<Muestra> listaParaFiltrar = new ArrayList<Muestra>();
		Integer result = filtroFechaCreacionIgual.filtrar(listaParaFiltrar).size();
		assertEquals(0, result);
	}

	@Test
	void test_UnFiltroObtieneUnaListaDe3MuestrasAlFiltrarPorFechaDeCreacion() {
		List<Muestra> listaParaFiltrar = this.lista.listaDeMuestrasParaFiltrarPorFechaDeCreacion();
		Integer result = filtroFechaCreacionIgual.filtrar(listaParaFiltrar).size();
		assertEquals(3, result);
	}
	
	@Test
	void test_UnFiltroDeFechaDeCreacionFechaActualObtieneUnaListaConUnSoloElementoDespuesDeFiltrar() {
		List<Muestra> listaParaFiltrar = this.lista.listaConUnSoloElementoDeFiltroPorFechaDeCreacion();
		Integer result = filtroFechaCreacionIgual.filtrar(listaParaFiltrar).size();
		assertEquals(1, result);
	}

}
