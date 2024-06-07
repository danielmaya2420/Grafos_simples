package GrafoSimple;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia del grafo
        GrafoSimple grafo = new GrafoSimple();

        // Crear el grafo (inicializar o limpiar)
        grafo.crearGrafo();

        // Agregar vértices al grafo
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);

        // Agregar aristas entre los vértices
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(2, 4);
        grafo.agregarArista(3, 4);

        // Imprimir la representación del grafo
        grafo.imprimirGrafo();

        // Recorrido en profundidad (DFS) desde el vértice 1
        System.out.println("Recorrido en Profundidad:");
        grafo.recorrerProfundidad(1);

        // Recorrido en anchura (BFS) desde el vértice 1
        System.out.println("Recorrido en Anchura:");
        grafo.recorrerAnchura(1);

        // Búsqueda usando BFS para encontrar el vértice 4
        System.out.println("Búsqueda BFS (4): " + grafo.buscarBFS(4));

        // Búsqueda usando DFS para encontrar el vértice 4
        System.out.println("Búsqueda DFS (4): " + grafo.buscarDFS(4));

        // Ordenamiento topológico del grafo
        System.out.println("Ordenamiento Topológico:");
        List<Integer> ordenTopologico = grafo.ordenarTopologico();
        for (int nodo : ordenTopologico) {
            System.out.print(nodo + " ");
        }
        System.out.println();

        // Ordenamiento por costos desde el vértice 1
        System.out.println("Ordenamiento por Costos desde 1:");
        grafo.ordenarPorCostos(1);
    }

}
