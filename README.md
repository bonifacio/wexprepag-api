[![CircleCI](https://circleci.com/gh/bonifacio/wexprepag-api/tree/master.svg?style=svg)](https://circleci.com/gh/bonifacio/wexprepag-api/tree/master)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=bonifacio_wexprepag-api&metric=coverage)](https://sonarcloud.io/dashboard?id=bonifacio_wexprepag-api)

# wexprepag-api

A aplicação foi construida seguindo alguns conceitos de clean architeture (conforme a imagem seguir). A implementação não foi purista, pois foram utilizados alguns recursos do spring nas camadas que deveriam ser indepentes de frameworks. 

![imagem](https://github.com/bonifacio/wexprepag-api/blob/master/clean.png)

A divisão dos pacotes ficou da seguinte forma:
```
---com.bonifacio.wexprepag.api
------config
------domain
------gateway
---------database
---------http
------usecase
```

O banco de dados utilizado foi o h2 (em memória) e as tabelas são criadas quando a aplicação é iniciada usando o flyway.

Para executar a aplicação é necessário ter o Docker instalado e executar os seguintes comandos:
```
docker build -t [nome da imagem] .
docker run -p 8080:8080 [nome da imagem]
```
A aplicação estará disponível na porta 8080 e a documentação da api pode ser acessada através do endereço [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).
É possíve testar a aplicação através da documentação.
