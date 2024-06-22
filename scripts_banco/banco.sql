DROP TABLE IF EXISTS movimiento;
DROP TABLE IF EXISTS cuenta;
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cliente_id VARCHAR(20) UNIQUE NOT NULL,
    estado BOOLEAN NOT NULL
);

CREATE TABLE cuenta (
	id SERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(20) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(10) NOT NULL,
    saldo_inicial DECIMAL(18, 2) NOT NULL,
    saldo_disponible DECIMAL(18, 2) NOT NULL,
    estado BOOLEAN NOT NULL,
    cliente_id INTEGER NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

CREATE TABLE movimiento (
    id SERIAL PRIMARY KEY,
    fecha TIMESTAMP NOT NULL,
    tipo_movimiento VARCHAR(10) NOT NULL,
    valor DECIMAL(18, 2) NOT null,
    saldo_disponible DECIMAL(18, 2) NOT NULL,
    cuenta_id INTEGER NOT NULL,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta (id)
);



