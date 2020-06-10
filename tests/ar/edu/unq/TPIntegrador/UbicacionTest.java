package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;

class UbicacionTest {
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;
	private Muestra muestra4;
	private Ubicacion ubicacion1;
	private Ubicacion ubicacion2;
	private Ubicacion ubicacion3;
	private Ubicacion ubicacion4;

	@BeforeEach
	void setUp() throws Exception {		
		mock(Usuario.class);
		mock(Opinion.class);
		mock(BufferedImage.class);
		
		ubicacion1 = new Ubicacion(1.5, 3.0);
		ubicacion2 = new Ubicacion(2.1, 1.0);    //232.07573107485268 de distancia de ubicacion 1
		ubicacion3 = new Ubicacion(6.0, 6.7);    //647.1728246890287 de distancia de distancia de ubicacion 1
		ubicacion4 = new Ubicacion(23.0, 12.0);  //2580.4795481852875 de distancia de distancia de ubicacion 1 
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
	}

	@Test
	void test_unaUbicacionTieneUnaLatitud1_5() {
		assertEquals(1.5 , ubicacion1.getLatitud());
	}
	
	@Test
	void test_unaUbicacionTieneUnaLongitud3_0() {
		assertEquals(3.0 , ubicacion1.getLongitud());
	}
	
	@Test
	void test_UnaUbicacionSeEncuentraA_232_07573107485268_DeLaUbicacion2() {
		ubicacion2 = new Ubicacion(2.1, 1.0);
		assertEquals(232.07573107485268, ubicacion1.medirDistancias(ubicacion2));
	}
	
	@Test
	void test_UnaUbicacionSeEncuentraDeLaUbicacion3() {
		ubicacion3 = new Ubicacion(6.0, 6.7);
		assertEquals(647.1728246890287, ubicacion1.medirDistancias(ubicacion3));
	}

	@Test
	void test_DistanciaConUbicacion4() {
		ubicacion4 = new Ubicacion(23.0, 12.0);
		assertEquals(2580.4795481852875, ubicacion1.medirDistancias(ubicacion4));
	}
	
	@Test
	void test_laDistanciaEntreUbicacion1TUbicacionDeMuestra1EsDe6471728246890287() {
		when(muestra1.getUbicacion()).thenReturn(ubicacion3);
		assertEquals(647.1728246890287, ubicacion1.medirDistancias(muestra1.getUbicacion()));
	}

	@Test
	void test_UnaUbicacionRetornaLasUbicacionesCercanas() {
		Set<Ubicacion> ubicacionesAMedir = new HashSet<Ubicacion>();
		Set<Ubicacion> ubCercanas = new HashSet<Ubicacion>();
		
		ubicacionesAMedir.add(ubicacion2);
		ubicacionesAMedir.add(ubicacion3);
		ubicacionesAMedir.add(ubicacion4);
		
		ubCercanas.add(ubicacion2);
		Set<Ubicacion> resultado = ubicacion1.ubicacionesCercanas(ubicacionesAMedir, 234.0);
		assertEquals(ubCercanas, resultado); 
	}

	@Test
	void test_UnaUbicacionRetornaLasMuestrasCercanas() {
		Set<Muestra> muestras_AMedir = new HashSet<Muestra>();
		Set<Muestra> muestras_Cercanas = new HashSet<Muestra>();
		
		when(muestra2.getUbicacion()).thenReturn(ubicacion2);
		when(muestra3.getUbicacion()).thenReturn(ubicacion3);
		when(muestra4.getUbicacion()).thenReturn(ubicacion4);
		
		muestras_AMedir.add(muestra2);
		muestras_AMedir.add(muestra3);
		muestras_AMedir.add(muestra4);
		
		muestras_Cercanas.add(muestra2);
		
		Set<Muestra> resultado = ubicacion1.muestrasCercanas(muestras_AMedir, 300.0);
		assertEquals(resultado, muestras_Cercanas);
		 
		verify(muestra2, atLeastOnce()).getUbicacion();
		verify(muestra3, atLeastOnce()).getUbicacion();
		verify(muestra4, atLeastOnce()).getUbicacion();

	}	
}
