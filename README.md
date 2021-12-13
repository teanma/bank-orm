# Bank ORM

## Configuración

Arranca el contenedor de mysql

Abre una terminal, ve hasta el directorio del proyecto y ejecuta
```bash
docker exec -i instituto mysql -u root -pfederica < src/main/resources/bank.sql
```

## Requisitos

Crea y configura los POJOs y JPARepository de las entidades: account, customer y movement.

### Endpoints

#### CustomerController /customers

- Listado de clientes
- Crear nuevo cliente
- Eliminar cliente
- Ver datos de cliente

#### AccountController /accounts

- Listado de cuentas
- Ver cuenta
- Eliminar cuenta

#### AccountController /customers/{id]/accounts

- Listado de cuentas de cliente
- Añadir cuenta a cliente

Cuando se recupera una cuenta se deben cargar todos los movimientos de la misma