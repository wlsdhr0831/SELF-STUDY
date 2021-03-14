# BFS (Breadth First Search)

- 너비 우선 탐색
- 레벨(높이)가 동일한 노드들을 방문.
- 각 노드들의 자식들을 Queue에 넣어 FIFO로 탐색

```java
bfs(0);

void bfs(int startNode){
	Queue<Status> q = new LinkedList<>();
	q.add(new Status(startNode));
	visit[startNode] = true;
	
	while(!q.isEmpty()){
		Status cur = q.poll();
		for(){ // 현재 상태에서 다음 상태 가는 조건
			if(visit[nextNode]) continue;
			if(종료 조건){
				// 종료시 할 행동
				return ;
			} 
				
			visit[nextNode] = true;
			q.add(new Status(startNode));	
		}
	}
}
```

# DFS (Depth First Search)

- 깊이 우선 탐색
- 루트 노드에서 출발
- 한 방향으로 깊이 탐색하다가 단말 노드를 만나면 다른 경로로 갈라지는 곳까지 되돌아옴
- 다른경로를 탐색
- 재귀적으로 구현 / Stack을 이용하여 LIFO로 탐색

```java
dfs(0,0);

void dfs(int nodeIdx, int cnt, boolean[] visit){
	if(종료 조건) {
		// 종료시 할 행동
		return ;
	} 

	for(){ // 현재 상태에서 다음 상태 가는 조건
		if(visit[nextNode]) continue;
					
		visit[nextNode] = true;
		dfs(nextNode, cnt+1, visit);
		visit[nextNode] = false;
	}
}
```