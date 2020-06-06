package ar.edu.unq.TPIntegrador;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private String identificacion;
	private List<Muestra> muestras;
	private List<Opinion> opinionesEnviadas;
	private AplicacionWeb aplicacionWeb;
	private EstadoDeUsuario estadoDeUsuario;
	
	public Usuario (String identificacion, AplicacionWeb aplicacionWeb) {
		this.identificacion = identificacion;
		this.muestras = new ArrayList<Muestra>();
		this.opinionesEnviadas = new ArrayList<Opinion>();
		this.aplicacionWeb = aplicacionWeb;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public Integer getRevisiones() {
		return this.opinionesEnviadas.size();
	}
	
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion) {
		this.estadoDeUsuario.opinarSobreMuestra(muestra, opinion, this);
	}

	public Integer getEnvios() {
		return this.muestras.size();
	}

	public void enviarMuestra(Muestra muestra) {
		this.muestras.add(muestra);
		this.aplicacionWeb.registrarMuestra(muestra);
	}
	
	protected void setEstadoDeUsuario(EstadoDeUsuario estadoDeUsuario) {
		this.estadoDeUsuario = estadoDeUsuario;
	}
	
	public EstadoDeUsuario getEstadoDeUsuario() {
		return this.estadoDeUsuario;
	}
	
	public void agregarOpinionEnviada(Opinion opinion) {
		this.opinionesEnviadas.add(opinion);
	}

	protected List<Opinion> getOpinionesEnviadas() {
		return this.opinionesEnviadas;
	}

	protected List<Muestra> getMuestrasEnviadas() {
		return this.muestras;
	}

	public Boolean esUsuarioExperto() {
		return this.getEstadoDeUsuario().esUsuarioExperto();
	}
	
	public Boolean esUsuarioBasico() {
		return this.getEstadoDeUsuario().esUsuarioBasico();
	}

}
