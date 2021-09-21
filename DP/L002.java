import java.util.Scanner;

public class L002 {
    static Scanner sc = new Scanner(System.in);
    // mcmt -> MINIMUM COST MAZE TRAVERSAL =========================================================================
    public static int mcmtM(int sr, int sc, int[][] maze, int[][] qb){
        int dr = maze.length-1;
        int dc = maze[0].length-1;
        if(sr==dr && sc==dc){
            return maze[sr][sc];
        }
        
        if(qb[sr][sc]!=0) return qb[sr][sc];
        
        int min = 10000;
        
        if(sc+1<=dc){
            min = Math.min(mcmtM(sr, sc+1, maze, qb),min);
        }
        
        if(sr+1<=dr){
            min = Math.min(mcmtM(sr+1, sc, maze, qb),min);
        }
        
        return qb[sr][sc] = min+maze[sr][sc];
    }

    
    private static int mcmtTab(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;
        int[][] qb = new int[n][m];

        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                if(i==n-1 && j==m-1){
                    qb[i][j] = maze[i][j];
                    continue;
                }
                int min = 10000;
                if(i+1 < n){
                    min = Math.min(qb[i+1][j], min);
                }
                if(j+1 < m){
                    min = Math.min(qb[i][j+1], min);
                }
                qb[i][j] = min + maze[i][j];
            }
        }
        print2D(qb);
        return qb[0][0];
    }


    // GOLD MINE ===================================================================================================
    public static int goldMineTab(int[][] mine){
        int n = mine.length;
        int m = mine[0].length;

        int[][] qb = new int[n][m];

        for(int j=m-1;j>=0;j--){
            for(int i=0;i<=n-1;i++){
                int max = 0;
                if(i-1 >=0 && j+1 <m){
                    max = Math.max(qb[i-1][j+1], max);
                }

                if(j+1<m){
                    max = Math.max(qb[i][j+1], max);
                }

                if(i+1<n && j+1<m){
                    max = Math.max(qb[i+1][j+1], max);
                }
                qb[i][j] = max + mine[i][j];
            }
        }

        int ans = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            if(qb[i][0]>ans) ans = qb[i][0];
        }

        print2D(qb);
        return ans; 
    }

    // TARGET SUM SUBSET -> repetition not allowed =================================================================
    public static boolean tssMemo(int idx, int cs, int tar, boolean[][] qb, int[] arr){
        if(cs==tar) return true;
        if(cs > tar || idx == arr.length) return false;
        if(qb[idx][cs]) return true;

        boolean inc = tssMemo(idx+1, cs+arr[idx], tar,qb, arr);
        boolean exc = tssMemo(idx+1, cs, tar ,qb,arr);

        return qb[idx][cs] = inc || exc;
        // return qb[cs] = tss(idx+1, cs+arr[idx], tar,qb, arr) || tss(idx+1, cs, tar ,qb,arr);
    }

    public static boolean tssTab(int[] arr, int tar){
        int n = arr.length;
        boolean[][] qb = new boolean[n+1][tar+1];
        qb[0][0] = true;
        for(int i = 1; i <= n; i++){
            int coin = arr[i-1];
            for(int j=0; j <= tar; j++){
                boolean exc = qb[i-1][j];
                boolean inc = (j-coin>=0)?qb[i-1][j-coin]:false;

                qb[i][j] = inc || exc;
            }
        }
        print2D(qb);
        return qb[n][tar];
    }

    // COIN CHANGE COMBINATIONS -> repetitions allowed =============================================================
    public static int coinChangeCombTab(int[] coins, int tar){
        int n = coins.length;
        int[] qb = new int[tar+1];
        for(int i=0;i<n;i++){
            int coin = coins[i];
            for(int t=0;t<=tar;t++){
                qb[t] += (t-coin>=0)?qb[t-coin]:0;
            }
        }

        return qb[tar];
    }

    // COIN CHANGE PERMUTATIONS -> repetitions allowed =============================================================
    public static int coinChangePermTab(int[] coins, int tar){
        int n = coins.length;
        int[] qb = new int[tar+1];
        qb[0] = 1;
        for(int t=0;t<=tar;t++){
            for(int i=0;i<n;i++){
                int coin = coins[i];
                qb[t] += (t-coin>=0)?qb[t-coin]:0;
            }
        }
        return qb[tar];
    }

    // 01  KNAPSACK ================================================================================================
    public static int knapsack01Tab(int[] v, int[] w, int cap){
        int n = v.length;
        int[][] qb = new int[n+1][cap+1];
        for(int i=1;i<=n;i++){
            for(int c=0;c<=cap;c++){
                int inc = (c-w[i-1]>=0)?qb[i-1][c-w[i-1]] + v[i-1]:0;
                int exc = qb[i-1][c];
                
                qb[i][c] = Math.max(inc, exc);
            }
        }
        return qb[n][cap];
    }

    // UNBOUNDED KNAPSACK ==========================================================================================
    public static int unboundedKS(int[] v, int[] w, int cap){
        int n = v.length;
        int[] qb = new int[cap+1];
        for(int i=1;i<=n;i++){
            for(int c=0;c<=cap;c++){
                int inc = (c-w[i-1]>=0)?qb[c-w[i-1]] + v[i-1]:0;
                int exc = qb[c];
            
                qb[c] = Math.max(inc, exc);
            }
        }
        return qb[cap];
    }

    public static void set1(){
        int[][] maze = take2Dinput();
        int n = maze.length;
        int m = maze[0].length;
        print2D(maze);
        int[][] qb = new int[n][m];
        System.out.println(mcmtM(0, 0, maze, qb));

        System.out.println(mcmtTab(maze));
        print2D(qb);
        // sc.close();
    }

    public static void set2(){
        // int[][] mine  = take2Dinput();
        // print2D(mine);
        // System.out.println("ANSWER : " +  goldMineTab(mine));

        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        
        int tar = sc.nextInt();
        boolean[][] qb = new boolean[arr.length][tar];
        System.out.println(tssMemo(0, 0, tar, qb, arr));
        
        print2D(qb);

        System.out.println("//=====================================//");
        System.out.println(tssTab(arr, tar));
    }

    public static void set3(){

    }
    public static void main(String[] args) throws Exception {
        // set1();
        // set2();
        set3();
    }



    private static void print1D(boolean[] arr){
        for(boolean ele: arr){
            System.out.print(ele+" ");
        }
        System.out.println();
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
