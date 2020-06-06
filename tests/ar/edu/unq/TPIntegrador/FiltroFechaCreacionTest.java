package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

class FiltroFechaCreacionTest {
	
	private FechaCreacion filtro;
	private FixtureListasDeMuestrasParaFiltros lista;
	
	@BeforeEach
	public void setUp() {
		filtro = new FechaCreacion();
		lista = new FixtureListasDeMuestrasParaFiltros();
	}

	@Test
	void test_UnFiltroObtieneUnaListaDe3MuestrasAlFiltrarPorFechaDeCreacion() {
		List<Muestra> listaParaFiltrar = this.lista.listaDeMuestrasParaFiltrarPorFechaDeCreacion();
		Integer result = filtro.filtrar(listaParaFiltrar, "06/06/20").size();
		assertEquals(3, result);
	}

}
