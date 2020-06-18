package ar.edu.unq.TPIntegrador.filtro;

import java.util.Set;

import ar.edu.unq.TPIntegrador.MuestraYEstados.Muestra;

public interface IFiltro {

	Set<Muestra> filtrar(Set<Muestra> listaDeMuestras);

}
