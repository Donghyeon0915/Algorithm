/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hash.BOJ5021;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Donghyeon <20183188>
 */
class Parent{
    String p_,m_;
    
    public Parent(String p_, String m_) {
        this.p_ = p_;
        this.m_ = m_;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Parent) {
            Parent p = (Parent)o;
            //부모 순서가 바뀌어서 들어오는 경우까지 체크(부 <-> 모)
            return ((this.p_.equals(p.p_)) && (this.m_.equals(p.m_))) ||
                    ((this.p_.equals(p.m_) && this.m_.equals(p.p_)));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.p_);
        hash = 53 * hash + Objects.hashCode(this.m_);
        return hash;
    }
}

public class Main {

    /**
     * @param args the command line arguments
     */
    public static int N,M;
                  
    public static Hashtable<Parent, HashSet<String>> fam = new Hashtable<>(); /* <부모 관계, 자식> */
    public static Hashtable<String, Integer> index = new Hashtable<>();       /* <이름, 상위 관계 수> */
    public static Hashtable<String, Double> bloodline = new Hashtable<>();    /* <이름, 혈통> */
    
    /*Topology Sort 이용*/
    public static String getNextKing(String king){
        Queue<Parent> q = new LinkedList<>();
        bloodline.put(king, 1.0);
        
        for (Map.Entry<Parent, HashSet<String>> entry : fam.entrySet()) {
            Parent e = entry.getKey();
            if((index.get(e.p_) + index.get(e.m_)) == 0) q.add(e);
        }
        
        HashSet<String> child;
        Iterator<String> iter;
        
        while(!q.isEmpty()){
            Parent p = q.poll();
            child = fam.getOrDefault(p, null);
            
            if(child == null) continue;
            
            double bp = bloodline.getOrDefault(p.p_, 0.0);
            double bm = bloodline.getOrDefault(p.m_, 0.0);
            double bc = (bp + bm) / 2;
            
            iter = child.iterator();
            while(iter.hasNext()){
                String c = iter.next();
                bloodline.put(c, bc);
                
                int idx = index.get(c)-1;
                if(idx < 0) idx = 0;
                index.put(c, idx);  //자식의 상위 관계 수 -1
            }
            
            //이미 계산한 부모 관계는 제거
            fam.remove(p);
            
            //다시 상위 관계가 없는 부모 관계를 큐에 삽입
            for (Map.Entry<Parent, HashSet<String>> entry : fam.entrySet()) {
                Parent e = entry.getKey();
                if((index.get(e.p_) + index.get(e.m_)) == 0) q.add(e);
            }
        }
        
        //여기서 혈통의 값이 가장 큰 사람을 리턴하면 됨
        for (Map.Entry<String, Double> entry : bloodline.entrySet()) {
            
        }
        return "";
    } 
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();   //가족 정보의 수
        M = sc.nextInt();   //왕위를 계상받기를 주장하는 사람 수
        
        String king = sc.next();
        String c; //자식
        Parent parent;
        HashSet<String> child;

        for (int i = 0; i < N; i++) {
            parent = new Parent(sc.next(), sc.next());
            c = sc.next();
            
            //부모 관계 밑에 자식을 추가
            child = fam.getOrDefault(parent, new HashSet<>());
            child.add(c);
            
            fam.put(parent, child);
            
            //자식의 상위 관계에 +1
            index.put(c, index.getOrDefault(c, -1) + 1);
        }
    }
    
}
