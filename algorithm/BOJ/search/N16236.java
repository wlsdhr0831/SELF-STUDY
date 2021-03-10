package BOJ.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 아기상어 위치를 확인하고 가장 가까운 물고기를 bfs로 찾아서 먹음

public class N16236 {
	static int N, time, findingTime, eatCnt, babySharkSize;
	static Pos babyShark;
	static int map[][], dir[][] = {{-1,0}, {0,-1}, {0,1}, {1,0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) { // 아기 상어 초기 위치 저장
					map[i][j] = 0;
					babyShark = new Pos(i, j);
				}
			}
		}
		
		time = 0; findingTime = 0; eatCnt = 0; babySharkSize = 2;
		while(true) {
			Pos nextFish = findFish();
			if(nextFish == null) break; // 더 먹을 수 있는 물고기 없으면 종료
			
			time += findingTime; // 물고기 찾는데 걸린 경과 시간 더하기
			findingTime = 0; // 시간 초기화
			eatFish(nextFish); // 해당 물고기 먹기
		}
		System.out.println(time); // 경과시간 출력
	}

	static Pos findFish() {
		boolean visit[][] = new boolean[N][N]; // 물고기 확인 한 지역인지 저장
		Queue<Pos> q = new LinkedList<>(); // 물고기 확인할 다음 위치 저장
		q.add(babyShark);
		visit[babyShark.r][babyShark.c] = true;
		
		while(!q.isEmpty()) {
			int size = q.size(); // 같은 거리대에 돌아다닐 장소 갯수
			findingTime++; // 경과시간 증가
			List<Pos> fishList = new ArrayList<>(); // 만난 물고기위치 저장
			for(int i = 0; i < size; i++) {
				Pos cur = q.poll();
				for(int d = 0; d < 4; d++) {
					int nr = cur.r + dir[d][0];
					int nc = cur.c + dir[d][1];
					
					// 범위 밖이거나 물고기 사이즈가 아기상어보다 크면 패스
					if(nr < 0 || nc < 0 || nr >= N || nc >= N 
							|| map[nr][nc] > babySharkSize || visit[nr][nc]) continue;
					
					// 물고기가 존재하고 아기상어보다 작으면 먹음
					if(map[nr][nc] != 0 && map[nr][nc] < babySharkSize) {
						fishList.add(new Pos(nr, nc));
						continue;
					}
					visit[nr][nc] = true;
					q.add(new Pos(nr, nc));
				}
			}
			if(fishList.size() > 0) { // 먹을 수 있는 물고기가 1이상 이면
				Collections.sort(fishList); // 우선순위에 맞춰서 정렬
				return fishList.get(0); // 하나 먹기
			}
		}
		return null; // 못 만나면 null 리턴
	}
	
	static void eatFish(Pos nextFish) { // 먹을 물고기 없애고 아기상어 위치 변경
		map[nextFish.r][nextFish.c] = 0;
		babyShark.r = nextFish.r;
		babyShark.c = nextFish.c;
		if(++eatCnt == babySharkSize) { // 먹은 물고기 수가 아기 상어 사이즈와 동일하면 사이즈 업
			babySharkSize++;
			eatCnt = 0;
		}
	}
	
	static class Pos implements Comparable<Pos>{
		int r, c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int compareTo(Pos o) {
			if(this.r == o.r)return this.c - o.c;
			else return this.r - o.r;
		}
	}
}
