package BOJ.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1520 {
	static int map[][], memo[][], dir[][] = {{1,0}, {0,1}, {-1,0}, {0,-1}};
	static int N, M;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		memo = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				memo[i][j] = -1;
			}
		}
		
		System.out.println(func(0,0));
	}
	
	private static int func(int r, int c) {
		if(r == N-1 && c == M-1) {
			memo[r][c] = 1;
			return memo[r][c];
		}
		
		int ret = 0;
		for(int i = 0; i < 4; i++) {
			int nextR = r + dir[i][0];
			int nextC = c + dir[i][1];
			
			if(nextR < 0 || nextC < 0 || nextR >= N || nextC >= M || 
					map[nextR][nextC] >= map[r][c]) continue;
			
			if(memo[nextR][nextC] != -1) {
				ret += memo[nextR][nextC];
			}else {
				memo[nextR][nextC] = func(nextR, nextC);
				ret+= memo[nextR][nextC];
			}
		}
		return ret;
	}
}
