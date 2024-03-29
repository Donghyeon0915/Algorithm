/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hash.BOJ3865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Donghyeon <20183188>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static int N;
    public static Hashtable<String, HashSet<String>> academy_table;
    
    public static void getMembers(String name, HashSet<String> memberSet){
        //학회 이름이라면 멤버에는 추가하지 않음
        if(academy_table.containsKey(name)) academy_table.get(name).forEach(m -> { getMembers(m, memberSet); });
        else memberSet.add(name);
    }
    
    public static int solution(String result){
        for (Map.Entry<String, HashSet<String>> entry : academy_table.entrySet()) {
            Iterator<String> iter = entry.getValue().iterator();
            HashSet<String> memberSet = new HashSet<>();
            
            while(iter.hasNext()) getMembers(iter.next(), memberSet); /* 학회의 속한 그룹(학회원 or 학회) */
            
            entry.setValue(memberSet);
        }
        
        return academy_table.get(result).size();
    }
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        //학회 : 학회원(멤버) .
        while((N = Integer.parseInt(br.readLine())) != 0){
            String result = "";
            HashSet<String> member;
            academy_table = new Hashtable<>();
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), "[:,.]");
                String academy = st.nextToken();
                if(i == 0) result = academy;
                member = academy_table.getOrDefault(academy, new HashSet<>());
                
                while(st.hasMoreTokens()) member.add(st.nextToken());
                academy_table.put(academy, member);
            }
            
            System.out.println(solution(result));
        }
    }
    
}
