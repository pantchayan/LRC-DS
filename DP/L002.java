import java.util.Scanner;

public class L002 {

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

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[][] maze = new int[n][m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                maze[i][j] = sc.nextInt();
            }
        }
        print2D(maze);
        int[][] qb = new int[n][m];
        System.out.println(mcmtM(0, 0, maze, qb));
        print2D(qb);
        sc.close();
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
