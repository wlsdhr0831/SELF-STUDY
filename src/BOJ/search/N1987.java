package BOJ.search;

import java.util.Scanner;
import java.io.IOException; 

// dfs로 같은 알파벳을 발견하면 현재 위치 값 비교

public class N1987 { 
	static int R;
	static int C;
	static int [][]dir = {{1,0}, {0,1}, {-1,0}, {0,-1}}; // 탐색 방향
	
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        R = sc.nextInt();
        C = sc.nextInt();
        
        char [][]board = new char[R][C];
        boolean []chk = new boolean [26];
        
        for(int i = 0; i < R; i++) {
        	String str = sc.next();
        	board[i] = str.toCharArray();
        }
        
        chk[board[0][0] - 'A'] = true; // 시작위치 visit 체크하기
        
        System.out.println(back(0, 0, board, chk));
                
    	sc.close();
    }
    
    public static int back(int r, int c, char[][]board, boolean[]chk) {
    	int max = 0;
    	for(int i = 0; i < 4; i++) {
    		int nextR = r + dir[i][0];
    		int nextC = c + dir[i][1];
    		
    		if(nextR < 0 || nextC < 0 || nextR >= R || nextC >= C || chk[board[nextR][nextC] - 'A']) continue;
    		
    		chk[board[nextR][nextC] - 'A'] = true; // 현재 위치 방문 체크
    		int ret = back(nextR, nextC, board, chk); // 다음 위치로 이동
    		chk[board[nextR][nextC] - 'A'] = false; // 현재 위치 방문 해제
    		
    		if(max < ret) max = ret; // 값 비교
    	}
    	return max + 1; // 현시점에서 갈 수 있는 다음 위치 방문 후 계산해서 온 값 + 현재위치 추가
    }
}