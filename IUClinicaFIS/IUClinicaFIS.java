/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IUClinicaFIS;

import clinicafis.ClinicaFIS;
import clinicafis.Medico;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ana anaya
 */
public class IUClinicaFIS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         
        ClinicaFIS unaClinica = ClinicaFIS.getInstance(); 
		String fecha, nombre, telefono, dniM;
		Calendar cal;
		SimpleDateFormat sdf;
        
        // Definir la variable que nos permite leer String desde teclado
        final Scanner in = new Scanner(System.in);
        int opcion = 0; 
        do{
            try{ // tratamiento de las excepciones. Bloque try en el que se puede producir una excepción y la capturamos
		 
                 //Terminar de diseñar el menú (usando System.out.println(...)) con las opciones que faltan
		 // Podéis hacer vuestros propios diseños de interfaz, esta es la interfaz mínima que tenéis que entregar
                 System.out.println("\n\n********************** OPCIONES ********************\n");
                 
                 System.out.println("===== GESTIÓN DE MEDICO ===== \n" +
                                    "\t10. Nuevo Médico \n" +
                                    "\t11. Definir agenda \n" +
                                    "\t12. Consultar agenda \n" +
                                    "\t13. Ver todos los médicos \n");                 
                 
                  System.out.println("===== GESTIÓN DE PACIENTES =====\n" +
                                     "\t20. Nuevo paciente \n" +
                                     "\t21. Consultar datos personales del paciente \n" +
                                     "\t22. Eliminar Paciente\n" +
                                     "\t23. Ver todos los pacientes\n");	
                                 
                System.out.println("===== GESTIÓN DE CITAS =====  \n" +                             
                                    "\t30. Ver todas las posibles citas \n" +
                                    "\t31. Pedir una cita \n" +
                                    "\t32. Anular una cita \n" +
                                    "\t33. Consultar todas las citas pendientes\n");
                
               System.out.println("===== GESTIÓN DE CONSULTAS MEDICAS =====  \n" +                             
                                    "\t40. Terminar Consulta \n" +
                                    "\t41. Diagnosticar paciente \n" +
                                    "\t42. Consultar datos clinicos paciente\n");
                                                                 
               
                
                System.out.println("\n*******************************************************");
                		         
                System.out.println("\t0. TERMINAR");
		System.out.println("\n*******************************************************");
                 
                // Lectura de un int, para darle valor a opcion.
                opcion =Integer.parseInt(in.nextLine()); 
                
                // Estructura switch con todas las opciones de menú. Algunos de ellos ya lo tenéis hecho
                // Tenéis que terminar las opciones que están incompletas y las que no están hechas
                switch(opcion){
                    case 10: //incluir un nuevo usuario en el sistema 
                                            
                        System.out.print("Nombre del médico: ");
                        nombre=in.nextLine();
                                       
                        System.out.print("dni del médico: ");
                        String dni= in.nextLine();
                        
                        System.out.print("Especialidad: ");
                        String especialidad= in.nextLine();
                        
                        unaClinica.nuevoMedico(dni, nombre, especialidad);                                             
                        System.out.print("++++++  Operación realizada con éxito ++++++\n");
                        in.nextLine();
                    break;  
                    
                    case 11:/*Definir agenda */
                        System.out.print("dni del médico: ");
                        dni= in.nextLine();
                        System.out.print("A partir de cuántos días? : ");
                        int numeroDias = Integer.parseInt(in.nextLine());                     
                        unaClinica.definirAgenda(dni,numeroDias);
                        System.out.print("++++++  Operación realizada con éxito ++++++\n");
                        in.nextLine();
                    break;
                    
                    case 12:/*Consultar agenda*/
                        System.out.print("dni del médico: ");
                        dni= in.nextLine(); 
                        System.out.print("para qué día \n \t1 = hoy \n \t2 = mañana \n \t .... \n \t-1 = todos los días \n ");
                        numeroDias = Integer.parseInt(in.nextLine()); 
                        
                        System.out.println(unaClinica.consultarAgenda(dni,numeroDias));
                        System.out.print("++++++  Operación realizada con éxito, enter para continuar ++++++\n");
                        in.nextLine();
                                                                
                    break;
                    case 13:/*Ver todos los médicos */
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("\t\tdni\t\tnombre\t\tespecialidad");
                        System.out.println("-------------------------------------------------------------");
                        System.out.println(unaClinica.todosLosMedico());
                        System.out.print("++++++  Operación realizada con éxito ++++++\n");
                        in.nextLine();                                   
                    break;
                
                    case 20: /*Nuevo paciente  */                         
						System.out.print("dni del paciente: ");
                        dni= in.nextLine();
						System.out.print("nombre del paciente: ");
                        nombre = in.nextLine();
						System.out.print("numero tarjeta del paciente: ");
                        String numeroTarjeta = in.nextLine();
						System.out.print("telefono del paciente: ");
                        telefono = in.nextLine();
						unaClinica.crearPaciente(dni, nombre, numeroTarjeta, telefono);
						System.out.print("++++++  Operación realizada con éxito, enter para continuar ++++++\n");
                        in.nextLine();
                    break;
                  
                    case 21: /* Consultar datos del paciente */
						System.out.print("dni del paciente: ");
                        dni = in.nextLine();
						ArrayList<String> data = unaClinica.consultarPaciente(dni);
						System.out.println("Nombre: " + data.get(1) + "\tNumero Tarjeta: " + data.get(2) + "\tTelefono: " + data.get(3));
						System.out.print("++++++  Operación realizada con éxito, enter para continuar ++++++\n");
                        in.nextLine();
                    break;
					
                    case 22: /* Eliminar paciente  */
						System.out.print("dni del paciente: ");
                        dni = in.nextLine();
						ArrayList<String> datos = unaClinica.eliminarPaciente(dni);
						System.out.println(datos);
						System.out.print("++++++  Operación realizada con éxito, enter para continuar ++++++\n");
                        in.nextLine();
                    break;
  
    
                    case 23: /* Ver todos los pacientes */
						System.out.println();
						List<String> pacientes = unaClinica.todosLosPacientes();
						
						for(String s : pacientes)
							System.out.println(s);
						System.out.print("\n++++++  Operación realizada con éxito, enter para continuar ++++++\n");
                        in.nextLine();
                    break;
					
                    case 30: /* Ver posibles citas */
						System.out.print("dni del paciente: ");
                        dni = in.nextLine();
						System.out.print("dni del medico: ");
                        dniM = in.nextLine();
						ArrayList<Calendar> citas = unaClinica.obtenerPosiblesCitas(dniM, dni);
						sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
						
						for(Calendar c : citas)
							System.out.println(sdf.format(c.getTime()));
						
						System.out.print("++++++  Operación realizada con éxito, enter para continuar ++++++\n");
                        in.nextLine();
                    break;

                    case 31: /* Pedir una cita */
						System.out.print("dni del paciente: ");
                        dni = in.nextLine();
						System.out.print("dni del medico: ");
                        dniM = in.nextLine();
						System.out.println("Introduce fecha en formato: DD/MM/YYYY HH:mm");
						fecha = in.nextLine();
						fecha = fecha.concat(":00");
						sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						cal = Calendar.getInstance();
						cal.setTime(sdf.parse(fecha));
						
						ArrayList<String> resultado = unaClinica.pedirCita(dni, dniM, cal);
						System.out.println("Paciente: " + resultado.get(0) + "\tMedico: " + resultado.get(1) + "\tA las: " + resultado.get(2));
						
						System.out.print("++++++  Operación realizada con éxito, enter para continuar ++++++\n");
                        in.nextLine();
                    break;

                    case 32: /* Anular cita */
						System.out.print("dni del paciente: ");
                        dni = in.nextLine();
						System.out.println("Introduce fecha en formato: dia/mes/anio_hora:minutos");
						fecha = in.nextLine();
						fecha = fecha.concat(":00");
						sdf = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss");
						cal = Calendar.getInstance();
						cal.setTime(sdf.parse(fecha));
						
						ArrayList<Object> res = unaClinica.anularCita(dni, cal);
						ArrayList<String> anulacion = (ArrayList<String>)res.get(1);
						System.out.println("Paciente: " + res.get(0) + "\tCita anulada en el dia: " + anulacion.get(0) + " Medico: " + anulacion.get(1));
						
						System.out.print("++++++  Operación realizada con éxito, enter para continuar ++++++\n");
                        in.nextLine();
                    break;

                    case 33: /*  Consultar todas las citas pendientes  */
						System.out.print("dni del paciente: ");
                        dni = in.nextLine();
						
						ArrayList<Object> listaCitas = unaClinica.consultarCitas(dni);
						System.out.println("Citas para el paciente: ");
						for(Object obj : listaCitas){
							ArrayList<String> o = (ArrayList<String>)obj;
							System.out.println("fecha: " + o.get(0) + "\tNombre medico: " + o.get(1) + "\tEspecialidad: " + o.get(2));
						}
						
						System.out.print("++++++  Operación realizada con éxito, enter para continuar ++++++\n");
                        in.nextLine();
                    break;  
                    case 40: /* Terminar consulta */
						System.out.print("dni del paciente: ");
                        dni = in.nextLine();
						System.out.print("dni del medico: ");
                        dniM = in.nextLine();
						unaClinica.terminarConsullta(dniM, dni);
						System.out.print("++++++  Operación realizada con éxito, enter para continuar ++++++\n");
                        in.nextLine();
                    break;

                    case 41: /* Hacer un diagnóstico */
						System.out.print("dni del paciente: ");
                        dni = in.nextLine();
						System.out.print("codigo diagnostico: ");
                        String codD = in.nextLine();
						System.out.print("texto explicativo: ");
                        String textoE = in.nextLine();
						System.out.print("dni del medico: ");
                        dniM = in.nextLine();
						
						ArrayList<Object> diag = unaClinica.diagnosticar(dni, codD, textoE, dniM);
						
						System.out.println("Paciente: " + diag.get(0));
						ArrayList<String> datosDiagnostico = (ArrayList<String>)diag.get(1);
						System.out.println("Medico: " + datosDiagnostico.get(0) + "\tFecha: " + datosDiagnostico.get(1) + "\tTexto Explicativo: " + datosDiagnostico.get(2));
						
						
						System.out.print("++++++  Operación realizada con éxito, enter para continuar ++++++\n");
                        in.nextLine();
                    break;

                    case 42: /*  Consultar datos clinicos pendientes  */
						System.out.print("dni del paciente: ");
                        dni = in.nextLine();
						ArrayList<Object> datosClinicos = unaClinica.consultarDatosClinicos(dni);
						System.out.println("Nombre: " + datosClinicos.get(0));
						for(int i = 1; i < datosClinicos.size(); i++){
							ArrayList<String> dc = ((ArrayList<String>)datosClinicos.get(i));
							System.out.println(dc.get(0) + "\tComentario:  " + dc.get(1) + "\nRealizado por el Doctor: " + dc.get(2));
						}
						System.out.print("++++++  Operación realizada con éxito, enter para continuar ++++++\n");
                        in.nextLine();
						System.out.print("++++++  Operación realizada con éxito, enter para continuar ++++++\n");
                        in.nextLine();
                    break; 

                    case 0: /* terminar */
                    break;                        
                                    
                    default:
                        System.out.println("OPCION NO VÁLIDA");
                    break;
                }
//               
            }catch(Exception ex){ // captura de la excepción
                System.err.println("se ha producido la siguiente excepcion: "+ ex.getMessage());
            } 
        }while(opcion !=0); 
        System.exit(0);
    }  
    }
    
    

