import java.io.IOException;

/**
 * MotifExperiment
 *
 * Esta clase ejecuta un experimento para generar secuencias de ADN, filtrarlas
 * según su entropia y encontrar el motivo mas frecuente en las secuencias filtradas.
 */
public class MotifExperiment {

    /**
     * Metodo principal que ejecuta el experimento.
     * 
     * @param args Argumentos de linea de comandos (no utilizados en este experimento).
     * @throws IOException Si ocurre un error de entrada/salida durante la generacion o filtrado de secuencias.
     */
    public static void main(String[] args) throws IOException {
        // Tamaños de bases de datos de secuencias a generar.
        int[] databaseSizes = {1000, 10000, 100000};

        // Diferentes distribuciones de probabilidades para las bases 'A', 'C', 'G', 'T'.
        double[][] probabilities = {
            {0.25, 0.25, 0.25, 0.25},   // Probabilidades uniformes
            {0.40, 0.20, 0.20, 0.20},   // Mayor probabilidad para 'A'
            {0.10, 0.30, 0.30, 0.30}    // Menor probabilidad para 'A'
        };

        // Tamaños de los motivos a buscar en las secuencias.
        int[] motifSizes = {4, 6, 8};

        // Umbral de entropía para filtrar secuencias.
        double entropyThreshold = 1.5; 

        // Itera sobre diferentes tamaños de bases de datos.
        for (int n : databaseSizes) {
            // Itera sobre diferentes distribuciones de probabilidades de bases.
            for (double[] prob : probabilities) {
                // Genera las secuencias de ADN y las guarda en un archivo.
                String originalFile = "sequences_" + n + "_" + prob[0] + ".txt";
                MotifDetector.generateSequences(n, 100, prob[0], prob[1], prob[2], originalFile);

                // Filtra las secuencias por entropia y guarda las secuencias filtradas en otro archivo.
                String filteredFile = "filtered_sequences_" + n + "_" + prob[0] + ".txt";
                EntropyFilter.filterSequencesByEntropy(originalFile, filteredFile, entropyThreshold);

                // Itera sobre diferentes tamaños de motivos.
                for (int s : motifSizes) {
                    // Mide el tiempo de ejecución para encontrar el motivo más frecuente en las secuencias filtradas.
                    long startTime = System.nanoTime();
                    String motif = MotifDetector.findMotif(filteredFile, s);
                    long endTime = System.nanoTime();
                    double timeTaken = (endTime - startTime) / 1e9;

                    // Imprime los resultados del experimento.
                    System.out.println("Database Size: " + n + ", Probabilities: " + prob[0] + ", Motif Size: " + s + " (Filtered)");
                    System.out.println("Motif: " + motif + ", Time Taken: " + timeTaken + " seconds");
                }
            }
        }
    }
}
