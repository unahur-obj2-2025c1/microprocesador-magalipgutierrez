package ar.edu.unahur.obj2.command;

public class Microprocesador {

    
    private Integer a;
    private Integer b;
    private Integer counter=0; // linea del prog que se ejecuta
    public static final Integer memoria=1024;

    public Microprocesador(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    public static Integer getMemoria() {
        return memoria;
    }
}