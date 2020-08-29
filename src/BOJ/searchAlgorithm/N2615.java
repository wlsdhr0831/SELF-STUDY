package BOJ.searchAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2615 {
	static int map[][] = new int[20][20];
	static boolean visit[][][] = new boolean[4][20][20]; // 세로,가로,대각선1,대각선2별로 visit 저장
	static int dir[][] = {{1,0}, {0,1}, {1,1}, {1,-1}}; // 세로,가로,대각선1,대각선2
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		for(int i = 1; i <= 19; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int r = 1; r <= 19; r++) {
			for(int c = 1; c <= 19; c++) {
				if(map[r][c] == 0) continue; // 돌이 안놓임
				
				for(int i = 0; i < 4; i++) {
					if(visit[i][r][c]) continue; // 방문한적있으면 패스, 5개 초과 거르기 위해
					
					int cnt = find(r, c, i);					
					if(cnt == 5) { // 현재 위치에서 i방향으로 바둑돌이 5개 이면
						System.out.println(map[r][c]);
						if(i == 3) System.out.println((r+4) + " " + (c-4)); // /대각선은 출력위치가 다름
						else System.out.println(r + " " + c); // 출력위치 출력
						return ; // 출력 후 종료
					}
				}
			}
		}
		System.out.println("0"); // 5돌이 없으면 0출력
	}	
	
	// (r,c)위치에서 i방향으로 탐색
	private static int find(int r, int c, int i) {
		int nr = r + dir[i][0];
		int nc = c + dir[i][1];
		
		// 다음위치가 범위 벗어나면 현재 위치는 가능했으므로 1 리턴
		if(nr < 1 || nc < 1 || nr > 19 || nc > 19 || visit[i][nr][nc] || map[r][c] != map[nr][nc]) return 1;
		
		// i방향으로 방문했다고 방문 체크
		visit[i][nr][nc] = true;
		int ret = find(nr, nc, i); // 다음위치 방문
		return ret + 1; // 다음위치에서 온 갯수 + 1 리턴
	}
}
