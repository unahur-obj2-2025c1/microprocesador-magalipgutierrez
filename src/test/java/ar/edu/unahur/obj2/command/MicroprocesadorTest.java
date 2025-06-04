package ar.edu.unahur.obj2.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.command.excepctions.MicroControladorBuilder;

public class MicroprocesadorTest {
    private MicroControladorBuilder micro;
      
    @BeforeEach
    void setUp() {
        micro = new MicroControladorBuilder(); // Instantiate your class
    }
    
    @Test
    @DisplayName("El microcontrolador debe inicializarse correctamente al resetear")
    void testResetInitialState() {
        assertEquals(0, micro.getAcumuladorA(), "Acumulador A debe ser 0 al inicio");
        assertEquals(0, micro.getAcumuladorB(), "Acumulador B debe ser 0 al inicio");
        assertEquals(0, micro.getProgramCounter(), "Program Counter debe ser 0 al inicio");
    
}
