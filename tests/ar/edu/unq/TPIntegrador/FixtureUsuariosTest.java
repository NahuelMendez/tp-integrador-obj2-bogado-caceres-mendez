package ar.edu.unq.TPIntegrador;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.mockito.Mock;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;
import ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario.Usuario;

public class FixtureUsuariosTest {
	
	/* 
	 * La creacion de esta clase fue unicamente con motivo de test
	 * crea distintos casos de usuarios
	*/

	private Usuario usuarioN;
	
	private @Mock Muestra muestra1;
	private @Mock Muestra muestra2;
	private @Mock Muestra muestra3;
	private @Mock Muestra muestra4;
	private @Mock Muestra muestra5;
	private @Mock Muestra muestra6;
	private @Mock Muestra muestra7;
	private @Mock Muestra muestra8;
	private @Mock Muestra muestra9;
	private @Mock Muestra muestra10;
	private @Mock Muestra muestra11;
	private @Mock Muestra muestra12;
	private @Mock Muestra muestra13;
	private @Mock Muestra muestra14;
	private @Mock Muestra muestra15;
	private @Mock Muestra muestra16;
	private @Mock Muestra muestra17;
	private @Mock Muestra muestra18;
	private @Mock Muestra muestra19;
	private @Mock Muestra muestra20;
	private @Mock Muestra muestra21;
	private @Mock Muestra muestra22;
	private @Mock Muestra muestra23;
	private @Mock Muestra muestra24;
	private @Mock Muestra muestra25;
	private @Mock Muestra muestra26;
	private @Mock Muestra muestra27;
	private @Mock Muestra muestra28;
	private @Mock Muestra muestra29;
	private @Mock Muestra muestra30;
	
	private @Mock Opinion opinion1;
	private @Mock Opinion opinion2;
	private @Mock Opinion opinion3;
	private @Mock Opinion opinion4;
	private @Mock Opinion opinion5;
	private @Mock Opinion opinion6;
	private @Mock Opinion opinion7;
	private @Mock Opinion opinion8;
	private @Mock Opinion opinion9;
	private @Mock Opinion opinion10;
	private @Mock Opinion opinion11;
	private @Mock Opinion opinion12;
	private @Mock Opinion opinion13;
	private @Mock Opinion opinion14;
	private @Mock Opinion opinion15;
	private @Mock Opinion opinion16;
	private @Mock Opinion opinion17;
	private @Mock Opinion opinion18;
	private @Mock Opinion opinion19;
	private @Mock Opinion opinion20;
	private @Mock Opinion opinionVieja;
	
	private LocalDate now;
	
	private AplicacionWeb sistema;
	
