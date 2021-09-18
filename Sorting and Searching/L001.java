public class L001{
    public static void segregateArr(int[] arr, int pivotIdx){
        int pivot = arr[pivotIdx];
        int itr = 0;
        int p1 = pivotIdx+1;
        while(itr < pivotIdx){
            if(arr[itr] > pivot){
                swap(arr, p1++, itr);
            }
            else{
                itr++;
                
            }
        }
    }


    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {10 , 1 , 12, 13, -1, -12, 99};
        int pivotIdx = 1;
        
        segregateArr(arr, pivotIdx);
    }
}