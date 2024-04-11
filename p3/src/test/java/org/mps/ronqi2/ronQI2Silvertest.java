package org.mps.ronqi2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mps.dispositivo.DispositivoSilver;

public class RonQI2SilverTest {

    RonQI2Silver ronQI2Silver;

    @BeforeEach
    public void setUp() {
        ronQI2Silver = new RonQI2Silver();
    }
    /*
     * Analiza con los caminos base qué pruebas se han de realizar para comprobar que al inicializar funciona como debe ser. 
     * El funcionamiento correcto es que si es posible conectar ambos sensores y configurarlos, 
     * el método inicializar de ronQI2 o sus subclases, 
     * debería devolver true. En cualquier otro caso false. Se deja programado un ejemplo.
     */
    
    
    @Test
    @DisplayName("Test de inicializar")
    public void testInicializar_SensoresFuncionan_DevuelveTrue() {
        DispositivoSilver d = mock(DispositivoSilver.class);
        when(d.conectarSensorPresion()).thenReturn(true);
        when(d.configurarSensorPresion()).thenReturn(true);
        when(d.conectarSensorSonido()).thenReturn(true);
        when(d.configurarSensorSonido()).thenReturn(true);

        ronQI2Silver.anyadirDispositivo(d);

        assertTrue(ronQI2Silver.inicializar());
    }

    @ParameterizedTest
    @CsvSource(
        {"true, true, true, false",
        "true, true, false, true",
        "true, false, true, true",
        "false, true, true, true"}
    )
    @DisplayName("Test de inicializar cuando falla algo en el proceso de conexión o configuración de los sensores")
    public void testInicializar_SensoresNoFuncionan_DevuelveFalso(boolean conectarPresion, boolean configurarPresion, boolean conectarSonido, boolean configurarSonido) {
        DispositivoSilver d = mock(DispositivoSilver.class);
        when(d.conectarSensorPresion()).thenReturn(conectarPresion);
        when(d.configurarSensorPresion()).thenReturn(configurarPresion);
        when(d.conectarSensorSonido()).thenReturn(conectarSonido);
        when(d.configurarSensorSonido()).thenReturn(configurarSonido);

        ronQI2Silver.anyadirDispositivo(d);

        assertFalse(ronQI2Silver.inicializar());
    }
    
    /*
     * Un inicializar debe configurar ambos sensores, comprueba que cuando se inicializa de forma correcta (el conectar es true), 
     * se llama una sola vez al configurar de cada sensor.
     */

    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */
    
    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
     * /
     
     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */
}
