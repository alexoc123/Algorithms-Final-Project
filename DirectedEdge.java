
public class DirectedEdge {
	public int from;
	public int to;
	public double weight;

	public DirectedEdge(int v, int w, double weight) {
		this.from = v;
		this.to = w;
		this.weight = weight;
	}
}