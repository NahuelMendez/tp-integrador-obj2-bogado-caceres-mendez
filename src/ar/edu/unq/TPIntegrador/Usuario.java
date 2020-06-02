package ar.edu.unq.TPIntegrador;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private String identificacion;
	private List<Muestra> muestras;
	private List<Opinion> opinionesEnviadas;
	private EstadoDeUsuario estadoDeUsuario;
	
	public Usuario (String identificacion) {
		this.identificacion = identificacion;
		this.muestras = new ArrayList<Muestra>();
		this.opinionesEnviadas = new ArrayList<Opinion>();
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public Integer getRevisiones() {
		return this.opinionesEnviadas.size();
	}
	
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion) {
		muestra.agregarOpinion(opinion, this);
		opinionesEnviadas.add(opinion);
	}

	public Integer getEnvios() {
		return this.muestras.size();
	}

	public void enviarMuestra(Muestra muestra) {
		this.muestras.add(muestra);
	}
	
	protected void setEstadoDeUsuario(EstadoDeUsuario estadoDeUsuario) {
		this.estadoDeUsuario = estadoDeUsuario;
	}
	
	public EstadoDeUsuario getEstadoDeUsuario() {
		return this.estadoDeUsuario;
	}

}