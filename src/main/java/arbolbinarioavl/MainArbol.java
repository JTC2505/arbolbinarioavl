package arbolbinarioavl;

// LIBRERIAS
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainArbol {
    // VARIABLES PARA LA LECTURA DEL ARCHIVO ENTRADA.TXT
    File archivoLectura = new File("Archivo/Lectura/ENTRADA.txt");
    BufferedReader almacenar;
    FileReader leer;
    
    Scanner TecladoEntrada = new Scanner(System.in);
    int menuPrincipal = 0;
    int CantidadDatos = 0;
    
    public MainArbol() {
        // VERIFICACION DE LA CREACION DEL ARCHIVO ENTRADA.TXT
        if(!archivoLectura.exists()){
            System.out.println("\033[31m \t~ ERROR ~");
            System.out.println("\033[31m \t===========================================");
            System.out.println("\033[31m \t EXISTE UN PROBLEMA CON EL ARCHIVO,\n"
                            + "\033[31m \t PÓNGASE EN CONTACTO CON EL DESARROLLADOR");
            System.out.println("\033[31m \t===========================================\n");
        } else {
            System.out.println("\033[36m \t==============================================");
            System.out.println("\033[36m \t ARCHIVO EXISTENTE, FUNCIONANDO CORRECTAMENTE");
            System.out.println("\033[36m \t==============================================\n");
            
            // INICIO DEL PROYECTO (MAIN)
            menuPrincipal();
        }
    }
    
    // MAIN DEL PROGRAMA - INICIO.
    public void menuPrincipal(){
        // CICLO - DO WHILE - PARA REPETIR EL MENU PRINCIPAL LAS VECES QUE SEAN NECESARIAS.
        do {
            try {
                // MENU PRINCIPAL - OPCIONES DE ELECCION.
                System.out.println("\033[33m\t+------ MENU PRINCIPAL ----------+");
                System.out.println("1 - LISTADO DE DATOS");
                System.out.println("2 - ORDEN DE DATOS");
                System.out.println("3 - REPORTES");
                System.out.println("4 - SALIR X->\n");
                System.out.print("INGRESE UNA OPCION:\t");
                menuPrincipal = TecladoEntrada.nextInt();
                System.out.println("____________________________________________");
                    // ELECCION DEL MENU PRINCIPAL.
                    switch(menuPrincipal){
                        case 1:
                            ListaDatos();
                            System.out.println("____________________________________________");
                            System.out.println("\n\n\n");
                            break;
                        case 2:
                            subMenu();
                            System.out.println("\n\n\n");
                            break;
                        case 3:
                            ListaReportes();
                            System.out.println("\n\n\n");
                            break;
                        case 4:
                            // FINALIZACION DEL SISTEMA
                            System.out.println("\n\n\n");
                            System.out.println("\033[36m \t================================");
                            System.out.println("\033[36m \t X - FINALIZANDO EL PROGRAMA - X\t");
                            System.out.println("\033[36m \t================================\n");
                            break;
                        default:
                                // PROCESO QUE MOSTRARA UNA MENSAJE DE ERROR EN LA PANTALLA.
                                Error();
                            break;
                    }
            } catch (Exception ex) {
                // ERROR DEL CATCH, LO CUAL SE MOSTRARA EN LA PATALLA CON EL EL ERROR DEL SISTEMA.
                ErrorSistema(ex.toString());
                menuPrincipal = 4;
            }
        } while(menuPrincipal != 4);
    }
    
    // OPCION 1 - MOSTRARA LOS DATOS EN FORMA DE LISTA EN LA PATALLA DEL ARCHIVO DE ENTRADA.TXT.
    public void ListaDatos() throws IOException{
        
        System.out.println("\n\t\t+------ LISTADO DE ALUMNOS ----------+\n");
        try {
            // VARIABLES PARA PODER LEER EL ARCHIVO.
            leer = new FileReader(archivoLectura);
            almacenar = new BufferedReader(leer);
            String cadenaArchivo = "";
           // CLICLO PARA VALIDAR CADA LINEA DEL ARCHIVO.
            while ((cadenaArchivo = almacenar.readLine()) != null){
                System.out.println("\t" + cadenaArchivo);
            }
            leer.close();
            almacenar.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainArbol.class.getName()).log(Level.SEVERE, null, ex);
            ErrorSistema(ex.toString());
            menuPrincipal = 4;
        }
        
    }
    
    // OPCION 2 - SUB MENU DONDE PARA REALIZAR EL ORDEN DE LOS DATOS.
    public void subMenu(){
        // DECLARACION DE VARIBLES Y CONSTRANTES PARA TOMAS LOS DATOS DEL ARCHIVO.
        int subMenuV = 0;
        CantidadDatos = CantidadDatos();
        String NombreOpcion = "";
        String separador = "|";
        String finalSig = ";";
        
        // ARRAY PARA OBTENER TODOS LOS DATOS DEL ARACHIVO.
        String[] CARNETs = new String[CantidadDatos];
        String[] NOMBRES = new String[CantidadDatos];
        String[] APELLIDOS = new String[CantidadDatos];
        String[] CARRERAS = new String[CantidadDatos];
        String[] posiciones = new String[CantidadDatos];
        
        // PROCEDIMIENTO QUE OTENDRA TODOS LOS DATOS POR CARNET,NOMBRE,APELLIDO Y CARRERA.
        SeparacionVariables(CARNETs,NOMBRES,APELLIDOS,CARRERAS, posiciones ,separador, finalSig);
        
        // INICIO DEL SUB MENU DE ORDEN
        try {
            // SUB MENU DE ORDEN - OPCIONES DE ELECCION.
            System.out.println("\n\033[33m\t\t+------ SUB-MENU DE ORDEN ------+" + CantidadDatos);
            System.out.println("\tELIJA UNA OPCION PARA REALIZAR EL REPORTE CON EL ORDEN ELEGIDO:");
            System.out.println("\t1 - CARNET");
            System.out.println("\t2 - NOMBRE");
            System.out.println("\t3 - APELLIDO");
            System.out.println("\t4 - CARRERA");
            System.out.println("\t5 - REGRESAR <-[]\n");
            System.out.print("\tINGRESE UNA OPCION:\t");
            subMenuV = TecladoEntrada.nextInt();
            System.out.println("\t____________________________________________");
            // ELECCION DEL SUB MENU DE ORDEN.
            switch(subMenuV){
                case 1:
                    NombreOpcion = "CARNETs";
                    System.out.println("\033[33m\t\t\t+------ ORDEN POR CARNET ------+");
                    System.out.println("\t\tDATOS ORDENADOS DE MANERA ASCENDENTE:\n");
                    posiciones = OrdenPosiciones(CARNETs, posiciones);
                    System.out.println("\n\t\tGENERANDO EL REPORTE...\n");
                    GenerarReporte(NombreOpcion,CARNETs,NOMBRES,APELLIDOS,CARRERAS, posiciones);
                    break;
                case 2:
                    NombreOpcion = "NOMBRES";
                    System.out.println("\033[33m\t\t\t+------ ORDEN POR NOMBRE ------+");
                    System.out.println("\t\tDATOS ORDENADOS DE MANERA ASCENDENTE:\n");
                    posiciones = OrdenPosiciones(NOMBRES, posiciones);
                    System.out.println("\n\t\tGENERANDO EL REPORTE...\n");
                    GenerarReporte(NombreOpcion,CARNETs,NOMBRES,APELLIDOS,CARRERAS, posiciones);
                    break;
                case 3:
                    NombreOpcion = "APELLIDOS";
                    System.out.println("\033[33m\t\t\t+------ ORDEN POR APELLIDOS ------+");
                    System.out.println("\t\tDATOS ORDENADOS DE MANERA ASCENDENTE:\n");
                    posiciones = OrdenPosiciones(APELLIDOS, posiciones);
                    System.out.println("\n\t\tGENERANDO EL REPORTE...\n");
                    GenerarReporte(NombreOpcion,CARNETs,NOMBRES,APELLIDOS,CARRERAS, posiciones);
                    break;
                case 4:
                    NombreOpcion = "CARRERAS";
                    System.out.println("\033[33m\t\t\t+------ ORDEN POR CARRERA ------+");
                    System.out.println("\t\tDATOS ORDENADOS DE MANERA ASCENDENTE:\n");
                    posiciones = OrdenPosiciones(CARRERAS, posiciones);
                    System.out.println("\n\t\tGENERANDO EL REPORTE...\n");
                    GenerarReporte(NombreOpcion,CARNETs,NOMBRES,APELLIDOS,CARRERAS, posiciones);
                    break;
                case 5:
                    System.out.println("\033[33m\tREGRESANDO AL MENU PRINCIPAL...");
                    System.out.println("\n\n\n");
                    break;
                default:
                    // PROCESO QUE MOSTRARA UNA MENSAJE DE ERROR EN LA PANTALLA.
                    Error();
                    break;
            }
        } catch (Exception ex) {
            // ERROR DEL CATCH, LO CUAL SE MOSTRARA EN LA PATALLA CON EL EL ERROR DEL SISTEMA.
            ErrorSistema(ex.toString());
            menuPrincipal = 4;
        }
    }
    // OPCION 3- LISTA DE REPORTES
    public void ListaReportes(){
        int OpcionReporte = 0;
        System.out.println("\n\033[33m\t+------ LISTADO DE TODOS LOS REPORTES GENERADOS ----------+\n");
        File directorio = new File("Archivo/Reporte/"); //directorio a listar                                             
        String[] lista = directorio.list();
        Arrays.sort(lista);
        for (int i = 0; i < lista.length; i++) {
            System.out.println("\tNo." + (i + 1) + " " + lista[i]);
        }
        do {
            System.out.println("\t____________________________________________");
            System.out.println("\n\tESCOGA UN EL NUMERO(No.)DEL REPORTE PARA VISUALIZAR EL CONTENIDO\n"
                            + "\tSI NO DESEA VER VISUALIZAR ALGUN REPORTE ESCRIBA 0(CERO) PARA SALIR AL MENU PRINCIPAL");
            System.out.print("\n\tINGRESE SU OPCION: ");
            OpcionReporte = TecladoEntrada.nextInt();
            System.out.println("\t____________________________________________");

            String NombreArchivoRepo = "";
            try {
                if(OpcionReporte > 0 && OpcionReporte <= lista.length){
                    NombreArchivoRepo = "Archivo/Reporte/" + lista[OpcionReporte - 1];
                    
                    leer = new FileReader(NombreArchivoRepo);
                    almacenar = new BufferedReader(leer);
                    
                    String cadenaArchivo = "";
                    System.out.println("\n\033[36m\t\t +-- CONTENIDO DEL ARCHIVO: " + lista[OpcionReporte - 1] + " --+");
                     while ((cadenaArchivo = almacenar.readLine()) != null){
                         System.out.println("\t" + cadenaArchivo);
                     }
                    almacenar.close();
                    leer.close();
                    
                    OpcionReporte = -1;
                }
                else {
                    if (OpcionReporte == 0){
                        OpcionReporte = -1;
                        System.out.println("\033[33m\t <- REGRESANDO AL MENU PRINCIPAL");
                    } else {
                        Error();
                    }
                }
            } catch (Exception ex) {
                ErrorSistema(ex.toString());
                menuPrincipal = 4;
                OpcionReporte = -1;
            }
        } while (OpcionReporte != -1);
        System.out.println("\t____________________________________________");
    }
    
    
    
    
    
