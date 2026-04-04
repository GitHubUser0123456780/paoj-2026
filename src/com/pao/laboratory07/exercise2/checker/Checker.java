package com.pao.laboratory07.exercise2.checker;

import com.pao.laboratory07.exercise2.Main;
import com.pao.test.IOTest;

public class Checker {
    public static void main(String[] args) {
        IOTest.runFlat("src/com/pao/laboratory07/exercise2/tests", Main::main);
    }
}

