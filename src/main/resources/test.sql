CREATE TABLE collectivity (
                              id VARCHAR PRIMARY KEY,
                              name VARCHAR NOT NULL,
                              address VARCHAR,
                              collectivity_type VARCHAR,
                              email VARCHAR,
                              phone_number INTEGER
);


CREATE TABLE member (
                        id VARCHAR PRIMARY KEY,
                        first_name VARCHAR NOT NULL,
                        last_name VARCHAR NOT NULL,
                        birth_date DATE,
                        gender VARCHAR,
                        address VARCHAR,
                        profession VARCHAR,
                        phone_number VARCHAR,
                        email VARCHAR,
                        occupation VARCHAR,
                        collectivity_id VARCHAR,

                        CONSTRAINT fk_collectivity
                            FOREIGN KEY (collectivity_id)
                                REFERENCES collectivity(id)
                                ON DELETE SET NULL
);

CREATE TABLE member_referee (
                                member_id VARCHAR,
                                referee_id VARCHAR,

                                PRIMARY KEY (member_id, referee_id),

                                CONSTRAINT fk_member
                                    FOREIGN KEY (member_id)
                                        REFERENCES member(id)
                                        ON DELETE CASCADE,

                                CONSTRAINT fk_referee
                                    FOREIGN KEY (referee_id)
                                        REFERENCES member(id)
                                        ON DELETE CASCADE
);