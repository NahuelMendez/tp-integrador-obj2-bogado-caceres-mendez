package ar.edu.unq.TPIntegrador;

import java.time.LocalDate;

public enum Opinion {
	
	VINCHUCA_INFESTANS, VINCHUCA_SORDIDA, VINCHUCA_GUASAYANA, CHINCHE_FOLIADA, PHTIA_CHINCHE, NINGUNA, IMAGEN_POCO_CLARA;

	private LocalDate fechaDeEmision;

	Opinion() {
		fechaDeEmision = LocalDate.now();
	}

	public LocalDate getFechaDeEmision() {
		return fechaDeEmision;
	}

	public String getDescripcion() {
		return this.toString();
	}

}
