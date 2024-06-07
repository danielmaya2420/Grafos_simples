package GrafoSimple;

import java.util.*;

public class GrafoSimple {
    private Map<Integer, List<Integer>> adyacencia;

    // Constructor para inicializar el mapa de adyacencia
    public GrafoSimple() {
        this.adyacencia = new HashMap<>();
    }

    // Método para crear (o limpiar) el grafo
    public void crearGrafo() {
        this.adyacencia.clear();
    }

    // Método para agregar un vértice al grafo
    public void agregarVertice(int dato) {
        if (!adyacencia.containsKey(dato)) {
            adyacencia.put(dato, new ArrayList<>());
        }
    }

    // Método para agregar una arista entre dos vértices
    public void agregarArista(int origen, int destino) {
        if (adyacencia.containsKey(origen) && adyacencia.containsKey(destino)) {
            adyacencia.get(origen).add(destino);
        }
    }

    // Método para imprimir el grafo
    public void imprimirGrafo() {
        for (Map.Entry<Integer, List<Integer>> entry : adyacencia.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (Integer destino : entry.getValue()) {
                System.out.print(destino + " ");
            }
            System.out.println();
        }
    }

    // Método para recorrer el grafo en profundidad (DFS)
    public void recorrerProfundidad(int verticeInicio) {
        Set<Integer> visitados = new HashSet<>();
        Stack<Integer> pila = new Stack<>();
        pila.push(verticeInicio);
        while (!pila.isEmpty()) {
            int vertice = pila.pop();
            if (!visitados.contains(vertice)) {
                visitados.add(vertice);
                System.out.print(vertice + " ");
                List<Integer> vecinos = adyacencia.get(vertice);
                if (vecinos != null) {
                    for (int i = vecinos.size() - 1; i >= 0; i--) {
                        pila.push(vecinos.get(i));
                    }
                }
            }
        }
        System.out.println();
    }

    // Método para recorrer el grafo en anchura (BFS)
    public void recorrerAnchura(int verticeInicio) {
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> cola = new LinkedList<>();
        cola.add(verticeInicio);
        visitados.add(verticeInicio);
        while (!cola.isEmpty()) {
            int vertice = cola.poll();
            System.out.print(vertice + " ");
            List<Integer> vecinos = adyacencia.get(vertice);
            if (vecinos != null) {
                for (int vecino : vecinos) {
                    if (!visitados.contains(vecino)) {
                        cola.add(vecino);
                        visitados.add(vecino);
                    }
                }
            }
        }
        System.out.println();
    }

    // Método para buscar un dato en el grafo usando BFS
    public boolean buscarBFS(int dato) {
        for (int vertice : adyacencia.keySet()) {
            if (vertice == dato) return true;
            Queue<Integer> cola = new LinkedList<>();
            Set<Integer> visitados = new HashSet<>();
            cola.add(vertice);
            visitados.add(vertice);
            while (!cola.isEmpty()) {
                int actual = cola.poll();
                if (actual == dato) return true;
                List<Integer> vecinos = adyacencia.get(actual);
                if (vecinos != null) {
                    for (int vecino : vecinos) {
                        if (!visitados.contains(vecino)) {
                            cola.add(vecino);
                            visitados.add(vecino);
                        }
                    }
                }
            }
        }
        return false;
    }

    // Método para buscar un dato en el grafo usando DFS
    public boolean buscarDFS(int dato) {
        for (int vertice : adyacencia.keySet()) {
            if (vertice == dato) return true;
            Stack<Integer> pila = new Stack<>();
            Set<Integer> visitados = new HashSet<>();
            pila.push(vertice);
            while (!pila.isEmpty()) {
                int actual = pila.pop();
                if (actual == dato) return true;
                if (!visitados.contains(actual)) {
                    visitados.add(actual);
                    List<Integer> vecinos = adyacencia.get(actual);
                    if (vecinos != null) {
                        for (int i = vecinos.size() - 1; i >= 0; i--) {
                            pila.push(vecinos.get(i));
                        }
                    }
                }
            }
        }
        return false;
    }

    // Método para ordenar los vértices del grafo topológicamente
    public List<Integer> ordenarTopologico() {
        Set<Integer> visitados = new HashSet<>();
        Stack<Integer> pila = new Stack<>();
        for (int vertice : adyacencia.keySet()) {
            if (!visitados.contains(vertice)) {
                ordenarTopologicoUtil(vertice, visitados, pila);
            }
        }
        List<Integer> ordenamiento = new ArrayList<>();
        while (!pila.isEmpty()) {
            ordenamiento.add(pila.pop());
        }
        return ordenamiento;
    }

    // Método auxiliar para la ordenación topológica
    private void ordenarTopologicoUtil(int vertice, Set<Integer> visitados, Stack<Integer> pila) {
        visitados.add(vertice);
        List<Integer> vecinos = adyacencia.get(vertice);
        if (vecinos != null) {
            for (int vecino : vecinos) {
                if (!visitados.contains(vecino)) {
                    ordenarTopologicoUtil(vecino, visitados, pila);
                }
            }
        }
        pila.push(vertice);
    }

    // Método para ordenar los vértices del grafo por costos
    public void ordenarPorCostos(int inicio) {
        Map<Integer, Integer> distancia = new HashMap<>();
        for (int vertice : adyacencia.keySet()) {
            distancia.put(vertice, Integer.MAX_VALUE);
        }
        distancia.put(inicio, 0);

        PriorityQueue<Integer> cola = new PriorityQueue<>(Comparator.comparingInt(distancia::get));
        cola.add(inicio);

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            List<Integer> vecinos = adyacencia.get(actual);
            if (vecinos != null) {
                for (int vecino : vecinos) {
                    int nuevaDistancia = distancia.get(actual) + 1;  // Asumimos costo de 1 para cada arista
                    if (nuevaDistancia < distancia.get(vecino)) {
                        distancia.put(vecino, nuevaDistancia);
                        cola.add(vecino);
                    }
                }
            }
        }

        // Imprimir las distancias desde el nodo inicial
        for (Map.Entry<Integer, Integer> entry : distancia.entrySet()) {
            System.out.println("Nodo " + entry.getKey() + " - Distancia: " + entry.getValue());
        }
    }

    // Método principal para probar las funcionalidades del grafo
    public static void main(String[] args) {
        GrafoSimple grafo = new GrafoSimple();
        grafo.crearGrafo();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(2, 4);
        grafo.agregarArista(3, 4);
        grafo.imprimirGrafo();

        System.out.println("Recorrido en Profundidad:");
        grafo.recorrerProfundidad(1);

        System.out.println("Recorrido en Anchura:");
        grafo.recorrerAnchura(1);

        System.out.println("Búsqueda BFS (4): " + grafo.buscarBFS(4));
        System.out.println("Búsqueda DFS (4): " + grafo.buscarDFS(4));

        System.out.println("Ordenamiento Topológico:");
        List<Integer> ordenTopologico = grafo.ordenarTopologico();
        for (int nodo : ordenTopologico) {
            System.out.print(nodo + " ");
        }
        System.out.println();

        System.out.println("Ordenamiento por Costos desde 1:");
        grafo.ordenarPorCostos(1);
    }
}
