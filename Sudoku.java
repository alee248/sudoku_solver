import java.util.Scanner;

public class Sudoku {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char a[][] = new char[9][9];
        for (int i = 0; i < 9; i++) {
            a[i] = sc.nextLine().toCharArray();
        }
        dfs(a,0,0);
    }

    private static void dfs(char[][] a, int x, int y) {
        if(x==9){ //When x==9, it will be all finished and can be output
            print(a);
            System.exit(0);
        }
        if(a[x][y]=='0'){    //there is no number that can be filled naturally.
            for (int i = 1; i <= 9; i++) {
                //check 1-9 if any number can be used
                if(check(a,x,y,i)){
                    a[x][y] = (char) ('0'+i); //fill in w the possible number
                    dfs(a,x+(y+1)/9,(y+1)%9); //Continue to walk down (first walk horizontally, then walk vertically)

                }
            }
            a[x][y] = '0'; // backtrack to 0

        }else{
            dfs(a,x+(y+1)/9,(y+1)%9); 
        }
    }

    private static void print(char[][] a) {//An output function
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static boolean check(char[][] a, int x, int y, int k) {

        for (int i = 0; i < 9; i++) {
            if(a[x][i]=='0'+k) return false;//Check it horizontally
            if (a[i][y]=='0'+k) return false;//Vertical inspection
        }
        for (int i = (x/3)*3; i < (x/3+1)*3; i++) {
            for (int j = (y/3)*3; j < (y/3+1)*3; j++) {
                if(a[i][j]=='0'+j) return  false;//Nine Palaces
            }
        }
        return true;
    }
}