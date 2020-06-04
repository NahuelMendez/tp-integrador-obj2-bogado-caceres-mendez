package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class OpinionTest {

	@Test
	void test_SeCreaUnaOpinionConLaFechaDeHoyYTieneLaFechaDeHoy() {
		Descripcion descripcion = Descripcion.VINCHUCA_INFESTANS;
		Opinion opinion = new Opinion(descripcion, LocalDate.now());
		
		assertEquals(LocalDate.now() , opinion.getFechaDeEmision());
	}
	
	@Test
	void test_SeCreaUnaOpinionConLaFechaDelAño2020Mes6Dia3YAlPedirleSuFechaDeEmisionEsAño2020Mes6Dia3() {
		Descripcion descripcion = Descripcion.VINCHUCA_INFESTANS;
		LocalDate fecha = LocalDate.of(2020, 6, 3);
		
		Opinion opinion = new Opinion(descripcion, fecha);
		
		assertEquals(fecha , opinion.getFechaDeEmision());
	}
	
	@Test
	void test_SeCreaUnaOpinionConLaDescripcionDeVinchucaInfestansYSuDescipcrionEsVinchucaInfestans() {
		Descripcion descripcion = Descripcion.VINCHUCA_INFESTANS;
		
		Opinion opinion = new Opinion(descripcion, LocalDate.now());
		
		assertEquals(descripcion, opinion.getDescripcion());
	}
	
	@Test
	void test_SeCreaUnaOpinionConLaDescripcionDeVinchucaSordidaYSuDescipcrionEsVinchucaSordida() {
		Descripcion descripcion = Descripcion.VINCHUCA_SORDIDA;
		
		Opinion opinion = new Opinion(descripcion, LocalDate.now());
		
		assertEquals(descripcion, opinion.getDescripcion());
	}

}
