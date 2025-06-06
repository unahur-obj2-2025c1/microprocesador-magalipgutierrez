package ar.edu.unahur.obj2.command.excepctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ar.edu.unahur.obj2.command.Programable;
import ar.edu.unahur.obj2.command.comandos.Operable;

public final class MicroControladorBuilder implements Programable {
        //Receptor

    private Integer acumuladorA;
    private Integer acumuladorB;
    private Integer programCounter; // aca esta el pc
    private List<Integer> memoriaDatos= new ArrayList<>(); // Área de 1024 valores -> array  de Adrr
    // ctes-> la memoria
    private static final int MEMORIA_SIZE = 1024;
    private static final int MEMORIA_MIN_ADDR = 0;
    private static final int MEMORIA_MAX_ADDR = MEMORIA_SIZE - 1;

     // Pila para el historial de comandos ejecutados
    private Stack<Operable> executedCommands;
    // constructor
    public MicroControladorBuilder() {
        reset(); // Inicializa el estado en el constructor en 0
    }
//permite ejcutar una serie de operaciones-> un porgrama en definita 
// actua como Invoker- > ya que toma una lista de comandos y los ejecuta
    @Override
    public void run(List<Operable> operaciones) {
       operaciones.stream().forEach(o->o.execute(this));
      
    }
    
    @Override
    public void incProgramCounter() {
        this.programCounter++;
    }

    @Override
    public Integer getProgramCounter() {
        return this.programCounter;
    }

    @Override
    public void setAcumuladorA(Integer valor) {
        this.acumuladorA = valor;
    }

    @Override
    public Integer getAcumuladorA() {
        return this.acumuladorA;
    }

    @Override
    public void setAcumuladorB(Integer valor) {
        this.acumuladorB = valor;
    }

    @Override
    public Integer getAcumuladorB() {
        return this.acumuladorB;
    }

    @Override
    public void copyFrom(Programable programable) {
        // Se valida que el objeto a copiar sea de la misma implementación (MicroControladorBuilder)
        if (!(programable instanceof MicroControladorBuilder)) {
            throw new IllegalArgumentException("Solo se puede copiar desde otra instancia de MicroControladorBuilder.");
        }
        MicroControladorBuilder otherMicro = (MicroControladorBuilder) programable; // Se usa 'programable'
        this.acumuladorA = otherMicro.acumuladorA;
        this.acumuladorB = otherMicro.acumuladorB;
        this.programCounter = otherMicro.programCounter;
        // ¡Importante! Copiar la lista de memoria, no solo la referencia
        this.memoriaDatos = new ArrayList<>(otherMicro.memoriaDatos); // Copia profunda de la lista
    }

    @Override
    public Programable copy() {
        Programable newMicro = new MicroControladorBuilder();
        newMicro.copyFrom(this); // Usa el propio método copyFrom para duplicar el estado
        return newMicro;
    }

    @Override
    public void reset() {
        this.acumuladorA = 0;
        this.acumuladorB = 0;
        this.programCounter = 0;
        this.memoriaDatos = new ArrayList<>(MEMORIA_SIZE);
        for (int i=0; i< MEMORIA_SIZE;i++){ memoriaDatos.add(0);}
        // Opcional: inicializar to la memoria a 0 o null
        //Arrays.fill(this.memoriaDatos, 0); -> no funciona, ver 
    }
    // faltaria agregar el dato asociado a la direccion
    @Override
    public void setAddr(Integer addr) {
        if (!esValidAddress(addr)) {
            throw new IllegalArgumentException("Dirección de memoria fuera de rango: " + addr);
        }
        memoriaDatos.set(addr, this.acumuladorA);
        
    }

    @Override
    public Integer getAddr(Integer addr) {
        // Valida el rango de la dirección antes de acceder a la memoria
        if (!esValidAddress(addr)) {
            throw new IllegalArgumentException("Dirección de memoria fuera de rango: " + addr);
        }
        return memoriaDatos.get(addr);
    }
    private boolean esValidAddress(Integer addr) {
      
        return addr != null && addr >= MEMORIA_MIN_ADDR && addr <= MEMORIA_MAX_ADDR;
    }

    @Override
    public void setMemoryValue(Integer addr, Integer valor) {
        if (!esValidAddress(addr)) {
            throw new IllegalArgumentException("Dirección de memoria fuera de rango: " + addr);
            }
                    // Asegurarse q haya corte, 
        while (memoriaDatos.size() <= addr) {
                memoriaDatos.add(0); // Rellena con ceros el tamaño de la lista
                    }
                    //List y set(index, value)
                    //reemplaza el elemento en la posición index con element
        memoriaDatos.set(addr, valor);
    }
    @Override
    public void undoLast() {
        if (!executedCommands.isEmpty()) {
            Operable lastCommand = executedCommands.pop(); // Obtiene y elimina el último comando
            lastCommand.undo(this); // Deshace la operación
            System.out.println("Se ha deshecho la última operación.");
        } else {
            System.out.println("No hay operaciones para deshacer.");
        }
    }
    @Override
    public Operable getLastExecutedCommand() {
         if (executedCommands.isEmpty()) {
            return null;
        }
        return executedCommands.peek(); // Solo retorna el último sin sacarlo de la pila
    }      
}
    


    
    