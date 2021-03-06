# <img src ="https://d2gd6pc034wcta.cloudfront.net/tier/9.svg" width="20px"> DFS와 BFS

## 문제

그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 
단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

## 입력

첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 
다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

## 출력

첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. 
V부터 방문된 점을 순서대로 출력하면 된다.


## 풀이
 ### - DFS
 

> 위와 같은 그래프가 있을 때 **루트 노드**를 **1**로 설정하면<br><br>
 **1을 먼저 방문**하고 **자식 노드**들 중 **방문하지 않은 곳**을 스택에 삽입한다. (삽입 후 출력)<br><br>
 **1**의 기준에선 **방문하지 않은 노드**들은 **2, 3, 4**가 있는데 **2**를 먼저 푸쉬한다.<br><br>
 **2, 3, 4**를 한 번에 푸쉬하는게 아니라 **자식 노드들을 따라 계속 푸쉬하는게 DFS**이다.<br><br>
 즉, **2**를 푸쉬했으면 **2의 자식 노드** 중 **방문하지 않은 노드**를 푸쉬한다.<br><br>
 **2**의 **자식 노드(1, 4)** 중 **1**은 이미 **방문** 했으므로 **4**를 푸쉬힌다.<br><br>
 **4**의 **자식 노드(1, 2, 3)** 중 **1, 2**는 이미 **방문**했으므로 **3**을 푸쉬한다.<br><br>


``` 위와 같은 방식으로 순회를 하면 **1 2 4 3** 의 결과가 나온다. ```
