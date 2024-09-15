import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * MotifDetector
 *
 * Esta clase proporciona metodos para generar secuencias de ADN aleatorias y
 * detectar el motivo (motif) más frecuente dentro de esas secuencias.
 */
public class MotifDetector {

    /**
     * Genera secuencias de ADN aleatorias y las escribe en un archivo.
     * 
     * @param n        Número de secuencias a generar.
     * @param m        Longitud de cada secuencia.
     * @param probA    Probabilidad de generar la base 'A'.
     * @param probC    Probabilidad de generar la base 'C'.
     * @param probG    Probabilidad de generar la base 'G'.
     * @param filename Nombre del archivo donde se guardarán las secuencias.
     * @throws IOException Si ocurre un error durante la escritura del archivo.
     */
    public static void generateSequences(int n, int m, double probA, double probC, double probG, String filename) throws IOException {
        char[] bases = {'A', 'C', 'G', 'T'};
        double[] probabilities = {probA, probC, probG, 1 - (probA + probC + probG)};
        Random random = new Random();

        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < n; i++) {
                StringBuilder sequence = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    sequence.append(selectBase(bases, probabilities, random));
                }
                writer.write(sequence.toString() + "\n");
            }
        }
    }

    /**
     * Selecciona una base de ADN ('A', 'C', 'G', 'T') de acuerdo con las probabilidades.
     * 
     * @param bases        Array que contiene las bases de ADN.
     * @param probabilities Array que contiene las probabilidades de cada base.
     * @param random        Instancia de la clase Random para generar numeros aleatorios.
     * @return La base seleccionada.
     */
    private static char selectBase(char[] bases, double[] probabilities, Random random) {
        double p = random.nextDouble();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < bases.length; i++) {
            cumulativeProbability += probabilities[i];
            if (p <= cumulativeProbability) {
                return bases[i];
            }
        }
        return 'T'; // Base por defecto si no se cumple ninguna condición.
    }

    /**
     * Encuentra el motivo (motif) más frecuente en las secuencias almacenadas en un archivo.
     * 
     * @param filename Nombre del archivo que contiene las secuencias de ADN.
     * @param s        Longitud del motivo a buscar.
     * @return El motivo más frecuente en las secuencias.
     * @throws IOException Si ocurre un error durante la lectura del archivo.
     */
    public static String findMotif(String filename, int s) throws IOException {
        Map<String, Integer> motifCount = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i <= line.length() - s; i++) {
                    String motif = line.substring(i, i + s);
                    motifCount.put(motif, motifCount.getOrDefault(motif, 0) + 1);
                }
            }
        }

        // Encuentra el motivo más frecuente y resuelve empates por repetición de bases consecutivas.
        return motifCount.entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue().equals(entry2.getValue()) ?
                        Integer.compare(countConsecutiveRepeats(entry1.getKey()), countConsecutiveRepeats(entry2.getKey())) :
                        Integer.compare(entry1.getValue(), entry2.getValue()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * Cuenta el numero maximo de repeticiones consecutivas de una base en un motivo.
     * 
     * @param motif El motivo cuyo numero de repeticiones consecutivas se va a contar.
     * @return El numero máximo de repeticiones consecutivas de una base en el motivo.
     */
    private static int countConsecutiveRepeats(String motif) {
        int maxRepeats = 1;
        int currentRepeats = 1;
        for (int i = 1; i < motif.length(); i++) {
            if (motif.charAt(i) == motif.charAt(i - 1)) {
                currentRepeats++;
                maxRepeats = Math.max(maxRepeats, currentRepeats);
            } else {
                currentRepeats = 1;
            }
        }
        return maxRepeats;
    }
}

