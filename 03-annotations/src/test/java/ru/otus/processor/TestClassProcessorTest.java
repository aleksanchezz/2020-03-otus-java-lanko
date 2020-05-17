package ru.otus.processor;

import org.junit.jupiter.api.Test;

import ru.otus.TestResultInterpreter;
import ru.otus.example.ExampleTest;
import ru.otus.example.ExceptionInBeforeMethodClass;
import ru.otus.model.TestClass;
import ru.otus.model.TestResult;
import ru.otus.example.NoTestsClass;
import ru.otus.example.TestAssertions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestClassProcessorTest {

    @Test
    public void testExampleTest() throws Exception {
        TestClass testClass = new AnnotationProcessor(ExampleTest.class).process();
        List<TestResult> testResults = new TestClassProcessor(testClass).executeTests();

        long successful = testResults.stream().filter(TestResult::getStatus).count();
        long failed = testResults.size() - successful;

        assertThat(successful).isEqualTo(2);
        assertThat(failed).isEqualTo(0);

        TestResultInterpreter.showResults(testResults);
    }

    @Test
    public void testAssertions() throws Exception {
        TestClass testClass = new AnnotationProcessor(TestAssertions.class).process();
        List<TestResult> testResults = new TestClassProcessor(testClass).executeTests();

        long successful = testResults.stream().filter(TestResult::getStatus).count();
        long failed = testResults.size() - successful;

        assertThat(successful).isEqualTo(1);
        assertThat(failed).isEqualTo(1);

        TestResultInterpreter.showResults(testResults);
    }

    @Test
    public void testMultipleBeforeAndAfterClass() throws Exception {
        assert true;
    }

    @Test
    public void testExceptionInBeforeMethodClass() throws Exception {
        TestClass testClass = new AnnotationProcessor(ExceptionInBeforeMethodClass.class).process();
        Exception exception =  assertThrows(
                RuntimeException.class,
                () -> new TestClassProcessor(testClass).executeTests()
        );
        assertThat(exception.getMessage()).contains("Exception while @before/@after methods execution - terminate");
    }
}
