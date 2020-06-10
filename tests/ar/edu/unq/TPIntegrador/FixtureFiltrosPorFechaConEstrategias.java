package ar.edu.unq.TPIntegrador;

import java.time.LocalDate;

public class FixtureFiltrosPorFechaConEstrategias {
	
	/* 
	 * La creacion de esta clase fue unicamente con motivo de test
	 * provee distintos tipos de FiltroPorFecha con distintas estrategias
	 * con el fin de testear el comportamiento de los mismos frente a distintos escenarios
	*/
	
	
	private IEstrategiaDeFiltrado estrategiaMayor;
	private IEstrategiaDeFiltrado estrategiaIgual;
	private IEstrategiaDeFiltrado estrategiaMenor;
	
	private FechaCreacion filtroFechaCreacion;
	private FechaVotacion filtroFechaVotacion;
	
	FixtureFiltrosPorFechaConEstrategias() {
		this.estrategiaMayor = new EstrategiaMayorAFecha();
		this.estrategiaIgual = new EstrategiaIgualAFecha();
		this.estrategiaMenor = new EstrategiaMenorAFecha();
	}
	
	public FechaCreacion nuevoFiltroFechaCreacionConEstrategiaMayor() {
		this.filtroFechaCreacion = new FechaCreacion(LocalDate.now(), estrategiaMayor);
		return filtroFechaCreacion;
	}
	
	public FechaCreacion nuevoFiltroFechaCreacionConEstrategiaIgual() {
		this.filtroFechaCreacion = new FechaCreacion(LocalDate.now(), estrategiaIgual);
		return filtroFechaCreacion;
	}
	
	public FechaCreacion nuevoFiltroFechaCreacionConEstrategiaMenor() {
		this.filtroFechaCreacion = new FechaCreacion(LocalDate.now(), estrategiaMenor);
		return filtroFechaCreacion;
	}
	
	public FechaVotacion nuevoFiltroFechaVotacionConEstrategiaMayor() {
		this.filtroFechaVotacion = new FechaVotacion(LocalDate.now(), estrategiaMayor);
		return this.filtroFechaVotacion;
	}
	
	public FechaVotacion nuevoFiltroFechaVotacionConEstrategiaIgual() {
		this.filtroFechaVotacion = new FechaVotacion(LocalDate.now(), estrategiaIgual);
		return this.filtroFechaVotacion;
	}
	
	public FechaVotacion nuevoFiltroFechaVotacionConEstrategiaMenor() {
		this.filtroFechaVotacion = new FechaVotacion(LocalDate.now(), estrategiaMenor);
		return this.filtroFechaVotacion;
	}

}
