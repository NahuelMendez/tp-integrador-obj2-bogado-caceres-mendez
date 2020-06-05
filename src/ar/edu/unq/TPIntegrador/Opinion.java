package ar.edu.unq.TPIntegrador;

import java.time.LocalDate;

public class Opinion {

	private LocalDate fechaDeEmision;
	private Descripcion descripcion;

	public Opinion(Descripcion descripcion) {
		this.fechaDeEmision = LocalDate.now();
		this.descripcion = descripcion;
	}

	public LocalDate getFechaDeEmision() {
		return fechaDeEmision;
	}

	public Descripcion getDescripcion() {
		return descripcion;
	}

}
