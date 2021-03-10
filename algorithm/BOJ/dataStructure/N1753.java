package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 시작 노드에서 다른 노드까지 가는데 최저 가중치 구하는 문제

public class N1753 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int K = Integer.parseInt(st.nextToken());
		
		// 시작점에서 해당 index 노드까지 도착하는게 소요된 가중치 저장
		int[] ans = new int[V+1];
		// 도착하지 않았음을 구분하기 위해 -1로 초기화
		Arrays.fill(ans, -1);
		
		// 해당노드에서 이어지는 간선을 저장하기 위해 이중 리스트 생성
		List<Pair>[] graph = new ArrayList[V+1];
		for(int i = 1; i <= V; i++)
			graph[i] = new ArrayList<>();
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			// u노드에서 v노드로 가는 w가중치의 간선 저장
			graph[u].add(new Pair(v, w));
		}
		
		// 시작 노드에서 갈 수 있는 다음 노드 이동하기 위한 queue 생성
		Queue<Pair> q = new PriorityQueue<>();
		ans[K] = 0; // 자기 자신은 가중치 0
		q.add(new Pair(K, 0));
		while(!q.isEmpty()) {
			Pair cur = q.poll(); // 현재에서 갈 수 있는
			for(Pair next : graph[cur.v]) { // 다음 노드 확인하며
				// 현재 노드에 방문한 적 없거나 저번에 방문했던 가중치가 지금보다 크면 값 업데이트
				if(ans[next.v] == -1 || ans[next.v] > next.w + cur.w) {
					ans[next.v] = next.w + cur.w;
					q.add(new Pair(next.v, ans[next.v]));
				}
			}
		}
		
		// 도착 여부에 따라 출력값 구분
		for(int i = 1; i <= V; i++) {
			if(ans[i] == -1) System.out.println("INF");
			else System.out.println(ans[i]);
		}
	}
	
	// 정점을 저장하기 위한 클래스
	private static class Pair implements Comparable<Pair>{
		int v, w;
		Pair(int v, int w){
			this.v = v;
			this.w = w;
		}
		
		// 가중치가 작은 순서대로 정렬하기 위한 compareTo()
		@Override
		public int compareTo(Pair o) {
			return this.w - o.w;
		}
	}
}
