import java.util.Scanner;

public class L003 {
    static Scanner sc = new Scanner(System.in);

    // ARRANGE BUILDINGS ============================================================================
    public static long arrangeBuildings(int n){
        int eb = 1;
        int es = 1;
    
        for(int i=2;i<=n;i++){
            int temp = es;
            es = eb + es;
            eb = temp;
        }
    
        long total = es + eb;
        return (total * total);
    }

    // COUNT ENCODINGS ==============================================================================
    public static int countEncodings(String str){
        int n = str.length();
        int[] qb = new int[n];
        
        if(str.charAt(0)=='0')
            return 0;
        
        qb[0] = 1;
        for(int i=1;i<n;i++){
            int c = str.charAt(i)-'0';
            if(c!=0)
                qb[i] += qb[i-1];
            
            int val = Integer.parseInt(str.substring(i-1, i+1));
            if(val >= 10 && val <= 26){
                qb[i] += (i-2==-1)?1:qb[i-2];
            }
        }
        
        return qb[n-1];
    }

    // COUNT ABC SUBEQUENCES ========================================================================
    public static int countABCsubsequences(String str){
        int a = 0;
        int ab = 0;
        int abc = 0;
        
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(c=='a'){
                a = 2*a + 1;
            }
            else if(c=='b'){
                ab = 2*ab + a;
            }
            else{
                abc = 2*abc + ab;
            }
        }
        
        return abc;
    }



    public static void main(String[] args) {
        
    }


    private static int[][] take2Dinput(){
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[][] arr = new int[n][m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        return arr;
    }

    private static void print2D(int[][] arr) {
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }

    }
    private static void print2D(boolean[][] arr) {
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }

    }
}
