package BOJ.divideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 한칸인곳까지 내려가서 4개씩 합치면서 
// 같은 색이면 해당색만 출력
// 다른 색이면 괄호와 각각의 색 나열해서 출력

public class N1992 {
	static int N; // map 사이즈
	static char[][] map; // map
	static int[][] dir = {{0,0}, {0,1}, {1,0}, {1,1}}; // 분할구역 나누기
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N+1][N+1];
		for(int i = 0; i < N; i++) // map 입력
			map[i] = br.readLine().toCharArray();
		
		System.out.println(func(0,0,N)); // 정답 출력
	}
	
	private static String func(int r, int c, int size) { // 시작점의 행,열값과 사이즈
		if(size == 1) { // 사이즈가 1이면 한칸을 의미하므로 바로 한칸 값 리턴
			return Character.toString(map[r][c]);
		}
		
		String ret[] = new String[4]; // 방향별로 리턴되어 올 값 저장 변수
		boolean same = true; // 4곳이 하나의 색으로 되어있을 때 true
		for(int i = 0; i < 4; i++) { // 한방향씩 방문해보기
			ret[i] = func(r + (size/2)*dir[i][0], c + (size/2)*dir[i][1], size/2);
			if(ret[i].length() != 1) same = false; // 방문했던 곳의 색이 동일하지 않으면 하나의 색이 아니므로 false로 값 수정
		}
		
		if(same) { // 4곳이 한가지 색으로 되어있을 때 해당 색상들 비교
			for(int i = 1; i < 4; i++) {
				if(!ret[0].equals(ret[i])) { // 다른색이 있으면 same 값 변경
					same = false;
				}
			}
		}
		
		if(same) { // 한가지의 색이면 해당 색상 리턴
			return ret[0];
		}else { // 여러 색으로 되어있으면 괄호넣어서 출력하기
			return "(" + ret[0] + ret[1] + ret[2] + ret[3] + ")";	
		}		
	}
}
