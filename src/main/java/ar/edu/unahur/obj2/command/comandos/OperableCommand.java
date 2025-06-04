package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Microprocesador;

public abstract class  OperableCommand implements Operable{
   
    private Microprocesador micro;

    public OperableCommand(Microprocesador m){
        this.micro=m;
    }

    public OperableCommand() {
    }

    
}
