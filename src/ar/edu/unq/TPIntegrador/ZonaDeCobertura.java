package ar.edu.unq.TPIntegrador;

import java.util.ArrayList;
import java.util.List;

public class ZonaDeCobertura {

	private String nombre;
	private Integer radio;
	private Ubicacion epicentro;
	private List<Organizacion> organizaciones;
	private List<Muestra> muestrasRegistradas;

	public ZonaDeCobertura(String nombre, Integer radio, Ubicacion epicentro) {
		this.nombre = nombre;
		this.radio = radio;
		this.epicentro = epicentro;
		this.organizaciones = new ArrayList<Organizacion>();
		this.muestrasRegistradas = new ArrayList<Muestra>();
	}

	public void agregarOrganizacion(Organizacion organizacion) {
		organizaciones.add(organizacion);
	}

	public void quitarOrganizacion(Organizacion organizacion) {
		organizaciones.remove(organizacion);
		
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

	public List<Organizacion> organizacionesRegistradas() {
		return organizaciones;
	}

	public void agregarNuevaMuestra(Muestra muestra) {
		muestrasRegistradas.add(muestra);
		
		for (Organizacion organizacion: organizaciones) {
			organizacion.ejecutarFuncionalidadExterna(this, muestra, "Nueva muestra");
		}
		
	}


	public List<Muestra> muestrasRegistradas() {
		return muestrasRegistradas;
	}

	public void muestraVerificada(Muestra muestra) {
		for (Organizacion organizacion: organizaciones) {
			organizacion.ejecutarFuncionalidadExterna(this, muestra, "Nueva verificacion");
		}
	}

}
