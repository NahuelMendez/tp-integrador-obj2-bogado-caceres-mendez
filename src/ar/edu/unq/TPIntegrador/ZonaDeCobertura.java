package ar.edu.unq.TPIntegrador;


import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public class ZonaDeCobertura {

	private String nombre;
	private Double radio;
	private Ubicacion epicentro;
	private Set<IObserver> observers;
	private Set<Muestra> muestrasRegistradas;

	public ZonaDeCobertura(String nombre, Double radio, Ubicacion epicentro) {
		this.nombre = nombre;
		this.radio = radio;
		this.epicentro = epicentro;
		this.observers = new HashSet<IObserver>();
		this.muestrasRegistradas = new HashSet<Muestra>();
	}

	public void agregarObserver(IObserver observer) {
		observers.add(observer);
	}

	public void quitarObserver(IObserver observer) {
		observers.remove(observer);
		
	}

	public String getNombre() {
		return nombre;
	}

	public Double getRadio() {
		return radio;
	}

	public Ubicacion getEpicentro() {
		return epicentro;
	}

	public Set<ZonaDeCobertura> zonasQueSolapan(Set<ZonaDeCobertura> listaDeZonas) {
		Set<ZonaDeCobertura> zonasQueSolapan = new HashSet<ZonaDeCobertura>();
		
		for (ZonaDeCobertura zona: listaDeZonas) {
			this.agregarSiSeSolapa(zona, zonasQueSolapan);
		}
		
		return zonasQueSolapan;
	}

	private void agregarSiSeSolapa(ZonaDeCobertura zona, Set<ZonaDeCobertura> zonasQueSolapan) {
		if (this.epicentro.medirDistancias(zona.epicentro) < this.radio + zona.radio) {
			zonasQueSolapan.add(zona);
		}
		
	}

	public Set<IObserver> observerRegistrados() {
		return observers;
	}

	public void agregarNuevaMuestra(Muestra muestra) {
		if (this.perteneceAZonaDeCobertura(muestra)) {
			muestrasRegistradas.add(muestra);
			this.avisarNuevaMuestra(muestra);
		}
		
	}
	
	public void muestraVerificada(Muestra muestra) {
		this.avisarNuevaVerificacion(muestra);
	}

	private void avisarNuevaMuestra(Muestra muestra) {
		for (IObserver observer: observers) {
			observer.actualizarNuevaMuestra(this, muestra);
		}
		
	}
	
	private void avisarNuevaVerificacion(Muestra muestra) {
		for (IObserver observer: observers) {
			observer.actualizarNuevaVerificacion(this, muestra);
		}
		
	}

	private Boolean perteneceAZonaDeCobertura(Muestra muestra) {
		
		return epicentro.medirDistancias(muestra.getUbicacion()) < radio;
	}

	//private void avisarAZonasDeCobertura(Muestra muestra, String mensaje) {
		//for (IObserver observer: observers) {
			//observer.actualizar(this, muestra, mensaje);
		//}
	//}


	public Set<Muestra> muestrasRegistradas() {
		return muestrasRegistradas;
	}


}
