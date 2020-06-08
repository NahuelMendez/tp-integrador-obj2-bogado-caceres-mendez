package ar.edu.unq.TPIntegrador;

import java.util.HashSet;
import java.util.Set;

public class Ubicacion {
	
	private double latitud;
	private double longitud;
	
	public Ubicacion(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public double getLatitud(){
		return latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}

	public Set<Ubicacion> ubicacionesCercanas(Set<Ubicacion> ubicaciones, double distancia){
		Set<Ubicacion> retorno = new HashSet<Ubicacion>();
		for(Ubicacion ubicacion : ubicaciones) {
			if(this.medirDistancias(ubicacion) <= distancia) {
				retorno.add(ubicacion);
			}
		}
		return retorno;
	}
	
	public Double medirDistancias(Ubicacion ubicacion2) {
		 return medirDistanciasEntreUbicaciones(this.getLatitud(), this.getLongitud(), ubicacion2.getLatitud(), ubicacion2.getLongitud());
	}

	private Double medirDistanciasEntreUbicaciones(double lat1, double lng1, double lat2, double lng2) {  
	        //double radioTierra = 3958.75;//en millas  
	        double radioTierra = 6371;//en kilómetros  
	        double dLat = Math.toRadians(lat2 - lat1);  
	        double dLng = Math.toRadians(lng2 - lng1);  
	        double sindLat = Math.sin(dLat / 2);  
	        double sindLng = Math.sin(dLng / 2);  
	        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
	                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));  
	        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
	        double distancia = radioTierra * va2;  
	   
	        return Math.abs(distancia);  
	    }

	public Set<Muestra> muestrasCercanas(Set<Muestra> muestrasAMedir, double distancia) {
		Set<Muestra> retorno = new HashSet<Muestra>();
		for(Muestra muestra : muestrasAMedir) {
			if(this.medirDistancias(muestra.getUbicacion()) <= distancia) {
				retorno.add(muestra);
			}
		}
		return retorno;
	}  
}


