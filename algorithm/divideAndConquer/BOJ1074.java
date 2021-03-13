package BOJ.divideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 더 작은 범위로 들어가면서 목표 지점 도착하는지 확인
// 목표 지점이 포함되어있지 않은 범위는 값만 더하고 다음 지점으로 패스

public class N1074 {
	static int cnt = -1, N, goalR, goalC;
	static boolean find = false;
	static int dir[][] = {{0,0}, {0,1}, {1,0}, {1,1}}; // 4방향 위치 정보
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		goalR = Integer.parseInt(st.nextToken());
		goalC = Integer.parseInt(st.nextToken());
		
		// 배열 사이즈 구하기
		int size = 1;
		for(int i = 0; i < N; i++)
			size *= 2;
		
		func(0, 0, size);
		System.out.println(cnt);
	}
	
	static void func(int r, int c, int size) {
		if(size == 1) { // 사이즈 1이면 종료
			cnt++;
			if(r == goalR && c == goalC) find = true; // 목표지점이면 find 변수 교체
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nr = r + ((size/2) * dir[i][0]);
			int nc = c + ((size/2) * dir[i][1]);
			
			// 목표위치가 가려는 범위 밖이면
			if(goalR < nr || goalC < nc || goalR > (nr + size/2) || goalC > (nc + size/2)) {
				cnt += (size/2 * size/2); // 여기에서 더해지는 cnt를 사이즈로 계산해서 더하기
				continue;
			}
			func(nr, nc, size/2);

			if(find) return; // 목표지점 방문한적 있으면 종료
		}
	}
}
