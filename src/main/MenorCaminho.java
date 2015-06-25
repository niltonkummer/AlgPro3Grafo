package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static main.MenorCaminho.grafo;
import util.ArrayObject;

class Grafo {

    public ArrayObject vertices = new ArrayObject();

    public Grafo() {
    
    }
    public void dijkstra(Vertice origem) {
        origem.custo = 0;

        PriorityQueue<Vertice> pilha = new PriorityQueue<>();
        // Adiciona a origem a pilha
        pilha.add(origem);
        // Enquanto a pilha não estiver vazia
        while (!pilha.isEmpty()) {
            // le um elemento da pilha
            Vertice n = pilha.poll();
            // Se já visitou n, retorna ao início do loop.
            if (n.visitado) {
                continue;
            }
            // Para cada vertice vizinho de n
            Object[] list = n.vizinhos.getList();
            for (int i = 0; i < n.vizinhos.getSize(); i++) {
                Aresta aresta = (Aresta) list[i];
                Vertice v = aresta.vertice;
                // calculo do custo
                double custoCalculado = n.custo + aresta.custo;

                if (custoCalculado < v.custo) {
                    // Remove o elemento que é o menor
                    pilha.remove(v);
                    v.custo = custoCalculado;
                    // Seta como o anterior 
                    v.setAnterior(n);
                    if (!v.visitado) {
                        pilha.add(v);
                    }
                }
            }
            n.visitado = true;
        }
    }

    public List<Vertice> caminhoMaisCurto(Vertice destino) {
        List<Vertice> caminho = new ArrayList<>();
        // Faz a rota de retorno do destino até a origem depois inverte o resultado
        for (Vertice v = destino; v != null; v = v.getAnterior()) {
            caminho.add(v);
        }
        // Inverte o resultado
        Collections.reverse(caminho);
        return caminho;
    }
    
    public void loadData() throws FileNotFoundException {

        Scanner s = new Scanner(
                new FileReader("src/resource/grafo.txt")).useDelimiter("\n");
        boolean isVertice = false;
        while (s.hasNext()) {
            String next = s.next();
            switch (next) {
                case "vertices":
                    isVertice = true;
                    continue;
                case "arestas":
                    isVertice = false;
                    continue;
            }
            if (isVertice) {
                // ler os vertices
                lerVertices(next);
            } else {
                // ler as vizinhos
                lerArestas(next);
            }
        }
    }

    private  void lerVertices(String linha) {
        Scanner s = new Scanner(linha).useDelimiter(" ");

        int v = s.nextInt();
        double lon = s.nextDouble();
        double lat = s.nextDouble();
        Vertice vertice = new Vertice(v, lon, lat);
        grafo.vertices.add(vertice);
    }

    private void lerArestas(String linha) {
        Scanner s = new Scanner(linha).useDelimiter(" ");
        // Origem
        int v1 = s.nextInt();
        // Destino
        int v2 = s.nextInt();
        // Ignorado
        double w = s.nextDouble();
        Vertice ver1 = getVertice(v1);
        Vertice ver2 = getVertice(v2);

        double dist = calculateDistance(ver2, ver1);
        ver1.vizinhos.add(new Aresta(ver2, dist));
    }

    public Vertice getVertice(int v1) {
        return (Vertice) vertices.get(v1);
    }

    private static double calculateDistance(Vertice ver2, Vertice ver1) {
        return Math.sqrt(Math.pow(ver2.getLat() - ver1.getLat(), 2)
                + Math.pow(ver2.getLon() - ver1.getLon(), 2));
    }
}

public class MenorCaminho {

    static final Grafo grafo = new Grafo();

    public static void main(String[] args) {
       
        // Carrega os dados
        try {
            Date d1 = new Date();
            grafo.loadData();
            System.out.printf("Terminei de carregar os dados: %ds\n",
                    (new Date().getTime() - d1.getTime()) / 1000);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MenorCaminho.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Informe o indice de origem:");
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        Vertice origem = grafo.getVertice(i);
        grafo.dijkstra(origem);

        System.out.println("Informe o indice de destino:");
        s = new Scanner(System.in);
        i = s.nextInt();
        Vertice destino = grafo.getVertice(i);
        System.out.printf("Total de metros de %s até %s: %.2fm\n", origem.nome,
                destino.nome, destino.custo);
        List<Vertice> path = grafo.caminhoMaisCurto(destino);

        printCaminho(path);
    }

    private static void printCaminho(List<Vertice> path) {
        ArrayList<String> list = new ArrayList();
        path.stream().forEach((vt) -> {
            list.add("(" + vt.toString() + ")");
        });
        System.out.println("Caminho: " + String.join(" -> ", list));
    }
}
