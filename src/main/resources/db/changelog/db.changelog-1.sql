--liquibase formatted sql

--changeset dmitry:1
CREATE TABLE IF NOT EXISTS log_info
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    date        TIMESTAMP,
    level       VARCHAR(16),
    pid         VARCHAR(10),
    app_name    VARCHAR(32),
    thread_name VARCHAR(64),
    class_name  VARCHAR(100),
    msg         TEXT,
    thrown      TEXT,
    cause       TEXT
)