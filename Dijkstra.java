import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Dijkstra {

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA,       sB, sC: speeds for 3 contestants
	 * @throws FileNotFoundException
	 */
	
	boolean failed = false;
	String filename;
	int numOfStreets;
	int numOfIntersections;
	int speedA;
	int speedB;
	int speedC;
	EdgeWeightedDigraph graph;

	Dijkstra(String filename)  {
		String temp;
		String[] temps;
		this.filename = filename;
		//if (filename == null) {
		//	graph = null;
		//	return;
		//} else {
			try {
				File file = new File(filename);
				Scanner scan = new Scanner(file);
				numOfIntersections = Integer.parseInt(scan.nextLine());
				numOfStreets = Integer.parseInt(scan.nextLine());
				graph = new EdgeWeightedDigraph(numOfStreets, numOfIntersections);
				while (scan.hasNextLine()) {
					temp = scan.nextLine();
					temps = temp.trim().split("\\s+");
					int from = Integer.parseInt(temps[0]);
					int to = Integer.parseInt(temps[1]);
					double cost = Double.parseDouble(temps[2]);
					List<DirectedEdge> list = graph.adj.getOrDefault(from, new ArrayList<>());
					list.add(new DirectedEdge(from, to, cost));
					graph.adj.put(from, list);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				graph = null;
				failed = true;
			}
		}

	

	/**
	 * @return int: minimum minutes that will pass before the three contestants can
	 *         meet
	 * @throws FileNotFoundException
	 */
	

	// Return shortest path for contestant.

	public double[] Dijkstra(int src) {
		boolean[] visited = new boolean[200000];
		double[] dist = new double[200000];
		PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(vertex -> dist[vertex]));
		Map<Integer, List<DirectedEdge>> adj = graph.adj;

		for (int i = 0; i < dist.length; i++) {
			dist[i] = Double.POSITIVE_INFINITY;
		}

		dist[src] = 0.0;
		queue.add(src);

		while (!queue.isEmpty()) {
			int v = queue.poll();
			visited[v] = true;
			for (DirectedEdge adjacent : adj.getOrDefault(v, new ArrayList<>())) {
				int adjNode = adjacent.to;
				System.out.println("size of visited was: " + visited.length);
				System.out.println("adjNode was: " + adjNode);
				if (!visited[adjNode]) {
					double tempDist = dist[v] + adjacent.weight;
					if (tempDist < dist[adjNode]) {
						dist[adjNode] = tempDist;
						queue.remove(adjNode);
						queue.add(adjNode);
					}
				}
			}
		}
		return dist;
	}
	
	public void initGraph() {
		
	}

	// public static void main (String args[]) throws FileNotFoundException {
	// String filename =
	// "C:/Users/alexh/eclipse-workspace/Assignment2/src/tinyEWD.txt";
	// int sA = 50;
	// int sB = 60;
	// int sC = 70;
	// CompetitionDijkstra test = new CompetitionDijkstra(filename,sA,sB,sC);
	// int time = test.timeRequiredforCompetition();
	// System.out.println(time);

	// }

}