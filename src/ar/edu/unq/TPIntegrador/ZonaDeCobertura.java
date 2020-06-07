package ar.edu.unq.TPIntegrador;


import java.util.HashSet;
import java.util.Set;

public class ZonaDeCobertura {

	private String nombre;
	private Integer radio;
	private Ubicacion epicentro;
	private Set<IObserver> observers;
	private Set<Muestra> muestrasRegistradas;

	public ZonaDeCobertura(String nombre, Integer radio, Ubicacion epicentro) {
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

	public Integer getRadio() {
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


	public Set<Muestra> muestrasRegistradas() {
		return muestrasRegistradas;
	}

	public void muestraVerificada(Muestra muestra) {
		this.avisarAZonasDeCobertura(muestra, "Nueva verificacion");
	}

}
