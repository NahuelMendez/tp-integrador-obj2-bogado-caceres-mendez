package ar.edu.unq.TPIntegrador;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.mockito.Mock;

public class FixtureUsuarioNovatoTest {

	private UsuarioNovato usuarioN;
	
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
	
	public FixtureUsuarioNovatoTest() {
		sistema = mock(AplicacionWeb.class);
		usuarioN = new UsuarioNovato("12123123", sistema);
		now = LocalDate.now();
		muestra1 = mock(Muestra.class);
		when(muestra1.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra2 = mock(Muestra.class);
		when(muestra2.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra3 = mock(Muestra.class);
		when(muestra3.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra4 = mock(Muestra.class);
		when(muestra4.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra5 = mock(Muestra.class);
		when(muestra5.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra6 = mock(Muestra.class);
		when(muestra6.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra7 = mock(Muestra.class);
		when(muestra7.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra8 = mock(Muestra.class);
		when(muestra8.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra9 = mock(Muestra.class);
		when(muestra9.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra10 = mock(Muestra.class);
		when(muestra10.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra11 = mock(Muestra.class);
		when(muestra11.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra12 = mock(Muestra.class);
		when(muestra12.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra13 = mock(Muestra.class);
		when(muestra13.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra14 = mock(Muestra.class);
		when(muestra14.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra15 = mock(Muestra.class);
		when(muestra15.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra16 = mock(Muestra.class);
		when(muestra16.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra17 = mock(Muestra.class);
		when(muestra17.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra18 = mock(Muestra.class);
		when(muestra18.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra19 = mock(Muestra.class);
		when(muestra19.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
		muestra20 = mock(Muestra.class);
		when(muestra20.usuarioAptoParaVotar(usuarioN)).thenReturn(true);
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
	
	public UsuarioNovato nuevoUsuarioListoParaActualizarCategoria() throws Exception {
		UsuarioNovato usuarioNuevo = this.usuarioN;
		usuarioNuevo.opinarSobreMuestra(muestra1, opinion1);
		usuarioNuevo.opinarSobreMuestra(muestra2, opinion2);
		usuarioNuevo.opinarSobreMuestra(muestra3, opinion3);
		usuarioNuevo.opinarSobreMuestra(muestra4, opinion4);
		usuarioNuevo.opinarSobreMuestra(muestra5, opinion5);
		usuarioNuevo.opinarSobreMuestra(muestra6, opinion6);
		usuarioNuevo.opinarSobreMuestra(muestra7, opinion7);
		usuarioNuevo.opinarSobreMuestra(muestra8, opinion8);
		usuarioNuevo.opinarSobreMuestra(muestra9, opinion9);
		usuarioNuevo.opinarSobreMuestra(muestra10, opinion10);
		usuarioNuevo.opinarSobreMuestra(muestra11, opinion11);
		usuarioNuevo.opinarSobreMuestra(muestra12, opinion12);
		usuarioNuevo.opinarSobreMuestra(muestra13, opinion13);
		usuarioNuevo.opinarSobreMuestra(muestra14, opinion14);
		usuarioNuevo.opinarSobreMuestra(muestra15, opinion15);
		usuarioNuevo.opinarSobreMuestra(muestra16, opinion16);
		usuarioNuevo.opinarSobreMuestra(muestra17, opinion17);
		usuarioNuevo.opinarSobreMuestra(muestra18, opinion18);
		usuarioNuevo.opinarSobreMuestra(muestra19, opinion19);
		usuarioNuevo.opinarSobreMuestra(muestra20, opinion20);
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
	
	public UsuarioNovato nuevoUsuarioNovatoQueCumpleRevisionesPeroNoEnvios() throws Exception {
		UsuarioNovato usuarioNuevo = this.usuarioN;
		usuarioNuevo.opinarSobreMuestra(muestra1, opinion1);
		usuarioNuevo.opinarSobreMuestra(muestra2, opinion2);
		usuarioNuevo.opinarSobreMuestra(muestra3, opinion3);
		usuarioNuevo.opinarSobreMuestra(muestra4, opinion4);
		usuarioNuevo.opinarSobreMuestra(muestra5, opinion5);
		usuarioNuevo.opinarSobreMuestra(muestra6, opinion6);
		usuarioNuevo.opinarSobreMuestra(muestra7, opinion7);
		usuarioNuevo.opinarSobreMuestra(muestra8, opinion8);
		usuarioNuevo.opinarSobreMuestra(muestra9, opinion9);
		usuarioNuevo.opinarSobreMuestra(muestra10, opinion10);
		usuarioNuevo.opinarSobreMuestra(muestra11, opinion11);
		usuarioNuevo.opinarSobreMuestra(muestra12, opinion12);
		usuarioNuevo.opinarSobreMuestra(muestra13, opinion13);
		usuarioNuevo.opinarSobreMuestra(muestra14, opinion14);
		usuarioNuevo.opinarSobreMuestra(muestra15, opinion15);
		usuarioNuevo.opinarSobreMuestra(muestra16, opinion16);
		usuarioNuevo.opinarSobreMuestra(muestra17, opinion17);
		usuarioNuevo.opinarSobreMuestra(muestra18, opinion18);
		usuarioNuevo.opinarSobreMuestra(muestra19, opinion19);
		usuarioNuevo.opinarSobreMuestra(muestra20, opinion20);
		usuarioNuevo.enviarMuestra(muestra21);
		usuarioNuevo.enviarMuestra(muestra22);
		usuarioNuevo.enviarMuestra(muestra23);
		usuarioNuevo.enviarMuestra(muestra24);
		usuarioNuevo.enviarMuestra(muestra25);
		return usuarioNuevo;
	}
	
	public UsuarioNovato nuevoUsuarioNovatoQueCumpleConEnviosPeroNoConRevisiones() throws Exception {
		UsuarioNovato usuarioNuevo = this.usuarioN;
		usuarioNuevo.opinarSobreMuestra(muestra1, opinion1);
		usuarioNuevo.opinarSobreMuestra(muestra2, opinion2);
		usuarioNuevo.opinarSobreMuestra(muestra3, opinion3);
		usuarioNuevo.opinarSobreMuestra(muestra4, opinion4);
		usuarioNuevo.opinarSobreMuestra(muestra5, opinion5);
		usuarioNuevo.opinarSobreMuestra(muestra6, opinion6);
		usuarioNuevo.opinarSobreMuestra(muestra7, opinion7);
		usuarioNuevo.opinarSobreMuestra(muestra8, opinion8);
		usuarioNuevo.opinarSobreMuestra(muestra9, opinion9);
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
