package BOJ.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N16953 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		long s = Long.parseLong(st.nextToken());
		long e = Long.parseLong(st.nextToken());
		
		int dir[][] = {{2,0}, {10,1}};
		
		Queue<Long> q = new LinkedList<>();
		q.add(s);
		
		int cnt = 1;
		while(!q.isEmpty()) {
			int size = q.size();
			cnt++;
			for(int i = 0; i < size; i++) {
				long cur = q.poll();
				for(int j = 0; j < 2; j++) {
					long next = cur*dir[j][0] + dir[j][1];
					if(next > e) continue;
					
					if(next == e) {
						System.out.println(cnt);
						return ;
					}
					q.add(next);
				}
			}
		}
		System.out.println("-1");
	}
}
