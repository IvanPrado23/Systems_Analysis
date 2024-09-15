# Systems_Analysis
### 1. Generating Sequences


- **Number of sequences**: Defines how many sequences will be generated.
- **Length of each sequence**: Specifies the number of nucleotide bases in each sequence.
- **Base probabilities**: Represents the likelihood of each nucleotide appearing in the sequence.

The generated sequences are saved in a text file.

### 2. Identifying Frequent Motifs
The system finds the most common motif (a repeating pattern) across all sequences:

- **Motif size**: Determines the length of the motif you want to search for.

- **Entropy threshold**: Sets a threshold value to filter out sequences based on their entropy.
- **Filtered output**: Sequences with entropy below the threshold are removed, and the remaining sequences are saved in a separate file.

### Example Workflow

1. Configure the parameters as needed:
   - Set the number of sequences, sequence length, base probabilities, motif size, and entropy threshold.

2. Compile the code:
   - Use the appropriate compiler for your programming language to compile the source code.

3. Run the program:
   - Execute the compiled program to generate sequences, find motifs, and apply entropy filtering.

### Program Output

After running the program, you will have:
1. A file containing the generated DNA sequences.
2. A file with sequences that passed the entropy filtering.
3. Console output showing the most frequent motif and its occurrences.
