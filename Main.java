import java.io.IOException;

/**
 * Main
 *
 * Esta clase actua como el punto de entrada principal para la ejecución del programa.
 * Llama al experimento de deteccion de motivos y maneja cualquier excepción de entrada/salida que pueda ocurrir.
 */
public class Main {

    /**
     * Metodo principal que inicia la ejecución del programa.
     * 
     * @param args Argumentos de linea de comandos (no utilizados en este programa).
     */
    public static void main(String[] args) {
        try {
            // Llama al experimento de deteccion de motivos desde la clase MotifExperiment.
            MotifExperiment.main(args);
        } catch (IOException e) {
            // Maneja cualquier excepcion de entrada/salida y muestra un mensaje de error.
            System.out.println("Error: " + e.getMessage());
        }
    }
}
