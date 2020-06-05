package ar.edu.unq.TPIntegrador;

import java.util.HashSet;
import java.util.Set;

public class AplicacionWeb {

	private Set<Muestra> muestrasRegistradas;
	private Set<Usuario> usuariosRegistrados;
	private Set<ZonaDeCobertura> zonasDeCobertura;

	public AplicacionWeb() {
		this.muestrasRegistradas = new HashSet<Muestra>();
		this.usuariosRegistrados = new HashSet<Usuario>();
		this.zonasDeCobertura = new HashSet<ZonaDeCobertura>();
	}

	public Set<Muestra> muestrasRegistradas() {
		// TODO Auto-generated method stub
		return muestrasRegistradas;
	}

	public void registrarMuestra(Muestra muestra) {
		muestrasRegistradas.add(muestra);
		this.avisarNuevaMuestra(muestra);
		
	}

	private void avisarNuevaMuestra(Muestra muestra) {
		for (ZonaDeCobertura zona: zonasDeCobertura) {
			zona.agregarNuevaMuestra(muestra);
		}
	}

	public Set<Usuario> usuariosRegistrados() {
		// TODO Auto-generated method stub
		return usuariosRegistrados;
	}

	public void registrarUsuario(Usuario usuario) {
		usuariosRegistrados.add(usuario);
		
	}

	public Set<ZonaDeCobertura> zonasDeCobertura() {
		// TODO Auto-generated method stub
		return zonasDeCobertura;
	}

	public void agregarZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		this.zonasDeCobertura.add(zonaDeCobertura);
		
	}

	public Set<Muestra> muestrasCercanas(Muestra muestra1, Integer kilometros) {

		return muestra1.muestrasCercanas(this.listaDeMuestrasSin(muestra1), kilometros);
	}

	private Set<Muestra> listaDeMuestrasSin(Muestra muestra1) {
		Set<Muestra> listaNueva = new HashSet<Muestra>();
		for (Muestra muestra: muestrasRegistradas) {
			this.agregarSiNoEs(muestra1, muestra, listaNueva);
		}
		return listaNueva;
	}

	private void agregarSiNoEs(Muestra muestra1, Muestra muestraAgregar, Set<Muestra> listaNueva) {
		if (muestra1 != muestraAgregar) {
			listaNueva.add(muestraAgregar);
		}
	}

}
