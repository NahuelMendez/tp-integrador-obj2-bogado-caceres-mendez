package ar.edu.unq.TPIntegrador;

import java.util.ArrayList;
import java.util.List;

public class ZonaDeCobertura {

	private String nombre;
	private Integer radio;
	private Ubicacion epicentro;
	private List<IObserver> observers;
	private List<Muestra> muestrasRegistradas;

	public ZonaDeCobertura(String nombre, Integer radio, Ubicacion epicentro) {
		this.nombre = nombre;
		this.radio = radio;
		this.epicentro = epicentro;
		this.observers = new ArrayList<IObserver>();
		this.muestrasRegistradas = new ArrayList<Muestra>();
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

	public Integer getRadio() {
		return radio;
	}

	public Ubicacion getEpicentro() {
		return epicentro;
	}

	public List<ZonaDeCobertura> zonasQueSolapan(List<ZonaDeCobertura> listaDeZonas) {
		List<ZonaDeCobertura> zonasQueSolapan = new ArrayList<ZonaDeCobertura>();
		
		for (ZonaDeCobertura zona: listaDeZonas) {
			this.agregarSiSeSolapa(zona, zonasQueSolapan);
		}
		
		return zonasQueSolapan;
	}

	private void agregarSiSeSolapa(ZonaDeCobertura zona, List<ZonaDeCobertura> zonasQueSolapan) {
		if (this.epicentro.medirDistancias(zona.epicentro) < this.radio + zona.radio) {
			zonasQueSolapan.add(zona);
		}
		
	}

	public List<IObserver> observerRegistrados() {
		return observers;
	}

	public void agregarNuevaMuestra(Muestra muestra) {
		if (this.perteneceAZonaDeCobertura(muestra)) {
			muestrasRegistradas.add(muestra);
			this.avisarAZonasDeCobertura(muestra, "Nueva muestra");
		}
		
	}

	private Boolean perteneceAZonaDeCobertura(Muestra muestra) {
		
		return epicentro.medirDistancias(muestra.getUbicacion()) < radio;
	}

	private void avisarAZonasDeCobertura(Muestra muestra, String mensaje) {
		for (IObserver observer: observers) {
			observer.actualizar(this, muestra, mensaje);
		}
	}


	public List<Muestra> muestrasRegistradas() {
		return muestrasRegistradas;
	}

	public void muestraVerificada(Muestra muestra) {
		this.avisarAZonasDeCobertura(muestra, "Nueva verificacion");
	}

}
