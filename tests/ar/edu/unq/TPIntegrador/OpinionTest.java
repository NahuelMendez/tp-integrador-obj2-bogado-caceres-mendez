package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class OpinionTest {

	@Test
	void test_SeCreaUnaOpinionConLaFechaDeHoyYTieneLaFechaDeHoy() {
		Descripcion descripcion = Descripcion.VINCHUCA_INFESTANS;
		Opinion opinion = new Opinion(descripcion);
		
		assertEquals(LocalDate.now() , opinion.getFechaDeEmision());
	}
	
	@Test
	void test_SeCreaUnaOpinionConLaDescripcionDeVinchucaInfestansYSuDescipcrionEsVinchucaInfestans() {
		Descripcion descripcion = Descripcion.VINCHUCA_INFESTANS;
		
		Opinion opinion = new Opinion(descripcion);
		
		assertEquals(descripcion, opinion.getDescripcion());
	}
	
	@Test
	void test_SeCreaUnaOpinionConLaDescripcionDeVinchucaSordidaYSuDescipcrionEsVinchucaSordida() {
		Descripcion descripcion = Descripcion.VINCHUCA_SORDIDA;
		
		Opinion opinion = new Opinion(descripcion);
		
		assertEquals(descripcion, opinion.getDescripcion());
	}

}
