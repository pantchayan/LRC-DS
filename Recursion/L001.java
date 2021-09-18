package Recursion;

import java.util.Scanner;

public class L001{
    public static void pattern01(int n){

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==0 || j==0 || i==n-1 || j==n-1 || i==j || i+j == n-1){
                    System.out.print("*\t");
                }
                else{
                    System.out.print("\t");
                }

            }
            System.out.println();
        }
    }


    public static void pattern02(int n){
        // n + n      star  space
        // 1st row ->  1     2*n-2*1
        // 2nd row ->  2     2*n-2*2
        // nth row ->  n     2*n-2*n

        // n+1th row -> n    2*n - 2*n
        // n+2th row -> n-1  2*n - 2*(n-1)
        // 2*nth row -> 1    2*n-2*1


        int nst = 1;
        int nsp = 2*n - 2*1;

        for(int row = 1; row<= 2*n; row++){
            for(int cst=1;cst<=nst;cst++){
                System.out.print("*\t");
            }

            for(int csp=1;csp<=nsp;csp++){
                System.out.print("\t");
            }

            for(int cst=1;cst<=nst;cst++){
                System.out.print("*\t");
            }

            System.out.println();

            if(row == n) continue;
            if(row >= n+1){
                nst--;
            }
            else{
                nst++;
            }

            nsp = 2*n - 2*nst;
        }

    }


    // RHOMBUS
    public static void pattern03(int n){
        //            sp1  star  stars
        // 1st row    n-1   1  
        // 2nd row
        int sp1 = n-1;
        int sp2 = n-2;
        for(int row = 1;row<=n;row++){
            for(int csp = 1;csp<=sp1;csp++){
                System.out.print("\t");
            }

            System.out.print("*\t");
            
            for(int csp = 1;csp<=sp2;csp++){
                System.out.print((row==1 || row==n)?"*\t":"\t");
            }
            System.out.println("*\t");

            sp1--;
        }
    }


    public static void pattern04(int n){
        for(int i = 0;i<n;i++){
            for(int j=0;j<n;j++){
                int x = (n%2==0)?n+ (n/2):n-1 + (n/2);
                if(i==n/2 || j-i == (n)/2 || i+j == x){
                    System.out.print("*\t");
                }
                else{
                    System.out.print("\t");
                }
            }
            
        System.out.println();
        }
    }

    public static int sumOfDigit(String str){
        if(str.length()==0) return 0;

        int curr = str.charAt(0) - '0';

        return sumOfDigit(str.substring(1)) + curr;
    }


    public static int reverseDigit(int num){
        if(num==0) return 0;
        int count = 0;
        int temp = num;
        while(temp!=0){
            count++;
            temp/=10;
        }
        int fact = (int)Math.pow(10, count-1);
        int digit = num / fact;

        return reverseDigit(num%fact) * 10 + digit; 

    } 

    public static String printAdjacentDuplicates(String str, int idx){
        if(idx==str.length()) return "";

        String asf = printAdjacentDuplicates(str, idx+1);
        if(idx==0) return asf + str.charAt(idx);
        char a = str.charAt(idx);
        char b = str.charAt(idx-1);
        return (a==b)?asf:asf+a;
    }


    public static void moveCharacters(String str, char c, int idx, int count){
        if(idx+count==str.length()){
            System.out.println(str);
            return;
        }

        if(str.charAt(idx)==c){
            str = str.substring(0, idx) + str.substring(idx+1) + c;
            count++;
            moveCharacters(str, c, idx, count);
        }
        else{
            moveCharacters(str, c, idx+1, count);
        }
    }

    public static void moveCharactersOptimised(String str, char c, int idx, String asfL, String asfR){
        if(idx==str.length()){
            System.out.println(asfL+asfR);
            return;
        }

        if(str.charAt(idx)==c){
            asfR += c;
        }
        else{
            asfL += str.charAt(idx);
        }

        moveCharactersOptimised(str, c, idx+1, asfL, asfR);
    }

    public static String moveCharactersReturn(String str, char c, int idx){
        if(idx==str.length()) return "";
        
        String asf = moveCharactersReturn(str, c, idx+1);
        char lastChar = str.charAt(idx);
        if(lastChar==c) asf = asf+lastChar;
        else asf = lastChar+asf;
        return asf;
    }



    // COUNT AND REMOVE "hi" from a string

    public static int countHi(String str, int idx, String asf){
        if(idx>=str.length()-1){
            if(idx==str.length())
                System.out.println(asf);
            else 
                System.out.println(asf+str.charAt(idx));
            return 0;
        }

        char a = str.charAt(idx);   
        char b = str.charAt(idx+1);
        int count = 0;
        if(a=='h' && b=='i'){
            count += countHi(str, idx+2, asf) + 1;
        }
        else{
            count += countHi(str, idx+1, asf+a);
        }

        return count;
    }         
    
    // KEEP HIT 
    public static int keepHit(String str, int idx, String asf){
        if(idx==str.length()){
            System.out.println(asf);
            return 0;
        }

        char a = str.charAt(idx);   
        int count = 0;
        if(a=='h' && idx+1<str.length() && str.charAt(idx+1)=='i'){
            if(idx+2<str.length() && str.charAt(idx+2)=='t') count+= keepHit(str, idx+1, asf+a);
            else count += keepHit(str, idx+2, asf) + 1;
        }
        else{
            count += keepHit(str, idx+1, asf+a);
        }

        return count;
    }
    


    public static int tunnelMaze(int sr, int sc, int er, int ec, String psf, int[][] tunnels, boolean[] vis){
        if(sr == er && sc == ec){
            System.out.println(psf);
            return 1;
        }
        int count = 0;

        if(sc+1 <= ec){
            count +=  tunnelMaze(sr, sc+1, er, ec, psf+"h", tunnels, vis);
        }

        if(sr+1 <= er){
            count +=  tunnelMaze(sr+1, sc, er, ec, psf+"v", tunnels, vis);
        }

        for(int i=0;i<tunnels.length && vis[i]==false;i++){
            int tsr = tunnels[i][0]/10;
            int tsc = tunnels[i][0]%10;
            
            
            int ter = tunnels[i][1]/10;
            int tec = tunnels[i][1]%10;

            if(sr==tsr && sc==tsc){
                vis[i] = true;
                count += tunnelMaze(ter, tec, er, ec, psf+"T:[("+tsr+", "+tsc+") -> ("+ter+", "+tec+")]", tunnels, vis);
                vis[i] = false;
            }
        }

        return count;
    }


    public static void allUniquePermutations(String str){
        // 
    }




    public static void main(String[] args) {
        // int n = 7;
        
        // pattern03(n);
        // System.out.println("-------------------------------------------------------");
        // pattern04(n);
        // moveCharacters("aecdbeedeef", 'e', 0, 0);
        // moveCharactersOptimised("aecdbeedeef", 'e', 0, "", "");
        // String ans = moveCharactersReturn("aecdbeedeef", 'e', 0);
        // System.out.println(ans.charAt(2));

        // System.out.println(printAdjacentDuplicates("pepppeeeepppeeeep", 0));
        // System.out.println(reverseDigit(12345));
        // System.out.println(sumOfDigit("1234"));

        // int c = countHi("hhhhhhhiiiiiiiihi", 0, "");
        // System.out.println(c);

        // replaceHi("hihiasashihi", 0, "");

        // keepHit("hihihithihiasdasdhihihitas", 0, "");

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[][] tunnels = new int[k][2];
        for(int i=0;i<k;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();

            tunnels[i][0] = a*10 +b;
            tunnels[i][1] = c*10 +d; 
        }
        boolean[] vis = new boolean[k];
        int ans = tunnelMaze(0, 0, n-1, m-1, "", tunnels, vis);


        System.out.println(ans);

        sc.close();
    }
}