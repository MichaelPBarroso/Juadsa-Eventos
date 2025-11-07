package br.com.juadsa.regional.infrastructure.config;

import br.com.juadsa.regional.application.controller.RegionalController;
import br.com.juadsa.regional.infrastructure.repositories.RegionalRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegionalConfig {

    @Bean
    public RegionalController regionalController(final RegionalRepository regionalRepository){
        return RegionalController.create(regionalRepository);
    }

}
