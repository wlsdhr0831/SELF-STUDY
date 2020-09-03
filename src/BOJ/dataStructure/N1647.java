package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 프림알고리즘을 사용해서
// MST를 만들고 최대 간선 하나값을 삭제한다.

public class N1647 {
	static int V, E;
	static List<Edge> graph[]; // 인접리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[V+1];
		for(int i = 1; i <= V; i++)
			graph[i] = new ArrayList<>();
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[s].add(new Edge(e, w));
			graph[e].add(new Edge(s, w));
		}
		System.out.println(prim()); // 프림 알고리즘 사용
	}
	
	static private int prim() {
		boolean select[] = new boolean[V+1]; // 선택한 노드 저장
		Queue<Edge> q = new PriorityQueue<>(); // 가중치를 오름차순으로 정렬
		q.add(new Edge(1, 0)); // 1번 노드를 선택
		
		int sum = 0, cnt = 0, max = 0; // 가중치 합, 선택한 노드 갯수, MST의 최대 가중치 값 저장
		while(cnt < V) {
			Edge e = q.poll(); // 가중치 가장 낮은 간선 선택
			if(select[e.node]) continue; // 이미 선택했던 노드면 패스
			select[e.node] = true; // 노드 선택
			sum += e.w; // 가중치 더하기
			max = Math.max(max, e.w); // 최대 가중치 저장
			cnt++; // 선택한 노드 갯수 저장
			
			for(Edge edge : graph[e.node]) { // 방금 선택한 노드와 연결된 간선 추가
				if(select[edge.node]) continue;
				q.add(edge);
			}
		}		
		return sum - max;
	}
	
	static class Edge implements Comparable<Edge>{
		int node, w;
		Edge(int e, int w){
			this.node = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) { // 가중치를 오름차순으로 정렬
			return this.w - o.w;
		}
	}
}
