package SWEA.searchAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간을 줄이기 위해 preCalDis()로 미리 거리 계산을 저장
// 재귀로 순열을 구해봄
// 이후 nextPermutation을 이용해서 순열을 구하니까 시간 확실히 줄음

public class N1247 {
	static int ans;
	static int N;
	static Pair[] Pos;
	static int HouseList[];
	static boolean visit[];
	static int dis[][];
	
	public static void main(String[] args) throws IOException {
		
		// 재귀로 순열 구현
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int testCase = Integer.parseInt(br.readLine());
			for(int test = 1; test <= testCase; test++) {
				N = Integer.parseInt(br.readLine());
				
				ans = Integer.MAX_VALUE;
				Pos = new Pair[N+2];
				HouseList = new int[N];
				visit = new boolean[N+2];
				dis = new int[N+2][N+2];
				
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				Pos[0] = new Pair(r, c); // 시작지점은 회사로
				r = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				Pos[N+1] = new Pair(r, c); // 도착지점은 집으로
				
				// 고객들 집 위치 입력받기
				for(int i = 1; i <= N; i++) {
					r = Integer.parseInt(st.nextToken());
					c = Integer.parseInt(st.nextToken());
					Pos[i] = new Pair(r, c);
				}
				
				preCalDis(); // 거리 계산 미리 하기
				lineUpHouse(0); // 방문할 고객집 순서 정하기
				System.out.println("#" + test + " " + ans);
			}
		}
		
		// next permutation 으로 순열 구현
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int testCase = Integer.parseInt(br.readLine());
			for(int test = 1; test <= testCase; test++) {
				N = Integer.parseInt(br.readLine());
				
				ans = Integer.MAX_VALUE;
				Pos = new Pair[N+2];
				HouseList = new int[N];
				visit = new boolean[N+2];
				dis = new int[N+2][N+2];
				
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				Pos[0] = new Pair(r, c); // 시작지점은 회사로
				r = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				Pos[N+1] = new Pair(r, c); // 도착지점은 집으로
				
				// 고객들 집 위치 입력받기
				for(int i = 1; i <= N; i++) {
					r = Integer.parseInt(st.nextToken());
					c = Integer.parseInt(st.nextToken());
					Pos[i] = new Pair(r, c);
				}
				
				preCalDis(); // 거리 계산 미리 하기
				
				for(int i = 0; i < N; i++) // 오름차순으로 초기화 시키기
					HouseList[i] = i+1;
				
				do {
					// 현재 순서대로 거리값 계산해서 최솟값과 비교
					ans = Math.min(ans, calDis()); 
				}while(nextPermutation()); // 전체가 내림차순이 되면 종료
				
				System.out.println("#" + test + " " + ans);
			}
		}
	}

	private static boolean nextPermutation() {
		int i = N-1; // 마지막 index 가르키기
		// 뒤에서부터 연속적으로 내림차순이 끝나는 곳 찾기
		while(i > 0 && HouseList[i-1] >= HouseList[i]) i--; 
		if(i == 0) return false; // 내림차순으로 정렬되어있으면 종료
		
		int j = N-1;
		// 다음으로 바뀌어야 하는 숫자의 자리 찾기
		while(HouseList[i-1] >= HouseList[j]) j--;
		
		// 다음으로 와야하는 수와 자리 교환
		swap(i-1, j);
		
		// 맨뒤로 index 바꾸기
		j = N-1;
		// 내림차순으로 되어있는 것 오름차순으로 수정하기
		while(i < j) swap(i++, j--);
		
		return true;
	}
	
	private static void swap(int a, int b) {
		int temp = HouseList[a];
		HouseList[a] = HouseList[b];
		HouseList[b] = temp;
	}

	// 방문할 고객집 순서 순열을 재귀로 구현
	private static void lineUpHouse(int cnt) {
		if(cnt == N) { // 마지막 순서까지 정해지면
			ans = Math.min(ans, calDis()); // 순서대로 거리 구한것이 최저값인지 비교
			return ;
		}
		
		for(int i = 1; i <= N; i++) {
			if(visit[i]) continue;
			
			visit[i] = true;
			HouseList[cnt] = i;
			lineUpHouse(cnt+1);
			visit[i] = false;
		}
	}

	// 방문 순서 정해진 대로 회사 - 고객들 집 - 집 순으로 거리 더해서 return
	private static int calDis() {
		int sum = dis[0][HouseList[0]];
		for(int i = 1; i < N; i++) {
			sum += dis[HouseList[i-1]][HouseList[i]];
		}
		sum += dis[HouseList[N-1]][N+1];
		return sum;
	}

	// 장소에서 다른 장소로 가는데 걸리는 이동거리 미리 계산
	// 미리 계산함으로써 중복계산하게 되는 시간을 줄임
	private static void preCalDis() {
		for(int i = 0; i <= N; i++) {
			Pair a = Pos[i];
			for(int j = i+1; j <= N+1; j++) {
				Pair b = Pos[j];
				dis[i][j] = Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
				dis[j][i] = dis[i][j]; // a에서 b, b에서 a 거리는 동일
			}
		}
	}
	
	// 위치 저장 클래스
	private static class Pair{
		int r, c;
		Pair(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}
