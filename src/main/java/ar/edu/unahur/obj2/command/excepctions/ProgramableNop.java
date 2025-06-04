package ar.edu.unahur.obj2.command.excepctions;

import java.util.List;

import ar.edu.unahur.obj2.command.Programable;
import ar.edu.unahur.obj2.command.comandos.Operable;

public class ProgramableNop implements Programable {
    private List<Operable> operaciones;
    @Override
    public void run(List<Operable> operaciones) {
        
       //operaciones=operaciones ;
    }

    @Override
    public void incProgramCounter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'incProgramCounter'");
    }

    @Override
    public Integer getProgramCounter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProgramCounter'");
    }

    @Override
    public void setAcumuladorA(Integer value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAcumuladorA'");
    }

    @Override
    public Integer getAcumuladorA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAcumuladorA'");
    }

    @Override
    public void setAcumuladorB(Integer value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAcumuladorB'");
    }

    @Override
    public Integer getAcumuladorB() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAcumuladorB'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
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

}
