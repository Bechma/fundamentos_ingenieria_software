/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafis;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author oiness
 */
public abstract class AnotacionHC {
    protected Date fecha;
    protected String comentario;
    protected Medico unMedico;
    protected HistoriaClinica hc;
    
	ArrayList<String> obtenerDatos(){
		ArrayList<String> devolver = new ArrayList();
		devolver.add(fecha.toString());
		devolver.add(comentario);
		devolver.add(unMedico.getNombre());
		
		return devolver;
	}
    
}
