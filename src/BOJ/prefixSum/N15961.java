package BOJ.prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 0부터 n-1까지에 있는 초밥그릇부터 먹기 시작했을 때를 모두 계산
// k길이만큼을 유지하기 때문에 window sliding 알고리즘 이용

public class N15961 {
	static int n, d, k, c;
	static int[] chkDish;
	static int belt[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		belt = new int[n];
		chkDish = new int[d+1];
		
		for(int i = 0; i < n; i++)
			belt[i] = Integer.parseInt(br.readLine());
		
		// 처음에 0번 그릇부터 K개를 골랐을 때를 확인
		int ans = 0;
		for(int i = 0; i < k; i++) {
			if(chkDish[belt[i]] == 0) ans++;
			chkDish[belt[i]]++;
		}
		if(chkDish[c]++ == 0) ans++;
		
		// 1번부터 n-1번째 그릇부터 고르기 시작하며 빠지는 i그릇과 추가되는 i+k그릇을 체크함
		// 그에 따라 변경되는 값 확인
		int before = ans;
		for(int i = 0; i < n; i++) {
			if(--chkDish[belt[i]] == 0) before--;
			if(++chkDish[belt[(i+k) % n]] == 1) before++;
			if(chkDish[c]++ == 0) before++;
			
			ans = Math.max(before, ans);
		}
		System.out.println(ans);
	}
}
