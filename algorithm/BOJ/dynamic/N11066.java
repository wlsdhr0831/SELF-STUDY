package BOJ.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파일합치기 틀린 코드

public class N11066 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for(int test = 0; test < testCase; test++) {
			int N = Integer.parseInt(br.readLine());
			
			int dp[][] = new int[N][N];
			int sum[][] = new int[N][N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) 
				dp[i][i] = Integer.parseInt(st.nextToken());
			
			for(int size = 2; size <= N; size++) {
				for(int s = 0; s <= N-size ; s++) {
					int e = s + size - 1;
					dp[s][e] = Math.min(dp[s][s] + dp[s+1][e], dp[s][e-1] + dp[e][e]);	
					int min = Integer.MAX_VALUE;
					for(int dis = 0; dis < e - s; dis++) {
						for(int i = s + dis; i < e; i++)
							min = Math.min(sum[s][i] + sum[i+1][e], min);
					}					
					sum[s][e] = min + dp[s][e];	
				}				
			}
			System.out.println(sum[0][N-1]);
		}
	}
}
