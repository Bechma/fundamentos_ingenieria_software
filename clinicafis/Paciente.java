/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafis;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author oiness
 */
public class Paciente {
    private String dni, nombre, numeroTarjeta, telefono;
	private Boolean activo;
	private HistoriaClinica hc;
	private ArrayList<Cita> citas;

	public Paciente(String dni, String nombre, String numeroTarjeta, String telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.numeroTarjeta = numeroTarjeta;
		this.telefono = telefono;
		activo = true;
		citas = new ArrayList();
	}
	
	ArrayList<String> obtenerDatos(){
		ArrayList<String> infoPaciente = new ArrayList();
		infoPaciente.add(dni);
		infoPaciente.add(nombre);
		infoPaciente.add(numeroTarjeta);
		infoPaciente.add(telefono);
		
		return infoPaciente;
	}
	
	ArrayList<Object> obtenerDatosClinicos(){
		ArrayList<Object> infoPaciente = new ArrayList();
		
		infoPaciente.add(nombre);
		infoPaciente.add(hc.obtenerDatosHistoria());
		
		return infoPaciente;
	}
	
	ArrayList<String> bajaClinica(){
		for(Cita cita : citas)
			cita.liberarCita();
		citas.clear();
		modificarActivo(false);
		ArrayList<String> infoBajaClinica = new ArrayList();
		infoBajaClinica.add(dni);
		infoBajaClinica.add(nombre);
		return infoBajaClinica;
	}
	
	ArrayList<Object> anularCita(Calendar fecha) throws Exception{
		Cita cita = buscarCita(fecha);
		ArrayList<Object> datosConfirmacion = new ArrayList();
		datosConfirmacion.add(nombre);
		datosConfirmacion.add(cita.anularCita());
		citas.remove(cita);
		return datosConfirmacion;
	}
	
	ArrayList<Object> consultarCitas(){
		ArrayList<Object> listadoCitas = new ArrayList();
		for(Cita cita : citas){
			if(cita.getEstado() == TipoEstado.PENDIENTE){
				listadoCitas.add(cita.obtenerDatos());
			}
		}
		return listadoCitas;
	}
	
	ArrayList<Object> diagnostico(String codDiagnostico, String textoExplicativo, Medico miMedico){
		ArrayList<Object> infoDiagnostico = new ArrayList();
		infoDiagnostico.add(nombre);
		infoDiagnostico.add(hc.nuevoDiagnostico(codDiagnostico, textoExplicativo, miMedico));
		return infoDiagnostico;
	}
	
	
	Boolean tienesCitas(Medico unMedico){
		ArrayList<Cita> citasPosteriores = seleccionarCitasPosteriores();
		for(Cita cita : citasPosteriores){
			if(cita.esTuMedico(unMedico))
				return true;
		}
		return false;
	}
	
	void incluirCita(Cita cita){
		citas.add(cita);
	}
	
	ArrayList<Cita> seleccionarCitasPosteriores(){
		ArrayList<Cita> posteriores = new ArrayList();
		Calendar hoy = Calendar.getInstance();
		for(Cita cita : citas){
			if(hoy.compareTo(cita.getFecha()) < 0)
				posteriores.add(cita);
		}
		return posteriores;
	}
	
	
	
	
	Cita buscarCita(Calendar fecha) throws Exception{
		for(Cita cita : citas){
			if(cita.getFecha().equals(fecha))
				return cita;
		}
		throw new Exception("No existe ninguna cita con esa fecha");
	}

	String getDni() {
		return dni;
	}

	String getNombre() {
		return nombre;
	}

	String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	String getTelefono() {
		return telefono;
	}

	Boolean getActivo() {
		return activo;
	}

	HistoriaClinica getHc() {
		return hc;
	}

	ArrayList<Cita> getCitas() {
		return citas;
	}
	
	private void modificarActivo(Boolean act){
		activo = act;
	}

	@Override
	public String toString() {
		return "Paciente{" + "dni=" + dni + ", nombre=" + nombre + ", numeroTarjeta=" + numeroTarjeta + ", telefono=" + telefono + ", activo=" + activo + '}';
	}
	
}
