package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;

public class LODVCommand implements Operable {
    //Concrete Commands
    private final Integer addr;
    private Programable prevState;

    public LODVCommand(Integer valor) {
        this.addr = valor;
    }

    @Override
    public void execute(Programable micro) {
        this.prevState = micro.copy();
        micro.setAcumuladorA(addr);
        micro.incProgramCounter();
    }

    @Override
    public void undo(Programable micro) {
        if (prevState != null) {
            micro.copyFrom(this.prevState);
        }
    }

}
