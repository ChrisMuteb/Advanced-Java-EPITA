package fr.epita.tests.quiz;

import org.junit.jupiter.api.Test;

import static fr.epita.core.maths.services.CommonUtils.factorial;

public class QuizCoreTests {
    @Test
    public void test(){
        // given
        int n = 5;

        // when
        int result = factorial(n);
        System.out.println(result);

        // then
        if(result != 120)
            throw new AssertionError("expected 120, but got: " + result);
        else
            System.out.println("test success");
    }


}
