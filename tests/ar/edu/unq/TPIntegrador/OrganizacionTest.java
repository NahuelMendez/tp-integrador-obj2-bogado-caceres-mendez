package ar.edu.unq.TPIntegrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrganizacionTest {

	private Organizacion organizacionSalud;
	private Organizacion organizacionEducativa;
	private Ubicacion ubicacion;
	private FuncionalidadExterna funcionalidadVerificacion;
	private FuncionalidadExterna funcionalidadMuestra;
	
	@BeforeEach
	public void setUp() {
		ubicacion = mock(Ubicacion.class);
		funcionalidadVerificacion = mock(FuncionalidadExterna.class);
		funcionalidadMuestra = mock(FuncionalidadExterna.class);
		
		organizacionSalud = new Organizacion(0, ubicacion, TipoDeOrganizacion.SALUD, funcionalidadMuestra, funcionalidadVerificacion);
		organizacionEducativa = new Organizacion(1, ubicacion, TipoDeOrganizacion.EDUCATIVA, funcionalidadMuestra, funcionalidadVerificacion);
	}
	
	@Test
	void test_Constructor() {
		TipoDeOrganizacion salud = TipoDeOrganizacion.SALUD ;
		TipoDeOrganizacion educativa = TipoDeOrganizacion.EDUCATIVA ;
		
		assertEquals(0,organizacionSalud.getCantidadDePersonasTrabajando());
		assertEquals(1,organizacionEducativa.getCantidadDePersonasTrabajando());
		assertEquals(ubicacion,organizacionEducativa.getUbicacion());
	    assertEquals(salud,organizacionSalud.getTipoDeOrganizacion());
	    assertEquals(educativa,organizacionEducativa.getTipoDeOrganizacion());
	}

	
	@Test
	void test_UnaOrganizacionSeRegistraEnUnaZonaDeCobertura() {
		ZonaDeCobertura zonaDeCobertura = mock(ZonaDeCobertura.class);
		
		organizacionSalud.registrarseAZonaDeCobertura(zonaDeCobertura);
		
		verify(zonaDeCobertura).agregarObserver(organizacionSalud);
	
	}
	
	@Test
	void test_UnaOrganizacionDejaDeEstarEnUnaZonaDeCobertura() {
		ZonaDeCobertura zonaDeCobertura = mock(ZonaDeCobertura.class);
		
		organizacionSalud.dejarZonaDeCobertura(zonaDeCobertura);
		
		verify(zonaDeCobertura).quitarObserver(organizacionSalud);
	
	}
	
	@Test
	void test_UnaOrganizacionYaTieneConfiguradaSuFuncionalidadExternaParaUnaNuevaMuestra() {
		
		assertEquals(funcionalidadMuestra, organizacionSalud.getFuncionalidadExternaParaNuevaMuestra());
	
	}
	
	@Test
	void test_UnaOrganizacionYaTieneConfiguradaSuFuncionalidadExternaParaUnaNuevaVerificacion() {
		
		assertEquals(funcionalidadVerificacion, organizacionSalud.getFuncionalidadExternaParaNuevaVerificacion());
	
	}
	
	@Test
	void test_UnaOrganizacionConfiguraSuFuncionalidadExternaParaUnaNuevaVerificacion() {
		FuncionalidadExterna funcionalidadExterna = mock(FuncionalidadExterna.class);
		
		organizacionSalud.configurarFuncionalidadExternaNuevaVerificacion(funcionalidadExterna);
		
		assertEquals(funcionalidadExterna,organizacionSalud.getFuncionalidadExternaParaNuevaVerificacion());
	
	}
	
	@Test
	void test_UnaOrganizacionConfiguraSuFuncionalidadExternaParaUnaNuevaMuestra() {
		FuncionalidadExterna funcionalidadExterna = mock(FuncionalidadExterna.class);
		
		organizacionSalud.configurarFuncionalidadExternaNuevaMuestra(funcionalidadExterna);
		
		assertEquals(funcionalidadExterna,organizacionSalud.getFuncionalidadExternaParaNuevaMuestra());
	
	}
	
	@Test
	void test_UnaOrganizacionEjecutaSuFuncionalidadExternaParaNuevaMuestraCuandoSeRegistraUnaNuevaMuestra() {
		ZonaDeCobertura zonaDeCobertura = mock(ZonaDeCobertura.class);
		Muestra muestra = mock(Muestra.class);
		
		organizacionSalud.actualizarNuevaMuestra(zonaDeCobertura, muestra);
		
		verify(funcionalidadMuestra).nuevoEvento(organizacionSalud, zonaDeCobertura, muestra);
	
	}
	
	@Test
	void test_UnaOrganizacionEjecutaSuFuncionalidadExternaParaNuevaVerificacionCuandoSeVerificaUnaMuestra() {
		ZonaDeCobertura zonaDeCobertura = mock(ZonaDeCobertura.class);
		Muestra muestra = mock(Muestra.class);
		
		organizacionSalud.actualizarNuevaVerificacion(zonaDeCobertura, muestra);
		
		verify(funcionalidadVerificacion).nuevoEvento(organizacionSalud, zonaDeCobertura, muestra);
	
	}
	
	

}
