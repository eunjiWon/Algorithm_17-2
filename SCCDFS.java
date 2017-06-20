package graph;

import java.util.*;

public class SCCDFS {

	// DFS function
	public void DFS(List<Integer>[] matrix, int node, boolean[] visited, List<Integer> stack){
		visited[node] = true;
		for(int v : matrix[node]){
			if(!visited[v])
				DFS(matrix, v, visited, stack);
		}
		stack.add(node);
	}
	
	// order이라는 List를 사용하여 matrix의 방문 순서 찾는 function
	public List<Integer> Order(List<Integer>[] matrix, boolean[] visited){
		int size = matrix.length;
		List<Integer> order = new ArrayList<>();
		for(int i = 0; i < size; i++){
			if(!visited[i])
				DFS(matrix, i, visited, order);
		}
		// order 를 프린트
		System.out.println("order : " + order);
		return order;
	}

	// transpose된 graph를 찾기 위해 matrix의 행과 열을 바꿔주는 function
	public List<Integer>[] TransposeMatrix(List<Integer>[] matrix){
		int size = matrix.length;
		List<Integer>[] Tmatrix = new List[size];
		for(int i = 0; i < size; i++){
			Tmatrix[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < size; i++){
			for(int j : matrix[i])
				Tmatrix[j].add(i);
		}
	
		return Tmatrix;
	}
	
	// transpose된 matrix를 역순으로 DFS를 호출하여 SCC를 얻는 function
	public List<List<Integer>> getSCC(List<Integer>[] matrix){
		int size = matrix.length;
		boolean[] visited = new boolean[size];
		List<Integer> order =  Order(matrix, visited);
		List<Integer>[] Tmatrix = TransposeMatrix(matrix);
		Arrays.fill(visited, false);
		Collections.reverse(order);
		// reverse된 order 를 프린트
		System.out.println("reverse order : " + order);
		List<List<Integer>> sccStack = new ArrayList<>();
		for (int o : order){
			if(!visited[o]){
				List<Integer> stack = new ArrayList<>();
				DFS(Tmatrix, o, visited, stack);
				sccStack.add(stack);
			}
		}
		return sccStack;
	} 
	
	
	public static void main(String[] args){
		SCCDFS sccdfs = new SCCDFS();
		// 8 * 8 행렬
		List<Integer>[] matrix = new List[8];
		for(int i = 0; i < matrix.length; i++){
			matrix[i] = new ArrayList<Integer>();
		}
		
		// graph를  matrix로 변환
		matrix[0].add(1);
		matrix[1].add(2);
		matrix[1].add(4);
		matrix[1].add(5);
		matrix[2].add(3);
		matrix[2].add(6);
		matrix[3].add(2);
		matrix[3].add(7);
		matrix[4].add(0);
		matrix[4].add(5);
		matrix[5].add(6);
		matrix[6].add(5);
		matrix[6].add(7);
		matrix[7].add(7);
		
		List<List<Integer>> scc = sccdfs.getSCC(matrix);
		List<Integer>[] Tmatrix = sccdfs.TransposeMatrix(matrix);
		// matrix를 프린트
		System.out.println("Matrix : " + matrix[0] +  matrix[1] + matrix[2]+ matrix[3]+ matrix[4]+ matrix[5]+ matrix[6]+ matrix[7]);
		// transpose된 matrix를 프린트
		System.out.println("Transposed Matrix : " + Tmatrix[0] +  Tmatrix[1] + Tmatrix[2]+ Tmatrix[3]+ Tmatrix[4]+ Tmatrix[5]+ Tmatrix[6]+ Tmatrix[7]);
		// strongly connected component를 출력
		System.out.println("Strongly Connected Components : " + scc);
		
		
	}
		
}
