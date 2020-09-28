package BOJ.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 메모리 초과 수정해야함

public class N2887 {
	static int N;
	static int root[];
	static Queue<Edge> edgeList = new PriorityQueue<>((e1, e2) -> {
		if(e1.w > e2.w) return 1;
		else if(e1.w == e2.w) return 0;
		else return -1;
	});
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int posX[] = new int[N];
		int posY[] = new int[N];
		int posZ[] = new int[N];
		root = new int[N];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			posX[i] = Integer.parseInt(st.nextToken());
			posY[i] = Integer.parseInt(st.nextToken());
			posZ[i] = Integer.parseInt(st.nextToken());
			root[i] = i;
		}
		
		for(int i = 0; i < N-1; i++) {
			for(int j = i+1; j < N; j++) {
				int dis = Math.min(Math.abs(posX[i] - posX[j]), Math.abs(posY[i] - posY[j]));
				dis = Math.min(dis, Math.abs(posZ[i] - posZ[j]));
				edgeList.add(new Edge(i, j, dis));
			}
		}
		System.out.println(kruskal());
	}
	
	static long kruskal() {
		long weightSum = 0;
		int edgeCnt = 0;
		while(edgeCnt < N-1) {
			Edge cur = edgeList.poll();
			if(!union(cur.s, cur.e)) continue;
			edgeCnt++;
			weightSum += cur.w;
		}
		return weightSum;
	}
	
	static boolean union(int a, int b) {
		int rA = find(a);
		int rB = find(b);
		
		if(rA == rB) return false;
		root[rB] = rA;
		return true;
	}
	
	static int find(int a) {
		if(root[a] != a) root[a] = find(root[a]);
		return root[a];
	}
	
	static class Edge{
		int s, e, w;
		Edge(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
}