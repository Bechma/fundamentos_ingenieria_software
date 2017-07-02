/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ana anaya
 */
public class ClinicaFIS {
    // patrón singleton
    private static ClinicaFIS instancia = new ClinicaFIS();
    public static ClinicaFIS getInstance() { 
        return instancia;
    }
    
    // Map en el que se guardan los médicos, la key es el dni del médico
    private Map<String, Medico> misMedicos = new HashMap();       
	
    private Map<String, Paciente> misPacientes = new HashMap();
	private Map<String, Paciente> tarjetaPaciente = new HashMap();
	
	
	public void crearPaciente(String dni, String nombre, String numeroTarjeta, String telefono) throws Exception{
		if(existePaciente(dni)) throw new Exception("ya existe otro paciente con ese dni");
		if(existePacienteConTarjeta(numeroTarjeta)) throw new Exception("ya existe otro paciente con ese numero de tarjeta");
		
		Paciente unPaciente = new Paciente(dni, nombre, numeroTarjeta, telefono);
		misPacientes.put(dni, unPaciente);
		tarjetaPaciente.put(numeroTarjeta, unPaciente);
	}
	
	public ArrayList<String> consultarPaciente(String dni) throws Exception{
		Paciente unPaciente = buscarPaciente(dni);
		return unPaciente.obtenerDatos();
	}
	
	public ArrayList<Object> consultarDatosClinicos(String dni) throws Exception{
		Paciente unPaciente = buscarPaciente(dni);
		return unPaciente.obtenerDatosClinicos();
	}
	
	public ArrayList<String> eliminarPaciente(String dni) throws Exception{
		Paciente unPaciente = buscarPaciente(dni);
		return unPaciente.bajaClinica();
	}
	
	public ArrayList<Object> anularCita(String dni, Calendar fecha) throws Exception{
		Paciente unPaciente = buscarPaciente(dni);
		return unPaciente.anularCita(fecha);
	}
	
	public ArrayList<Object> consultarCitas(String dni) throws Exception{
		Paciente unPaciente = buscarPaciente(dni);
		return unPaciente.consultarCitas();
	}
	
	public ArrayList<Object> diagnosticar(String dniP, String codDiagnostico, String textoExplicativo, String dniM) throws Exception{
		Medico unMedico = buscarMedico(dniM);
		Paciente unPaciente = buscarPaciente(dniP);
		return unPaciente.diagnostico(codDiagnostico, textoExplicativo, unMedico);
	}
	
	public ArrayList<Calendar> obtenerPosiblesCitas(String dniM, String dniP) throws Exception{
		Medico unMedico = buscarMedico(dniM);
		Paciente unPaciente = buscarPaciente(dniP);
		
		if(unPaciente.tienesCitas(unMedico))
			throw new Exception("ya tienes cita PENDIENTE para ese médico");
		return unMedico.obtenerPosiblesCitas();
	}
	
	public ArrayList<String> pedirCita(String dniP, String dniM, Calendar fecha) throws Exception{
		Medico unMedico = buscarMedico(dniM);
		Paciente unPaciente = buscarPaciente(dniP);
		return unMedico.asignarCita(unPaciente, fecha);
	}
	
	public void terminarConsullta(String dniM, String dniP) throws Exception{
		Medico unMedico = buscarMedico(dniM);
		Paciente unPaciente = buscarPaciente(dniP);
		unMedico.terminarConsulta(unPaciente);
	}
	
	
	private Paciente buscarPaciente(String dni) throws Exception{
		if(!existePaciente(dni)) throw new  Exception("NO EXISTE EL Paciente CON ESE DNI"); 
       return misPacientes.get(dni);
	}
	
	private Boolean existePaciente(String dni){
		return misPacientes.containsKey(dni);
	}
	
	private Boolean existePacienteConTarjeta(String nTarjeta){
		return tarjetaPaciente.containsKey(nTarjeta);
	}
	
	
	//////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////
    
    public void nuevoMedico(String dni,String nombre,String especialidad) throws Exception{
        if(existeMedico(dni)) throw new Exception("YA EXISTE UN MÉDICO CON ESE DNI");
        misMedicos.put(dni, new Medico(dni,nombre,especialidad));
    }

    private boolean existeMedico(String dni) {
         return misMedicos.containsKey(dni);
    }
    
    public void definirAgenda(String dniM,int aPartirDe) throws Exception { 
         Medico unMedico = buscarMedico(dniM);
         unMedico.definirAgenda(aPartirDe);
    }

    private Medico buscarMedico(String dni) throws Exception {
       if(!existeMedico(dni)) throw new  Exception("NO EXISTE EL MÉDICO CON ESE DNI"); 
       return misMedicos.get(dni);
    }

    public List<String> consultarAgenda(String dniM,int numeroDias) throws Exception { 
        Medico unMedico = buscarMedico(dniM);
        return unMedico.obtenerCitas(numeroDias);
    }
    
    public List<String> todosLosMedico(){
        List<String> salida = new ArrayList();
        for (Map.Entry<String, Medico> entry : misMedicos.entrySet()) {            
            Medico unMedico = entry.getValue();
            salida.add(unMedico.toString());
        }
        return salida;
    }
    
	public List<String> todosLosPacientes(){
        List<String> salida = new ArrayList();
        for (Map.Entry<String, Paciente> entry : misPacientes.entrySet()) {            
            Paciente unPaciente = entry.getValue();
            salida.add(unPaciente.toString());
        }
        return salida;
    }
    
}
