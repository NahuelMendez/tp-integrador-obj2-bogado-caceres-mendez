package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

class UsuarioNovatoTest {
	
	private UsuarioNovato usuarioN;
	private LocalDate fechaActual;
	@Mock private Opinion opinion1;
	@Mock private Opinion opinion2;
	@Mock private Muestra muestra1;
	
	@BeforeEach
	public void setUp() {
		usuarioN = new UsuarioNovato("30112113");
		opinion1 = mock(Opinion.class);
		opinion1 = mock(Opinion.class);
		muestra1 = mock(Muestra.class);
		fechaActual = LocalDate.now();
	}

	@Test
	void test_UnNuevoUsuarioNovatoNoTieneOpinionesEnLosUltimos30Dias() {
		Integer result = usuarioN.cantidadDeEnviosEnLosUltimos30Dias(fechaActual);
		assertEquals(0, result);
	}

}
