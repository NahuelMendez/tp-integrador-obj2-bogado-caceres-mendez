package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class OpinionTest {

	@Test
	void test_SeCreaUnaOpinionConLaFechaDeHoyYTieneLaFechaDeHoy() {
		Opinion opinion = new Opinion(Descripcion.CHINCHE_FOLIADA);
		
		assertEquals(LocalDate.now() , opinion.getFechaDeEmision());
	}
	
	@Test
	void test_SeCreaUnaOpinionConLaDescripcionDeVinchucaInfestansYSuDescipcrionEsVinchucaInfestans() {
		Opinion opinion = new Opinion(Descripcion.CHINCHE_FOLIADA);
		
		assertEquals("CHINCHE_FOLIADA", opinion.getDescripcion());
	}
	
	@Test
	void test_SeCreaUnaOpinionConLaDescripcionDeVinchucaSordidaYSuDescipcrionEsVinchucaSordida() {
		Opinion opinion = new Opinion(Descripcion.PHTIA_CHINCHE);
		
		assertEquals("PHTIA_CHINCHE", opinion.getDescripcion());
	}

}
