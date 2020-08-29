package BOJ.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가장 많은 파이프를 설치하려면 그 앞전에 설치되는 파이프가 가장 윗라인으로 설치되어야 함
// 오른쪽 위 대각선,오른쪽,오른쪽 아래 대각선 순서로 현재 위치에서 다음 위치를 탐색
// 이미 방문했던 곳은 이미 파이프가 설치되어있거나 파이프를 설치할 수 없는 곳이므로 '-'로 수정
// 설치가 완료되면 다른 길은 찾지 않고 종료

public class N3109 {
	static int N, M, ans = 0;
	static char map[][];
	static int dir[][] = {{-1,1}, {0,1}, {1,1}};
	static boolean find = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for(int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();
		
		for(int i = 0; i < N; i++) {
			find = false;
			back(i,0); // 헹을 한칸씩 내려가며 파이프 설치 가능한지 확인 후 설치
		}
		System.out.println(ans);
	}

	private static void back(int r, int c) {
		if(c == M - 1) { // 빵집까지 도착했으면
			ans++;
			find = true; // 경로 찾음
			return ;
		}
		
		for(int i = 0; i < 3; i++) {
			// 다음위치 장소와 범위 체크
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			if(nr < 0 || nr >= N || map[nr][nc] != '.') continue;
			
			map[nr][nc] = '-'; // 값 변경
			back(nr, nc); // 다음위치로 이동
			if(find) return; // 파이프 설치가 완료 되었으면 종료
		}
	}
}
