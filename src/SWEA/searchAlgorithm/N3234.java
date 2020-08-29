package SWEA.searchAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 추를 올리는 순서와 해당 추를 어느쪽에 올리는지에 따라 나눌수 있음
// 추를 올리는 순서는 next permutation을 이용하여 순열 제작
// 왼쪽과 오른쪽에 한번씩 올려보며 가능하면 다음 추를 올리도록 제작

public class N3234 {
	static int N;
	static int []weight;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for(int test = 1; test <= testCase; test++) {
			N = Integer.parseInt(br.readLine());
			
			weight = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++)
				weight[i] = Integer.parseInt(st.nextToken());
			
			// 추 무게 오름차순으로 정렬하기
			Arrays.sort(weight);
			
			int ans = 0; // 측정방법 갯수 세는 변수
			do{
				ans += chkWeight(0,0,0); // 정해진 순열로 방법 세보기
			}while(nextPermutation()); // 다음 순열 만들기
			System.out.println("#" + test + " " + ans);
		}
	}

	private static int chkWeight(int left, int right, int idx) {
		if(idx == N) return 1; // 마지막 추까지 올렸으면 방법 한가지 추가
		
		int ret = 0; // 다음 단계에서 리턴되어온 값 저장
		if(left + weight[idx] >= right) // 왼쪽에 올릴 때 무게 확인
			ret += chkWeight(left + weight[idx], right, idx+1);
		
		if(left >= right + weight[idx]) // 오른쪽에 올릴 때 무게 확인
			ret += chkWeight(left, right + weight[idx], idx+1);
		
		return ret; // 방법 갯수 리턴
	}
	
	// 저울에 올릴 추 순서 next permutation으로 정하기
	private static boolean nextPermutation() {
		// 뒤에서 내림차순 끝나는 부분 찾기
		int i = N-1;
		while(i > 0 && weight[i-1] > weight[i]) i--;
		if(i == 0) return false; // 내림차순으로 정렬되어있으면 완료
		
		// 다음오로 바꿔야 하는 숫자 위치 찾기
		int j = N-1;
		while(weight[i-1] >= weight[j]) j--;
		
		// 숫자 swap 하기
		swap(i-1, j);
		
		// 교환한 숫자 뒷자리 오름차순으로 바꾸기
		j = N-1;
		while(i < j) swap(i++, j--);
			
		return true;
	}
	
	// swap함수
	private static void swap(int a, int b) {
		int temp = weight[a];
		weight[a] = weight[b];
		weight[b] = temp;
	}
}
