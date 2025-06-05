package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;

public class NOPCommand implements Operable{
    //Concrete Commands
    private Programable prevState;

    @Override
    public void execute(Programable micro) {
        this.prevState = micro.copy();
        // Su único efecto es avanzar el Program Counter.
        micro.incProgramCounter();
    }

    @Override
    public void undo(Programable micro) {
        if (this.prevState != null) {
            micro.copyFrom(this.prevState);
    } else {
            // Si no hay estado previo, el comando no pudo ser ejecutado
            System.err.println("No se puede deshacer NOPCommand: no hay estado previo registrado.");
        }
    }

}
