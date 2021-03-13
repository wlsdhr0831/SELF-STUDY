package BOJ.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N3967 {
	static char[][] map = new char[5][9], temp = new char[5][9];
	static boolean[] used = new boolean[12];
	static int[][] dir = {{1,-1}, {1,1}, {0,2}};
	static int[] dirNum = {0,1,2,1,0,2};
	static int[][] start = {{0,4}, {0,4}, {1,1}, {1,1}, {1,7}, {3,1}};
	static int[][] pos = {{0,4}, {1,1}, {1,3}, {1,5}, {1,7},
						{2,2}, {2,6}, {3,1}, {3,3}, {3,5}, {3,7}, {4, 4}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 12;
		for(int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < 9; j++) {
				if(map[i][j] >= 'A' && map[i][j] <= 'L') {
					used[map[i][j] - 'A'] = true;
					cnt--;
				}
			}
		}
		
		makeMagic(cnt, 0);
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	private static boolean makeMagic(int cnt, int idx) {
		if(cnt == 0) {
			for(int i = 0; i < 6; i++) {
				if(chkSum(i) != 26) return false;
			}
			return true;
		}
		
		if(idx == 5 && chkSum(2) != 26) return false;
		if(idx == 8 && chkSum(0) != 26) return false;
		if(idx == 11 && chkSum(1) != 26) return false;
		if(idx == 11 && chkSum(5) != 26) return false;
		
		int r = pos[idx][0], c = pos[idx][1];
		if(map[r][c] != 'x') {
			if(makeMagic(cnt, idx+1)) return true;
		}
		else {
			for(int i = 0; i < 12; i++) {
				if(used[i]) continue;
				
				used[i] = true;
				map[r][c] = (char) (i + 'A');
				if(makeMagic(cnt-1, idx+1)) return true;
				map[r][c] = 'x';
				used[i] = false;
			}
		}
		return false;
	}
	
	private static int chkSum(int idx) {
		int sum = 0;
		
		int r = start[idx][0];
		int c = start[idx][1];
		for(int i = 0; i <= 3; i++) {
			int nr = r + dir[dirNum[idx]][0] * i;
			int nc = c + dir[dirNum[idx]][1] * i;

			sum += (map[nr][nc] - 'A' + 1);
			if(sum > 26) return 0;
		}		
		return sum;
	}
}
