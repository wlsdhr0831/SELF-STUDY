package BOJ.combinational;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// 회전 연산을 순열로 정하기

public class N17406 {
	static int map[][], dir[][] = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	static int N, M, K, min = Integer.MAX_VALUE;
	static List<Pair> kList = new LinkedList<>();
	static int[] kPIdx;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		visit = new boolean[K];
		kPIdx = new int[K];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			kList.add(new Pair(r,c,s));
		}
		
		kListP(0); // 회전 연산 순열 정하기
		System.out.println(min);
	}
	
	static void kListP(int cnt) {
		if(cnt == K) {
			calValue(); // 정한 순서대로 배열돌려서 값 구하기
			return;
		}
		
		for(int i = 0; i < K; i++) {
			if(visit[i]) continue;
			
			visit[i] = true;
			kPIdx[cnt] = i;
			kListP(cnt+1);
			visit[i] = false;
		}
	}
	
	static void calValue() {
		int temp[][] = new int[N+1][M+1];
		
		for(int i = 1; i <= N; i++)
			for(int j = 1; j <= M; j++)
				temp[i][j] = map[i][j]; // 배열 돌리기 전 map 따로 저장하기
			
		temp = rotateArr(temp); // 배열 돌리기
		
		int value = Integer.MAX_VALUE;
		for(int r = 1; r <= N; r++) {
			int sum = 0;
			for(int c = 1; c <= M; c++) 
				sum += temp[r][c]; // 배열 값 구하기
			value = Math.min(value, sum);
		}
		min = Math.min(value, min);
	}
	
	// 배열 돌리는 함수
	static int[][] rotateArr(int[][] temp){
		for(int i = 0; i < K; i++) {
			Pair cur = kList.get(kPIdx[i]);
			
			int sr = cur.r - cur.s;
			int sc = cur.c - cur.s;
			int er = cur.r + cur.s;
			int ec = cur.c + cur.s;
			int r = sr, c = sc;
			
			int curDir = 0, save = temp[r][c];
			while(true) {
				if(curDir == 4) { // 한바퀴 돌았으면 안쪽 으로 들어가기
					curDir = 0;
					r++; c++;
					sr++; sc++;
					er--; ec--;
					save = temp[r][c];
				}
				// 사이즈가 1이면 돌릴 필요 없으므로 종료
				if(sr == er && sc == ec) break; 
				
				int nr = r + dir[curDir][0];
				int nc = c + dir[curDir][1];
				
				// 한쪽 방향으로 끝까지 가서 범위를 벗어나면 방향 전환
				if(nr < sr || nc < sc || nr > er || nc > ec) {
					curDir++;
					continue;
				}				
				
				int save2 = temp[nr][nc];
				temp[nr][nc] = save;
				save = save2;
				
				r = nr; c = nc;
			}
		}
		return temp;
	}
	
	// 회전 연산 정보 저장 클래스
	static class Pair {
		int r, c, s;
		Pair(int r, int c, int s){
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
}
