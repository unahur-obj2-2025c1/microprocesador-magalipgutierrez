package ar.edu.unahur.obj2.command;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.command.comandos.Operable;

public class ProgramBuilder {
     private List<Operable> operaciones = new ArrayList<>();
     
     public ProgramBuilder(List<Operable> opss) {
        this.operaciones=opss;
     }

     public Programable build(){
        return this;
     }    
}
