-- src/main/resources/db/schema.sql

-- 1) Countries
CREATE TABLE IF NOT EXISTS country (
                         id         SERIAL         PRIMARY KEY,
                         name       VARCHAR(100)   NOT NULL,
                         continent  VARCHAR(100)   NOT NULL
);

-- 2) Hosts
CREATE TABLE IF NOT EXISTS host (
                      id          SERIAL       PRIMARY KEY,
                      name        VARCHAR(100)  NOT NULL,
                      surname     VARCHAR(100)  NOT NULL,
                      country_id  INTEGER       NOT NULL,
                      CONSTRAINT fk_host_country
                          FOREIGN KEY (country_id)
                              REFERENCES country(id)
);

-- 3) Accommodations
CREATE TABLE IF NOT EXISTS accommodation (
                               id         SERIAL       PRIMARY KEY,
                               name       VARCHAR(255) NOT NULL,
                               category   VARCHAR(50)  NOT NULL,
                               host_id    INTEGER      NOT NULL,
                               num_rooms  INTEGER      NOT NULL,
                               rented     BOOLEAN      NOT NULL DEFAULT FALSE,
                               CONSTRAINT fk_accommodation_host
                                   FOREIGN KEY (host_id)
                                       REFERENCES host(id)
);

-- 4) Users
CREATE TABLE IF NOT EXISTS users (
                       username                  VARCHAR(50)   PRIMARY KEY,
                       password                  VARCHAR(255)  NOT NULL,
                       name                      VARCHAR(100)  NOT NULL,
                       surname                   VARCHAR(100)  NOT NULL,
                       is_account_non_expired    BOOLEAN       NOT NULL DEFAULT TRUE,
                       is_account_non_locked     BOOLEAN       NOT NULL DEFAULT TRUE,
                       is_credentials_non_expired BOOLEAN      NOT NULL DEFAULT TRUE,
                       is_enabled                BOOLEAN       NOT NULL DEFAULT TRUE,
                       role                      VARCHAR(50)   NOT NULL
);

-- 5) Temporary Reservations
CREATE TABLE IF NOT EXISTS temporary_reservations (
                                        id                 SERIAL       PRIMARY KEY,
                                        user_id            VARCHAR(50)  NOT NULL,
                                        accommodation_id   INTEGER      NOT NULL,
                                        CONSTRAINT fk_tempres_user
                                            FOREIGN KEY (user_id)
                                                REFERENCES users(username),
                                        CONSTRAINT fk_tempres_accommodation
                                            FOREIGN KEY (accommodation_id)
                                                REFERENCES accommodation(id)
);

CREATE MATERIALIZED VIEW IF NOT EXISTS accommodations_by_host AS
SELECT host_id,
       COUNT(*) AS accommodations_count
FROM accommodation
GROUP BY host_id;

CREATE INDEX IF NOT EXISTS idx_accommodations_by_host_host_id
    ON accommodations_by_host(host_id);

CREATE MATERIALIZED VIEW IF NOT EXISTS hosts_by_country AS
SELECT h.country_id,
       COUNT(*)    AS hosts_count
FROM host h
GROUP BY h.country_id;

CREATE INDEX IF NOT EXISTS idx_hosts_by_country_country_id
    ON hosts_by_country(country_id);