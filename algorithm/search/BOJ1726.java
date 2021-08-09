package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1726 {
    static int N, M;
    static boolean[][] map;
    static boolean[][][] visit;
    static int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    static State start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        visit = new boolean[N][M][4];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++){
                int num = Integer.parseInt(st.nextToken());

                if(num == 1) map[i][j] = true;
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int sr = Integer.parseInt(st.nextToken());
        int se = Integer.parseInt(st.nextToken());
        int sd = Integer.parseInt(st.nextToken());
        sd = sd == 1 ? 0 : sd == 3 ? 1 : sd == 4 ? 3 : 2;

        st = new StringTokenizer(br.readLine(), " ");
        int er = Integer.parseInt(st.nextToken());
        int ee = Integer.parseInt(st.nextToken());
        int ed = Integer.parseInt(st.nextToken());
        ed = ed == 1 ? 0 : ed == 3 ? 1 : ed == 4 ? 3 : 2;

        start = new State(sr-1, se-1, sd);
        end = new State(er-1, ee-1, ed);

        System.out.println(bfs());
    }

    static int bfs(){
        int cnt = 0;
        boolean find = false;

        Queue<State> q = new LinkedList<>();
        q.add(new State(start.r, start.c, start.d));
        visit[start.r][start.c][start.d] = true;

        while(!q.isEmpty()){
            int size = q.size();
            for(int s = 0; s < size; s++){
                State cur = q.poll();
                if(cur.r == end.r && cur.c == end.c && cur.d == end.d){
                    find = true;
                    break;
                }

                for(int k = 1; k <= 3; k++){
                    int nr = cur.r + dir[cur.d][0]*k;
                    int nc = cur.c + dir[cur.d][1]*k;

                    if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc]) break;

                    if(!visit[nr][nc][cur.d]) {
                        q.add(new State(nr, nc, cur.d));
                        visit[nr][nc][cur.d] = true;
                    }
                }

                if(!visit[cur.r][cur.c][(cur.d+1)%4]){
                    q.add(new State(cur.r, cur.c, (cur.d+1)%4));
                    visit[cur.r][cur.c][(cur.d+1)%4] = true;
                }

                if(!visit[cur.r][cur.c][(cur.d+3)%4]){
                    q.add(new State(cur.r, cur.c, (cur.d+3)%4));
                    visit[cur.r][cur.c][(cur.d+3)%4] = true;
                }
            }
            if(find) break;
            cnt++;
        }
        return cnt;
    }

    static class State {
        int r, c, d;
        State(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}