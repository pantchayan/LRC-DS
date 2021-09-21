import java.util.Scanner;

public class L002 {
    static Scanner sc = new Scanner(System.in);
    // mcmt -> MINIMUM COST MAZE TRAVERSAL

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
        int[][] mine  = take2Dinput();
        print2D(mine);
        System.out.println("ANSWER : " +  goldMineTab(mine));
    }

    public static void main(String[] args) throws Exception {
        // set1();
        set2();

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
}
