package ar.edu.unq.TPIntegrador;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioNovato extends Usuario {
	
	public UsuarioNovato(String identificacion) {
		super(identificacion);
		super.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
	}
	
	private ArrayList<Opinion> opinionesDelUltimoMes(LocalDate fechaActual){
		ArrayList<Opinion> opinionesDelUltimoMes = new ArrayList<Opinion>();
		for (Opinion opinion : super.getOpinionesEnviadas()) {
			if(opinion.getFecha().until(fechaActual).getDays() <= 30) {
				opinionesDelUltimoMes.add(opinion);
			}
		}
		return opinionesDelUltimoMes;
	}
	
	public Integer cantidadDeOpinionesEnElUltimoMes(LocalDate fechaActual) {
		return this.opinionesDelUltimoMes(fechaActual).size();
	}
	
	public void actualizarCategoria() {
		LocalDate fechaActual = LocalDate.now();
		if (cumpleConRevisionesNecesarias(fechaActual)
				&& cumpleConEnviosNecesarios(fechaActual)) {
			super.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		}
	}

	private boolean cumpleConEnviosNecesarios(LocalDate fechaActual) {
		return this.cantidadDeEnviosEnLosUltimos30Dias(fechaActual) >= 10;
	}

	private boolean cumpleConRevisionesNecesarias(LocalDate fechaActual) {
		return this.cantidadDeOpinionesEnElUltimoMes(fechaActual) >= 20;
	}

	public Integer cantidadDeEnviosEnLosUltimos30Dias(LocalDate fechaActual) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
