package ar.edu.unq.TPIntegrador;

import java.time.LocalDate;

import ar.edu.unq.TPIntegrador.filtro.EstrategiaIgualAFecha;
import ar.edu.unq.TPIntegrador.filtro.EstrategiaMayorAFecha;
import ar.edu.unq.TPIntegrador.filtro.EstrategiaMenorAFecha;
import ar.edu.unq.TPIntegrador.filtro.FiltroFechaCreacion;
import ar.edu.unq.TPIntegrador.filtro.FiltroFechaVotacion;
import ar.edu.unq.TPIntegrador.filtro.IEstrategiaDeFiltrado;

public class FixtureFiltrosPorFechaConEstrategias {
	
	/* 
	 * La creacion de esta clase fue unicamente con motivo de test
	 * provee distintos tipos de FiltroPorFecha con distintas estrategias
	 * con el fin de testear el comportamiento de los mismos frente a distintos escenarios
	*/
	
	
	private IEstrategiaDeFiltrado estrategiaMayor;
	private IEstrategiaDeFiltrado estrategiaIgual;
	private IEstrategiaDeFiltrado estrategiaMenor;
	
	private FiltroFechaCreacion filtroFechaCreacion;
	private FiltroFechaVotacion filtroFechaVotacion;
	
	FixtureFiltrosPorFechaConEstrategias() {
		this.estrategiaMayor = new EstrategiaMayorAFecha();
		this.estrategiaIgual = new EstrategiaIgualAFecha();
		this.estrategiaMenor = new EstrategiaMenorAFecha();
	}
	
	public FiltroFechaCreacion nuevoFiltroFechaCreacionConEstrategiaMayor() {
		this.filtroFechaCreacion = new FiltroFechaCreacion(LocalDate.now(), estrategiaMayor);
		return filtroFechaCreacion;
	}
	
	public FiltroFechaCreacion nuevoFiltroFechaCreacionConEstrategiaIgual() {
		this.filtroFechaCreacion = new FiltroFechaCreacion(LocalDate.now(), estrategiaIgual);
		return filtroFechaCreacion;
	}
	
	public FiltroFechaCreacion nuevoFiltroFechaCreacionConEstrategiaMenor() {
		this.filtroFechaCreacion = new FiltroFechaCreacion(LocalDate.now(), estrategiaMenor);
		return filtroFechaCreacion;
	}
	
	public FiltroFechaVotacion nuevoFiltroFechaVotacionConEstrategiaMayor() {
		this.filtroFechaVotacion = new FiltroFechaVotacion(LocalDate.now(), estrategiaMayor);
		return this.filtroFechaVotacion;
	}
	
	public FiltroFechaVotacion nuevoFiltroFechaVotacionConEstrategiaIgual() {
		this.filtroFechaVotacion = new FiltroFechaVotacion(LocalDate.now(), estrategiaIgual);
		return this.filtroFechaVotacion;
	}
	
	public FiltroFechaVotacion nuevoFiltroFechaVotacionConEstrategiaMenor() {
		this.filtroFechaVotacion = new FiltroFechaVotacion(LocalDate.now(), estrategiaMenor);
		return this.filtroFechaVotacion;
	}

}
