//package SWEA.graph;
package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 학생의 키 순서를 알기 위해서는 자신보다 큰 학생수와 작은 학생수의 합이 자신을 제외한 학생수와 동일해야한다.
// 행렬의 진입차수와 진출차수의 합을 이용하여 구함
// 진입차수의 수 : 자신보다 작은 학생수
// 진출차수의 수 : 자신보다 큰 학생수

public class N5643 {
	static ArrayList<Integer>[] map;
	static boolean [][]visit;
	static int n, m;
	
	static int []inN;
	static int []outN;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			int ans = 0;
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			
			map = new ArrayList[n+1];
			for(int i = 1; i <= n; i++)
				map[i] = new ArrayList<Integer>();
			
			visit = new boolean[n+1][n+1];
			inN = new int[n+1];
			outN = new int[n+1];
			
			for(int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a].add(b); // a보다 b가 큰 것 저장
				visit[a][b] = true; // a와 b를 비교한 적있는지 체크
				outN[a]++; // a행의 진출차수 체크
				inN[b]++; // b열의 진입차수 체크
			}
			
			// 시작학생과 비교가 가능한 학생 따라가며 자신보다 더 큰 학생 확인하기 
			for(int start = 1; start <= n; start++) 
				for(int next : map[start])
					dfs(start, next); 				
		
			// 진입 차수와 진출차수의 합이 자신을 제외한 학생수와 동일한지 확인
			for(int i = 1; i <= n; i++) {
				if(inN[i] + outN[i] == n-1) ans++;
			}
			
			System.out.println("#" + test + " " + ans);
		}
	}
	
	private static void dfs(int cur, int to) {
		for(int next : map[to]) {
			// 시작학생과 다음학생이 비교한적있는지 확인
			if(visit[cur][next]) continue;
			
			// 비교했다고 체크후 각 학생별 진입차수, 진출차수 값 변경
			visit[cur][next] = true;
			outN[cur]++;
			inN[next]++;
			
			// 다음 학생보다 큰애들 비교하러 가기
			dfs(cur, next);
		}
	}
}
