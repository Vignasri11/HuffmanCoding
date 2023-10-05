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
        return this.freq - other.freq;
    }
}

public class HuffmanCoding {
    public static void printNodes(Node node, String val) {
        if (node.left == null && node.right == null) {
            System.out.println(node.symbol + " -> " + val);
        }
        if (node.left != null) {
            printNodes(node.left, val + "0");
        }
        if (node.right != null) {
            printNodes(node.right, val + "1");
        }
    }

    public static void buildHuffmanTree(char[] chars, int[] freq) {
        int n = chars.length;

        PriorityQueue<Node> nodes = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            nodes.add(new Node(freq[i], chars[i], null, null));
        }

        long startTime = System.nanoTime();

        while (nodes.size() > 1) {
            Node left = nodes.poll();
            Node right = nodes.poll();
            Node newNode = new Node(left.freq + right.freq, '-', left, right);
            nodes.add(newNode);
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        Node root = nodes.poll();
        System.out.println("Huffman Codes:");
        printNodes(root, "");

        System.out.println("Time Complexity: " + elapsedTime + " nanoseconds");
    }

    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'c', 'd', 'e','f','g','h'};
        int[] freq = {5, 9, 12, 13, 16,20,38,45};

        buildHuffmanTree(chars, freq);
    }
}
