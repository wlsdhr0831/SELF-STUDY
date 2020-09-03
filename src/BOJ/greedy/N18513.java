package BOJ.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 집을 지은 곳의 위치와 샘터와의 거리, 방향을 저장한 Pair 객체를
// queue에 추가하여 다음에 집을 지을 수 있는지 확인한다.

public class N18513 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int size = 201000000; // 집을 지을 최대 범위
		
		Queue<Pair> q = new LinkedList<>();
		int water[] = new int[N];
		boolean visit[] = new boolean[size+1];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			water[i] = Integer.parseInt(st.nextToken()) + size/2;
			visit[water[i]] = true;
			q.add(new Pair(water[i], 1, 1)); // +방향으로 거리 1 확인하기
			q.add(new Pair(water[i], -1, 1)); // -방향으로 거리 1 확인하기
		}
		
		int cnt = 0; // 집을 지은 횟수
		long sum = 0; // 집은 지은 곳들의 샘터와의 거리 합
		while (cnt < K) {
			Pair cur = q.poll();
			if(!visit[cur.pos + cur.dir]) { // 집을 지은 곳이 아니라면
				visit[cur.pos + cur.dir] = true; // 집을 짓고
				cnt++; // 집 갯수 증가
				sum += cur.dis; // 집과 샘터와의 거리 추가
				q.add(new Pair(cur.pos + cur.dir, cur.dir, cur.dis+1)); // 같은 방향에 거리 1추가해서 확인해보기
			}
		}
		System.out.println(sum);
	}
	
	static class Pair{
		int pos, dir, dis;
		Pair(int pos, int dir, int dis){
			this.pos = pos;
			this.dir = dir;
			this.dis = dis;
		}
	}
}