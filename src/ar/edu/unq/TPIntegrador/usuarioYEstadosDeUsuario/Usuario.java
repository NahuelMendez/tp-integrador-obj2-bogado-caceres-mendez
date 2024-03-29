package ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
		this.estadoDeUsuario = new EstadoDeUsuarioBasico();
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
	
	public void agregarOpinionEnviada(Opinion opinion) {
		this.opinionesEnviadas.add(opinion);
	}

	public List<Opinion> getOpinionesEnviadas() {
		return this.opinionesEnviadas;
	}

	public Set<Muestra> getMuestrasEnviadas() {
		return this.muestras;
	}

	public Boolean esUsuarioExperto() {
		return this.estadoDeUsuario.esUsuarioExperto();
	}
	
	public Boolean esUsuarioBasico() {
		return this.estadoDeUsuario.esUsuarioBasico();
	}
	
	private Integer cantidadDeOpinionesEnLosUltimos30Dias() {
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
		if(laOpinionEstaDentroDe30DiasDeLaFecha(opinion)) {
			opinionesDelUltimoMes.add(opinion);
		}
	}

	private Boolean laOpinionEstaDentroDe30DiasDeLaFecha(Opinion opinion) {
		LocalDate fechaActual = LocalDate.now();
		return ChronoUnit.DAYS.between(opinion.getFechaDeEmision(), fechaActual) <= 30;
	}
	
	private Integer cantidadDeEnviosEnLosUltimos30Dias() {
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
		if(laMuestraEstaDentroDe30DiasDeLaFecha(muestra)) {
			enviosDelUltimoMes.add(muestra);
		}
	}

	private Boolean laMuestraEstaDentroDe30DiasDeLaFecha(Muestra muestra) {
		LocalDate fechaActual = LocalDate.now();
		return ChronoUnit.DAYS.between(muestra.getFechaDeCreacion(), fechaActual) <= 30;
	}

	public void agregarOpinionAMuestraVotadaPorExperto(Muestra muestra, Opinion opinionAAgregar) throws Exception {
		this.estadoDeUsuario.agregarOpinionAMuestraVotadaPorExperto(this, muestra, opinionAAgregar);
	}
	

	public void actualizarCategoria() {
		this.estadoDeUsuario.actualizarCategoria(this);
	}
	
	protected Boolean cumpleConRevisionesNecesarias() {
		return this.cantidadDeOpinionesEnLosUltimos30Dias() >= 20;
	}
	

	protected Boolean cumpleConEnviosNecesarios() {
		return this.cantidadDeEnviosEnLosUltimos30Dias() >= 10;
	}

	public void cambiarAUsuarioEspecialista() {
		this.estadoDeUsuario = new EstadoDeUsuarioEspecialista();
	}


}
