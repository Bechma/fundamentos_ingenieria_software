/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafis;

import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author ana anaya
 */
class Cita {
    private static int TiempoDeCita = 10; // variable de ámbito de clase que indica el tiempo que tiene asignado cada cita
    private Calendar fecha;
    private TipoEstado estado = TipoEstado.LIBRE;   
    private Medico miMedico;
	private Paciente miPaciente;
    
    // A partir del número de cita de esa fecha se calcula la hora de la cita
    // a cada cita se le reservan 10 minutos
    Cita(Calendar fecha, int numeroCita, Medico medico) { 
        this.fecha =(Calendar) fecha.clone();
        this.fecha.set(Calendar.HOUR,9);
        this.fecha.set(Calendar.MINUTE,0);
        this.fecha.add(fecha.MINUTE,numeroCita*TiempoDeCita);
        this.miMedico = medico;
		this.miPaciente = null;
    }
	
	void liberarCita(){
		this.miPaciente = null;
		modificarEstado(TipoEstado.LIBRE);
	}
	
	ArrayList<String> anularCita() throws Exception{
		Calendar hoy = Calendar.getInstance();
		
		if(fecha.compareTo(hoy) > 0 || estado == TipoEstado.ATENDIDA){
			throw new Exception("la cita no puede ser cancelada");
		}
		liberarCita();
		ArrayList<String> datosAnulacion = new ArrayList();
		datosAnulacion.add(new SimpleDateFormat("dd-MM-yyyy").format(fecha));
		datosAnulacion.add(miMedico.getNombre());
		
		return datosAnulacion;
	}
	
	ArrayList<String> obtenerDatos(){
		return new ArrayList(Arrays.asList(new SimpleDateFormat("dd-MM-yyyy").format(fecha), miMedico.getNombre(), miMedico.getEspecialidad()));
	}
	
	void asignarPaciente(Paciente unPaciente){
		modificarEstado(TipoEstado.PENDIENTE);
		unPaciente.incluirCita(this);
		this.miPaciente = unPaciente;
	}
	
	Boolean esTuPaciente(Paciente paciente){
		return paciente == miPaciente;
	}
	
	Boolean esTuMedico(Medico medico){
		return medico == miMedico;
	}
    
	void modificarEstado(TipoEstado estado){
		this.estado = estado;
	}
	
    // Redefinición de toString, cuando la cita tenga asignado el Paciente 
    // tendrás que quitar los comentarios que hay en este código
    @Override
    public String toString()
     {
          SimpleDateFormat hmsf = new SimpleDateFormat("hh:mm");
          String salida =  "hora: " + hmsf.format(fecha.getTime()) +" (" + estado.toString()+ ")";
          if (miPaciente != null)
              salida += "Paciente: " + miPaciente.getNombre();
          salida +=  "\n";
          return salida;
     }

	Calendar getFecha() {
		return fecha;
	}

	TipoEstado getEstado() {
		return estado;
	}
    
}
