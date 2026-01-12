import java.util.Scanner;

public class CRC {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the length of generator polynomial:");
        int genLength = sc.nextInt();
        int[] GEN = new int[genLength];
        
        System.out.println("Enter the generator polynomial bits (space separated):");
        for (int i = 0; i < genLength; i++) {
            GEN[i] = sc.nextInt();
        }
        
        System.out.println("Enter the length of data:");
        int dataLength = sc.nextInt();
        int[] data = new int[dataLength];
        
        System.out.println("Enter the data bits (space separated):");
        for (int i = 0; i < dataLength; i++) {
            data[i] = sc.nextInt();
        }
        
        // SENDER SIDE
        System.out.println("\n--- Sender Side ---");
        int[] senderArray = new int[dataLength + genLength - 1];
        // Copy data and append zeros for CRC
        for (int i = 0; i < dataLength; i++) {
            senderArray[i] = data[i];
        }
        
        int[] senderArrayCopy = senderArray.clone();
        divide(senderArray, GEN);
        
        // Create codeword: original data + CRC
        for (int i = dataLength; i < senderArray.length; i++) {
            senderArrayCopy[i] = senderArray[i];
        }
        
        System.out.print("Sent Code: ");
        for (int bit : senderArrayCopy) {
            System.out.print(bit);
        }
        System.out.println();

        System.out.println("\n--- Receiver Side ---");
        System.out.println("Enter the length of received data:");
        int receivedLength = sc.nextInt();
        int[] receivedData = new int[receivedLength];
        
        System.out.println("Enter the received data bits (space separated):");
        for (int i = 0; i < receivedLength; i++) {
            receivedData[i] = sc.nextInt();
        }
        
        divide(receivedData, GEN);
        System.out.print("Received Data Check: ");
        check(receivedData);
        
        sc.close();
    }

    static void divide(int[] data, int[] GEN) {
        int dlen = data.length;
        int Glen = GEN.length;

        for (int i=0; i<=dlen-Glen; i++) {
            if (data[i] == 1) {
                for (int j=0; j<Glen; j++) {
                    data[i+j] ^=  GEN[j];
                }
            }
        } 
    }

    static void check(int[] data) {
        for (int i : data) {
            if (i != 0) {
                System.out.println("Error Detected!");
                return;
            }
        }
        System.out.println("Data is Valid.");
    }
}