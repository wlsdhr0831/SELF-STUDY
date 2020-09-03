package BOJ.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 마지막 구역은 0으로 고정하고 다른 구역을 부분집합으로 만들어서
// 1이면 A, 0이면 B로 나누어서 인구 수 게산

public class N17471 {
	static List<Integer> graph[];
	static int N, min = Integer.MAX_VALUE;
	static int num[];
	static boolean visit[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		N = Integer.parseInt(br.readLine());
		
		num = new int[N];
		visit = new boolean[N];
		graph = new ArrayList[N]; // 인접 리스트
		for(int i = 0; i < N; i++)
			graph[i] = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++)
			num[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int size = Integer.parseInt(st.nextToken());
			for(int j = 0; j < size; j++) {
				int n = Integer.parseInt(st.nextToken()) - 1;
				graph[i].add(n);
			}
		}
		
		// 비트 마스킹으로 부분집합 제작
		for(int i = 1; i < (1 << N - 1); i++) { 
			Arrays.fill(visit, false);
			int sum = 0;
			for(int j = 0; j < N; j++) {
				if((i & (1<<j)) > 0) {
					sum = chkLocality(i, j, true);
					break;
				}
			}
			sum += chkLocality(i, N-1, false);
			
			if(sum == N) { // 전체 구역을 모두 선택했다면
				int sumA = 0; // A구역 인구수
				int sumB = num[N-1]; // B구역 인구수
				for(int j = 0; j < N-1; j++) {
					if( (i & (1<<j)) > 0) sumA += num[j]; // 1이면 A구역
					else sumB += num[j]; // 0이면 B구역
				}
				min = Math.min(min, Math.abs(sumA-sumB)); // 차이 최솟값 저장
			}
		}
		if(min == Integer.MAX_VALUE) min = -1; // 2구역으로 나누지 못하면 -1
		System.out.println(min);
	}
	
	static int chkLocality(int i, int j, boolean type) { // true : 1인 구역 , false : 0인 구역
		int cnt = 1;
		Queue<Integer> q = new LinkedList<>();
		q.add(j);
		visit[j] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next : graph[cur]) {
				if(type) { // 1로 되어 있는 구역이 이어져 있는지 확인
					if(visit[next] || ((i & (1<<next)) == 0)) continue;
					visit[next] = true;
					cnt++;
					q.add(next);					
				}else { // 0으로 되어 있는 구역이 이어져 있는지 확인
					if(visit[next] || ((i & (1<<next)) != 0)) continue;
					visit[next] = true;
					cnt++;
					q.add(next);
				}
			}		
		}
		return cnt;
	}
}
