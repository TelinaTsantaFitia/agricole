CREATE TABLE collectivity (
                              id SERIAL PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              address VARCHAR(255),
                              collectivity_type VARCHAR(100),
                              email VARCHAR(255),
                              phone_number INTEGER
);

CREATE TABLE member (
                        id SERIAL PRIMARY KEY,
                        first_name VARCHAR(255) NOT NULL,
                        last_name VARCHAR(255) NOT NULL,
                        birth_date DATE,
                        gender VARCHAR(10),
                        address VARCHAR(255),
                        profession VARCHAR(255),
                        phone_number INTEGER,
                        email VARCHAR(255),
                        occupation VARCHAR(255),
                        collectivity_id INTEGER REFERENCES collectivity(id) ON DELETE SET NULL
);

CREATE TABLE member_referee (
                                member_id INTEGER REFERENCES member(id) ON DELETE CASCADE,
                                referee_id INTEGER REFERENCES member(id) ON DELETE CASCADE,
                                PRIMARY KEY (member_id, referee_id)
);

-- Données de test (Note : les IDs seront 1, 2, 3...)
INSERT INTO collectivity (name, address, collectivity_type, email, phone_number)
VALUES ('Collectivite Antananarivo', 'Tana', 'RIZ', 'c1@mail.com', 320000000);

INSERT INTO member (first_name, last_name, birth_date, gender, address, profession, phone_number, email, occupation, collectivity_id)
VALUES
    ('Jean', 'Rakoto', '1990-01-01', 'M', 'Tana', 'Agriculteur', 320000001, 'm1@mail.com', 'Riziculteur', 1),
    ('Paul', 'Rabe', '1988-05-10', 'M', 'Tana', 'Agriculteur', 320000002, 'm2@mail.com', 'Riziculteur', 1);


