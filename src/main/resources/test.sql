DROP TABLE IF EXISTS member_referee;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS collectivity;

CREATE TABLE collectivity (
                              id SERIAL PRIMARY KEY,
                              collectivity_number INTEGER UNIQUE,
                              name VARCHAR(255) UNIQUE NOT NULL,
                              address VARCHAR(255),
                              collectivity_type VARCHAR(100),
                              email VARCHAR(255),
                              phone_number VARCHAR(20),
                              federation_authorized BOOLEAN DEFAULT FALSE
);

CREATE TABLE member (
                        id SERIAL PRIMARY KEY,
                        first_name VARCHAR(255) NOT NULL,
                        last_name VARCHAR(255) NOT NULL,
                        birth_date DATE,
                        gender VARCHAR(10)
                            CHECK (gender IN ('M','F')),
                        address VARCHAR(255),
                        profession VARCHAR(255),
                        phone_number VARCHAR(20),
                        email VARCHAR(255),
                        occupation VARCHAR(255),
                        role VARCHAR(50),
                        collectivity_id INTEGER
                                                REFERENCES collectivity(id)
                                                    ON DELETE SET NULL
);



CREATE TABLE account (
                         id SERIAL PRIMARY KEY,
                         collectivity_id INTEGER REFERENCES collectivity(id),
                         type VARCHAR(20) CHECK (type IN ('CASH', 'BANK', 'MOBILE')),
                         provider VARCHAR(100),
                         account_number VARCHAR(50),
                         balance DOUBLE PRECISION DEFAULT 0
);

CREATE TABLE payment (
                         id SERIAL PRIMARY KEY,
                         member_id INTEGER REFERENCES member(id),
                         collectivity_id INTEGER REFERENCES collectivity(id),
                         amount DOUBLE PRECISION NOT NULL,
                         payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         payment_method VARCHAR(20),
                         account_id INTEGER REFERENCES account(id)
);

