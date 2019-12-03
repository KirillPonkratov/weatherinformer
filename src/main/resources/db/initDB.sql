DROP TRIGGER IF EXISTS delete_old_users ON users_weather_informer;

CREATE TABLE IF NOT EXISTS users_weather_informer
(
    id               uuid PRIMARY KEY,
    date             TIMESTAMP DEFAULT now(),
    city             VARCHAR(50),
    weather_provider VARCHAR(50)
);

CREATE OR REPLACE FUNCTION delete_old_users()
    RETURNS trigger AS
$BODY$
BEGIN
    DELETE FROM users_weather_informer WHERE now() >= date + '30 days'::interval;
    IF NOT FOUND THEN
        RETURN NULL;
    END IF;
    RETURN old;
END;
$BODY$
    LANGUAGE plpgsql;

CREATE TRIGGER delete_old_users
    AFTER INSERT
    ON users_weather_informer
    FOR ROW
EXECUTE PROCEDURE delete_old_users();