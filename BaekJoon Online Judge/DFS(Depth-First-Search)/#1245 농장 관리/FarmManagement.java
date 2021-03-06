import java.util.Scanner;

class Pair{
    int x_;
    int y_;

    public Pair(int x_, int y_) {
        this.x_ = x_;
        this.y_ = y_;
    }
}

public class Main {

    /**
     * @param args the command line arguments
     */
    public static int N,M;
    public static int[][] farm;
    public static boolean[][] visited;
    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0,-1};
    public static int[] dy = { 0, -1,-1,-1, 0, 1, 1, 1};
    public static boolean isPeak;
    
    public static int isMountainPeak(Pair root, int cur){
        visited[root.x_][root.y_] = true;
        
        for (int i = 0; i < 8; i++) {
            Pair next = new Pair(root.x_ + dx[i], root.y_ + dy[i]);
            
            if(next.x_ < 0 || next.y_ < 0 || next.x_ >= N || next.y_ >= M) continue;
            if(farm[next.x_][next.y_] > cur) isPeak = false;
            if(visited[next.x_][next.y_]) continue;
            
            if(farm[next.x_][next.y_] == cur) isMountainPeak(next, cur);
        }

        return isPeak ? 1 : 0;
    }
        
    public static int getMountainPeak(){
        int peak = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(farm[i][j] > 0 && !visited[i][j]){
                    isPeak = true;
                    int dfs = isMountainPeak(new Pair(i,j), farm[i][j]);
                    peak += dfs;
                }
            }
        }
        
        return peak;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        farm = new int[N][M];
        visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < M; j++) 
                farm[i][j] = sc.nextInt();
        
        System.out.println(getMountainPeak());
    }
}
