package ar.edu.unahur.obj2.command.comandos;


import ar.edu.unahur.obj2.command.Programable;

public class  ADDCommand implements Operable{

        //Concrete Commands

   // Para guardar el estado del microcontrolador antes de la ejecucion
    private Programable prevState;

    public Programable getPrevState() {
        return prevState;
    }

    @Override
    public void execute(Programable micro) {
        // Guardar el estado actual del microcontrolador ANTES de realizar la operacion
        this.prevState = micro.copy(); 
          // Suma los valores de los acumuladores A y B.
        Integer resultado = micro.getAcumuladorA() + micro.getAcumuladorB();
        micro.setAcumuladorA(resultado);
        micro.setAcumuladorB(0);
        micro.incProgramCounter();

        
    }

    @Override
    public void undo(Programable micro) {
        // Verificar si hay un estado previo guardado para deshacer.
        if (this.prevState != null) {
             micro.copyFrom(this.prevState);
        } else {
            // Si no hay estado previo (ej. el comando no se ejecutó o no se guardo),
            //  lanza una excepcion 
            System.err.println("No se puede deshacer: no hay estado previo registrado.");
        }
    }

    

    
}
