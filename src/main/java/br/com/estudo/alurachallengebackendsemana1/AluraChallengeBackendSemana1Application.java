package br.com.estudo.alurachallengebackendsemana1;

import br.com.estudo.alurachallengebackendsemana1.utils.security.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CorsConfig.class)
public class AluraChallengeBackendSemana1Application {

    public static void main(String[] args) {
        SpringApplication.run(AluraChallengeBackendSemana1Application.class, args);
    }

}
