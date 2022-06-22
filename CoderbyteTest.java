import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CoderbyteTest {

    public enum Action {
        DIVIDE,
        MULTIPLY,
        REMINDER_OF_THE_DIVISION
    }

    public int numbers(Integer a, Integer b, Action action) {
        var result = 0;
        switch (action) {
            case DIVIDE -> {
                try {
                    result = a / b;
                } catch (ArithmeticException ae) {
                    System.out.println("Divide by zero probably?");
                }
            }
            case MULTIPLY -> {
                result = a * b;
            }
            case REMINDER_OF_THE_DIVISION -> {
                result = a % b;
            }
        }
        ;
        return result;
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3, 6",
            "10, 30, 300",
            "15, 0, 0"
    })
    void shouldReturnMultiplicationResult(int a, int b, int x) {
        Assertions.assertThat(numbers(a, b, Action.MULTIPLY)).isEqualTo(x);
    }

    @ParameterizedTest
    @CsvSource({
            "6, 2, 3",
            "300, 30, 10",
            "15, 1, 15"
    })
    void shouldReturnDivideResult(int a, int b, int x) {
        Assertions.assertThat(numbers(a, b, Action.DIVIDE)).isEqualTo(x);
    }

    @ParameterizedTest
    @CsvSource({
            "6, 2, 0",
            "64, 30, 4",
            "15, 10, 5"
    })
    void shouldReturnModuloResult(int a, int b, int x) {
        Assertions.assertThat(numbers(a, b, Action.REMINDER_OF_THE_DIVISION)).isEqualTo(x);
    }
}