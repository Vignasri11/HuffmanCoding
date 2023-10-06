import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    int freq;
    char symbol;
    Node left;
    Node right;

    public Node(int freq, char symbol, Node left, Node right) {
        this.freq = freq;
        this.symbol = symbol;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node other) {
        // Compare nodes based on their frequencies
        return this.freq - other.freq;
    }
}

public class HuffmanCoding {
    // Function to print Huffman codes for each symbol
    public static void printNodes(Node node, String val) {
        if (node.left == null && node.right == null) {
            // Leaf node: Print symbol and its Huffman code
            System.out.println(node.symbol + " -> " + val);
        }
        if (node.left != null) {
            // Traverse left side of treewith '0' 
            printNodes(node.left, val + "0");
        }
        if (node.right != null) {
            // Traverse right side of tree with '1' 
            printNodes(node.right, val + "1");
        }
    }

    // Function to build the Huffman tree
    public static void buildHuffmanTree(char[] chars, int[] freq) {
        int n = chars.length;

        // Create a priority queue to store Huffman nodes
        PriorityQueue<Node> nodes = new PriorityQueue<>();

        // Initialize the priority queue with leaf nodes
        for (int i = 0; i < n; i++) {
            nodes.add(new Node(freq[i], chars[i], null, null));
        }

        // Measure the start time for the algorithm
        long startTime = System.nanoTime();

        // Build the Huffman tree by combining nodes
        while (nodes.size() > 1) {
            Node left = nodes.poll();
            Node right = nodes.poll();
            Node newNode = new Node(left.freq + right.freq, '-', left, right);
            nodes.add(newNode);
        }

        // Measure the end time and calculate elapsed time
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        // The root of the Huffman tree is the single remaining node in the priority queue
        Node root = nodes.poll();

        // Print Huffman codes and elapsed time
        System.out.println("Huffman Codes:");
        printNodes(root, "");
        System.out.println("Time Complexity: " + elapsedTime + " nanoseconds");
    }

    public static void main(String[] args) {
        // Define the characters and their frequencies
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] freq = {5, 9, 12, 13, 16, 45};

        // Call the function to build the Huffman tree
        buildHuffmanTree(chars, freq);
    }
}
