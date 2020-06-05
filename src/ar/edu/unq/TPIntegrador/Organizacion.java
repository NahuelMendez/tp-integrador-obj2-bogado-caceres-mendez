package ar.edu.unq.TPIntegrador;

public class Organizacion implements IObserver{

	private Integer cantidadDePersonasTrabajando;
	private Ubicacion ubicacion;
	private TipoDeOrganizacion tipoDeOrganizacion;
	private FuncionalidadExterna funcionalidadParaNuevaMuestra;
	private FuncionalidadExterna  funcionalidadParaNuevaVerificacion;

	public Organizacion(Integer cantidadDePersonasTrabajando, Ubicacion ubicacion, TipoDeOrganizacion tipoDeOrganizacion, FuncionalidadExterna funcionalidadMuestra, FuncionalidadExterna funcionalidadVerificacion) {
		super();
		this.cantidadDePersonasTrabajando = cantidadDePersonasTrabajando;
		this.ubicacion = ubicacion;
		this.tipoDeOrganizacion = tipoDeOrganizacion;
		this.funcionalidadParaNuevaMuestra = funcionalidadMuestra;
		this.funcionalidadParaNuevaVerificacion = funcionalidadVerificacion;
	}

	public Integer getCantidadDePersonasTrabajando() {
		// TODO Auto-generated method stub
		return this.cantidadDePersonasTrabajando;
	}

	public Ubicacion getUbicacion() {
		// TODO Auto-generated method stub
		return ubicacion;
	}

	public TipoDeOrganizacion getTipoDeOrganizacion() {
		// TODO Auto-generated method stub
		return tipoDeOrganizacion;
	}

	public void registrarseAZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		zonaDeCobertura.agregarObserver(this);
		
	}

	public void dejarZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		zonaDeCobertura.quitarObserver(this);
		
	}

	public FuncionalidadExterna getFuncionalidadExternaParaNuevaMuestra() {
		return funcionalidadParaNuevaMuestra;
	}

	public void configurarFuncionalidadExternaNuevaMuestra(FuncionalidadExterna funcionalidadExterna) {
		this.funcionalidadParaNuevaMuestra = funcionalidadExterna;
		
	}

	public FuncionalidadExterna getFuncionalidadExternaParaNuevaVerificacion() {
		return funcionalidadParaNuevaVerificacion;
	}

	public void configurarFuncionalidadExternaNuevaVerificacion(FuncionalidadExterna funcionalidadExterna) {
		this.funcionalidadParaNuevaVerificacion = funcionalidadExterna;
		
	}

	public void actualizar(ZonaDeCobertura zonaDeCobertura, Muestra muestra, String aviso) {
		if (aviso == "Nueva muestra") {
			funcionalidadParaNuevaMuestra.nuevoEvento(this, zonaDeCobertura, muestra);
		}else {
			funcionalidadParaNuevaVerificacion.nuevoEvento(this, zonaDeCobertura, muestra);
		}
		
	}

}
