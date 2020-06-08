package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

class FiltroPorStringTest {
	
	private FiltroTipoDeInsecto filtroInsecto;
	private FiltroPorNivelDeVerificacion filtroVerificacion;
	private Set<Muestra> listaParaFiltrar;
	@Mock private Muestra muestra1;
	@Mock private Muestra muestra2;
	
	@BeforeEach
	public void setUp() {
		filtroInsecto = new FiltroTipoDeInsecto("VINCHUCA_INFESTANS");
		filtroVerificacion = new FiltroPorNivelDeVerificacion("votada");
		listaParaFiltrar = new HashSet<Muestra>();
		muestra1 = mock(Muestra.class);
		when(muestra1.getEspecieDeVinchuca()).thenReturn("VINCHUCA_INFESTANS");
		when(muestra1.nivelDeVerificacion()).thenReturn("verificada");
		muestra2 = mock(Muestra.class);
		when(muestra2.getEspecieDeVinchuca()).thenReturn("VINCHUCA_INFESTANS");
		when(muestra2.nivelDeVerificacion()).thenReturn("votada");
		listaParaFiltrar.add(muestra1);
		listaParaFiltrar.add(muestra2);
		
	}

	@Test
	void test_UnNuevoFiltroPorEspecieDeInsectoObtiene0CantidadDeElementosAlFiltrarUnaListaVacia() {
		Set<Muestra> listaVacia = new HashSet<Muestra>();
		Integer result = filtroInsecto.filtrar(listaVacia).size();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnFiltroPorEspecieDeInsectoObtiene2CantidadDeElementosAlFiltrarUnaListaArmada() {
		Integer result = filtroInsecto.filtrar(listaParaFiltrar).size();
		assertEquals(2, result);
	}
	
	@Test
	void test_UnNuevoFiltroPorNivelDeVerificacionObtiene0CantidadDeElementosAlFiltrarUnaListaVacia() {
		Set<Muestra> listaVacia = new HashSet<Muestra>();
		Integer result = filtroVerificacion.filtrar(listaVacia).size();
		assertEquals(0, result);
	}
	
	@Test
	void test_UnFiltroPorNivelDeVerificacionObtiene2CantidadDeElementosAlFiltrarUnaListaConCondicionVotada() {
		Integer result = filtroVerificacion.filtrar(listaParaFiltrar).size();
		assertEquals(1, result);
	}

}
