package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N10451 {
	static int root[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for(int test = 0; test < testCase; test++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			root = new int[N+1];
			for(int i = 1; i <= N; i++) root[i] = i;
			
			for(int i = 1; i <= N; i++) {
				int num = Integer.parseInt(st.nextToken());
				union(i, num);
			}
			
			int cnt = 0;
			for(int i = 1; i <= N; i++) {
				if(i == root[i]) cnt++;
			}
			System.out.println(cnt);
		}
	}
	
	private static int find(int a) {
		if(root[a] != a) root[a] = find(root[a]);
		return root[a];
	}
	
	private static boolean union(int a, int b) {
		int rA = find(a);
		int rB = find(b);
		
		if(rA == rB) return false;
		root[rB] = find(rA);		
		return true;
	}
}
