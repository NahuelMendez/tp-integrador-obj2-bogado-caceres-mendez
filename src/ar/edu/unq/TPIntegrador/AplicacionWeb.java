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
		
		return usuariosRegistrados;
	}

	public void registrarUsuario(Usuario usuario) {
		usuariosRegistrados.add(usuario);
		
	}

	public Set<ZonaDeCobertura> zonasDeCobertura() {
		return zonasDeCobertura;
	}

	public void agregarZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		this.zonasDeCobertura.add(zonaDeCobertura);
		
	}

	public Set<Muestra> muestrasCercanas(Muestra muestra1, Double kilometros) {

		return muestra1.muestrasCercanas(this.listaDeMuestrasSin(muestra1), kilometros);
	}

	private Set<Muestra> listaDeMuestrasSin(Muestra muestra1) {
		Set<Muestra> listaNueva = new HashSet<Muestra>();
		for (Muestra muestra: muestrasRegistradas) {
			listaNueva.add(muestra);
		}
		listaNueva.remove(muestra1);
		return listaNueva;
	}

	public Set<Muestra> filtrarMuestras(IFiltro filtro) {
		return filtro.filtrar(muestrasRegistradas);
	}

	public Set<ZonaDeCobertura> zonasQueSeSolapanCon(ZonaDeCobertura zonaDeCobertura1) {
		return zonaDeCobertura1.zonasQueSolapan(this.listaDeZonasSin(zonaDeCobertura1));
		
	}

	private Set<ZonaDeCobertura> listaDeZonasSin(ZonaDeCobertura zonaDeCobertura1) {
		Set<ZonaDeCobertura> listaNueva = new HashSet<ZonaDeCobertura>();
		for (ZonaDeCobertura zona: zonasDeCobertura) {
			listaNueva.add(zona);
		}
		listaNueva.remove(zonaDeCobertura1);
		return listaNueva;
	}


}
