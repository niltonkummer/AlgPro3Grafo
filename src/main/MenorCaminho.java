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
