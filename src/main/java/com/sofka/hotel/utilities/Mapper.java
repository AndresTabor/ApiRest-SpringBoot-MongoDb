package com.sofka.hotel.utilities;

import org.modelmapper.Conditions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class Mapper {
    @Bean
    public ModelMapper getMapper() {
        var modelmapper = new ModelMapper();
        var config = modelmapper.getConfiguration();
        config.setPropertyCondition(Conditions.isNotNull());
        return modelmapper;
    }
}