	FixtureUsuariosTest() {
		sistema = mock(AplicacionWeb.class);
		usuarioN = new Usuario("12123123", sistema);
		now = LocalDate.now();
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestra5 = mock(Muestra.class);
		muestra6 = mock(Muestra.class);
		muestra7 = mock(Muestra.class);
		muestra8 = mock(Muestra.class);
		muestra9 = mock(Muestra.class);
		muestra10 = mock(Muestra.class);
		muestra11 = mock(Muestra.class);
		muestra12 = mock(Muestra.class);
		muestra13 = mock(Muestra.class);
		muestra14 = mock(Muestra.class);
		muestra15 = mock(Muestra.class);
		muestra16 = mock(Muestra.class);
		muestra17 = mock(Muestra.class);
		muestra18 = mock(Muestra.class);
		muestra19 = mock(Muestra.class);
		muestra20 = mock(Muestra.class);
		muestra21 = mock(Muestra.class);
		when(muestra21.getFechaDeCreacion()).thenReturn(now);
		muestra22 = mock(Muestra.class);
		when(muestra22.getFechaDeCreacion()).thenReturn(now);
		muestra23 = mock(Muestra.class);
		when(muestra23.getFechaDeCreacion()).thenReturn(now);
		muestra24 = mock(Muestra.class);
		when(muestra24.getFechaDeCreacion()).thenReturn(now);
		muestra25 = mock(Muestra.class);
		when(muestra25.getFechaDeCreacion()).thenReturn(now);
		muestra26 = mock(Muestra.class);
		when(muestra26.getFechaDeCreacion()).thenReturn(now);
		muestra27 = mock(Muestra.class);
		when(muestra27.getFechaDeCreacion()).thenReturn(now);
		muestra28 = mock(Muestra.class);
		when(muestra28.getFechaDeCreacion()).thenReturn(now);
		muestra29 = mock(Muestra.class);
		when(muestra29.getFechaDeCreacion()).thenReturn(now);
		muestra30 = mock(Muestra.class);
		when(muestra30.getFechaDeCreacion()).thenReturn(now);
		opinion1 = mock(Opinion.class);
		when(opinion1.getFechaDeEmision()).thenReturn(now);
		opinion2 = mock(Opinion.class);
		when(opinion2.getFechaDeEmision()).thenReturn(now);
		opinion3 = mock(Opinion.class);
		when(opinion3.getFechaDeEmision()).thenReturn(now);
		opinion4 = mock(Opinion.class);
		when(opinion4.getFechaDeEmision()).thenReturn(now);
		opinion5 = mock(Opinion.class);
		when(opinion5.getFechaDeEmision()).thenReturn(now);
		opinion6 = mock(Opinion.class);
		when(opinion6.getFechaDeEmision()).thenReturn(now);
		opinion7 = mock(Opinion.class);
		when(opinion7.getFechaDeEmision()).thenReturn(now);
		opinion8 = mock(Opinion.class);
		when(opinion8.getFechaDeEmision()).thenReturn(now);
		opinion9 = mock(Opinion.class);
		when(opinion9.getFechaDeEmision()).thenReturn(now);
		opinion10 = mock(Opinion.class);
		when(opinion10.getFechaDeEmision()).thenReturn(now);
		opinion11 = mock(Opinion.class);
		when(opinion11.getFechaDeEmision()).thenReturn(now);
		opinion12 = mock(Opinion.class);
		when(opinion12.getFechaDeEmision()).thenReturn(now);
		opinion13 = mock(Opinion.class);
		when(opinion13.getFechaDeEmision()).thenReturn(now);
		opinion14 = mock(Opinion.class);
		when(opinion14.getFechaDeEmision()).thenReturn(now);
		opinion15 = mock(Opinion.class);
		when(opinion15.getFechaDeEmision()).thenReturn(now);
		opinion16 = mock(Opinion.class);
		when(opinion16.getFechaDeEmision()).thenReturn(now);
		opinion17 = mock(Opinion.class);
		when(opinion17.getFechaDeEmision()).thenReturn(now);
		opinion18 = mock(Opinion.class);
		when(opinion18.getFechaDeEmision()).thenReturn(now);
		opinion19 = mock(Opinion.class);
		when(opinion19.getFechaDeEmision()).thenReturn(now);
		opinion20 = mock(Opinion.class);
		when(opinion20.getFechaDeEmision()).thenReturn(now);
		opinionVieja = mock(Opinion.class);
		when(opinionVieja.getFechaDeEmision()).thenReturn(now.minusMonths(5));
		
		
	}
	
	public Usuario nuevoUsuarioListoParaActualizarCategoria() {
		Usuario usuarioNuevo = this.usuarioN;
		usuarioNuevo.agregarOpinionEnviada(opinion1);
		usuarioNuevo.agregarOpinionEnviada(opinion2);
		usuarioNuevo.agregarOpinionEnviada(opinion3);
		usuarioNuevo.agregarOpinionEnviada(opinion4);
		usuarioNuevo.agregarOpinionEnviada(opinion5);
		usuarioNuevo.agregarOpinionEnviada(opinion6);
		usuarioNuevo.agregarOpinionEnviada(opinion7);
		usuarioNuevo.agregarOpinionEnviada(opinion8);
		usuarioNuevo.agregarOpinionEnviada(opinion9);
		usuarioNuevo.agregarOpinionEnviada(opinion10);
		usuarioNuevo.agregarOpinionEnviada(opinion11);
		usuarioNuevo.agregarOpinionEnviada(opinion12);
		usuarioNuevo.agregarOpinionEnviada(opinion13);
		usuarioNuevo.agregarOpinionEnviada(opinion14);
		usuarioNuevo.agregarOpinionEnviada(opinion15);
		usuarioNuevo.agregarOpinionEnviada(opinion16);
		usuarioNuevo.agregarOpinionEnviada(opinion17);
		usuarioNuevo.agregarOpinionEnviada(opinion18);
		usuarioNuevo.agregarOpinionEnviada(opinion19);
		usuarioNuevo.agregarOpinionEnviada(opinion20);
		usuarioNuevo.enviarMuestra(muestra21);
		usuarioNuevo.enviarMuestra(muestra22);
		usuarioNuevo.enviarMuestra(muestra23);
		usuarioNuevo.enviarMuestra(muestra24);
		usuarioNuevo.enviarMuestra(muestra25);
		usuarioNuevo.enviarMuestra(muestra26);
		usuarioNuevo.enviarMuestra(muestra27);
		usuarioNuevo.enviarMuestra(muestra28);
		usuarioNuevo.enviarMuestra(muestra29);
		usuarioNuevo.enviarMuestra(muestra30);
		return usuarioNuevo;
	}
	
