# 크루스칼 (Kruskal)

## 로직

1. **최초 모든 간선들을 가중치에 따라 오름차순 정렬**
2. **가중치가 가장 낮은 간선부터 선택하면서 트리 생성**

    ⇒ 사이클이 생성되면 다음 간선 선택

3. **n-1개의 간선이 선택될 때까지 2번 반복**

```java
int weightSum = 0, edgeCnt = 0;

Queue<Edge> q = new PriorityQueue<>((o1, o2) -> {return o1.w - o2.w;});
// q에 모든 간선 넣기

while(edgeCnt < N-1){
	Edge cur = q.poll();
	if(findRoot(cur.u) != findRoot(cur.v)){
		union(u, v);
		edgeCnt++;
		weightSum += cur.w;
	}
}
```

# 프림 (Prim)

- **하나의 정점에서 연결된 간선들 중에서 하나씩 선택**하면서 MST 만듬
- tree verticles (트리 정점들) : MST 만들기 위해 선택된 정점들
- non-tree verticles (비트리 정점들) : 선택되지 않은 정점들

## 로직

1. **임의의 정점을 하나선택**
2. **선택한 정점과 인접하는 정점 중 최소비용의 간선 선택**
3. **모든 정점 선택할 때까지 2번 과정 반복**

```java
int weightSum = 0, nodeCnt = 0;
boolean[] select = new boolean[N];

Queue<Node> q = new PriorityQueue<>((o1, o2) -> {return o1.w - o2.w;});
q.add(new Node(startNodeIdx, 0));

while(nodeCnt < N){
	Node cur = q.poll();
	if(select[cur.node]) continue;

	select[cur.node] = true;
	nodeCnt++;
	weightSum += cur.w;

	for(Node next : graph[cur.node]){
		if(select[next.node]) continue;
		q.add(next);
	}
}
```

# 다익스트라 (Dijkstra)

- **시작 정점에서 거리가 최소인 정점 선택** (탐욕 기법 사용)
- 프림 알고리즘과 유사

## 로직

1. **시작 정점에서 도착하는 애들 Dis 배열에 가중치 값 저장**
2. **선택되지 않은 정점 중에서 거리가 최소인 정점 찾기**
3. **선택한 후 해당 정점에서 갈 수 있는 곳 Dis 거리가 더 작으면 수정**
4. **모든 정점이 선택될 때까지 2,3번 반복**

# 벨만-포드 (Bellman-Ford)

- **가중치가 음수도 가능**

# 플로이드-워샬 (Floyd-Warshall)

- **모든 정점들에 대한 최단 경로 찾기**
- 모든 정점들을 시작 정점으로 잡고 반복문 돌리기