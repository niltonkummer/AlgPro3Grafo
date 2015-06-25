package main;

import util.ArrayObject;

/**
 *
 * @author niltonkummer
 */
class Vertice implements Comparable<Vertice> {

    public final int nome;
    public ArrayObject vizinhos = new ArrayObject();
    public double custo = Double.POSITIVE_INFINITY;
    private double lon, lat;
    private Vertice anterior;
    public boolean visitado = false;
    
    public Vertice(int valor) {
        nome = valor;
    }

    public Vertice(int valor, double lon, double lat) {
        this.nome = valor;
        this.lon = lon;
        this.lat = lat;
    }

    /**
     * @return the lon
     */
    public double getLon() {
        return lon;
    }

    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    public String toString() {
        return Integer.toString(nome);
    }

    public int compareTo(Vertice other) {
        return Double.compare(custo, other.custo);
    }

    /**
     * @return the anterior
     */
    public Vertice getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(Vertice anterior) {
        this.anterior = anterior;
    }
}
