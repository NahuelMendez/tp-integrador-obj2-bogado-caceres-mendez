package ar.edu.unq.TPIntegrador;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioNovato extends Usuario {
	
	public UsuarioNovato(String identificacion, AplicacionWeb aplicacionWeb) {
		super(identificacion, aplicacionWeb);
		super.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
	}
	
	public void actualizarCategoria() {
		super.getEstadoDeUsuario().actualizarCateogiriaDeUsuarioNovato(this);
	}
	
	public Boolean cumpleConRevisionesNecesarias() {
		return this.cantidadDeOpinionesEnLosUltimos30Dias() >= 20;
	}
	
	public Integer cantidadDeOpinionesEnLosUltimos30Dias() {
		return this.opinionesDelUltimoMes().size();
	}
	
	private ArrayList<Opinion> opinionesDelUltimoMes(){
		ArrayList<Opinion> opinionesDelUltimoMes = new ArrayList<Opinion>();
		for (Opinion opinion : super.getOpinionesEnviadas()) {
			agregarAListaDeOpinionesDeUltimoMesSi(opinionesDelUltimoMes, opinion);
		}
		return opinionesDelUltimoMes;
	}

	private void agregarAListaDeOpinionesDeUltimoMesSi(ArrayList<Opinion> opinionesDelUltimoMes, Opinion opinion) {
		LocalDate fechaActual = LocalDate.now();
		if((opinion.getFechaDeEmision().until(fechaActual).getMonths()) <= 1) {
			opinionesDelUltimoMes.add(opinion);
		}
	}

	public Boolean cumpleConEnviosNecesarios() {
		return this.cantidadDeEnviosEnLosUltimos30Dias() >= 10;
	}

	public Integer cantidadDeEnviosEnLosUltimos30Dias() {
		return this.enviosDelUltimoMes().size();
	}

	private ArrayList<Muestra> enviosDelUltimoMes() {
		ArrayList<Muestra> enviosDelUltimoMes = new ArrayList<Muestra>();
		for (Muestra muestra : super.getMuestrasEnviadas()) {
			agregarAListaDeMuestrasDelUltimoMesSI(enviosDelUltimoMes, muestra);
		}
		return enviosDelUltimoMes;
	}

	private void agregarAListaDeMuestrasDelUltimoMesSI(ArrayList<Muestra> enviosDelUltimoMes, Muestra muestra) {
		LocalDate fechaActual = LocalDate.now();
		if(muestra.getFechaDeCreacion().until(fechaActual).getMonths() <= 1) {
			enviosDelUltimoMes.add(muestra);
		}
	}
	
	

}
