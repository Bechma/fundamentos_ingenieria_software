/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafis;

import java.util.Date;

/**
 *
 * @author oiness
 */
public class Diagnostico extends AnotacionHC {
    private String codDiagnostico;

    public Diagnostico(String codDiagnostico, String textoExplicativo, Medico medico) {
        this.codDiagnostico = codDiagnostico;
		this.comentario = textoExplicativo;
		this.unMedico = medico;
		fecha = new Date();
    }
    
}
