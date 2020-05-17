package ru.otus.processor;

import org.junit.jupiter.api.Test;
import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.example.ExampleTest;
import ru.otus.example.MultipleBeforeAndAfterClass;
import ru.otus.example.NoTestsClass;
import ru.otus.example.TestAssertions;
import ru.otus.model.TestClass;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnnotationProcessorTest {
    @Test
    public void testAnnotationsProcessingForClassExampleTest() throws Exception {
        TestClass testClass = new AnnotationProcessor(ExampleTest.class).process();
        assertThat(testClass.getClazz()).isEqualTo(ExampleTest.class);
        // Before
        assertThat(testClass.getBeforeMethods().size()).isEqualTo(1);
        assertThat(testClass.getBeforeMethods().get(0).getName()).isEqualTo("setUp");
        assertThat(testClass.getBeforeMethods().get(0).isAnnotationPresent(Before.class)).isTrue();
        // After
        assertThat(testClass.getAfterMethods().size()).isEqualTo(1);
        assertThat(testClass.getAfterMethods().get(0).getName()).isEqualTo("tearDown");
        assertThat(testClass.getAfterMethods().get(0).isAnnotationPresent(After.class)).isTrue();
        // Test
        assertThat(testClass.getTestMethods().size()).isEqualTo(2);
        assertThat(testClass.getTestMethods().stream().map(Method::getName)).containsOnly("test1", "test2");
        assertThat(testClass.getTestMethods().stream().map(i -> i.isAnnotationPresent(ru.otus.annotation.Test.class)))
                .containsOnly(true);
    }

    @Test
    public void testAnnotationsProcessingForClassTestAssertions() throws Exception {
        TestClass testClass = new AnnotationProcessor(TestAssertions.class).process();
        assertThat(testClass.getClazz()).isEqualTo(TestAssertions.class);
        // Before
        assertThat(testClass.getBeforeMethods().size()).isEqualTo(0);
        // After
        assertThat(testClass.getAfterMethods().size()).isEqualTo(0);
        // Test
        assertThat(testClass.getTestMethods().size()).isEqualTo(2);
        assertThat(testClass.getTestMethods().stream().map(Method::getName)).containsOnly("test1", "test2");
        assertThat(testClass.getTestMethods().get(0).isAnnotationPresent(ru.otus.annotation.Test.class)).isTrue();
        assertThat(testClass.getTestMethods().get(1).isAnnotationPresent(ru.otus.annotation.Test.class)).isTrue();
    }

    @Test
    public void testAnnotationsProcessingForMultipleBeforeAndAfterClass() throws Exception {
        TestClass testClass = new AnnotationProcessor(MultipleBeforeAndAfterClass.class).process();
        assertThat(testClass.getClazz()).isEqualTo(MultipleBeforeAndAfterClass.class);
        // Before
        assertThat(testClass.getBeforeMethods().size()).isEqualTo(2);
        assertThat(testClass.getBeforeMethods().stream().map(Method::getName))
                .containsOnly("setUpNumberOne", "setUpNumberTwo");
        assertThat(testClass.getBeforeMethods().get(0).isAnnotationPresent(Before.class)).isTrue();
        assertThat(testClass.getBeforeMethods().get(1).isAnnotationPresent(Before.class)).isTrue();
        // After
        assertThat(testClass.getAfterMethods().size()).isEqualTo(2);
        assertThat(testClass.getAfterMethods().stream().map(Method::getName))
                .containsOnly("tearDownOne", "tearDownTwo");
        assertThat(testClass.getAfterMethods().get(0).isAnnotationPresent(After.class)).isTrue();
        assertThat(testClass.getAfterMethods().get(1).isAnnotationPresent(After.class)).isTrue();
        // Test
        assertThat(testClass.getTestMethods().size()).isEqualTo(3);
        assertThat(testClass.getTestMethods().stream().map(Method::getName))
                .containsOnly("test1", "test2", "complexTestNumberThree");
        assertThat(testClass.getTestMethods().stream().map(i -> i.isAnnotationPresent(ru.otus.annotation.Test.class)))
                .containsOnly(true);
    }

    @Test
    public void testNoTestsAnnotationProcessor() {
        Exception exception = assertThrows(
                NoSuchMethodException.class,
                () -> new AnnotationProcessor(NoTestsClass.class).process()
        );
        assertThat(exception.getMessage()).contains("No tests in ru.otus.example.NoTestsClass");
    }
}
