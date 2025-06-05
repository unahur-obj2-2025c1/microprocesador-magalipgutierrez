package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;


public class SWAPCommand implements Operable {
    //Concrete Commands
    private Programable prevState;

    @Override
    public void execute(Programable micro) {
        this.prevState = micro.copy();
        Integer tempA = micro.getAcumuladorA();
        micro.setAcumuladorA(micro.getAcumuladorB());
        micro.setAcumuladorB(tempA);
        micro.incProgramCounter();
    }

    @Override
    public void undo(Programable micro) {
        if (prevState != null) {
            micro.copyFrom(this.prevState);
        }
    }
}

