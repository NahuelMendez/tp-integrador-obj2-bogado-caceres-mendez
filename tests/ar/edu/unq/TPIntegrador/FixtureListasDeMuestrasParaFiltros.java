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
		when(muestra1.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra1.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2016, 05, 22));
		muestra2 = mock(Muestra.class);
		when(muestra2.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra2.getFechaUltimaVotacion()).thenReturn(LocalDate.now());
		muestra3 = mock(Muestra.class);
		when(muestra3.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra3.getFechaUltimaVotacion()).thenReturn(LocalDate.now());
		muestra4 = mock(Muestra.class);
		when(muestra4.getFechaDeCreacion()).thenReturn(LocalDate.of(2019, 05, 22));
		when(muestra4.getFechaUltimaVotacion()).thenReturn(LocalDate.now());
		muestra5 = mock(Muestra.class);
		when(muestra5.getFechaDeCreacion()).thenReturn(LocalDate.of(2019, 02, 23));
		when(muestra5.getFechaUltimaVotacion()).thenReturn(LocalDate.now());
	}

	public List<Muestra> listaDeMuestrasParaFiltrarPorFechaDeCreacion() {
		ArrayList<Muestra> listaParaFiltrarPorFecha = new ArrayList<Muestra>();
		listaParaFiltrarPorFecha.add(muestra1);
		listaParaFiltrarPorFecha.add(muestra2);
		listaParaFiltrarPorFecha.add(muestra3);	
		return listaParaFiltrarPorFecha;
	}
	
	public List<Muestra> listaConUnSoloElementoDeFiltroPorFechaDeCreacion(){
		ArrayList<Muestra> listaParaFiltrarPorFecha = new ArrayList<Muestra>();
		listaParaFiltrarPorFecha.add(muestra1);
		listaParaFiltrarPorFecha.add(muestra4);
		listaParaFiltrarPorFecha.add(muestra5);	
		return listaParaFiltrarPorFecha;
	}
	
}
