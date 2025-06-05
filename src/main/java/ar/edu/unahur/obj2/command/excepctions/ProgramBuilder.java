package ar.edu.unahur.obj2.command.excepctions;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.command.Programable;
import ar.edu.unahur.obj2.command.comandos.Operable;

public class ProgramBuilder {

    private final List<Operable> programa;
    public ProgramBuilder(){
        this.programa= new ArrayList<>();
    }
    public ProgramBuilder agregarInstruccion(Operable i){
        this.programa.add(i);
        return this;
    }

    public List<Operable> build(){
        return this.programa;
    }
    public ProgramBuilder ejecutarPrograma(Programable micro){
        micro.run(programa);
        return this;
    }
     public void setearAcumA(MicroControladorBuilder unMicro, Integer valor){
        unMicro.setAcumuladorA(valor);
    }
    public void setearAcumB(MicroControladorBuilder unMicro, Integer valor){
        unMicro.setAcumuladorB(valor);
    }
    public void setearAddr(MicroControladorBuilder unMicro, Integer valor){
        unMicro.setAddr(valor);
    }

}
