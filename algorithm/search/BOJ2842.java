package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2842 {
    static int N, houseCnt;
    static char[][] map;
    static int[][] height;
    static int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
    static List<Integer> h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        height = new int[N][N];
        houseCnt = 0;
        h = new LinkedList<>();

        int r = -1, c = -1;

        for(int i = 0; i < N; i++){
            char[] line = br.readLine().toCharArray();
            for(int j = 0; j < N; j++){
                map[i][j] = line[j];

                if(map[i][j] == 'K') houseCnt++;
                else if(map[i][j] == 'P'){
                    r = i;
                    c = j;
                }
            }
        }

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++){
                height[i][j] = Integer.parseInt(st.nextToken());
                h.add(height[i][j]);
            }
        }
        Collections.sort(h);

        System.out.println(solve(r, c));
    }

    public static int solve(int r, int c){
        int minIdx = 0;
        int maxIdx = 0;
        int answer = Integer.MAX_VALUE;

        while(minIdx < N*N && maxIdx < N*N){
            if(bfs(r, c, h.get(minIdx), h.get(maxIdx))) {
                answer = Math.min(answer, h.get(maxIdx) - h.get(minIdx));
                minIdx++;
            }else{
                maxIdx++;
            }
        }
        return answer;
    }

    public static boolean bfs(int r, int c, int min, int max){
        if(height[r][c] < min || height[r][c] > max) return false;

        boolean[][] visit = new boolean[N][N];
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(r, c));
        visit[r][c] = true;

        int cnt = 0;
        while(!q.isEmpty()){
            Pos cur = q.poll();
            for(int d = 0; d < 8; d++){
                int nr = cur.r + dir[d][0];
                int nc = cur.c + dir[d][1];

                if(nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc]
                    || height[nr][nc] < min || height[nr][nc] > max) continue;

                q.add(new Pos(nr, nc));
                visit[nr][nc] = true;

                if(map[nr][nc] == 'K') cnt++;

                if(cnt == houseCnt) return true;
            }
        }
        return false;
    }

    static class Pos{
        int r, c;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}
