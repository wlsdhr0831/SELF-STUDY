package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// prim 알고리즘을 모든 노드마다 돌리면서
// 해당 노드에서 도착한 노드와의 거리를 더해서 케빈베이컨 수를 구함

public class N1389 {
	static List<Integer> graph[];
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1]; // 인접리스트
		for(int i = 1; i <= N; i++)
			graph[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		int min = Integer.MAX_VALUE, minNum = 0;
		for(int i = 1; i <= N; i++) { // 모든 노드들을
			int cnt = prim(i); // 프림 알고리즘을 돌림
			if(cnt < min) { // 최저 케빈 베이컨수가 나오면
				min = cnt;
				minNum = i; // 사람 번호 업데이트
			}
		}
		System.out.println(minNum);
	}

	private static int prim(int idx) {
		int node[] = new int[N+1];
		Arrays.fill(node, -1); // -1로 초기화
		node[idx] = 0; // 시작 노드는 0으로 초기화
		
		// bfs로 특정노드에 도달하는 최저 케빈베이컨 수 구하기
		Queue<Integer> q = new LinkedList<>(); 
		q.add(idx);
		
		int level = 0; // 케빈베이컨 수 저장
		while(!q.isEmpty()) {
			int size = q.size();
			level++; // 케빈베이컨 수 증가
			for(int i = 0; i < size; i++) {
				int cur = q.poll();
				for(int next : graph[cur]) {
					if(node[next] != -1) continue; // 방문한적 있는 노드면 패스
					node[next] = level;
					q.add(next);
				}
			}
		}
		
		int sum = 0;
		for(int n : node)
			sum += n;
		return sum;
	}
}
