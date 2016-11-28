import java.util.Random;
import java.util.Scanner;

public class SelectKth4 {  
  
    // Median of Medians Algorithm, Time complexity: O(n)  
    public static int quickSort(int[] A, int k) {  
        int value = 0;  
        int n = A.length;  
        int c = 5;      // Constant used to divide the array into columns  
  
        while (true) {  
            // Extract median of medians and take it as the pivot  
            int pivot = findPivot(A, n, c);  
  
            // Now count how many smaller and larger elements are there  
            int smallerCount = 0;  
            int largerCount = 0;  
  
            int[] data = new int[2];  
  
            // CountElements(a, n, pivot, out smallerCount, out largerCount);  
            CountElements(A, n, pivot, data);  
  
            smallerCount = data[0];  
            largerCount = data[1];  
  
            // Finally, partition the array  
            if (k < smallerCount) {  
                n = Partition(A, n, pivot, true);  
            } else if (k < n - largerCount) {  
                value = pivot;  
                break;  
            } else {  
                k -= n - largerCount;  
                n = Partition(A, n, pivot, false);  
            }  
        }  
        return value;  
    }  
  
    private static int findPivot(int[] A, int n, int c) {  
        while (n > 1) {  
            int pos = 0;  
            int tmp = 0;  
  
            for (int start = 0; start < n; start += c) {  
                int end = start + c;  
                if (end > n){    // Last column may have  
                    end = n;    // less than c elements  
                }  
                  
                // Sort the column  
                for (int i = start; i < end - 1; i++){  
                    for (int j = i + 1; j < end; j++){  
                        if (A[j] < A[i]) {  
                            tmp = A[i];  
                            A[i] = A[j];  
                            A[j] = tmp;  
                        }  
                    }  
                }  
  
                // Pick the column's median and promote it  
                // to the beginning of the array  
                end = (start + end) / 2; // Median position  
                tmp = A[end];  
                A[end] = A[pos];  
                A[pos++] = tmp;  
  
            }  
            n = pos; // Reduce the array and repeat recursively  
        }  
  
        return A[0]; // Last median of medians is the pivot  
    }  
  
    // static void CountElements(int[] a, int n, int pivot, out int  
    // smallerCount, out int largerCount)  
    private static void CountElements(int[] a, int n, int pivot, int[] values) {  
        for (int i = 0; i < n; i++) {  
            if (a[i] < pivot)  
                values[0]++;  
            if (a[i] > pivot)  
                values[1]++;  
        }  
    }  
  
    private static int Partition(int[] A, int n, int pivot, boolean extractSmaller) {  
        int pos = 0;  
        for (int i = 0; i < n; i++) {  
            if ((extractSmaller && A[i] < pivot)  
                    || (!extractSmaller && A[i] > pivot)) {  
                int tmp = A[i];  
                A[i] = A[pos];  
                A[pos++] = tmp;  
            }  
        }  
        n = pos;  
        return n;  
    }  
  
    public static void main(String[] args) {  
    	for (int j = 0; j < 10; j++) {
	        Random random = new Random();
	        int[] A = new int[10];
	        for (int i = 0; i < 10; i++)
	            A[i] = random.nextInt(1000);
	 
	        System.out.println("The original sequence is:  ");
	        for (int i = 0; i < 10; i++) {
	            System.out.print(A[i] + " ");
	        }
	        int k = random.nextInt(9) + 1;
	        System.out.println("");
	        System.out.println(k + "th smallest element :" + quickSort(A, k));
    	}
    }   
  
}  