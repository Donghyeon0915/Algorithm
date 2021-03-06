/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baekjoon1260;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringJoiner;

/**
 *
 * @author Donghyeon <20183188>
 */
public class DFSandBFS {

    /**
     * @param args the command line arguments
     */
    
    static int vortex;                      //정점의 개수                  
    static int edge;                        //간선의 개수
    static int root;                        //시작할 노드(루트 노드)
    static int[][] adjacent;                //인접 행렬 (ArrayList[] 타입으로도 표현해보기)
    
    
    public static String DFS(){
        boolean[] visited = new boolean[vortex + 1];
        Stack<Integer> s = new Stack<>();
        StringJoiner sj = new StringJoiner(" ");
        
        s.push(root);
        visited[root] = true;
        sj.add(root + "");
        
        while(!s.isEmpty()){
            int nowNode = s.peek();
            boolean flag = false;
            visited[nowNode] = true;
            
            for (int i = 0; i < vortex + 1; i++) {
                if(visited[i] == false && adjacent[nowNode][i] == 1){
                    s.push(i);                                          //노드를 push
                    sj.add(i + "");                                     //출력
                    
                    visited[i] = true;
                    flag = true;
                    break;                                              //노드 하나를 push하면 break    
                                                                        //노드 하나를 push 했으면 해당 노드의 자식을 봐야하므로 break 후 반복문 처음으로 돌아간다.
                }
            }
            if(!flag){                                                  //만약 방문할 노드가 없으면 pop한다.
                s.pop();
            }
        }
        
        
        return sj.toString();
    }
    public static String BFS(){
        boolean[] visited = new boolean[vortex + 1];
        
        Queue<Integer> q = new LinkedList<>();
        StringJoiner sj = new StringJoiner(" ");
        q.add(root);
        visited[root] = true;
        
        while(!q.isEmpty()){
            int nowNode = q.poll();
            
            sj.add(nowNode + "");              //방문하는 즉시 출력
            
            for (int i = 1; i < vortex + 1; i++) {
                if(visited[i] == false && adjacent[nowNode][i] == 1){      //현재 노드랑 연결되어있는 노드이면
                    q.add(i);   
                    visited[i] = true;
                }
            }
        }
        
        return sj.toString();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner sc = new Scanner(System.in);
        
        vortex = sc.nextInt();
        edge = sc.nextInt();
        root = sc.nextInt();
        
        adjacent = new int[vortex + 1][vortex + 1];
        
        
        for (int i = 0; i < edge; i++) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            adjacent[node1][node2] = adjacent[node2][node1] = 1;
        }
        
        System.out.println(DFS());
        System.out.println(BFS());
    }
}
