package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 가중치가 음수도 가능하지만
// MST를 찾는 것이므로 프림 알고리즘 이용하여 풀었다.

public class N1197 {
	static List<Edge> graph[];
	static int V, E;
	
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
			int a = Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Edge(b, c));
			graph[b].add(new Edge(a, c));
		}
		System.out.println(prim());		
	}
	
	static private long prim() {
		// 가중치를 기준으로 오름차순 정렬하기 위한 우선순위 큐
		Queue<Edge> q = new PriorityQueue<>(); 
		
		boolean select[] = new boolean[V+1];
		q.add(new Edge(1,0)); // 1번 노드부터 시작
		int cnt = 0; // 선택한 노드의 갯수
		long sum = 0; // 선택한 간선의 가중치 합
		
		while(cnt < V) {
			Edge e = q.poll();
			if(select[e.node]) continue; // 기존의 선택한 노드면 패스
			cnt++; // 노드 갯수 증가
			sum += e.w; // 가중치 추가
			select[e.node] = true; // 선택한 노드로 수정
			
			// 선택한 노드와 연결된 간선 추가
			for(Edge edge : graph[e.node]) { 
				if(select[edge.node]) continue;
				q.add(edge);
			}
		}
		return sum;
	}
	
	static class Edge implements Comparable<Edge>{
		int node, w;
		Edge(int node, int w){
			this.node = node;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
}
