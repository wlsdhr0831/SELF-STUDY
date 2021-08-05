package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {
    static int N, M, min;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        min = Integer.MAX_VALUE;
        map = new boolean[N][M];
        for(int h = 0; h < H; h++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a-1][b-1] = true;
        }

        if(checkLadge()) min = 0;
        else {
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M-1; j++){
                    if(map[i][j] || map[i][j+1]) continue;
                    if(j > 0 && map[i][j-1]) continue;

                    map[i][j] = true;
                    makeLadge(i, j+2, 1);
                    map[i][j] = false;
                }
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static boolean checkLadge(){
        System.out.println("현재 맵 상태");
        printMap();
        System.out.println("-----------------------");
        for(int idx = 0; idx < M; idx++){
            System.out.println(idx + " " + resultLine(idx));
            if(idx != resultLine(idx)) return false;
        }

        return true;
    }

    static void printMap(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                System.out.print(map[i][j] ? 1 + " " : 0 + " ");
            }
            System.out.println();
        }
    }

    static int resultLine(int c){
        int r = 0;
        while(r < N){
            if(map[r][c]){
                c++;
            }else if(c > 0 && map[r][c-1]){
                c--;
            }
            r++;
        }
        return c;
    }

    static void makeLadge(int curR, int curC, int cnt){
        //System.out.println(curR + " " + curC + " " + cnt);
        if(min <= cnt || checkLadge()) {
            min = Math.min(min, cnt);
            return ;
        }
        if(cnt >= 3) return ;

        int nextR = curR;
        int nextC = curC;
        if(nextC > M-2){
            nextC = 0;
            nextR++;
        }

        for(; nextR < N; nextR++){
            for(; nextC < M-1; nextC++){
                if(map[nextR][nextC] || map[nextR][nextC+1]) continue;
                if(nextC > 0 && map[nextR][nextC-1]) continue;


                map[nextR][nextC] = true;
                makeLadge(nextR, nextC+2, cnt+1);
                map[nextR][nextC] = false;
            }
            nextC = 0;
        }
    }
}
