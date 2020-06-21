package ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario;

import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public class EstadoDeUsuarioExperto implements EstadoDeUsuario{

	@Override
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario) throws Exception {
			muestra.agregarOpinion(opinion, usuario);
	}

	@Override
	public Boolean esUsuarioBasico() {
		return false;
	}

	@Override
	public Boolean esUsuarioExperto() {
		return true;
	}

	@Override
	public void actualizarCategoria(Usuario usuario) {
		if (!usuario.cumpleConRevisionesNecesarias()
				&& !usuario.cumpleConEnviosNecesarios()) {
			usuario.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
		}
	}

	@Override
	public void agregarOpinionAMuestraVotada(Usuario usuario, Muestra muestra, Opinion opinionAAgregar) {
		muestra.cerrarOpinionesParaUsuariosBasicos();
		muestra.agregarOpinionDeUsuario(opinionAAgregar, usuario);
	}

	@Override
	public void agregarOpinionAMuestraVotadaPorExperto(Usuario usuario, Muestra muestra, Opinion opinionAAgregar) throws Exception {
		muestra.agregarOpinionDeUsuario(opinionAAgregar, usuario);
		muestra.verificarMuestra();
	}
}
