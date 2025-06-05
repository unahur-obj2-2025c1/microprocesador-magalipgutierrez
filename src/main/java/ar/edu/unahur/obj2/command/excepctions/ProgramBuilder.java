package ar.edu.unahur.obj2.command.excepctions;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.command.Programable;
import ar.edu.unahur.obj2.command.comandos.Operable;

public class ProgramBuilder {

    private List<Operable> programa;
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

}
