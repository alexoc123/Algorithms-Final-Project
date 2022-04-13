
import java.util.HashMap;
import java.util.List;

public class EdgeWeightedDigraph {
	public int V;
	public int E;
	public HashMap<Integer, List<DirectedEdge>> adj;

	public EdgeWeightedDigraph(int V, int E) {
		this.V = V;
		this.E = E;
		this.adj = new HashMap<>();
	}
}