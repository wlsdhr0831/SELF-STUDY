package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1525 {
    static int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    static HashMap<String, Integer> visit = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String map = "";
        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 3; j++){
                map += st.nextToken();
            }
        }

        int answer = 0;
        if(!map.equals("123456780")){
            answer = bfs(map);
        }

        System.out.println(answer);
    }

    static int bfs(String map){
        visit.put(map, 1);

        Queue<String> q = new LinkedList<>();
        q.add(map);

        int cnt = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int s = 0; s < size; s++){
                String cur = q.poll();
                int idx = cur.indexOf("0");
                int cR = idx / 3;
                int cC = idx % 3;

                for(int d = 0; d < 4; d++){
                    int nR = cR + dir[d][0];
                    int nC = cC + dir[d][1];

                    if(nR < 0 || nR > 2 || nC < 0 || nC > 2) continue;

                    int nextIdx = nR * 3 + nC;

                    String nextMap = String.copyValueOf(cur.toCharArray());

                    nextMap = nextMap.substring(0, idx)
                            + Character.toString(nextMap.charAt(nextIdx))
                            + nextMap.substring(idx+1);

                    nextMap = nextMap.substring(0, nextIdx)
                            + "0"
                            + nextMap.substring(nextIdx+1);

                    if(visit.containsKey(nextMap)) continue;

                    if(nextMap.equals("123456780")) return cnt;

                    q.add(nextMap);
                    visit.put(nextMap, visit.get(cur));
                }
            }
            cnt++;
        }
        return -1;
    }
}
