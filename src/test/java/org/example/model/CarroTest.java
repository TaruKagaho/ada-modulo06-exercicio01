package org.example.model;

import org.example.exception.VelocidadeNegativaException;
import org.example.exception.CarroNaoLigadoException;
import org.example.exception.VelocidadeDiferenteDeZeroException;
import org.example.model.Carro;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Annotations
 *
 * @Before // Junit 4 - Roda uma vez antes de cada teste
 * @BeforeEach // Junit 5
 * @BeforeClass // Junit 4 - Roda uma vez antes de TODOS os tests
 * @BeforeAll // Junit 5
 * @After // Junit 4 - Roda uma vez após cada teste
 * @AfterEach // Junit 5
 * @AfterClass // Junit 4 - Roda uma vez após TODOS os testes
 * @AfterAll // Junit 5
 * @Ignore // Junit 4 - Ignora um teste
 * @Disabled Novo:
 * @DisplayName // Junit 5
 * <p>
 * Assert // Junit 4
 * Assertion // Junit 5
 * <p>
 * fail - fail
 * assertTrue - assertTrue
 * assertSame - assertSame
 * assertNull - assertNull
 * assertNotSame - assertNotSame
 * assertNotNull - assertNotNull
 * assertFalse - assertFalse
 * assertEquals - assertEquals
 * assertArrayEquals - assertArrayEquals
 * assertThat - N/A
 * assertThrows (4.13) - assertThrows
 * <p>
 * Nova:
 * assertAll
 * assertThrows
 */

public class CarroTest {
    // F.I.R.S.T - Principios
    // F - Fast
    // I - Isolado/Independente
    // R - Repetable
    // S - Self-validating
    // T - Oportuno (TDD)

    @BeforeAll
    public static void beforeClass() {
        System.out.println("roda uma vez antes de todos testes");
    }

    @BeforeEach
    public void before() {
        System.out.println("roda uma vez antes da cada teste");
    }

    @AfterAll
    public static void afterClass() {
        System.out.println("roda uma vez depois de todos testes");
    }

    @AfterEach
    public void after() {
        System.out.println("roda uma vez depois da cada teste");
    }

    @Test
    public void deveCriarUmCarroComCorMarcaModelo() {
        // Given (Dado)
        Carro carro = new Carro("Preto", "BMW", "X1");

        // Then (Então)
        assertAll("Testando atributos do carro",
                () -> assertEquals("Preto", carro.getCor()),
                () -> assertEquals("BMW", carro.getMarca()),
                () -> assertEquals("X1", carro.getModelo())
        );
    }

    @Test
    public void deveCriarUmCarroComTodosOsCampos() {
        // Given (Dado)
        Carro carro = new Carro("Preto", "BMW", "X1", 350);

        // Then (Então)
        assertAll("Testando atributos do carro",
                () -> assertEquals("Preto", carro.getCor()),
                () -> assertEquals("BMW", carro.getMarca()),
                () -> assertEquals("X1", carro.getModelo()),
                () -> assertEquals(350, carro.getVelocidadeMaxima())
        );
    }

    @Test
    public void deveSerPossivelAlterarTodosCamposDeUmCarro() {
        // Given (Dado)
        Carro carro = new Carro("Preto", "BMW", "X1", 350);

        // When (Quando)
        carro.setCor("Cinza");
        carro.setMarca("Audi");
        carro.setModelo("A1 Sportback");
        carro.setVelocidadeMaxima(320);

        // Then (Então)
        assertAll("Testando atributos do carro",
                () -> assertEquals("Cinza", carro.getCor()),
                () -> assertEquals("Audi", carro.getMarca()),
                () -> assertEquals("A1 Sportback", carro.getModelo()),
                () -> assertEquals(320, carro.getVelocidadeMaxima())
        );
    }

    @Test
    public void deveIniciarDesligado() {
        //  System.out.println("deveIniciarDesligado");
        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando)

