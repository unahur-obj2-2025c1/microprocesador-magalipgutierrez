package ar.edu.unahur.obj2.command.excepctions;

import java.util.List;

import ar.edu.unahur.obj2.command.Programable;
import ar.edu.unahur.obj2.command.comandos.Operable;

public class MicroControlador implements Programable {
    private Integer a;
    private Integer b;
    private Integer addr=0; //nro de pos de memoria
    private Integer counter=0; // linea del prog que se ejecuta
    public static final Integer memoria=1024;

    public MicroControlador(Integer a, Integer b, Integer addr) {
        this.a = a; //dos acumuladores
        this.b = b;
        this.addr=addr;
    }

    public static Integer getMemoria() {
        return memoria;
    }

    public void DoProgCounter(){
        
    }

    public void incrementarCounter(Integer nro) {
        this.counter += nro;
    }

    public Integer getCounter() {
        return counter;
    }
//permite ejcutar una serie de operaciones-> un porgrama en definita 
    @Override
    public void run(List<Operable> operaciones) {
       
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    @Override
    public void incProgramCounter() {
        this.counter +=1;
    }

    @Override
    public Integer getProgramCounter() {
        return this.counter;
    }

    @Override
    public void setAcumuladorA(Integer value) {
        this.a=value;
    }

    @Override
    public Integer getAcumuladorA() {
        return a;
    }

    @Override
    public void setAcumuladorB(Integer value) {
        
        this.b=value;
    }

    @Override
    public Integer getAcumuladorB() {
        return b;
    }

    @Override
    public void copyFrom(Programable programable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'copyFrom'");
    }

    @Override
    public Programable copy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'copy'");
    }

    @Override
    public void reset() {
        this.a=0;
        this.b=0;
        this.counter=0;
    }

    @Override
    public void setAddr(Integer addr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAddr'");
    }

    @Override
    public Integer getAddr(Integer addr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAddr'");
    }

    