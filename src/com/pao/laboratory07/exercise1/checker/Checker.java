package com.pao.laboratory07.exercise1.checker;

import com.pao.laboratory07.exercise1.Main;
import com.pao.test.IOTest;

public class Checker {
    public static void main(String[] args) {
        // Ruleaza toate testele:
        IOTest.runParts("src/com/pao/laboratory07/exercise1/tests", Main::main);

        // Sau ruleaza doar testele pentru o parte specifica:
//        IOTest.runPart("src/com/pao/laboratory07/exercise1/tests", "partA", Main::main);
//        IOTest.runPart("src/com/pao/laboratory07/exercise1/tests", "partB", Main::main);
//        IOTest.runPart("src/com/pao/laboratory07/exercise1/tests", "partC", Main::main);
    }
}