        // Then (Então)
        assertFalse(carro.getLigado());
    }

    @Test
    // @Disabled // TODO
    public void deveLigarCorretamente() {
        // System.out.println("deveLigarCorretamente");
        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando)
        carro.ligar();

        // Then (Então)
        assertTrue(carro.getLigado());
    }

    @Test
    // @Disabled // TODO
    public void deveDesligarCorretamente() throws VelocidadeDiferenteDeZeroException {
        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando)
        carro.ligar();
        carro.desligar();

        // Then (Então)
        assertFalse(carro.getLigado());
    }

    @Test
    // @Disabled // TODO
    public void deveLancarExceptionEmCasoDeDesligarComVelocidadeAtualDiferenteDeZero() throws
            VelocidadeNegativaException,
            CarroNaoLigadoException {
        // Given (Dado)
        Carro carro = new Carro();
        carro.ligar();
        carro.acelerar(10);
        carro.frear(5);

        // When (Quando)
        Throwable throwable = Assertions.assertThrows(
                VelocidadeDiferenteDeZeroException.class,
                // runnable
                carro::desligar
        );

        // Then (Então)
        Assertions.assertEquals(
                "Para desligar o carro deve está parado!",
                throwable.getMessage()
        );
    }

    @Test
    @DisplayName("Meu teste com nome bonito")
    public void deveIniciarComVelocidadeZero() {
        // System.out.println("deveIniciarComVelocidadeZero");
        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando)

        // Then (Então)
        assertEquals((Integer) 0, carro.getVelocidadeAtual());
    }

    @Test
    public void deveLancarExceptionEmCasoDeAcelerarComCarroDesligado() {
        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando)
        Throwable throwable = Assertions.assertThrows(
                CarroNaoLigadoException.class,
                // runnable
                () -> carro.acelerar(10)
        );

        // Then (Então)
        Assertions.assertEquals(
                "Não é possível acelerar com o carro desligado!",
                throwable.getMessage()
        );
    }

    @Test
    public void deveLancarExceptionEmCasoDeAceleracaoNegativa() {
        // Given
        Carro carro = new Carro();
        carro.ligar();

        // When
        Throwable throwable = Assertions.assertThrows(
                VelocidadeNegativaException.class,
                // runnable
                () -> carro.acelerar(-10)
        );

        // Then
        Assertions.assertEquals(
                "A aceleracao não pode ser menor que zero!",
                throwable.getMessage()
        );
    }

    @Test
    public void deveAcelerarCorretamente() throws Exception {
        // System.out.println("deveAcelerarCorretamente");
        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando)
        carro.ligar();
        carro.acelerar(10);

        // Then (Então)
        assertEquals((Integer) 10, carro.getVelocidadeAtual());
    }

    @Test
    public void naoDeveUltrapassarAVelocidadeMaxima() throws Exception {
        //System.out.println("naoDeveUltrapassarAVelocidadeMaxima");
        // Given
        Carro carro = new Carro(200);

        // When
        carro.ligar();
        carro.acelerar(100);
        carro.acelerar(100);
        carro.acelerar(100);

        // Then
        assertEquals((Integer) 200, carro.getVelocidadeAtual());
    }

    @Test
    public void deveLancarExceptionEmCasoDeFrearComCarroDesligado() {
        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando)
        Throwable throwable = Assertions.assertThrows(
                CarroNaoLigadoException.class,
                // runnable
                () -> carro.frear(10)
        );

        // Then (Então)
        Assertions.assertEquals(
                "Não é possível frear com o carro desligado!",
                throwable.getMessage()
        );
    }

    @Test
    public void deveLancarExceptionEmCasoDeFrenagemNegativa() throws
            VelocidadeNegativaException,
            CarroNaoLigadoException {
        // Given
        Carro carro = new Carro();
        carro.ligar();
        carro.acelerar(10);

        // When
        Throwable throwable = Assertions.assertThrows(
                VelocidadeNegativaException.class,
                // runnable
                () -> carro.frear(-10)
        );

        // Then
        Assertions.assertEquals(
                "A frenagem não pode ser menor que zero!",
                throwable.getMessage()
        );
    }

    @Test
    public void deveFrearCorretamente() throws Exception {
        // System.out.println("deveAcelerarCorretamente");
        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando)
        carro.ligar();
        carro.acelerar(10);
        carro.frear(5);

        // Then (Então)
        assertEquals((Integer) 5, carro.getVelocidadeAtual());
    }

    @Test
    public void naoDeveTerVelocidadeInferiorAZero() throws
            VelocidadeNegativaException,
            CarroNaoLigadoException {
        // System.out.println("naoDeveTerVelocidadeInferiorAZero");
        // Given
        Carro carro = new Carro();
        carro.ligar();
        carro.acelerar(100);

        // When
        carro.frear(50);
        carro.frear(51);

        // Then
        assertEquals((Integer) 0, carro.getVelocidadeAtual());
    }

    @Test
    public void deveDestrancarCorretamente() {
        // Given
        Carro carro = new Carro();

        // When
        carro.destrancar();

        // Then
        assertEquals(false, carro.getTrancado());
    }

    @Test
    public void deveTrancarCorretamente() {
        // Given
        Carro carro = new Carro();

        // When
        carro.destrancar();
        carro.trancar();

        // Then
        assertEquals(true, carro.getTrancado());
    }

    @Test
    public void aoTrancarUmCarroJaTrancadoNaoDeveFazerNada() {
        // Given
        Carro carro = new Carro();
        carro.ligar();

        // When
        carro.trancar();
        carro.trancar();

        // Then
        assertEquals(true, carro.getTrancado());
    }

    @Test
    // deveSerCarrosIguaisComMesmaMarcaModelo()
    public void deveSerPossivelCompararDoisCarros() {
        // Given
        Carro carro01 = new Carro("Preto", "BMW", "X1", 350);
        Carro carro02 = new Carro("Cinza", "BMW", "X1", 320);

        // When

        // Then
        assertEquals(carro01, carro02);
    }

    @Test
    public void deveSerPossivelCompararUmCarroConsigoMesmo() {
        Carro carro01 = new Carro("Preto", "BMW", "X1", 350);

        // When

        // Then
        assertEquals(carro01, carro01);
    }

    @Test
    public void naoDeveSerPossivelCompararUmCarroComOutroObjeto() {
        Carro carro01 = new Carro("Preto", "BMW", "X1", 350);
        Object objeto01 = new Object();
        // When

        // Then
        assertNotEquals(carro01, objeto01);
    }

    @Test
    public void deveSerPossivelObterInformacoesDeUmCarro() {
        // Given
        Carro carro = new Carro("Preto", "BMW", "X1", 350);

        // When
        /*String infos = """
                Carro{
                       cor='Preto'
                       , marca='BMW'
                       , modelo='X1'
                       , ligado=false
                       , velocidadeAtual=0
                       , velocidadeMaxima=350
                       , trancado=false
                       };
                """;*/
        String infos = "Carro{" +
                "cor='Preto'" +
                ", marca='BMW'" +
                ", modelo='X1'" +
                ", ligado=false" +
                ", velocidadeAtual=0" +
                ", velocidadeMaxima=350" +
                ", trancado=true" +
                "}";

        // Then
        assertEquals(infos, carro.toString());
    }
}
