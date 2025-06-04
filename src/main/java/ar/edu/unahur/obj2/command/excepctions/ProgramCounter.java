package ar.edu.unahur.obj2.command.excepctions;

public abstract class ProgramCounter {
    protected MicroControladorBuilder micro;

    public ProgramCounter(MicroControladorBuilder micru){
        this.micro=micru;
    }
    public void programCounter(MicroControladorBuilder micru){
        this.doProgCounter(micru);
        micru.incrementarCounter(1);
    }
    public abstract void doProgCounter(MicroControladorBuilder micro);


}
