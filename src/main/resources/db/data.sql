-- src/main/resources/db/data.sql

-- 1) Countries
INSERT INTO country (name, continent)
SELECT 'Texas', 'N. America'
    WHERE NOT EXISTS (
  SELECT 1 FROM country c WHERE c.name = 'Texas'
);

INSERT INTO country (name, continent)
SELECT 'Houston', 'N. America'
    WHERE NOT EXISTS (
  SELECT 1 FROM country c WHERE c.name = 'Houston'
);

INSERT INTO country (name, continent)
SELECT 'Macedonia', 'Europe'
    WHERE NOT EXISTS (
  SELECT 1 FROM country c WHERE c.name = 'Macedonia'
);

-- 2) Hosts
INSERT INTO host (name, surname, country_id)
SELECT 'Danilo', 'Ivanov', (SELECT id FROM country WHERE name = 'Macedonia')
    WHERE NOT EXISTS (
  SELECT 1 FROM host h
   WHERE h.name = 'Danilo'
     AND h.surname = 'Ivanov'
);

INSERT INTO host (name, surname, country_id)
SELECT 'Olinad', 'Evilov', (SELECT id FROM country WHERE name = 'Texas')
    WHERE NOT EXISTS (
  SELECT 1 FROM host h
   WHERE h.name = 'Olinad'
     AND h.surname = 'Evilov'
);

INSERT INTO host (name, surname, country_id)
SELECT 'Pivo', 'Majstor', (SELECT id FROM country WHERE name = 'Houston')
    WHERE NOT EXISTS (
  SELECT 1 FROM host h
   WHERE h.name = 'Pivo'
     AND h.surname = 'Majstor'
);

-- 3) Accommodations
INSERT INTO accommodation (name, category, host_id, num_rooms, rented)
SELECT 'Trapot', 'HOUSE',
       (SELECT id FROM host WHERE name = 'Danilo' AND surname = 'Ivanov'),
       2, FALSE
    WHERE NOT EXISTS (
  SELECT 1 FROM accommodation a
   WHERE a.name = 'Trapot'
     AND a.host_id = (SELECT id FROM host WHERE name = 'Danilo' AND surname = 'Ivanov')
);

INSERT INTO accommodation (name, category, host_id, num_rooms, rented)
SELECT 'Sopiste', 'HOTEL',
       (SELECT id FROM host WHERE name = 'Olinad' AND surname = 'Evilov'),
       6, FALSE
    WHERE NOT EXISTS (
  SELECT 1 FROM accommodation a
   WHERE a.name = 'Sopiste'
     AND a.host_id = (SELECT id FROM host WHERE name = 'Olinad' AND surname = 'Evilov')
);

INSERT INTO accommodation (name, category, host_id, num_rooms, rented)
SELECT 'Boba', 'APARTMENT',
       (SELECT id FROM host WHERE name = 'Pivo' AND surname = 'Majstor'),
       1, TRUE
    WHERE NOT EXISTS (
  SELECT 1 FROM accommodation a
   WHERE a.name = 'Boba'
     AND a.host_id = (SELECT id FROM host WHERE name = 'Pivo' AND surname = 'Majstor')
);

-- -- 4) Users
-- INSERT INTO users (username, password, name, surname, role)
-- SELECT 'admin', '<bcrypt-hash-of-admin>', 'ADMIN', 'admin', 'ROLE_ADMIN'
--     WHERE NOT EXISTS (
--   SELECT 1 FROM users u WHERE u.username = 'admin'
-- );
--
-- INSERT INTO users (username, password, name, surname, role)
-- SELECT 'user', '<bcrypt-hash-of-user>', 'user', 'user', 'ROLE_USER'
--     WHERE NOT EXISTS (
--   SELECT 1 FROM users u WHERE u.username = 'user'
-- );
--
-- INSERT INTO users (username, password, name, surname, role)
-- SELECT 'host', '<bcrypt-hash-of-host>', 'host', 'host', 'ROLE_HOST'
--     WHERE NOT EXISTS (
--   SELECT 1 FROM users u WHERE u.username = 'host'
-- );
