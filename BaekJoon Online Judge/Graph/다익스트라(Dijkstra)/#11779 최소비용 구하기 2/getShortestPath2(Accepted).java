/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijkstra.Baekjoon11779;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 *
 * @author Donghyeon <20183188>
 */

class Dis{
    int distance;
    //ArrayList<Integer> path;
    int prev = -1;
    public Dis(int distance) {
        this.distance = distance;
    }
}

class Node implements Comparable<Node>{
    int index;
    int distance;

    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }
    
    @Override
    public int compareTo(Node o){
        return this.distance - o.distance;
    }
}

public class Main {

    /**
     * @param args the command line arguments
     */
    public static final int INF = 100000001;
    public static int[][] adj;
    public static Dis[] dis;
    
    public static void dijkstra(int start, int end){
        int n = adj.length;
        StringJoiner result = new StringJoiner(" ");
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        dis[start].distance = 0;
        pq.add(new Node(start, dis[start].distance));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.distance > dis[cur.index].distance) continue;
            
            for (int i = 1; i < n; i++) {
                if(adj[cur.index][i] != INF){
                    if(dis[i].distance > dis[cur.index].distance + adj[cur.index][i]){
                        dis[i].distance = dis[cur.index].distance + adj[cur.index][i];
                        dis[i].prev = cur.index;
                        pq.offer(new Node(i, dis[i].distance));
                    }
                }
            }
        }
                       
        System.out.println(dis[end].distance);
        
        print_Path(start, end, result);
        System.out.println(result.toString().split(" ").length);
        System.out.println(result);
    }
    //경로를 출력하는 메소드(역추적)
    //dis[i]에 어느 노드에서 왔는지를 저장해둔다(prev)
    //경로가 1 > 4 > 2 > 3이면 dis[3] = 2, dis[2] = 4, dis[4] = 1 이런식으로 저장하고 end부터 거꾸로 올라가며 경로를 추적한다.(재귀)
    public static void print_Path(int start, int end, StringJoiner result){
        if(dis[end].prev == -1) return;
        if(dis[end].prev == start) result.add(start + "");
        
        print_Path(start, dis[end].prev, result);
        result.add(end + "");
    }
    
    public static void print(ArrayList<Integer> list){
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
        System.out.println("\n");
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int city = sc.nextInt();
        int bus = sc.nextInt();
        adj = new int[city + 1][city + 1];
        dis = new Dis[city + 1];
        
        for (int[] is : adj) {
            Arrays.fill(is, INF);
        }

        
        for (int i = 0; i < city + 1; i++) {
            dis[i] = new Dis(INF);
        }
        
        for (int i = 0; i < bus; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj[a][b] = Math.min(adj[a][b], sc.nextInt());
            
        }
        
        int start = sc.nextInt();
        int end = sc.nextInt();

        dijkstra(start, end);
    }
}
