DROP TABLE IF EXISTS member_referee;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS collectivity;

CREATE TABLE collectivity (
                              id VARCHAR(20) PRIMARY KEY,
                              number INTEGER UNIQUE,
                              name VARCHAR(100) UNIQUE,
                              locality VARCHAR(100),
                              specialization VARCHAR(100)
);

CREATE TABLE member (
                        id VARCHAR(20) PRIMARY KEY,
                        first_name VARCHAR(100),
                        last_name VARCHAR(100),
                        birth_date DATE,
                        gender VARCHAR(10),
                        address TEXT,
                        occupation VARCHAR(100),
                        phone_number VARCHAR(20),
                        email VARCHAR(150),
                        role VARCHAR(50),
                        collectivity_id VARCHAR(20),

                        FOREIGN KEY (collectivity_id)
                            REFERENCES collectivity(id)
);

CREATE TABLE membership_fee (
                                id VARCHAR(20) PRIMARY KEY,
                                label VARCHAR(100),
                                status VARCHAR(30),
                                frequency VARCHAR(30),
                                amount DECIMAL(12,2),
                                eligible_since DATE,

                                collectivity_id VARCHAR(20),

                                FOREIGN KEY (collectivity_id)
                                    REFERENCES collectivity(id)
);

CREATE TABLE account (
                         id VARCHAR(30) PRIMARY KEY,
                         type VARCHAR(50),
                         balance DECIMAL(12,2),

                         holder_name VARCHAR(100),
                         phone_number VARCHAR(20),

                         collectivity_id VARCHAR(20),

                         FOREIGN KEY (collectivity_id)
                             REFERENCES collectivity(id)
);

CREATE TABLE payment (
                         id SERIAL PRIMARY KEY,

                         member_id VARCHAR(20),
                         collectivity_id VARCHAR(20),
                         account_id VARCHAR(30),

                         amount DECIMAL(12,2),
                         payment_method VARCHAR(30),
                         payment_date DATE,

                         FOREIGN KEY(member_id)
                             REFERENCES member(id),

                         FOREIGN KEY(collectivity_id)
                             REFERENCES collectivity(id),

                         FOREIGN KEY(account_id)
                             REFERENCES account(id)
);

INSERT INTO collectivity VALUES
                             ('col-1',1,'Mpanorina','Ambatondrazaka','Riziculture'),
                             ('col-2',2,'Dobo voalohany','Ambatondrazaka','Pisciculture'),
                             ('col-3',3,'Tantely mamy','Brickaville','Apiculture');

INSERT INTO member VALUES
                       ('C1-M1','Prénom membre 1','Nom membre 1',
                        '1980-02-01','M',
                        'Lot II V M Ambato',
                        'Riziculteur',
                        '0341234567',
                        'member.1@fed-agri.mg',
                        'PRESIDENT',
                        'col-1'),

                       ('C1-M2','Prénom membre 2','Nom membre 2',
                        '1982-03-05','M',
                        'Lot II F Ambato',
                        'Agriculteur',
                        '0321234567',
                        'member.2@fed-agri.mg',
                        'VICE_PRESIDENT',
                        'col-1'),

                       ('C1-M3','Prénom membre 3','Nom membre 3',
                        '1992-03-10','M',
                        'Lot II J Ambato',
                        'Collecteur',
                        '0331234567',
                        'member.3@fed-agri.mg',
                        'SECRETARY',
                        'col-1');

INSERT INTO membership_fee VALUES
                               ('cot-1','Cotisation annuelle','ACTIVE',
                                'ANNUALLY',100000,'2026-01-01','col-1'),

                               ('cot-2','Cotisation annuelle','ACTIVE',
                                'ANNUALLY',100000,'2026-01-01','col-2'),

                               ('cot-3','Cotisation annuelle','ACTIVE',
                                'ANNUALLY',50000,'2026-01-01','col-3');

INSERT INTO account VALUES
                        ('C1-A-CASH','CASH',750000,NULL,NULL,'col-1'),

                        ('C1-A-MOBILE-1','ORANGE_MONEY',0,
                         'Mpanorina','0370489612','col-1'),

                        ('C2-A-CASH','CASH',450000,NULL,NULL,'col-2'),

                        ('C2-A-MOBILE-1','ORANGE_MONEY',100000,
                         'Dobo voalohany','0320489612','col-2'),

                        ('C3-A-CASH','CASH',0,NULL,NULL,'col-3');

INSERT INTO payment
(member_id, collectivity_id, account_id, amount, payment_method, payment_date)
VALUES
    ('C1-M1','col-2','C2-A-CASH',60000,'CASH','2026-01-01'),
    ('C1-M2','col-2','C2-A-CASH',90000,'CASH','2026-01-01'),
    ('C1-M3','col-2','C2-A-CASH',100000,'CASH','2026-01-01'),
    ('C1-M4','col-2','C2-A-CASH',100000,'CASH','2026-01-01'),
    ('C1-M5','col-2','C2-A-CASH',100000,'CASH','2026-01-01'),
    ('C1-M6','col-2','C2-A-CASH',100000,'CASH','2026-01-01'),
    ('C1-M7','col-2','C2-A-MOBILE-1',40000,'MOBILE_MONEY','2026-01-01'),
    ('C1-M8','col-2','C2-A-MOBILE-1',60000,'MOBILE_MONEY','2026-01-01');

