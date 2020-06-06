package ar.edu.unq.TPIntegrador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FechaCreacion {
	
	private LocalDate localDate;
    private DateTimeFormatter formatter;
	
	public List<Muestra> filtrar(List<Muestra> muestras, String fecha){
		LocalDate fechaDeFiltro = this.fechaObtenidaDeString(fecha);
		ArrayList<Muestra> listaFiltrada = new ArrayList<Muestra>();
		for (Muestra muestra : muestras) {
			agregarMuestraSiPasaElFiltro(fechaDeFiltro, listaFiltrada, muestra);
		}
		return listaFiltrada;
	}

	private void agregarMuestraSiPasaElFiltro(LocalDate fechaDeFiltro, ArrayList<Muestra> listaFiltrada,
			Muestra muestra) {
		if(muestra.getFechaDeCreacion().isEqual(fechaDeFiltro)) {
			listaFiltrada.add(muestra);
		}
	}

	private LocalDate fechaObtenidaDeString(String fecha) {
        formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        localDate = LocalDate.parse(fecha, formatter);
        return localDate;
	}

}
