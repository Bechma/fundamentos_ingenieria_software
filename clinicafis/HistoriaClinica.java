/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author oiness
 */
public class HistoriaClinica {
    private static int numeroDeHistorias = 0;
	private Date fechaApertura;
	private int numeroHistoria;
	private ArrayList<AnotacionHC> diagnosticos;
	
	HistoriaClinica(){
		numeroHistoria = numeroDeHistorias;
		numeroDeHistorias++;
		fechaApertura = new Date();
		diagnosticos = new ArrayList();
	}
	
	ArrayList<Object> obtenerDatosHistoria(){
		ArrayList<Object> devolver = new ArrayList();
		devolver.add((new SimpleDateFormat("dd-MM-yyyy")).format(fechaApertura));
		devolver.add(String.valueOf(numeroHistoria));
		
		for(AnotacionHC anotacion : diagnosticos){
			devolver.add(anotacion.obtenerDatos());
		}
		
		return devolver;
	}
	
	ArrayList<String> nuevoDiagnostico(String cod, String textoExplicativo, Medico unMedico){
		Diagnostico diag = new Diagnostico(cod, textoExplicativo, unMedico);
		diagnosticos.add(diag);
		return diag.obtenerDatos();
	}
}
