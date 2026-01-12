import java.util.*;

class FrameSort {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of frames:");
        int n = sc.nextInt();

        int[][] frames = new int[n][2];

        for (int i = 0; i < n; i++) {
            System.out.printf("Enter data for frame %d: ", i + 1);
            frames[i][1] = sc.nextInt();           // Store data
            frames[i][0] = random.nextInt(500);    // Store random sequence number
        }

        System.out.println("\nBefore sorting:");
        printFrames(frames);

        Arrays.sort(frames, (a, b) -> Integer.compare(a[0], b[0]));

        System.out.println("\nAfter sorting:");
        printFrames(frames);
        
        sc.close();
    }

    public static void printFrames(int[][] frames) {
        int n = frames.length;
        for (int i=0; i<n; i++) {
            System.out.println("Seq: " + frames[i][0] + " | Data: " + frames[i][1]);
        }
    }
}