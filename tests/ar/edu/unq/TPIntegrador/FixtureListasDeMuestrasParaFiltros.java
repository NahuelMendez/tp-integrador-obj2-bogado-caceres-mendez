package ar.edu.unq.TPIntegrador;

import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FixtureListasDeMuestrasParaFiltros {
	
	/* 
	 * La creacion de esta clase fue unicamente con motivo de test
	 * provee distintos tipos de listas pre armadas con mocks con distintas fechas de creacion
	 * y opinion con el fin de testear el comportamiento de los filtros frente a distintos escenarios
	*/
	
	@Mock private Muestra muestra1;
	@Mock private Muestra muestra2;
	@Mock private Muestra muestra3;
	@Mock private Muestra muestra4;
	@Mock private Muestra muestra5;
	@Mock private Muestra muestra6;
	@Mock private Muestra muestra7;
	
	FixtureListasDeMuestrasParaFiltros() {
		muestra1 = mock(Muestra.class);
		when(muestra1.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra1.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2022, 05, 22));
		muestra2 = mock(Muestra.class);
		when(muestra2.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra2.getFechaUltimaVotacion()).thenReturn(LocalDate.now());
		muestra3 = mock(Muestra.class);
		when(muestra3.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra3.getFechaUltimaVotacion()).thenReturn(LocalDate.now());
		muestra4 = mock(Muestra.class);
		when(muestra4.getFechaDeCreacion()).thenReturn(LocalDate.of(2022, 05, 22));
		when(muestra4.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2023, 05, 22));
		muestra5 = mock(Muestra.class);
		when(muestra5.getFechaDeCreacion()).thenReturn(LocalDate.of(2021, 02, 23));
		when(muestra5.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2022, 02, 23));
		muestra6 = mock(Muestra.class);
		when(muestra6.getFechaDeCreacion()).thenReturn(LocalDate.of(2007, 03, 27));
		when(muestra6.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2009, 05, 07));
		muestra7 = mock(Muestra.class);
		when(muestra7.getFechaDeCreacion()).thenReturn(LocalDate.of(2002, 03, 27));
		when(muestra7.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2012, 05, 07));
	}

	public Set<Muestra> listaDeMuestrasParaFiltrarPorFechaDeCreacion() {
		//return muestras con fechaDeCreacion [now, now, now]
		//return muestras con fechaDeVotacion [2022, now, now]
		Set<Muestra> listaParaFiltrarPorFecha = new HashSet<Muestra>();
		listaParaFiltrarPorFecha.add(muestra1);
		listaParaFiltrarPorFecha.add(muestra2);
		listaParaFiltrarPorFecha.add(muestra3);	
		return listaParaFiltrarPorFecha;
	}
	
	public Set<Muestra> listaConUnSoloElementoDeFiltroPorFechaDeCreacion(){
		//return muestras con fechaDeCreacion [now, 2022, 2021]
		//return muestras con fechaDeVotacion [2022, 2023, 2022]
		Set<Muestra> listaParaFiltrarPorFecha = new HashSet<Muestra>();
		listaParaFiltrarPorFecha.add(muestra1);
		listaParaFiltrarPorFecha.add(muestra4);
		listaParaFiltrarPorFecha.add(muestra5);	
		return listaParaFiltrarPorFecha;
	}
	
	public Set<Muestra> listaCon1MuestrasConFechaMayorDeCreacionY2ConFechaDeVotacionMayores(){
		//return muestras con fechaDeCreacion [now, now, 2021]
		//return muestras con fechaDeCreacion [2022, now, 2022]
		Set<Muestra> listaParaFiltrarPorFecha = new HashSet<Muestra>();
		listaParaFiltrarPorFecha.add(muestra1);
		listaParaFiltrarPorFecha.add(muestra3);
		listaParaFiltrarPorFecha.add(muestra5);	
		return listaParaFiltrarPorFecha;
	}
	
	public Set<Muestra> listaConNingunaFechaDeCreacionMayorANowY2UltimaOpinionAntesDeNow(){
		//return muestras con fechaDeCreacion [now, now, 2007]
		//return muestras con fechaDeCreacion [2022, now, 2009]
		Set<Muestra> listaParaFiltrarPorFecha = new HashSet<Muestra>();
		listaParaFiltrarPorFecha.add(muestra1);
		listaParaFiltrarPorFecha.add(muestra2);
		listaParaFiltrarPorFecha.add(muestra6);	
		return listaParaFiltrarPorFecha;
	}
	
	public Set<Muestra> listaCon2ElementosConFechaMenorANow(){
		//return muestras con fechaDeCreacion [now, 2007, 2002]
		//return muestras con fechaDeCreacion [2022, 2009, 2012]
		Set<Muestra> listaParaFiltrarPorFecha = new HashSet<Muestra>();
		listaParaFiltrarPorFecha.add(muestra1);
		listaParaFiltrarPorFecha.add(muestra6);
		listaParaFiltrarPorFecha.add(muestra7);	
		return listaParaFiltrarPorFecha;
	}
	
}
