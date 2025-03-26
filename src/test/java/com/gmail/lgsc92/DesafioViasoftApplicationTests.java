package com.gmail.lgsc92;

import static org.assertj.core.api.Assertions.assertThat;

import com.gmail.lgsc92.controller.EmailController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesafioViasoftApplicationTests {

    @Autowired
    private EmailController emailController;

    @Test
    void contextLoads() {
        assertThat(emailController).isNotNull();
    }
}
