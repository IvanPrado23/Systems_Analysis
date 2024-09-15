import java.io.*;

/**
 * EntropyFilter
 *
 * Esta clase proporciona metodos para calcular la entropía de Shannon en una secuencia
 * de ADN y filtrar secuencias de acuerdo con un umbral de entropía.
 */
public class EntropyFilter {

    /**
     * Calcula la entropia de Shannon de una secuencia de ADN.
     * 
     * @param sequence Secuencia de ADN (compuesta por 'A', 'C', 'G', 'T').
     * @return La entropia de Shannon de la secuencia.
     */
    public static double shannonEntropy(String sequence) {
        int[] counts = new int[4]; // Array para contar las apariciones de 'A', 'C', 'G', 'T'.
        
        // Contar el numero de apariciones de cada base en la secuencia.
        for (char base : sequence.toCharArray()) {
            switch (base) {
                case 'A' -> counts[0]++;
                case 'C' -> counts[1]++;
                case 'G' -> counts[2]++;
                case 'T' -> counts[3]++;
            }
        }

        double entropy = 0.0;
        int total = sequence.length(); // Numero total de bases en la secuencia.
        
        // Calcular la entropia basada en las probabilidades de aparicion de cada base.
        for (int count : counts) {
            if (count > 0) {
                double probability = (double) count / total;
                entropy -= probability * (Math.log(probability) / Math.log(2)); // Fórmula de entropia de Shannon.
            }
        }
        return entropy;
    }

    /**
     * Filtra las secuencias de un archivo según un umbral de entropía y las escribe
     * en otro archivo si superan el umbral.
     * 
     * @param inputFile  Nombre del archivo que contiene las secuencias de ADN.
     * @param outputFile Nombre del archivo donde se escribirán las secuencias filtradas.
     * @param threshold  Umbral mínimo de entropia que una secuencia debe tener para ser escrita en el archivo de salida.
     * @throws IOException Si ocurre un error durante la lectura o escritura de los archivos.
     */
    public static void filterSequencesByEntropy(String inputFile, String outputFile, double threshold) throws IOException {
        // Lee las secuencias del archivo de entrada y escribe en el archivo de salida si cumplen con el umbral de entropía.
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (shannonEntropy(line) >= threshold) { // Filtrar por entropiSa.
                    writer.write(line + "\n");
                }
            }
        }
    }
}

