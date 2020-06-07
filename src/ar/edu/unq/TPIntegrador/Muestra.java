package ar.edu.unq.TPIntegrador;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Muestra {
	private Usuario usuario;	
	private Opinion opinion;
	private BufferedImage fotoDeLaVinchuca;
	private Ubicacion ubicacion;
	private LocalDate fechaDeCreacion;
	private LocalDate fechaDeUltimaVotacion;
	private LinkedHashMap<Usuario, Opinion> historialDeOpiniones;
	private EstadoDeMuestra estadoActual;
	private ArrayList<ZonaDeCobertura> zonasDeCobertura;

	public Muestra(Usuario usuario, Opinion opinion, BufferedImage fotoDeLaVinchuca, 
				Ubicacion ubicacion, LocalDate fechaDeCreacion) {
		this.usuario = usuario;
		this.opinion = opinion;
		this.fotoDeLaVinchuca = fotoDeLaVinchuca;
		this.ubicacion = ubicacion;
		this.fechaDeCreacion = fechaDeCreacion;
		this.historialDeOpiniones = new LinkedHashMap<Usuario, Opinion>();
			this.historialDeOpiniones.put(usuario, opinion);
		this.fechaDeUltimaVotacion =  getFechaUltimaVotacion();	
		this.estadoActual = getEstadoSegunEstadoDeUsuario(usuario);
		this.zonasDeCobertura = new ArrayList<ZonaDeCobertura>();
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

	private void actualizarFechaUltimaVotacion() {
		this.fechaDeUltimaVotacion = getFechaUltimaVotacion();
	}
	
	public LocalDate getFechaUltimaVotacion() {
		return getUltimaOpinion().getFechaDeEmision();
	}
	
	private Opinion getUltimaOpinion(){
		return (Opinion) historialDeOpiniones.values().toArray()[0];
	    	
	}
	
	public LinkedHashMap<Usuario, Opinion> getHistorialDeOpiniones(){
		return this.historialDeOpiniones;
	}

	private void setEstadoDeMuestra(EstadoDeMuestra estado){
		this.estadoActual = estado;
	}
	
	public EstadoDeMuestra getEstadoDeMuestra(){
		return this.estadoActual;
	}

	protected EstadoDeMuestra getEstadoSegunEstadoDeUsuario(Usuario usuario) {
		EstadoDeMuestra retorno = new EstadoDeMuestraVotada();
		if(usuario.esUsuarioExperto()){
			retorno = new EstadoMuestraVotadaPorExperto();
		}
		return retorno;
	}
	
	public void agregarZonaDeCobertura(ZonaDeCobertura zona) {
		this.zonasDeCobertura.add(zona);
	}

	public ArrayList<ZonaDeCobertura> getZonasDeCobertura() {
		return this.zonasDeCobertura;
	}
	
	public void agregarOpinion(Opinion opinionAAgregar, Usuario usuario) throws Exception {
		this.estadoActual.comprobarSiSePuedeOpinar(this, usuario);
		this.historialDeOpiniones.put(usuario, opinionAAgregar);
		this.actualizarFechaUltimaVotacion();
		this.verificarMuestra();
	}
	
	protected void verificarMuestra() throws Exception{
		if(this.estadoActual.sePuedeVerificarMuestra(this)) {
			this.setEstadoDeMuestra(new EstadoDeMuestraVerificada());
			this.avisarVerificacionAZonasDeCobertura();
		}
	}
	
	protected void cerrarOpinionesParaUsuariosBasicos() {
		this.setEstadoDeMuestra(new EstadoMuestraVotadaPorExperto());
	}
	
	private void avisarVerificacionAZonasDeCobertura() {

	}

	public boolean noContieneLaOpinionDelUsuario(Usuario usuario) {
		return !this.historialDeOpiniones.containsKey(usuario);
	}
	
	public String getResultadoActual() {
		return null;
	}
	
	protected boolean usuarioEsAptoParaVotar(Usuario usuario) {
		return this.estadoActual.usuarioAptoParaVotar(usuario, this);
	}

	protected boolean coincidenDosExpertosEnSuOpinion() {
		final Set<Opinion> opiniones = new HashSet<Opinion>();
		for (Opinion opinion : filtrarOpinionesDeUsuariosExpertos()) {
			if (!opiniones.add(opinion)){
				return true;
	        }
	    }
		return false;
	}
			
	private ArrayList<Opinion> filtrarOpinionesDeUsuariosExpertos(){
		ArrayList<Opinion> retorno = new ArrayList<Opinion>();
		for(HashMap.Entry<Usuario, Opinion> opinionDeUsuario : historialDeOpiniones.entrySet()) {
			if (opinionDeUsuario.getKey().esUsuarioExperto()){
				retorno.add(opinionDeUsuario.getValue());
			}
		}
		return retorno;	
	}
	
	//public Set<Muestra> muestrasCercanas(Set<Muestra> muestras, Integer distancia)){
	
	
	
}
	

	



//Consejo del profe Mati:
//hace falta un mensaje en la app web que diga "dame todas las muestras que se capturaron cerca de esta otra que te mando como parametro". 
//La app habla con la muestra y le dice 'dame todas las que estan cercanas a vos'. 
//La muestra lo delega en la ubicacion, y le dice 'dame todas las que estan cercanas a mi ubicacion' y le manda la lista de todas las muestras
	

