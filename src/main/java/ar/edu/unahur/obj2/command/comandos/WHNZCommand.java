package ar.edu.unahur.obj2.command.comandos;

import java.util.List;

import ar.edu.unahur.obj2.command.Programable;

public class WHNZCommand implements Operable{
    private final List<Operable> instrucciones;
    private Programable prevState;

    public WHNZCommand(List<Operable> instructions) {
        this.instrucciones = instructions;
    }

    @Override
    public void execute(Programable micro) {
        this.prevState = micro.copy(); // Guarda el estado antes de entrar
        while (micro.getAcumuladorA() != 0) {
            instrucciones.stream().forEach( i->i.execute(micro));}
    }

    @Override
    public void undo(Programable micro) {
         if (this.prevState != null) {
            micro.copyFrom(this.prevState);
            System.out.println("Se deshizo la operación WHNZ.");
         }
    }

}
