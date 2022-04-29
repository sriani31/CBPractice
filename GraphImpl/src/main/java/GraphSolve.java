import java.util.Iterator;
import java.util.LinkedList;

public class GraphSolve {

    public class Graph{
        private int V;
        private LinkedList<Integer> adj[];

        Graph(int v){
            V = v;
            adj = new LinkedList[V];

            for(int i = 0; i < V ; i++){
                adj[i] =  new LinkedList<Integer>();
            }
        }

        void addEdge(int u, int v){
            adj[u].add(v);
        }
        void traverse(int u, boolean[] isVisited){

            System.out.println(u + "   ");

            isVisited[u] = true;

            Iterator<Integer> itr = adj[u].listIterator();

            while (itr.hasNext()){
                Integer s = itr.next();
                if(!isVisited[s]){
                    traverse(s,isVisited);
                }
            }

        }
        void traverseGraph(){

            boolean [] isVisited = new boolean[V];

            for(int i = 0 ; i < V; i++){
                traverse( i , isVisited);
            }
        }
    }

    public static void main(String[] args) {

    }
}
