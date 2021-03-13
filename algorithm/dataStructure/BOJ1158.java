package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 큐를 이용하여 조건에 맞으면 출력하고, 맞지 않으면 다시 추가하였다.

public class N1158 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i <= N; i++) // 큐 초기화 시키기
			q.add(i);
		
		int ans[] = new int[N]; // 정답을 저장할 배열
		int cnt = 0, idx = 0; // 배출된 갯수와 k를 체크하는 배열
		
		while(!q.isEmpty()) { // 큐가 빌때 까지
			if(idx == K-1) // k번째이면 정답배열에 추가하기
				ans[cnt++] = q.poll();
			else q.add(q.poll()); // k번째 아니면 다시 큐에 넣기			
			idx = (idx+1) % K; // k번째 체크하는 변수
		}
		
		// 출력양식  지키기
		System.out.print("<");
		for(int i = 0; i < N-1; i++)
			System.out.print(ans[i] + ", ");
		System.out.println(ans[N-1] + ">");
	}
}
