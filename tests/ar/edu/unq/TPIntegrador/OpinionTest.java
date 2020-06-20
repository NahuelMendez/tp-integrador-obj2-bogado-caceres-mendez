package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class OpinionTest {

	
	@Test
	void test_Constructor() {
        Opinion opinion1 = new Opinion(Descripcion.CHINCHE_FOLIADA);
        Opinion opinion2 = new Opinion(Descripcion.PHTIA_CHINCHE);
		
		assertEquals(LocalDate.now() , opinion1.getFechaDeEmision());
		assertEquals("CHINCHE_FOLIADA", opinion1.getDescripcion());
		assertEquals("PHTIA_CHINCHE", opinion2.getDescripcion());
	}

}