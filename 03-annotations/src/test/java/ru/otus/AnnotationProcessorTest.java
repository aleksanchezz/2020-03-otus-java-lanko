package ru.otus;

import org.junit.jupiter.api.Test;

import ru.otus.example.ExampleTest;
import ru.otus.model.TestResult;
import ru.otus.example.NoTestsClass;
import ru.otus.example.TestAssertions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnnotationProcessorTest {

    @Test
    public void testExampleTest() throws Exception {
        List<TestResult> testResults = new AnnotationProcessor(ExampleTest.class)
                .executeTests();

        long successful = testResults.stream().filter(TestResult::getStatus).count();
        long failed = testResults.size() - successful;

        assertThat(successful).isEqualTo(2);
        assertThat(failed).isEqualTo(0);

        TestResultInterpreter.showResults(testResults);
    }

    @Test
    public void testAssertions() throws Exception {
        List<TestResult> testResults = new AnnotationProcessor(TestAssertions.class)
                .executeTests();

        long successful = testResults.stream().filter(TestResult::getStatus).count();
        long failed = testResults.size() - successful;

        assertThat(successful).isEqualTo(1);
        assertThat(failed).isEqualTo(1);

        TestResultInterpreter.showResults(testResults);
    }

    @Test()
    public void testNoTests() {
        Exception exception = assertThrows(
                NoSuchMethodException.class,
                () -> {
                    List<TestResult> testResults = new AnnotationProcessor(NoTestsClass.class)
                            .executeTests();
                });
        assertThat(exception.getMessage()).contains("No tests in ru.otus.testClass.NoTestsClass");
    }
}
