package ar.edu.unq.TPIntegrador;

import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FixtureListasDeMuestrasParaFiltros {
	
	@Mock private Muestra muestra1;
	@Mock private Muestra muestra2;
	@Mock private Muestra muestra3;
	@Mock private Muestra muestra4;
	@Mock private Muestra muestra5;
	
	public FixtureListasDeMuestrasParaFiltros() {
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestra5 = mock(Muestra.class);
	}

	public List<Muestra> listaDeMuestrasParaFiltrarPorFechaDeCreacion() {
		ArrayList<Muestra> listaParaFiltrarPorFecha = new ArrayList<Muestra>();
		listaParaFiltrarPorFecha.add(muestra1);
		when(muestra1.getFechaDeCreacion()).thenReturn(LocalDate.now());
		listaParaFiltrarPorFecha.add(muestra2);
		when(muestra1.getFechaDeCreacion()).thenReturn(LocalDate.now());
		listaParaFiltrarPorFecha.add(muestra3);
		when(muestra3.getFechaDeCreacion()).thenReturn(LocalDate.now());
		return listaParaFiltrarPorFecha;
	}
	
}
