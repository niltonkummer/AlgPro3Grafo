package main;

/**
 *
 * @author niltonkummer
 */
class Aresta {

    public final Vertice vertice;
    public final double custo;

    public Aresta(Vertice v, double c) {
        this.vertice = v;
        this.custo = c;
    }
}