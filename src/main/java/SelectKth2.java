import java.util.Random;

public class SelectKth2 {  
 
    public static void quickSort(int[] A, int start, int end, int K) {  
        if (start < end) {  
            int pivot = partition(A, start, end);  
              
            if (pivot == K - 1) {               // pivot位于第K小的位置（K从1开始，即第1小）  
                System.out.println(K + "th smallest element :" + A[pivot]);  
            } else if (pivot > K - 1) {      // pivot过大，所以在前半段继续搜索（排除pivot位置）  
                quickSort(A, start, pivot, K);  
            } else{                             // pivot过小，所以在后半段继续搜索（排除pivot位置）  
            	quickSort(A, pivot + 1, end, K);  
            }  
        }  
    }  
    
    public static void swap(int[] A, int dex1, int dex2) 
    {
        int temp = A[dex1];
        A[dex1] = A[dex2];
        A[dex2] = temp;
    }
      
    /** 
     * Partition the array into two halves and return the index 
     * of the pivot. 
     */  
     private static int partition(int[] A, int start, int end) {  
         int i = start + 1;
         int j = i;
         int pivot = start;
         for (; i < end; i++) 
         {
             if (A[i] < A[pivot]) 
             {
                 swap(A, i, j);
                 j++;
             }
         }
         if (j <= end)
             swap(A, pivot, (j - 1));
  
         return j - 1;
     }  
  
    public static void main(String[] args) {  
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
        quickSort(A, 0, 10, k);
    }  
  
} 