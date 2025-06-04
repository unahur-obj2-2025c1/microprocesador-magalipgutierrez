package ar.edu.unahur.obj2.command.excepctions;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.command.Programable;
import ar.edu.unahur.obj2.command.comandos.Operable;

public final class MicroControladorBuilder implements Programable {
   private Integer acumuladorA;
    private Integer acumuladorB;
    private Integer programCounter;
    private List<Integer> memoriaDatos= new ArrayList<>(); // Área de 1024 valores -> array  de Adrr
    // ctes-> la memoria
    private static final int MEMORIA_SIZE = 1024;
    private static final int MEMORIA_MIN_ADDR = 0;
    private static final int MEMORIA_MAX_ADDR = MEMORIA_SIZE - 1;

    // constructor
    public MicroControladorBuilder() {
        reset(); // Inicializa el estado en el constructor
    }
//permite ejcutar una serie de operaciones-> un porgrama en definita 
    @Override
    public void run(List<Operable> operaciones) {
       for (int i = 0; i < operaciones.size(); i++) {
            // Se asume que el PC se incrementa DESPUÉS de ejecutar la instrucción.
            // Para que el PC apunte a la instrucción que causó el error,
            // no lo incrementamos hasta que la operación sea exitosa.
            this.programCounter = i; // El PC apunta a la instrucción actual
        try {
                Operable operacion = operaciones.get(i);
                operacion.execute(this); // 'this' es el propio Microcontrolador (Receptor)
            } catch (IllegalArgumentException e) {
                // Manejo de errores de acceso a memoria fuera de rango
                System.err.println("Error de ejecución en PC " + this.programCounter + ": " + e.getMessage());
                // El PC ya está en la dirección del error, así que solo detenemos la ejecución.
                break;
            } catch (Exception e) {
                // Otros tipos de errores no esperados
                System.err.println("Error inesperado en PC " + this.programCounter + ": " + e.getMessage());
                break;
            }
      
         }
    }

    @Override
    public void incProgramCounter() {
        this.programCounter++;;
    }

    @Override
    public Integer getProgramCounter() {
        return this.programCounter;
    }

    @Override
    public void setAcumuladorA(Integer value) {
        this.acumuladorA = value;
    }

    @Override
    public Integer getAcumuladorA() {
        return this.acumuladorA;
    }

    @Override
    public void setAcumuladorB(Integer value) {
        this.acumuladorB = value;
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
        this.memoriaDatos = new ArrayList<>();
        // Opcional: inicializar to la memoria a 0 o null
        //Arrays.fill(this.memoriaDatos, 0); -> no funciona, ver 
    }
    // faltaria agregar el dato asociado a la direccion
    @Override
    public void setAddr(Integer addr) {
        if (!esValidAddress(addr)) {
            throw new IllegalArgumentException("Dirección de memoria fuera de rango: " + addr);
        }
        memoriaDatos.remove(addr); //ver esto
        
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
}
    


    
    