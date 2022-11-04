package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.initialiser.Postgres;
import org.junit.jupiter.api.AfterAll;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.cvetkov.fedor.laboratoryWork.controller")
public class ControllerTestCase {

    @AfterAll
    static void destroy(){
        Postgres.container.stop();
    }
}
