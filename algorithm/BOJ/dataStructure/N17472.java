package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 1. 섬들의 번호를 먼저 정한다. 섬들의 갯수 파악
// 2. 섬들간의 가장 짧은 거리를 dis 배열에 저장한다.(거리가 2이상일 떄만)
// 3. dis 배열을 가지고 prim 알고리즘을 이용하여 MST를 구한다.
// 4. 섬들이 다 연결되지 않았는데 q가 비면 다리를 설치해서 섬을 이을 수 없는 경우이다.

public class N17472 {
	static int N, M;
	static int map[][], dis[][], dir[][] = {{1,0}, {0,1}, {-1,0}, {0,-1}};
	static int islandCnt = 0;
	static boolean visit[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 갯수 세고 섬에 번호로 수정하기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0 || visit[i][j]) continue; // 바다이거나 다른 섬에 속해있는 땅이면 패스
				islandCnt++; // 섬 갯수 증가
				cntIsland(i, j); // 해당 섬 땅 수정하기
			}
		}
		dis = new int[islandCnt+1][islandCnt+1]; // 섬끼리의 거리 저장
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) continue; // 바다면 패스
				
				for(int d = 0; d < 4; d++) // 방향별로 최저 다리 길이 체크
					chkLength(i,j,d);
				
			}
		}
		System.out.println(prim()); // MST 제작으로 최저 비용 출력
	}
	
	static int prim() {
		// 가중치합, 선택한 노드 갯수
		int weightSum = 0, nodeCnt = 0;
		boolean select[] = new boolean[islandCnt + 1]; // 연결한 섬 체크
		
		Queue<Pair> q = new PriorityQueue<>((o1, o2) -> {return o1.b - o2.b;});
		q.add(new Pair(1,0)); // 1번섬을 시작으로 확인
		while(nodeCnt < islandCnt) { // 모든 노드가 선택되지 않으면
			Pair cur = q.poll();
			if(cur == null) { // 큐가 비었다는 의미이므로
				weightSum = -1; // 모든 섬이 연결될 수 없다는 경우의 출력값
				break;
			}
			if(select[cur.a]) continue; // 이미 선택한 섬이면 패스
			select[cur.a] = true;
			weightSum += cur.b;
			nodeCnt++;
			
			for(int i = 1; i <= islandCnt; i++) { // 방금 선택한 섬과 연결된 간선 추가
				if(select[i] || dis[cur.a][i] == 0) continue; // 이미 선택한 섬이거나 다리를 설치하지 못하면 패스
				q.add(new Pair(i, dis[cur.a][i]));
			}
		}
		return weightSum; // 최저 가중치 합 출력
	}
	
	static void chkLength(int r, int c, int d) {
		int length = 0, island = map[r][c], nr = r, nc = c;
		
		while(true) {
			// 확인할 다음 위치
			nr += dir[d][0];
			nc += dir[d][1];
			
			if(nr < 0 || nc < 0 || nr >= N || nc >= M) return ; // 범위 밖이면 패스
			
			if(map[nr][nc] != 0) break;	// 다른 섬을 만나면 종료
			length++; // 아직 바다면 길이 증가
		}
		
		// 길이가 2이상이고 dis가 초기화 값이거나 현재 값보다 작은 길이를 찾으면 값 수정
		if(length >= 2  && (dis[island][map[nr][nc]] == 0 || dis[island][map[nr][nc]] > length)) {
			dis[island][map[nr][nc]] = length;
			dis[map[nr][nc]][island] = length;
		}
	}
	
	// bfs로 해당 섬 지역 파악
	static void cntIsland(int r, int c) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(r, c));
		visit[r][c] = true;
		map[r][c] = islandCnt;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			for(int i = 0; i < 4; i++) {
				int nr = cur.a + dir[i][0];
				int nc = cur.b + dir[i][1];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 0 || visit[nr][nc])
					continue;
				
				visit[nr][nc] = true;
				map[nr][nc] = islandCnt;
				q.add(new Pair(nr, nc));
			}
		}
	}
	
	static class Pair{
		int a, b;
		Pair(int a, int b){
			this.a = a;
			this.b = b;
		}
	}
}
