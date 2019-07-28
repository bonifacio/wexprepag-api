package com.bonifacio.wexprepag.api.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bonifacio.wexprepag.api.domain.Cartao;
import com.bonifacio.wexprepag.api.gateway.database.entity.CartaoData;

@Configuration
public class ModelMapperConfig {

	@Bean
    public ModelMapper modelMapper() {
    	ModelMapper modelMapper = new ModelMapper();
    	modelMapper.addMappings(new PropertyMap<Cartao, CartaoData>() {
			@Override
			protected void configure() {
//				map().setSenha(source.getSenhaCriptografada());
			}
		});
    	modelMapper.addMappings(new PropertyMap<CartaoData, Cartao>() {
			@Override
			protected void configure() {
//				map().setSenhaCriptografada(source.getSenha());
//				map().setSenha(null);
			}
		});
		return modelMapper;
    }
}
