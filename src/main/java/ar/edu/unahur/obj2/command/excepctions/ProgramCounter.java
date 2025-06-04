package ar.edu.unahur.obj2.command.excepctions;

public abstract class ProgramCounter {
    protected MicroControlador micro;

    public ProgramCounter(MicroControlador micru){
        this.micro=micru;
    }
    public void programCounter(MicroControlador micru){
        this.doProgCounter(micru);
        micru.incrementarCounter(1);
    }
    public abstract void doProgCounter(MicroControlador micro);


}
