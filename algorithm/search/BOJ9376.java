package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9376 {
    static char[][] map;
    static int[][][] visit;
    static int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    static int N, M;
    static int[][] people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for(int t = 0; t < testCase; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N+2][M+2];
            visit = new int[3][N+2][M+2];
            people = new int[2][2];
            int peopleCnt = 0;

            for(int i = 0; i < 3; i++){
                for(int j = 0; j <= N+1; j++){
                    Arrays.fill(visit[i][j], -1);
                }
            }

            for (int i = 1; i <= N; i++) {
                String line = br.readLine();
                for(int j = 1; j <= M; j++) {
                    map[i][j] = line.charAt(j - 1);
                    if (map[i][j] == '$') {
                        people[peopleCnt][0] = i;
                        people[peopleCnt++][1] = j;
                    }
                }
            }

            bfs(0, 0, 0);
            bfs(1, people[0][0], people[0][1]);
            bfs(2, people[1][0], people[1][1]);

            int min = Integer.MAX_VALUE;

            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= M; j++){
                    int sum = 0;

                    for(int k = 0; k < 3; k++){
                        sum += visit[k][i][j];
                    }

                    if(map[i][j] == '#') sum -= 2;

                    if(sum != -3) min = Math.min(min, sum);
                }
            }
            System.out.println(min);
        }
    }

    public static int bfs(int type, int r, int c){
        Queue<State> q = new LinkedList<>();
        q.add(new State(r, c, 0));

        while(!q.isEmpty()){
            State cur = q.poll();

            for(int d = 0; d < 4; d++){
                int nr = cur.r + dir[d][0];
                int nc = cur.c + dir[d][1];

                if(nr < 0 || nc < 0 || nr > N+1 || nc > M+1
                        || map[nr][nc] == '*') continue;

                int nextCnt = cur.cnt + (map[nr][nc] == '#' ? 1 : 0);

                if(visit[type][nr][nc] != -1 && visit[type][nr][nc] <= nextCnt) continue;
                visit[type][nr][nc] = nextCnt;

                q.add(new State(nr, nc, nextCnt));
            }
        }
        return Integer.MAX_VALUE;
    }

    public static class State{
        int r, c, cnt;
        State(int r, int c, int cnt){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}