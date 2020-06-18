package ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unq.TPIntegrador.AplicacionWeb;
import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public class Usuario {
	
	private String identificacion;
	private Set<Muestra> muestras;
	private List<Opinion> opinionesEnviadas;
	private AplicacionWeb aplicacionWeb;
	private EstadoDeUsuario estadoDeUsuario;
	
	public Usuario (String identificacion, AplicacionWeb aplicacionWeb) {
		this.identificacion = identificacion;
		this.muestras = new HashSet<Muestra>();
		this.opinionesEnviadas = new ArrayList<Opinion>();
		this.aplicacionWeb = aplicacionWeb;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public Integer getRevisiones() {
		return this.opinionesEnviadas.size();
	}
	
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion) throws Exception {
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

	public List<Opinion> getOpinionesEnviadas() {
		return this.opinionesEnviadas;
	}

	protected Set<Muestra> getMuestrasEnviadas() {
		return this.muestras;
	}

	public Boolean esUsuarioExperto() {
		return this.getEstadoDeUsuario().esUsuarioExperto();
	}
	
	public Boolean esUsuarioBasico() {
		return this.getEstadoDeUsuario().esUsuarioBasico();
	}
	
	public Integer cantidadDeOpinionesEnLosUltimos30Dias() {
		return this.opinionesDelUltimoMes().size();
	}
	
	private ArrayList<Opinion> opinionesDelUltimoMes(){
		ArrayList<Opinion> opinionesDelUltimoMes = new ArrayList<Opinion>();
		for (Opinion opinion : this.getOpinionesEnviadas()) {
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
	
	public Integer cantidadDeEnviosEnLosUltimos30Dias() {
		return this.enviosDelUltimoMes().size();
	}

	private ArrayList<Muestra> enviosDelUltimoMes() {
		ArrayList<Muestra> enviosDelUltimoMes = new ArrayList<Muestra>();
		for (Muestra muestra : this.getMuestrasEnviadas()) {
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

	public void agregarOpinionAMuestraVotada(Muestra muestra, Opinion opinionAAgregar) {
		this.estadoDeUsuario.agregarOpinionAMuestraVotada(this, muestra, opinionAAgregar);
		
	}

	public void agregarOpinionAMuestraVotadaPorExperto(Muestra muestra, Opinion opinionAAgregar) throws Exception {
		this.estadoDeUsuario.agregarOpinionAMuestraVotadaPorExperto(this, muestra, opinionAAgregar);
	}

}