// PROCESO Y FUNCIONES DEL PROYECTO -----------
    
    public int CantidadDatos(){
        int cantidad = 0;
        
        try {
            leer = new FileReader(archivoLectura);
            almacenar = new BufferedReader(leer);
            
            // PRIMER CICLO QUE RECORRERA LINE POR LINEA AL ARCHIVO "ENTADA.TXT".
            while(almacenar.readLine() != null){
                cantidad++;
            }
            almacenar.close();
            leer.close();
        } catch (Exception ex) {
            ErrorSistema(ex.toString());
            menuPrincipal = 4;
        }
        return cantidad;
    }
    
    // PROCEDIMIENTO QUE OTENDRA TODOS LOS DATOS POR CARNET,NOMBRE,APELLIDO Y CARRERA.
    public void SeparacionVariables(String[] CARNET, String[] NOMBRE, String[] APELLIDO, String[] CARRERA, String[] posiciones, String separador,String finalizacion){
        try {
            // VARIABLE PARA PODER GUARDAR EN LAS POSICIONES DEL LAS VARIABLES ARRAY.
            int NumeroDatos = 0;
            // VARIBALES PARA LEER EL ARCHIVO DE "ENTRADA.TXT".
            leer = new FileReader(archivoLectura);
            almacenar = new BufferedReader(leer);
            String Cadena = "";
            
            // PRIMER CICLO QUE RECORRERA LINE POR LINEA AL ARCHIVO "ENTADA.TXT".
            while((Cadena = almacenar.readLine()) != null){
                // VARIABLE CONTADOR Y VARIABLES STRING PARA GUARDAR LOS DATOS TEMPORALMENTE.
                int tempContador = 0;
                String carnet = "";
                String nombre = "";
                String apellido = "";
                String carrera = "";
                
                
                // SEGUNDO CICLO QUE RECORRERA CADA ESPACIO DE LA LINE ENCONTRADA EN EL ARACHIVO.
                for (int i = 0; i < Cadena.length(); i++) {
                    //CONDICIONES PARA ENCONTRAR LAS CEPARACIONES COMO "|" Y ";".
                    if(Cadena.charAt(i) != finalizacion.charAt(0)) {
                        if (Cadena.charAt(i) != separador.charAt(0)){
                            // CONDICIOM PARA SABER QUE DATOS GUARDAR COMO EL
                            //  CARNET, NOMBRE, APELLIDO Y CARRERA
                            switch(tempContador){
                                case 0:
                                        carnet = carnet + Cadena.charAt(i);
                                    break;
                                case 1:
                                        nombre = nombre + Cadena.charAt(i);
                                    break;
                                case 2:
                                        apellido = apellido + Cadena.charAt(i);
                                    break;
                                case 3:
                                        carrera = carrera + Cadena.charAt(i);
                                    break;
                            }
                        } else {
                            tempContador ++;
                        }
                    }
                }
                // SE GUARDARAN LOS DATOS OPTENIDO EN EL ARRAY PARA EL PREVIO ORDEN  DE DATOS.
                CARNET[NumeroDatos] = carnet;
                NOMBRE[NumeroDatos] = nombre;
                APELLIDO[NumeroDatos] = apellido;
                CARRERA[NumeroDatos] = carrera;
                posiciones[NumeroDatos] = String.valueOf(NumeroDatos);
                
                NumeroDatos ++;
            }
            almacenar.close();
            leer.close();
        } catch(Exception ex){
            ErrorSistema(ex.toString());
            menuPrincipal = 4;
        }
    }
   
    // FUNCION PARA PODER OBTENER EL NUMERO DE POSICION DE LOS VALORES DEL ARRAY
    public String[] OrdenPosiciones(String[] VectorOrden,String[] posiciones){
        String[] VectorTemp = new String[CantidadDatos];
        for (int i = 0; i < VectorOrden.length; i++) {
            VectorTemp[i] = VectorOrden[i];
        }
        
        for (int i = 0; i < VectorTemp.length; i++) {
            for (int j = 0; j < VectorTemp.length && i != j ; j++) {
                if(VectorTemp[i].compareToIgnoreCase(VectorTemp[j]) < 0){
                    String aux = VectorTemp[i];
                    VectorTemp[i] = VectorTemp[j];
                    VectorTemp[j] = aux;
                    // -----------------------
                    String auxPosicion = posiciones[i];
                    posiciones[i] = posiciones[j];
                    posiciones[j] = auxPosicion;
                }
            }
        }
        for (String DatosOrden : VectorTemp) {
            System.out.println("\t\t\t" + DatosOrden);
        }
        return posiciones;
    }
    // PROCESO PARA PODER CREAR EL REPORTE ELEGIDO
    public void GenerarReporte(String nomOpcion, String[] CARNET, String[] NOMBRE, String[] APELLIDO, String[] CARRERA, String[] posiciones) throws IOException{
        FileWriter fichero = null;
        PrintWriter pw = null;
        String AdressArchivo = "Archivo/Reporte/Reporte " + nomOpcion + " " + horaFecha() + ".txt";
        
        File file = new File(AdressArchivo);
        if (!file.exists()) {
           file.createNewFile();
        }
        try {
            
            fichero = new FileWriter(AdressArchivo);
            pw = new PrintWriter(fichero);
            
            for (int i = 0; i < posiciones.length; i++) {
                int TemPos =Integer.parseInt(posiciones[i]);
                pw.println(CARNET[TemPos] + "," + NOMBRE[TemPos] + "," + APELLIDO[TemPos] + "," + CARRERA[TemPos]);
            }
            System.out.println("\033[36m \t\t==============================");
            System.out.println("\033[36m \t\tREPORTE GENERADO CON EXITO :)\n"
                                    + "\033[36m\t" + AdressArchivo );
            System.out.println("\033[36m \t\t==============================\n");
            
        } catch (Exception ex) {
            ErrorSistema(ex.toString());
            menuPrincipal = 4;
        } finally{
            if (null != fichero)
              fichero.close();
        }
    }
    // FUNCION PARA OBTENER LA FECHA Y LA HORA DEL REPORTE
    public String horaFecha(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy-HHmmss");
        return dtf.format(LocalDateTime.now());
    }
    
    // PROCEDIMIENTO DE ERROR QUE SE MOSTRARA EN LA PANATALLA.
    public void Error(){
        System.out.println("\n\033[31m\t~ ERROR ~");
        System.out.println("\033[31m\t============================");
        System.out.println("\033[31m\t INGRESO UN DATO INCORRECTO,\n"
                            + "\033[31m\t VUELVA A INTENTARLO...\t");
        System.out.println("\033[31m\t============================\n");
    }
    // PROCEDIMIENTO DE ERROR DEL SISTEMA.
    public void ErrorSistema(String err){
        System.out.println("\n\033[31m\t~ ERROR DEL SISTEMA ~");
        System.out.println("\033[31m\t=======================================");
        System.out.println("\033[31m\t OCURRIO UN PROBLEMA CON LA APLICACION,\n"
                            + "\033[31m\t CERRANDO LA APLICACION...\t");
        System.out.println("\033[31m\t_____________________________________");
        System.out.println("\033[31m\tError:");
        System.out.println("\033[31m\t " + err);
        System.out.println("\033[31m\t=======================================\n");
    }
    
    
}
