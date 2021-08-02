package BOJ.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ11725 {
    static int[] root;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        root = new int[N+1];
        graph = new LinkedList[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new LinkedList<>();
        }

        for(int i = 1; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, 1);

        for(int i = 2; i <= N; i++){
            System.out.println(root[i]);
        }
    }

    public static void dfs(int cur, int parent){
        root[cur] = parent;

        for(int child: graph[cur]){
            if(root[child] != 0) continue;
            dfs(child, cur);
        }
    }
}
