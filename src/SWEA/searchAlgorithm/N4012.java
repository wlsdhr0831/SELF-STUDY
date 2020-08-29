package SWEA.searchAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 요리재료들을 조합으로 N/2개를 뽑아 A와 B재료들로 각각 나누어 시너지를 계산
// 조합할 때 next permutation을 사용
// combination 배열에 뒷부분 절반을 선택하는 식으로 초기화

public class N4012 {
	static int combination[];
	static int N, ans = Integer.MAX_VALUE;
	static int taste[][];
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for(int test = 1; test <= testCase; test++) {
			N = Integer.parseInt(br.readLine());
			taste = new int[N][N];
			
			StringTokenizer st;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					taste[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// next permutation을 사용하기 위한 배열 초기화
			combination = new int[N];
			for(int i = N-1; i >= N/2; i--)
				combination[i] = 1;
			
			int ans = Integer.MAX_VALUE;
			do {
				int[] a = new int[N/2];
				int[] b = new int[N/2];
				int aCnt = 0, bCnt = 0;
				
				// a요리와 b요리로 재료 나누기
				for(int i = 0; i < N; i++) {
					if(combination[i] == 1) {
						a[aCnt++] = i;		
					}
					else {
						b[bCnt++] = i;					
					}
				}
				
				// 각 재료 사이의 시너지 계산하기
				int sumA = 0, sumB = 0;
				for(int i = 0; i < N/2 - 1; i++) {
					for(int j = i+1; j < N/2; j++) {
						sumA += (taste[a[i]][a[j]] + taste[a[j]][a[i]]);
						sumB += (taste[b[i]][b[j]] + taste[b[j]][b[i]]);
					}
				}
				ans = Math.min(ans, Math.abs(sumA - sumB));	// 현재 시너치 차이 계산			
			}while (nextPermutation()); // 내림차순으로 정렬되면 종료
			System.out.println("#" + test + " " + ans);
		}
	}
	
	private static boolean nextPermutation() {
		int i = N-1; // 뒤에서 내림차순이 종료되는 위치 찾기
		while(i > 0 && combination[i-1] >= combination[i]) i--;
		if(i == 0) return false; // 내림차순으로 되어있으면 종료
		
		// 다음으로 교환할 숫자 위치 찾기
		int j = N-1;
		while(combination[i-1] >= combination[j]) j--;
		
		// 다음 숫자로 교환
		swap(i-1, j);
		
		// 교환한 숫자 뒷부분을 오름차순으로 제작
		j = N-1;
		while(i < j) swap(i++, j--);
		
		return true;
	}

	// swap 함수
	private static void swap(int i, int j) {
		int temp = combination[i];
		combination[i] = combination[j];
		combination[j] = temp;
	}
}
