package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 무향 그래프로 만들어서 간선 하나 갈때마다 +1하기

public class N2644 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int find1 = Integer.parseInt(st.nextToken());
		int find2 = Integer.parseInt(st.nextToken());
		
		int m = Integer.parseInt(br.readLine());
		List<Integer>[] graph = new ArrayList[N+1]; // 인접 그래프
		for(int i = 1; i <= N; i++)
			graph[i] = new ArrayList<>();
	
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		int ans = 0;
		boolean visit[] = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(find1); // find1에서 시작
		visit[find1] = true;
		while(!q.isEmpty()) {
			int size = q.size();
			ans++; // 촌수 하나씩 증가
			for(int i = 0 ; i < size; i++) {
				int cur = q.poll();
				
				for(int next : graph[cur]) {
					if(visit[next]) continue;
					
					if(next == find2) { // find2 만나면 종료
						System.out.println(ans);
						return ;
					}
					visit[next] = true;
					q.add(next);
				}
			}
		}
		System.out.println("-1"); // 가족이 아니면 -1 출력
	}
}
