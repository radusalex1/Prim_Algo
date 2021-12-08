import java.util.Vector;

public class Prims_Algo {
    private int noduri;
    Vector<Pair> pairs =  new Vector<Pair>();
    public Prims_Algo(int noduri) {
        this.noduri = noduri;
    }

    public Vector<Pair> getPairs() {
        return pairs;
    }

    int minKey(int key[], Boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v <noduri; v++) {
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }

        return min_index;
    }

    // A utility function to print the constructed MST stored in
    // parent[]
    void printMST(int parent[], int graph[][])
    {
        //ArrayList <Pair<Integer, Integer>> result =  new ArrayList<Pair<Integer,Integer>>(noduri);
        System.out.println("Edge \tWeight");
        for (int i = 1; i < noduri; i++) {
            addPair(parent[i]+1,i+1);
            System.out.println(parent[i] + 1 + " - " + (i + 1) + "\t" + graph[i][parent[i]]);
        }

    }
    private void addPair(int first,int second)
    {
        Pair p = new Pair(first,second);
        pairs.add(p);
    }

    // Function to construct and print MST for a graph represented
    // using adjacency matrix representation
    void primMST(int graph[][]) {
        // Array to store constructed MST
        int parent[] = new int[noduri];

        // Key values used to pick minimum weight edge in cut
        int key[] = new int[noduri];

        // To represent set of vertices included in MST
        Boolean mstSet[] = new Boolean[noduri];

        // Initialize all keys as INFINITE
        for (int i = 0; i < noduri; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < noduri - 1; count++) {
            // Pick the minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < noduri; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }
        // print the constructed MST
        printMST(parent, graph);
    }
}