	public Usuario nuevoUsuarioBasicoQueCumpleRevisionesPeroNoEnvios() {
		Usuario usuarioNuevo = new Usuario("323u21093u291", sistema);
		usuarioNuevo.agregarOpinionEnviada(opinion1);
		usuarioNuevo.agregarOpinionEnviada(opinion2);
		usuarioNuevo.agregarOpinionEnviada(opinion3);
		usuarioNuevo.agregarOpinionEnviada(opinion4);
		usuarioNuevo.agregarOpinionEnviada(opinion5);
		usuarioNuevo.agregarOpinionEnviada(opinion6);
		usuarioNuevo.agregarOpinionEnviada(opinion7);
		usuarioNuevo.agregarOpinionEnviada(opinion8);
		usuarioNuevo.agregarOpinionEnviada(opinion9);
		usuarioNuevo.agregarOpinionEnviada(opinion10);
		usuarioNuevo.agregarOpinionEnviada(opinion11);
		usuarioNuevo.agregarOpinionEnviada(opinion12);
		usuarioNuevo.agregarOpinionEnviada(opinion13);
		usuarioNuevo.agregarOpinionEnviada(opinion14);
		usuarioNuevo.agregarOpinionEnviada(opinion15);
		usuarioNuevo.agregarOpinionEnviada(opinion16);
		usuarioNuevo.agregarOpinionEnviada(opinion17);
		usuarioNuevo.agregarOpinionEnviada(opinion18);
		usuarioNuevo.agregarOpinionEnviada(opinion19);
		usuarioNuevo.agregarOpinionEnviada(opinion20);
		usuarioNuevo.enviarMuestra(muestra21);
		usuarioNuevo.enviarMuestra(muestra22);
		usuarioNuevo.enviarMuestra(muestra23);
		usuarioNuevo.enviarMuestra(muestra24);
		usuarioNuevo.enviarMuestra(muestra25);
		return usuarioNuevo;
	}
	
	public Usuario nuevoUsuarioBasicoQueCumpleConEnviosPeroNoConRevisiones() {
		Usuario usuarioNuevo = new Usuario("323232", sistema);
		usuarioNuevo.agregarOpinionEnviada(opinion1);
		usuarioNuevo.agregarOpinionEnviada(opinion2);
		usuarioNuevo.agregarOpinionEnviada(opinion3);
		usuarioNuevo.agregarOpinionEnviada(opinion4);
		usuarioNuevo.agregarOpinionEnviada(opinion5);
		usuarioNuevo.agregarOpinionEnviada(opinion6);
		usuarioNuevo.agregarOpinionEnviada(opinion7);
		usuarioNuevo.agregarOpinionEnviada(opinion8);
		usuarioNuevo.agregarOpinionEnviada(opinion9);
		usuarioNuevo.enviarMuestra(muestra21);
		usuarioNuevo.enviarMuestra(muestra22);
		usuarioNuevo.enviarMuestra(muestra23);
		usuarioNuevo.enviarMuestra(muestra24);
		usuarioNuevo.enviarMuestra(muestra25);
		usuarioNuevo.enviarMuestra(muestra26);
		usuarioNuevo.enviarMuestra(muestra27);
		usuarioNuevo.enviarMuestra(muestra28);
		usuarioNuevo.enviarMuestra(muestra29);
		usuarioNuevo.enviarMuestra(muestra30);
		return usuarioNuevo;
	}
	
}
