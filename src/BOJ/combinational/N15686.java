package BOJ.combinational;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N15686 {
	static int min = Integer.MAX_VALUE, N, M;
	static boolean visit[];
	static List<Pair> chkSto = new ArrayList<>(), house = new ArrayList<>();
	static List<Integer>[] dis;
	
	public static void main(String[] args) throws IOException {
		
		// 치킨집을 조합으로 선정 후 바로바로 거리 계산
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if(num == 1)
						house.add(new Pair(i, j));
					else if(num == 2)
						chkSto.add(new Pair(i, j));
				}
			}
			visit = new boolean[chkSto.size()]; // 선택된 치킨집 저장
			
			choiceStore(-1, 0); // 치킨집 조합
			System.out.println(min);
		}
		
		// 집별 치킨집의 거리를 미리 측정 후 치킨집 조합으로 거리 계산
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if(num == 1)
						house.add(new Pair(i, j));
					else if(num == 2)
						chkSto.add(new Pair(i, j));
				}
			}
			
			visit = new boolean[chkSto.size()]; // 선택된 치킨집 저장
			dis = new ArrayList[chkSto.size()]; // 각 가게와 집 거리 미리 저장 변수
			for(int i = 0; i < chkSto.size(); i++)
				dis[i] = new ArrayList<>();
					
			preCalDis(); // 거리 미리 계산하는 함수
			choiceStore(-1, 0); // 치킨집 조합
			System.out.println(min);
		}
	}
	
	private static void choiceStore(int idx, int cnt) {
		if(cnt == M) {
			min = Math.min(min, calDis1()); // 치킨집 선택 완료 후 거리 계산
			return;
		}
		
		for(int i = idx + 1; i < chkSto.size(); i++) {
			visit[i] = true;
			choiceStore(i, cnt+1);
			visit[i] = false;
		}
	}
	
	private static int calDis1() {
		int sum = 0;		
		for(int h = 0; h < house.size(); h++) {			
			Pair home = house.get(h);
			int min = Integer.MAX_VALUE;
			for(int s = 0; s < chkSto.size(); s++) {
				if(!visit[s]) continue; // 선택된 치킨집이 아니면 패스
				Pair store = chkSto.get(s); // 선택된 치킨집 뽑아내기
				min = Math.min(min, (Math.abs(store.r - home.r) + Math.abs(store.c - home.c)));
			}
			sum += min;
		}	
		return sum;
	}
	
	private static void preCalDis() { // 집과 치킨집 미리 계산
		for(int s = 0; s < chkSto.size(); s++) {
			Pair store = chkSto.get(s);
			for(int h = 0; h < house.size(); h++) {
				Pair home = house.get(h);
				dis[s].add(Math.abs(store.r - home.r) + Math.abs(store.c - home.c));
			}
		}
	}
	
	private static int calDis2() {
		int sum = 0;
		for(int h = 0; h < house.size(); h++) {
			int minDis = Integer.MAX_VALUE;
			for(int s = 0; s < chkSto.size(); s++) {
				if(!visit[s]) continue; // 선택된 치킨집이 아니면 패스
				minDis = Math.min(minDis, dis[s].get(h)); // 미리 계산해 둔 집과 가장 가까운 치킨집 거리 저장
			}
			sum += minDis;
		}	
		return sum;
	}
	
	// 치킨집과 집의 위치 저장 클래스
	private static class Pair{
		int r, c;
		Pair(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}
