
CREATE DATABASE IF NOT EXISTS app;

USE app;

CREATE TABLE IF NOT EXISTS payment_entity (
    id VARCHAR(255) PRIMARY KEY,
    amount INT,
    created DATETIME,
    status VARCHAR(50),
    transaction_id VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS credit_request_entity (
    id VARCHAR(255) PRIMARY KEY,
    bank_id VARCHAR(255),
    created DATETIME,
    status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS order_entity (
    id VARCHAR(255) PRIMARY KEY,
    created DATETIME,
    credit_id VARCHAR(255),
    payment_id VARCHAR(255)
);

-- Создание индексов для улучшения производительности
CREATE INDEX idx_payment_entity_created ON payment_entity(created);
CREATE INDEX idx_credit_request_entity_created ON credit_request_entity(created);
CREATE INDEX idx_order_entity_created ON order_entity(created);