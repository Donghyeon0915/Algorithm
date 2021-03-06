package BFS.Baekjoon2458;

import java.util.*;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void bfs(int root, int[][] student){
        int std = student.length - 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            int now = q.poll();
            
            for (int i = 1; i < std + 1; i++) {
                //현재(now) 사람보다 큰 사람들을 add한다.
                //student[i][root]가 -1이라는 것은 이미 이 노드를 탐색했다는 뜻이므로 add하지 않는다. (중복으로 add하는 경우 방지 - 메모리 초과남)
                if(student[now][i] == 1 && student[i][root] != -1){
                    q.add(i);
                    student[i][root] = -1; //도달하는 노드의 사람보다 root가 작다는 것을 표시
                    student[root][i] = 1;  // *도달하는 노드가 자신(root)보다 크다는 것도 표시해줘야함*
                }
            }
        }
    }
    
    public static void printStatus(int std, int[][] student){
        for (int i = 1; i < std + 1; i++) {
            System.out.print(i + " : ");
            for (int j = 1; j < std + 1; j++) {
                System.out.print(student[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public static int getStudent(int std, int[][] student){
        int result = 0;
        for (int i = 1; i < std + 1; i++) {
            bfs(i, student);
        }
        
        //printStatus(std, student);
        
        for (int i = 1; i < std + 1; i++) {
            int cnt = 0;
            for (int j = 1; j < std + 1; j++) {
                if(student[i][j] == -1 || student[i][j] == 1) cnt++;
            }
            if(cnt >= std - 1) result++;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int std = sc.nextInt();     // 2<= std <= 500
        int comp = sc.nextInt();    // 0 <= comp <= (500 * 499) / 2
        int[][] student = new int[std + 1][std + 1];
        //최대 학생 수 500
        //최대 비교 횟수 124,750
        
        for (int i = 0; i < comp; i++) {
            student[sc.nextInt()][sc.nextInt()] = 1;
        }
        
        System.out.println(getStudent(std, student));
    }
    
}
