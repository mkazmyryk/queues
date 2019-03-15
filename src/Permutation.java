import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        for (int i = 0; i < k; i++) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        for (String s : randomizedQueue) {
            System.out.println(s);
        }
    }
}
