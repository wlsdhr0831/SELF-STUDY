package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9328 {
    static int N, M, keyCnt;
    static char[][] map;
    static boolean[] key;
    static int[][] visit;
    static int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int t = 0; t < testCase; t++){
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N+2][M+2];
            visit = new int[N+2][M+2];
            key = new boolean[26];
            keyCnt = 0;

            for(int i = 0; i <= N+1; i++){
                Arrays.fill(map[i], '.');
                Arrays.fill(visit[i], -1);
            }

            for(int i = 1; i <= N; i++){
                char[] temp = br.readLine().toCharArray();
                for(int j = 1; j <= M; j++){
                    map[i][j] = temp[j-1];
                }
            }

            char[] keys = br.readLine().toCharArray();
            if(keys[0] != '0'){
                keyCnt = keys.length;
                for(char k: keys){
                    key[k-'a'] = true;
                }
            }

            System.out.println(bfs());
        }
    }

    static int bfs(){
        int docs = 0;

        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0));
        visit[0][0] = keyCnt;

        while(!q.isEmpty()){
            Pos cur = q.poll();

            for(int d = 0; d < 4; d++){
                int nr = cur.r + dir[d][0];
                int nc = cur.c + dir[d][1];

                if(nr < 0 || nc < 0 || nr > N+1 || nc > M+1
                        || map[nr][nc] == '*' || visit[nr][nc] >= keyCnt) continue;

                if(map[nr][nc] == '$'){
                    docs++;
                    map[nr][nc] = '.';
                    q.add(new Pos(nr, nc));
                }else if(map[nr][nc] >= 'a' && map[nr][nc] <= 'z'){
                    key[map[nr][nc] - 'a'] = true;
                    map[nr][nc] = '.';
                    q.add(new Pos(nr, nc));
                    keyCnt++;
                }else if(map[nr][nc] >= 'A' && map[nr][nc] <= 'Z') {
                    if(key[map[nr][nc] - 'A']){
                        map[nr][nc] = '.';
                        q.add(new Pos(nr, nc));
                    }else{
                        q.add(new Pos(cur.r, cur.c));
                    }
                }else{
                    q.add(new Pos(nr, nc));
                }
                visit[nr][nc] = keyCnt;
            }
        }

        return docs;
    }

    static class Pos{
        int r, c;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}
