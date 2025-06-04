package ar.edu.unahur.obj2.command;

public class Microprocesador{ 
    //disculpas es el microControlador
    private Integer a;
    private Integer b;
    private Integer addr=0; //nro de pos de memoria
    private Integer counter=0; // linea del prog que se ejecuta
    public static final Integer memoria=1024; //es constante esto?

    public Microprocesador(Integer a, Integer b) {
        this.a = a; //dos acumuladores 
        this.b = b;
    }

    public static Integer getMemoria() {
        return memoria;
    }
//get and set
    public Integer getAddr() {
        return addr;
    }
    public void setAddr(Integer addr) {
        this.addr = addr;
    }

    public Integer getCounter() {
        return counter;
    }
    

}