package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 1번 노드를 제외한 2번부터 n번 노드까지를 연결하는 MST 만들기
// 처음에 주어지는 m개의 연결간선에는 싸이클이 존재할 수 있으므로
// 그 점을 확인하여 계산하여야 한다.

public class N2406 {
	static Queue<Edge> edgeList;
	static int n, m, edgeCnt = 0;
	static long minWeight = 0;
	static List<Edge> ans = new ArrayList<>();
	static int root[];
	static boolean selectNode[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		edgeList = new PriorityQueue<>();
		selectNode = new boolean[n];
		root = new int[n];
		for(int i = 1; i < n; i++)
			root[i] = i;
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			if(union(a, b)) edgeCnt++; // 처음 연결되는 노드면 의미있는 간선 갯수 추가
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i < n; i++) { // 1번 노드는 고려하지 않음
			st = new StringTokenizer(br.readLine(), " ");
			// 자신보다 번호가 작은 노드는 어차피 중복되므로 제외
			for(int j = 0; j <= i; j++) st.nextToken(); 
			for(int j = i+1; j < n; j++) {
				int w = Integer.parseInt(st.nextToken());
				edgeList.add(new Edge(i, j, w)); // 연결되는 노드와 가중치 저장
			}
		}
		
		kruskal(); // 크루스칼 알고리즘
		System.out.println(minWeight + " " + ans.size());
		for(Edge e : ans)
			System.out.println((e.s+1) + " " + (e.e+1));
	}
	
	static void kruskal() {
		while(edgeCnt < n-2) { // 간선 갯수가 연결해야하는 노드수-1과 같으면 종료
			Edge cur = edgeList.poll();
			if(!union(cur.s, cur.e)) continue; // 이미 연결되어있으면 패스
			ans.add(cur);
			edgeCnt++;
			minWeight += cur.w;
		}
	}
	
	static int find(int a) { // 루트 동일한 지 확인하기
		if(root[a] != a) root[a] = find(root[a]);
		return root[a];
	}
	
	static boolean union(int a, int b) { // 이미 연결된 노드인지 확인 후 합치기
		int rA = find(a);
		int rB = find(b);
		
		if(rA == rB) return false;
		
		root[rB] = rA;
		return true;
	}
	
	static class Edge implements Comparable<Edge>{ // 간선 정보 저장 클래스
		int s, e, w;
		
		Edge(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
}
