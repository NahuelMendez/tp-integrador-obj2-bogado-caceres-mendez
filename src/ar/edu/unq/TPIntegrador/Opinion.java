package ar.edu.unq.TPIntegrador;

import java.time.LocalDate;

public class Opinion {

	private LocalDate fechaDeEmision;
	private Descripcion descripcion;

	public Opinion(Descripcion descripcion, LocalDate fechaDeEmision) {
		this.fechaDeEmision = fechaDeEmision;
		this.descripcion = descripcion;
	}

	public LocalDate getFechaDeEmision() {
		return fechaDeEmision;
	}

	public Descripcion getDescripcion() {
		return descripcion;
	}

}
