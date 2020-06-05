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
	void test_UnaOrganizacionNoTieneNingunaPersonaTrabajandoYSuCantidadDePersonasTrabajandoEsCero() {
		
	     assertEquals(0,organizacionSalud.getCantidadDePersonasTrabajando());
	}
	
	@Test
	void test_UnaOrganizacionTieneUnaPersonaTrabajandoYSuCantidadDePersonasTrabajandoEsUno() {
		
	     assertEquals(1,organizacionEducativa.getCantidadDePersonasTrabajando());
	}
	
	@Test
	void test_UnaOrganizacionTieneUnaUbicacion() {
		
	     assertEquals(ubicacion,organizacionEducativa.getUbicacion());
	}
	
	@Test
	void test_UnaOrganizacionEsDeTipoSalud() {
		TipoDeOrganizacion salud = TipoDeOrganizacion.SALUD ;
		 
		
	     assertEquals(salud,organizacionSalud.getTipoDeOrganizacion());
	}
	
	@Test
	void test_UnaOrganizacionEsDeTipoEducativa() {
		TipoDeOrganizacion educativa = TipoDeOrganizacion.EDUCATIVA ;
		 
		
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
		
		organizacionSalud.actualizar(zonaDeCobertura, muestra, "Nueva muestra");
		
		verify(funcionalidadMuestra).nuevoEvento(organizacionSalud, zonaDeCobertura, muestra);
	
	}
	
	@Test
	void test_UnaOrganizacionEjecutaSuFuncionalidadExternaParaNuevaVerificacionCuandoSeVerificaUnaMuestra() {
		ZonaDeCobertura zonaDeCobertura = mock(ZonaDeCobertura.class);
		Muestra muestra = mock(Muestra.class);
		
		organizacionSalud.actualizar(zonaDeCobertura, muestra, "Nueva verificacion");
		
		verify(funcionalidadVerificacion).nuevoEvento(organizacionSalud, zonaDeCobertura, muestra);
	
	}
	
	

}
