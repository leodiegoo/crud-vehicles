# Crud Vehicles

### Pré-requisitos
#### Node e vue cli
```
npm install -g @vue/cli
npm install -g @vue/cli-init
```

## Primeira execução do aplicativo

dentro do diretório raiz:

``` bash
mvn clean install
```
irá fazer com que rode o comando `npm run build` dentro da pasta *frontend* e depois rodar `mvn clean install` dentro da pasta *backend*. Após compilado, rode o comando abaixo e acesse: `http://localhost:8080`

``` bash
java -jar backend/target/backend-0.0.1-SNAPSHOT.jar
```

## Executando apenas o backend
dentro do diretório raiz: 
``` bash
mvn --projects backend spring-boot:run
```

## Executando apenas o frontend
dentro do diretório frontend: 
``` bash
npm run dev
```