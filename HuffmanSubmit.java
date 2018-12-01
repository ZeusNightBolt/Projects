import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class HuffmanSubmit implements Huffman {
  
	/** 
	 * Data structure for a node in the tree. We need the
	 * character value, it's frequency (for ordering in heap)
	 * and it's children (which are again of Node type).
	 * 
	 * @param value The input Character at the node (or null)
	 * @param freq The frequency of occurence of value in file
	 * @param l The left child Node (or null)
	 * @param r The right child Node (or null)
	 */
	public static class Node<T> {
		public T data;
		public Integer count;

		public Node<T> left;
		public Node<T> right;
		
		public Node(T value, Integer freq, Node<T> l, Node<T> r) {
			data = value;
			count = freq;
			left = l;
			right = r;
		}
	}

	/**
	 * The custom Comparator instance for comparing two Node data structures,
	 * by comparing the frequencies of the Characters.
	 * 
	 * @param n1 First Node instance
	 * @param n2 Second Node instance
	 */
	Comparator<Node<Character> > nodeCompare = new Comparator<Node<Character> >() {
		@Override
		public int compare(Node<Character> n1, Node<Character> n2) {
			if (n1.count > n2.count) {
				return 1;
			}
			else {
				return 0;
			}
		}
	};

	private static Map<String, Integer> freqMap = new LinkedHashMap<String, Integer>();
	private static Map<Character, String> codeMap = new LinkedHashMap<Character, String>();
	private static PriorityQueue<Node<Character> > minHeap = null;
	private static Node<Character> root = null;

	public static final Boolean debug = true;

	/**
	 * This function is for debugging purposes. If the global variable `debug` is
	 * turned ON, this function is called to print the Huffman encoding of the
	 * characters present in the input file.
	 * The output prints the binary string, ascii and the character itself, with the
	 * huffman code generated for the character.
	 */
	private void printHuffmanCodes() {
		for(Map.Entry<Character, String> entry: codeMap.entrySet()) {
			Character letter = entry.getKey();
			String bstring = Integer.toBinaryString(letter);
			Integer ascii = Integer.parseInt(bstring, 2);
			String encoding = entry.getValue();

			System.out.println("( " + bstring + " [" + ascii + "] ) -> { " + letter + " }" + " => " + encoding);
		}
	}

	/**
	 * Function to traverse the Huffman tree and generate the binary Huffman codes for
	 * the nodes (only leaf nodes matter as the characters are there; non-leaf nodes
	 * have null value).
	 * 
	 * @param node A copy of the root node of the Huffman tree
	 * @param code The present Huffman code string (changes according to recursive calls)
	 */
	private void generateHuffmanCodes(Node<Character> node, String code) {
		if(node == null) {
			return;
		}
		if(node.data != null) {
			codeMap.put(node.data, code);
		}
		generateHuffmanCodes(node.left, code + "0");
		generateHuffmanCodes(node.right, code + "1");
	}

	private void buildHuffmanTree() {
		/**
		 * Fill the priority queue and store the letters sorted by their
		 * frequencies. Sorting by *frequencies* is ensured by the custom comparator.
		 */
		minHeap = new PriorityQueue<Node<Character> >(100, nodeCompare);
		for(Map.Entry<String, Integer> entry: freqMap.entrySet()) {
			String bstring = entry.getKey();
			/* parseInt(bstring, 2) -> parse the string of base 2 and generate integer */
			Character letter = Character.valueOf((char) Integer.parseInt(bstring, 2));
			Integer frequency = entry.getValue();
			Node<Character> element = new Node<Character>(letter, frequency, null, null);
			minHeap.add(element);
		}

		/**
		 * Once this is done, you take the first two entries
		 * in the min heap and create a node with their frequencies added,
		 * as their parent. The children of this parent node are the two
		 * entries selected from the queue.
		 */
		while(minHeap.size() != 1) {
			Node<Character> n1 = minHeap.remove();
			Node<Character> n2 = minHeap.remove();
			Integer totalFrequency = n1.count + n2.count;
			root = new Node<Character>(null, totalFrequency, n1, n2);
			minHeap.add(root);
		}
	}

	/**
	 * Function to read the stored frequency file and fill the frequency
	 * map which is required in order to build the Huffman tree. This is
	 * called during the decoding phase.
	 * 
	 * @param freqFile The path to the stored frequency file
	 */
	private void parseFrequencyFile(String freqFile) {
		try {
			FileReader reader = new FileReader(freqFile);
			BufferedReader buffReader = new BufferedReader(reader);
			String line = null;
			while((line = buffReader.readLine()) != null) {
				String bstring = line.split(":")[0];
				Integer frequency = Integer.valueOf(line.split(":")[1]);
				freqMap.put(bstring, frequency);
			}
		}
		catch(IOException ioe) {
			System.out.println("Couldn't open file " + freqFile);
		}
	}

	/**
	 * This function read an input file with several characters and generates
	 * a corresponding frequency map storing the number of occurences of each
	 * unique character.
	 * 
	 * @param inputFile The path to file that is to be encoded using Huffman
	 */
	private void generateFrequencyMap(String inputFile) {
		BinaryIn inFile = new BinaryIn(inputFile);
		while(!inFile.isEmpty()) {
			Character c = inFile.readChar();
			String bstring = Integer.toBinaryString(c);
			if (freqMap.containsKey(bstring)) {
				Integer freq = freqMap.get(bstring);
				freqMap.put(bstring, freq + 1);
			}
			else {
				freqMap.put(bstring, 1);
			}
		}
	}

	/**
	 * This function writes the frequencies of the characters occuring in the input
	 * file to a file for further use during decoding phase.
	 * 
	 * @param freqFile The path to file in which the frequencies are written
	 */
	private void writeFrequenciesToFile(String freqFile) {
		try {
			FileWriter freqOutFile = new FileWriter(freqFile);
			BufferedWriter storeFreqs = new BufferedWriter(freqOutFile);
		
			for(Map.Entry<String, Integer> entry: freqMap.entrySet()) {
				String key = entry.getKey();
				Integer freq = entry.getValue();

				String fString = key + ":" + String.valueOf(freq);
				storeFreqs.write(fString);
				storeFreqs.newLine();
			}
			storeFreqs.close();
		}
		catch(IOException ioe) {
			System.err.println("Could not open file " + freqFile);
		}
	}

	/**
	 * Function to decode the binary string (consisting of 0s and 1s) reconstructed
	 * from the boolean strings written to the encoding file. The generated output is
	 * the recovered (lossless) result.
	 * 
	 * @param s The reconstructed binary string which is to be decoded
	 * @param outputFile The path to file in which the decompressed output is written
	 */
	public String decodeBinaryString(String s, String outputFile) {
		BinaryOut outFile = new BinaryOut(outputFile);

		String decoding = "";
		Node<Character> curr = root;
		
		for(int i = 0; i < s.length(); i++) {
			Character bit = s.charAt(i);
			if(bit.equals('0')) {
				curr = curr.left;
			}
			else {
				curr = curr.right;
			}

			if(curr.left == null && curr.right == null) {
				decoding = decoding + curr.data;
				outFile.write(curr.data);
				curr = root;
			}
		}
		outFile.flush();
		outFile.close();
		return decoding;
	}

	/**
	 * This function performs the encoding of the input file and writes the encoded
	 * result to a file. The frequencies of characters in the input file are also stored
	 * in a file for future decoding requirements.
	 * 
	 * @param inputFile The path to input file for Huffman encoding
	 * @param outputFile The path to output file to write the encoded result
	 * @param freqFile The path to output file to write frequencies of characters
	 */
	public void encode(String inputFile, String outputFile, String freqFile) {
		/* Generate the frequency map: String -> Integer and write to file */
		generateFrequencyMap(inputFile);
		writeFrequenciesToFile(freqFile);

		/* Build the Huffman tree and generate the Huffman codes by traversal */
		buildHuffmanTree();
		generateHuffmanCodes(root, "");
		
		if(debug) {
			printHuffmanCodes();
		}

		BinaryOut outFile = new BinaryOut(outputFile);
		BinaryIn inFile = new BinaryIn(inputFile);

		while(!inFile.isEmpty()) {
			Character c = inFile.readChar();
			String encoding = codeMap.get(c);
			for(int i = 0; i < encoding.length(); i++) {
				Character bit = encoding.charAt(i);
				/**
				 * Keep this '1' as we are comparing Characters.
				 * "1" will fails as it is a String.
				 */
				if(bit.equals('1')) {
					outFile.write(true);
				}
				else {
					outFile.write(false);
				}
			}
		}
		outFile.flush();
		outFile.close();
    }


	/**
	 * This function decodes the Huffman-encoded file. It requires the encoding input
	 * and the frequencies of the characters in the original file (this is required
	 * for generating the Huffman tree which is traversed to decode characters).
	 * 
	 * @param inputFile The path to file containing the Huffman-encoded result
	 * @param outputFile The path to file where the decoded output is written
	 * @param freqFile The path to stored frequency file
	 */
    public void decode(String inputFile, String outputFile, String freqFile){
		/* Fill the frequency map: parse the input frequency file */
		parseFrequencyFile(freqFile);
		/* Using the frequency map, build the Huffman tree for decoding */
		buildHuffmanTree();
		
		BinaryIn inFile = new BinaryIn(inputFile);

		/**
		 * Read the input file boolean by boolean as it was encoded as booleans.
		 * Reading it as anything else will give the incorrect output.
		 */
		List<Boolean> bools = new ArrayList<Boolean>();
		while(!inFile.isEmpty()) {
			Boolean b = inFile.readBoolean();
			bools.add(b);
		}
		String binary = "";
		for(Boolean b: bools) {
			if(b) {
				binary = binary + "1";
			}
			else {
				binary = binary + "0";
			}
		}
		/**
		 * Use the Huffman tree traversal to identify the leafs (characters) in
		 * the reconstructed binary string.
		 */
		String decoding = decodeBinaryString(binary, outputFile);
		if(debug) {
			System.out.println("***************************************************");
			System.out.println(decoding.substring(0, 500));
			System.out.println("***************************************************");
		}
    }

    public static void main(String[] args) {
      	Huffman huffman = new HuffmanSubmit();
		/* Peform huffman encoding - decoding for UoR jpg file */
		huffman.encode("ur.jpg", "ur.enc", "freq.txt");
		huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");
		/* Perform huffman encoding - decoding for Alice in Wonderland */
		huffman.encode("alice30.txt", "alice30.enc", "freq.txt");
		huffman.decode("alice30.enc", "alice30_dec.txt", "freq.txt");
    }

}
