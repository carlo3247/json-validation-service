# json-validation-service #

## Build & Run ##

```sh
$ cd json-validation-service
$ sbt
> jetty:start
```

## Usage ##
#### POST /schema/ID ####
```curl http://localhost:8080/schema/ID -X POST -H "Content-Type: application/json" -d @schema.json```

#### GET /schema/ID ####
```curl http://localhost:8080/schema/ID -X GET```

#### POST /validate/ID ####
```curl http://localhost:8080/validate/ID -X POST -H "Content-Type: application/json" -d @file.json```
