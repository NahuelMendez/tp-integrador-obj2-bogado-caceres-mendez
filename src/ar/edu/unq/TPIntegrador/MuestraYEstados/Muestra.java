package ar.edu.unq.TPIntegrador.MuestraYEstados;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.function.BinaryOperator;

import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.Ubicacion;
import ar.edu.unq.TPIntegrador.ZonaDeCobertura;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

public class Muestra {
	private Usuario usuario;	
	private Opinion opinion;
	private BufferedImage fotoDeLaVinchuca;
	private Ubicacion ubicacion;
	private LocalDate fechaDeCreacion;
	private LocalDate fechaDeUltimaVotacion;
	LinkedHashMap<Usuario, Opinion> historialDeOpiniones;
	private EstadoDeMuestra estadoActual;
	private ArrayList<ZonaDeCobertura> zonasDeCobertura;

	public Muestra(Usuario usuario, Opinion opinion, BufferedImage fotoDeLaVinchuca, 
				Ubicacion ubicacion, LocalDate fechaDeCreacion) throws Exception {
		this.usuario = usuario;
		this.opinion = opinion;
		this.fotoDeLaVinchuca = fotoDeLaVinchuca;
		this.ubicacion = ubicacion;
		this.fechaDeCreacion = fechaDeCreacion;
		this.historialDeOpiniones = new LinkedHashMap<Usuario, Opinion>();
		this.estadoActual = new EstadoDeMuestraVotada();
		this.zonasDeCobertura = new ArrayList<ZonaDeCobertura>();
		this.agregarOpinionDeUsuario(opinion, usuario);
		this.fechaDeUltimaVotacion = fechaDeCreacion;
	}
	
	public String getEspecieDeVinchuca() {
		return this.opinion.getDescripcion();
	}

	public BufferedImage getFotoDeLaVinchuca() {
		return this.fotoDeLaVinchuca;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public String getIdentificacionPropietarioDeLaMuestra() {
		return this.usuario.getIdentificacion();
	}
		
	public LocalDate getFechaDeCreacion() {
		return this.fechaDeCreacion;
	}

	private void actualizarFechaUltimaVotacion(Opinion opinion) {
		this.fechaDeUltimaVotacion = opinion.getFechaDeEmision();
	}
	
	public LocalDate getFechaUltimaVotacion() {
		return this.fechaDeUltimaVotacion;
	}

	public LinkedHashMap<Usuario, Opinion> getHistorialDeOpiniones(){
		return this.historialDeOpiniones;
	}

	void setEstadoDeMuestra(EstadoDeMuestra estado){  
		this.estadoActual = estado;
	}

	public String nivelDeVerificacion() {
		return this.estadoActual.getNivelDeVerificacion(this);
	}

	public void agregarZonaDeCobertura(ZonaDeCobertura zona) {
		this.zonasDeCobertura.add(zona);
	}

	public ArrayList<ZonaDeCobertura> getZonasDeCobertura() {  
		return this.zonasDeCobertura;
	}
	
	public void agregarOpinion(Opinion opinionAAgregar, Usuario usuario) throws Exception { 
		this.estadoActual.agregarOpinion(this, opinionAAgregar, usuario);
	}
	
	public void agregarOpinionDeUsuario(Opinion opinionAAgregar, Usuario usuario) {
		this.historialDeOpiniones.put(usuario, opinionAAgregar);
		this.actualizarFechaUltimaVotacion(opinionAAgregar);
	}
/*
	public void verificarMuestra() throws Exception{
		if(this.coincidenDosExpertosEnSuOpinion()) {  
			this.cerrarOpinionesParaTodosLosUsuarios(); 
			this.avisarVerificacionAZonasDeCobertura(); 
		}
	}
*/
	public void verificarMuestra() throws Exception {
		this.estadoActual.actualizarEstado(this);
	}
	
	/*
	public void cerrarOpinionesParaUsuariosBasicos() {
		this.historialDeOpiniones.clear(); 
		this.setEstadoDeMuestra(new EstadoMuestraVotadaPorExperto());
	}
	//lo utilizaba estado de usuario experto! 
	void cerrarOpinionesParaTodosLosUsuarios() {
		this.setEstadoDeMuestra(new EstadoDeMuestraVerificada());
	}
	*/
	public boolean contieneAlUsuario(Usuario usuario) {
		return this.historialDeOpiniones.containsKey(usuario); 
	}

	public boolean contieneLaOpinion(Opinion opinion) {
		return this.historialDeOpiniones.containsValue(opinion);
	}

	public boolean coincidenDosExpertosEnSuOpinion() {
        final Set<String> opiniones = new HashSet<String>();
        boolean retorno = false;
        for (String opinion: getOpiniones()) {
             retorno |= !opiniones.add(opinion); 
        }
        return retorno;
    }

	public ArrayList<String> getOpiniones() {
		ArrayList<String> opiniones = new ArrayList<String>();
		for(HashMap.Entry<Usuario, Opinion> opinionDeUsuario : historialDeOpiniones.entrySet()) {
			opiniones.add((opinionDeUsuario.getValue()).getDescripcion());	
		}
		return opiniones;	
	}	
		
	public String getResultadoActual() {
		String opinionMasVotada = getOpiniones().stream()
		    .reduce(BinaryOperator.maxBy((o1, o2) -> Collections.frequency(getOpiniones(), o1) -
		            Collections.frequency(getOpiniones(), o2))).orElse(null);
		return (opinionMasVotada);
	}	
	
	void avisarVerificacionAZonasDeCobertura() {
		for(ZonaDeCobertura zona : this.zonasDeCobertura) {
			zona.muestraVerificada(this);
		}
	}
	
	public Set<Muestra> muestrasCercanas(Set<Muestra> muestras, double distancia){
		return this.ubicacion.muestrasCercanas(muestras, distancia);
	}
}


