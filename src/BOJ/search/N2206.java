package BOJ.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간을 줄이기 위해 visit 배열을 이용
// 해당위치에 벽을 부수고 도착했는지 부수지 않고 도착했는지에 따라 값이 다르므로 3차원 배열 사용

public class N2206 {
	static char[][] map;
	static int N, M;
	static int[][][] visit;
	static int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visit = new int[2][N][M];
		for(int i = 0; i < N; i++) 
			map[i] = br.readLine().toCharArray();
		
		bfs(0,0,0,0); // bfs로 탐색
		if(min == Integer.MAX_VALUE) min = -1; // 도착한적이 없으면 -1 출력
		System.out.println(min);	
	}
	
	static void bfs(int r, int c, int cnt, int broken) {
		if(r == N-1 && c == M-1) { // 도착했으면 cnt값 비교
			min = Math.min(min, cnt+1);
			return ;
		}
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
			
			if(map[nr][nc] == '1' && broken == 0) { // 다음 방문 장소가 벽이고 벽을 부순적이 없으면
				// 방문한적이 없거나 이전방문보다 횟수가 적으면
				if(visit[0][nr][nc] == 0 || visit[0][nr][nc] > cnt+1) { 
					visit[0][nr][nc] = cnt+1;
					bfs(nr, nc, cnt+1, 1);
				}
			}else if(map[nr][nc] == '0') { // 다음 방문 장소가 이동가능하면
				// 방문한적이 없거나 이전방문보다 횟수가 적으면
				if(visit[broken][nr][nc] == 0 || visit[broken][nr][nc] > cnt+1) {
					visit[broken][nr][nc] = cnt+1;
					bfs(nr, nc, cnt+1, broken);
				}
			}
		}
	}
}
