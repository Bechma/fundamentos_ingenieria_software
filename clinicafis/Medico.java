/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar; 
import java.util.List;
import java.util.Map; 
import java.util.TreeMap;

/**
 *
 * @author ana anaya
 */
public class Medico {
    private static int NumeroDias = 5; // Variable de ámbito de clase que indica el número de días que definimos en la agenda de un médico
	private static final int MAXCITAS = 10;
    private String dni;
    private String nombre;
    private String especialidad;
    // Map en el que se guardan las citas del médico ordenadas por la key, que son fechas 
    // Cada value de este Map va a ser la lista de Citas del médico para un determinado día
    private Map<Calendar, List<Cita>> agenda = new TreeMap(); 
    
    // Constructor
    Medico(String dni, String nombre, String especialidad){
        this.dni=dni;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.definirAgenda(0);
    }
    
    // Redefinición de toString 
    @Override
    public String toString(){     
        return  "\t\t" + dni +  "\t\t" + nombre + "\t\t" + especialidad + "\n";
    }
	
	ArrayList<Calendar> obtenerPosiblesCitas() throws Exception{
		List<Cita> citas = seleccionarCitasPosteriores();
		ArrayList<Calendar> listaPosiblesFechas = new ArrayList();
		for(int i = 0; i < citas.size() && listaPosiblesFechas.size() < MAXCITAS; ++i){
			Cita cita = citas.get(i);
			if(cita.getEstado() == TipoEstado.LIBRE){
				listaPosiblesFechas.add(cita.getFecha());
			}
		}
		return listaPosiblesFechas;
	}
	
	
	ArrayList<Cita> seleccionarCitasPosteriores(){
		ArrayList<Cita> citas = new ArrayList();
		Calendar hoy = Calendar.getInstance();
		for(Map.Entry<Calendar, List<Cita>> it : agenda.entrySet()){
			if(hoy.before(it.getKey()))
				citas.addAll(it.getValue());
		}
		return citas;
	}
	
	
	ArrayList<String> asignarCita(Paciente unPaciente, Calendar fecha) throws Exception{
		ArrayList<String> infoCita = new ArrayList();
		infoCita.add(unPaciente.getNombre());
		infoCita.add(nombre);
		infoCita.add(new SimpleDateFormat("HH:mm | dd-MM-yyyy").format(fecha.getTime()));
		Cita cita = buscarCita(fecha);
		cita.asignarPaciente(unPaciente);
		return infoCita;
	}
	
	
	Cita buscarCita(Calendar fecha) throws Exception{
		List<Cita> listaCitas = this.seleccionarCitasDia(fecha);
		for(Cita cita : listaCitas)
			if(fecha.getTime() == cita.getFecha().getTime())
				return cita;
		throw new Exception("No existe ninguna cita en esa fecha dada.");
	}
	
	
	void terminarConsulta(Paciente unPaciente) throws Exception{
		List<Cita> citas = seleccionarCitasDia(Calendar.getInstance());
		Boolean encontrado = false;
		for(int i = 0; i < citas.size() && !encontrado; i++)
			if(citas.get(i).esTuPaciente(unPaciente)){
				citas.get(i).modificarEstado(TipoEstado.ATENDIDA);
				encontrado = true;
			}
		if(!encontrado)
			throw new Exception("no existe Cita para hoy del ese Paciente con ese Médico");
	}
	
	
    
    // Definir una agenda para 5 días
    // a partir de un número de dias posteriores al actual proporcionado en @aPartirDe
    public void definirAgenda(int aPartirDe){
    
        Calendar hoy = Calendar.getInstance();
        hoy.add(Calendar.DATE, aPartirDe);
        // se crean 5 entradas en la agenda a partir del numero de días indicado
        for (int i = 0; i < NumeroDias; i++) {  
            hoy.add(Calendar.DATE, i);
            List<Cita> citas = new ArrayList();     
            for (int j = 0; j < 10; j++) {
                    citas.add(new Cita(hoy,j,this));
            }
            agenda.put(hoy,citas);
            hoy = Calendar.getInstance();  
            hoy.add(Calendar.DATE, aPartirDe);
        }
    }
    
    // Proporciona las citas de un médico para el día que se le indique 
    // Si numeroDias == 0 --> hoy
    // Si numeroDias == 1 --> mañana
    // Si numeroDias == 2 --> pasado mañana
    // ....
    // Si numeroDias == -1 --> todos los días    
    List<String> obtenerCitas(int numeroDias) throws Exception{
        List<String> citas = new ArrayList();
        List<Cita> citasDelDia;
        if(numeroDias != -1){ 
            Calendar dia = Calendar.getInstance();
            dia.add(Calendar.DATE, numeroDias-1);            
            citas.add((new SimpleDateFormat("dd/MM")).format(dia.getTime())+"\n");
            citasDelDia = this.seleccionarCitasDia(dia);
            for (Cita cita : citasDelDia) {
                citas.add(cita.toString());  
            }
        }else if(numeroDias == -1)
            {
                for (Map.Entry<Calendar, List<Cita>> entry : agenda.entrySet()) {
                    Calendar key = entry.getKey();
                    List<Cita> citasDia = entry.getValue();
                    citas.add((new SimpleDateFormat("dd/MM")).format(key.getTime())+"\n");
                    for (Cita cita : citasDia) {
                            citas.add(cita.toString());  
                    }                    
                }  
            }
        return citas;   
    }
    
   // La visibilidad de esta operación es privada porque no se necesita usalar desde fuera de la clase Medico,
    // si necesitáis de ella en otro ámbito cambiarla 
   private  List<Cita> seleccionarCitasDia(Calendar dia ) throws Exception{
        List<Cita> salida = new ArrayList();         
        boolean encontrado = false; 
        for (Map.Entry<Calendar, List<Cita>> entry : agenda.entrySet()) {
            Calendar key = entry.getKey();
            if((key.get(Calendar.DATE) == dia.get(Calendar.DATE)) && (key.get(Calendar.MONTH)==dia.get(Calendar.MONTH)) && (key.get(Calendar.YEAR)== dia.get(Calendar.YEAR)) && !encontrado)
                encontrado = true;
                salida = agenda.get(key);
        }
        if(!encontrado) throw new Exception("PARA ESE DÍA NO ESTÁ DEFINIDA LA AGENDA");
        return salida;    
    
    }

	String getNombre() {
		return nombre;
	}

	String getEspecialidad() {
		return especialidad;
	}
   
   
}