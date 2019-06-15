# Crud Vehicles

### Pré-requisitos
#### Node e vue cli
```bash
npm install -g @vue/cli
npm install -g @vue/cli-init
```

## Primeira execução do aplicativo

dentro do diretório raiz:

```bash
mvn clean install
```
irá fazer com que rode o comando `npm run build` dentro da pasta *frontend* e depois rodar `mvn clean install` dentro da pasta *backend*. Após compilado, rode o comando abaixo e acesse: `http://localhost:8080`

```bash
java -jar backend/target/backend-0.0.1-SNAPSHOT.jar
```

## Executando apenas o backend
dentro do diretório raiz: 
```bash
mvn --projects backend spring-boot:run
```

## Executando apenas o frontend
dentro do diretório frontend: 
```bash
npm run dev
```

## Realizando requisições
### Types Vehicle
todos os endpoints da entidade `vehicleTypes` ficam em `http://localhost:8080/api/v1/vehicleTypes`

##### POST
request `(http://localhost:8080/api/v1/vehicleTypes)`

```json
{
	"name": "Type name teste",
	"desc": "Desc name test"
}
```
response
```json
{
    "model": {
        "id": 1,
        "name": "Type name teste",
        "desc": "Desc name test"
    },
    "message": null,
    "success": 1
}
```

##### GET
request `(http://localhost:8080/api/v1/vehicleTypes)`

response
```json
{
    "model": [
        {
            "id": 1,
            "name": "Type name teste",
            "desc": "Desc name test"
        }
    ],
    "message": null,
    "success": 1
}
```

##### GET BY ID
request `(http://localhost:8080/api/v1/vehicleTypes/{id})`

response
```json
{
    "model": {
            "id": 1,
            "name": "Type name teste",
            "desc": "Desc name test"
     },
    "message": null,
    "success": 1
}
```

##### PUT

request `(http://localhost:8080/api/v1/vehicleTypes/{id})`
```json
{
	"name": "Type name alterado"
}
```
response
```json
{
    "model": {
        "id": 1,
        "name": "Type name alterado",
        "desc": "Desc name test"
    },
    "message": null,
    "success": 1
}
```
##### DELETE
request `(http://localhost:8080/api/v1/vehicleTypes/{id})`

response
```json
{
    "model": null,
    "message": "Registro deletado com sucesso",
    "success": 1
}
```

### Vehicles
todos os endpoints da entidade `vehicles` ficam em `http://localhost:8080/api/v1/vehicles`

##### POST
request `(http://localhost:8080/api/v1/vehicles)`

```json
{
	"name": "Name teste",
	"desc": "Desc teste",
	"idVehicleType": 2,
	"plate": "AAA1234"
}
```
response
```json
{
    "model": {
        "id": 3,
        "name": "Name teste",
        "desc": "Desc teste",
        "idVehicleType": 2,
        "plate": "AAA1234",
        "vehicleType": {
            "id": 2,
            "name": "Type name teste",
            "desc": "Desc name test"
        }
    },
    "message": null,
    "success": 1
}
```

##### GET
request `(http://localhost:8080/api/v1/vehicles)`

response
```json
{
    "model": [
        {
            "id": 3,
            "name": "Name teste",
            "desc": "Desc teste",
            "idVehicleType": 2,
            "plate": "AAA1234",
            "vehicleType": {
                "id": 2,
                "name": "Type name teste",
                "desc": "Desc name test"
            }
        }
    ],
    "message": null,
    "success": 1
}
```

##### GET BY ID
request `(http://localhost:8080/api/v1/vehicles/{id})`

response
```json
{
    "model": {
        "id": 3,
        "name": "Name teste",
        "desc": "Desc teste",
        "idVehicleType": 2,
        "plate": "AAA1234",
        "vehicleType": {
            "id": 2,
            "name": "Type name teste",
            "desc": "Desc name test"
        }
    },
    "message": null,
    "success": 1
}
```

##### PUT

request `(http://localhost:8080/api/v1/vehicles/{id})`
```json
{
	"desc": "Desc alterado"
}
```
response
```json
{
    "model": {
        "id": 3,
        "name": "Name teste",
        "desc": "Desc alterado",
        "idVehicleType": 2,
        "plate": "AAA1234",
        "vehicleType": {
            "id": 2,
            "name": "Type name teste",
            "desc": "Desc name test"
        }
    },
    "message": null,
    "success": 1
}

```
##### DELETE
request `(http://localhost:8080/api/v1/vehicles/{id})`

response
```json
{
    "model": null,
    "message": "Registro deletado com sucesso",
    "success": 1
}
```