package ar.edu.unq.TPIntegrador.usuarioYEstadosDeUsuario;

import ar.edu.unq.TPIntegrador.Opinion;
import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public class EstadoDeUsuarioBasico implements EstadoDeUsuario{

	@Override
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario) throws Exception {
			muestra.agregarOpinion(opinion, usuario);
	}

	@Override
	public Boolean esUsuarioBasico() {
		return true;
	}

	@Override
	public Boolean esUsuarioExperto() {
		return false;
	}

	@Override
	public void actualizarCategoria(Usuario usuario) {
		if (usuario.cumpleConRevisionesNecesarias()
				& usuario.cumpleConEnviosNecesarios()) {
			usuario.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		}
	}

	/*
	@Override
	public void agregarOpinionAMuestraVotada(Usuario usuario, Muestra muestra, Opinion opinionAAgregar) {
		muestra.agregarOpinionDeUsuario(opinionAAgregar, usuario);
	}
	*/

	@Override
	public void agregarOpinionAMuestraVotadaPorExperto(Usuario usuario, Muestra muestra, Opinion opinionAAgregar) throws Exception {
		throw new Exception("Esta muestra solo puede ser votada por usuarios expertos");
	}

}
