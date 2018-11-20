package dados;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Tania Orellana
 */
class Juego {

    /**
     * *********** MÓDULO MENÚ ************
     */
    /**
     * Este es el menú que el usuario verá repetidamente, hasta que se
     * seleccione Salir, que es cuando el programa acaba.
     */
    public void opcionesMenuPrincipal() {
        System.out.println("\nSeleccione una opción:");
        System.out.println("1. Jugar.");
        System.out.println("2. Salir.");
    }

    public void opcionesRestricciones() {
        System.out.println("\nSeleccione una opción:");
        System.out.println("1. Sin restricciones (sin resultados predeterminados y con repeticiones).");
        System.out.println("2. Escoger resultado en posición predeterminada (sin repeticiones).");
        System.out.println("3. Escoger resultado en posición predeterminada (con repeticiones).");
        System.out.println("4. Salir.");
    }

    public void opcionesLadosDados() {
        System.out.println("\nSeleccione una opción:");
        System.out.println("1. Seis caras.");
        System.out.println("2. Ocho caras.");
        System.out.println("3. Doce caras.");
        System.out.println("4. Salir.");
    }

    public int pedirCantidadDados() {
        int cantDados = 1, opcion = -1;
        Scanner leer = new Scanner(System.in);
        while (opcion != 0) {
            System.out.println("\nIngrese la cantidad de dados a utilizar [0 para salir].");
            try {
                System.out.print("\tElección: ");
                opcion = leer.nextInt();
                if (opcion < 0) {
                    /**
                     * Valida que solo ingrese números positivos.
                     */
                    System.err.println("Opción inválida. No ingrese números negativos. Intente de nuevo.");
                } else {
                    if (opcion > 0) {
                        cantDados = opcion;
                        opcion = 0;
                    }
                }
            } catch (InputMismatchException e) {
                System.err.println("Por favor, ingrese un número.");
                leer.nextLine();
            }
        }
        
        return cantDados;
    }

    public int pedirCantidadLados() {
        Scanner leer = new Scanner(System.in);
        System.out.println("\nIngrese la cantidad de lados que tendrá cada dado.");
        int cantLados = 6, opcion = 5;
        while (opcion != 4) {
            
            opcionesLadosDados();
            try {
                System.out.print("\tElección: ");
                opcion = leer.nextInt();
                switch (opcion) {
                    case 1:
                        /**
                         * El dado es de seis caras
                         */
                        return 6;
                    case 2:
                        /**
                         * El dado es de ocho caras
                         */
                        return 8;
                    case 3:
                        /**
                         * El dado es de doce caras
                         */
                        return 12;
                    case 4:
                        /**
                         * Salir
                         */
                        break;
                    default:
                        /**
                         * Valida que solo ingrese alguno de los números
                         * permitidos.
                         */
                        System.err.println("Opción inválida. Intente de nuevo. ");
                }
            } catch (InputMismatchException e) {
                System.err.println("Por favor, ingrese un número.");
                leer.nextLine();
            }
        }
   
        return cantLados;
    }

    public int pedirRestricciones() {
        System.out.println("\n¿Desea ingresar alguna restricción?");
        int opcion = 5;
        Scanner leer = new Scanner(System.in);
        while (opcion != 4) {
            opcionesRestricciones();
            try {
                System.out.print("\tElección: ");
                opcion = leer.nextInt();
                switch (opcion) {
                    case 1:
                        return 1;
                    case 4: // ACCIÓN: SALIR
                        break;
                    case 2:
                        return 2;
                    case 3:
                        return 3;
                    default:
                        /**
                         * Valida que solo ingrese alguno de los números
                         * permitidos.
                         */
                        System.err.println("Opción inválida. Intente de nuevo. ");
                }
            } catch (InputMismatchException e) {
                System.err.println("Por favor, ingrese un número.");
                leer.nextLine();
            }
        }
   
        return 1;
    }

    /**
     * Esto es lo que se ve al iniciar el programa, la base del menú.
     */
    public void MenuPrincipal() {
        int opcion = 3;
        Scanner leer = new Scanner(System.in);
        while (opcion != 2) {
            opcionesMenuPrincipal();
            try {
                System.out.print("\tElección: ");
                opcion = leer.nextInt();
                switch (opcion) {
                    case 1:
                        /**
                         * Ejecutar programa
                         */
                        iniciarJuego();
                        break;
                    case 2: // ACCIÓN: SALIR
                        break;
                    default:
                        /**
                         * Valida que solo ingrese alguno de los números
                         * permitidos.
                         */
                        System.err.println("Opción inválida. Intente de nuevo. ");
                }
            } catch (InputMismatchException e) {
                System.err.println("Por favor, ingrese un número.");
                leer.nextLine();
            }
        }
    
    }

    /**
     * *********** MÓDULO ADMIN ************
     */
    int datosJuego[] = new int[3];

    /**
     * Pide los datos que el juego requiere a través de la respectiva función, y
     * luego los retorna en un arreglo Posición 0: cantidad de dados. Posición
     * 1: cantidad de lados o caras. Posición 2: Indica si se jugará con
     * restricciones.
     */
    public int[] pedirDatos() {
        int datos[] = new int[3];
        datos[0] = pedirCantidadDados();
        
        datos[1] = pedirCantidadLados();
        datos[2] = pedirRestricciones();
        System.out.println("##### respuesta");
        calculo(datos[0],true,datos[1]);
        return datos;
    }

    /**
     * FALTA GUARDAR EN VARIABLE EL ARREGLO DE PEDIR DATOS
     */
    public void iniciarJuego() {
        this.datosJuego = pedirDatos();
    }

    /**
     * *********** MÓDULO VALIDADOR ************
     */
    /**
     * Los límites superior e inferior son cerrados.
     */
    public boolean validarIntervalo(int inferior, int superior, int eleccionUsuario) {
        return (eleccionUsuario >= inferior && eleccionUsuario <= superior);
    }

    /**
     * Los límites superior e inferior son cerrados.
     */
    public boolean validarIntervalo(char inferior, char superior, char eleccionUsuario) {
        return (eleccionUsuario >= inferior && eleccionUsuario <= superior);
    }
    
    
    
    
    
    
    
    
    public void calculo(int nudados,boolean repet,int caras){
        int result=1;
        if(repet){
                for(int i=0;i<nudados;i++){
                result=result*caras;
                caras=caras-1;
                if(caras==0){
                    break;
                    
                }
            }
                System.out.println(result);
      
        }
        else{
            
               for(int i=0;i<nudados;i++){
                result=result*caras;
            }
          System.out.println(result);  
        }
        
        
       
    }

}
