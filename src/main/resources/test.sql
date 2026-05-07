-- Supprimer toutes les tables
DROP TABLE IF EXISTS payment CASCADE;
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS membership_fee CASCADE;
DROP TABLE IF EXISTS member CASCADE;
DROP TABLE IF EXISTS collectivity CASCADE;

-- Recréer les tables
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
                        FOREIGN KEY (collectivity_id) REFERENCES collectivity(id)
);

CREATE TABLE membership_fee (
                                id VARCHAR(20) PRIMARY KEY,
                                label VARCHAR(100),
                                status VARCHAR(30),
                                frequency VARCHAR(30),
                                amount DECIMAL(12,2),
                                eligible_since DATE,
                                collectivity_id VARCHAR(20),
                                FOREIGN KEY (collectivity_id) REFERENCES collectivity(id)
);

CREATE TABLE account (
                         id VARCHAR(30) PRIMARY KEY,
                         type VARCHAR(50),
                         balance DECIMAL(12,2),
                         holder_name VARCHAR(100),
                         phone_number VARCHAR(20),
                         collectivity_id VARCHAR(20),
                         FOREIGN KEY (collectivity_id) REFERENCES collectivity(id)
);

CREATE TABLE payment (
                         id SERIAL PRIMARY KEY,
                         member_id VARCHAR(20),
                         collectivity_id VARCHAR(20),
                         account_id VARCHAR(30),
                         amount DECIMAL(12,2),
                         payment_method VARCHAR(30),
                         payment_date DATE,
                         FOREIGN KEY (member_id) REFERENCES member(id),
                         FOREIGN KEY (collectivity_id) REFERENCES collectivity(id),
                         FOREIGN KEY (account_id) REFERENCES account(id)
);

-- Insérer les données
INSERT INTO collectivity VALUES
                             ('col-1', 1, 'Mpanorina', 'Ambatondrazaka', 'Riziculture'),
                             ('col-2', 2, 'Dobo voalohany', 'Ambatondrazaka', 'Pisciculture'),
                             ('col-3', 3, 'Tantely mamy', 'Brickaville', 'Apiculture');

INSERT INTO member (id, first_name, last_name, birth_date, gender, address, occupation, phone_number, email, role, collectivity_id)
VALUES
    ('C1-M1','Prénom membre 1','Nom membre 1','1980-02-01','M','Lot II V M Ambato','Riziculteur','0341234567','member.1@fed-agri.mg','PRESIDENT','col-1'),
    ('C1-M2','Prénom membre 2','Nom membre 2','1982-03-05','M','Lot II F Ambato','Agriculteur','0321234567','member.2@fed-agri.mg','VICE_PRESIDENT','col-1'),
    ('C1-M3','Prénom membre 3','Nom membre 3','1992-03-10','M','Lot II J Ambato','Collecteur','0331234567','member.3@fed-agri.mg','SECRETARY','col-1'),
    ('C1-M4','Prénom membre 4','Nom membre 4','1988-05-22','F','Lot A K 50 Ambato','Distributeur','0381234567','member.4@fed-agri.mg','TREASURER','col-1'),
    ('C1-M5','Prénom membre 5','Nom membre 5','1999-08-21','M','Lot UV 80 Ambato','Riziculteur','0373434567','member.5@fed-agri.mg','CONFIRMED','col-1'),
    ('C1-M6','Prénom membre 6','Nom membre 6','1998-08-22','F','Lot UV 6 Ambato','Riziculteur','0372234567','member.6@fed-agri.mg','CONFIRMED','col-1'),
    ('C1-M7','Prénom membre 7','Nom membre 7','1998-01-31','M','Lot UV 7 Ambato','Riziculteur','0374234567','member.7@fed-agri.mg','CONFIRMED','col-1'),
    ('C1-M8','Prénom membre 8','Nom membre 8','1975-08-20','M','Lot UV 8 Ambato','Riziculteur','0370234567','member.8@fed-agri.mg','CONFIRMED','col-1'),
    ('C2-M1','Prénom membre 1','Nom membre 1','1980-02-01','M','Lot II V M Ambato','Riziculteur','0341234567','member.1@fed-agri.mg','CONFIRMED','col-2'),
    ('C2-M2','Prénom membre 2','Nom membre 2','1982-03-05','M','Lot II F Ambato','Agriculteur','0321234567','member.2@fed-agri.mg','CONFIRMED','col-2'),
    ('C2-M3','Prénom membre 3','Nom membre 3','1992-03-10','M','Lot II J Ambato','Collecteur','0331234567','member.3@fed-agri.mg','CONFIRMED','col-2'),
    ('C2-M4','Prénom membre 4','Nom membre 4','1988-05-22','F','Lot A K 50 Ambato','Distributeur','0381234567','member.4@fed-agri.mg','CONFIRMED','col-2'),
    ('C2-M5','Prénom membre 5','Nom membre 5','1999-08-21','M','Lot UV 80 Ambato','Riziculteur','0373434567','member.5@fed-agri.mg','PRESIDENT','col-2'),
    ('C2-M6','Prénom membre 6','Nom membre 6','1998-08-22','F','Lot UV 6 Ambato','Riziculteur','0372234567','member.6@fed-agri.mg','VICE_PRESIDENT','col-2'),
    ('C2-M7','Prénom membre 7','Nom membre 7','1998-01-31','M','Lot UV 7 Ambato','Riziculteur','0374234567','member.7@fed-agri.mg','SECRETARY','col-2'),
    ('C2-M8','Prénom membre 8','Nom membre 8','1975-08-20','M','Lot UV 8 Ambato','Riziculteur','0370234567','member.8@fed-agri.mg','TREASURER','col-2'),
    ('C3-M1','Prénom membre 9','Nom membre 9','1988-01-02','M','Lot 33 J Antsirabe','Apiculteur','034034567','member.9@fed-agri.mg','PRESIDENT','col-3'),
    ('C3-M2','Prénom membre 10','Nom membre 10','1982-03-05','M','Lot 2 J Antsirabe','Agriculteur','0338634567','member.10@fed-agri.mg','VICE_PRESIDENT','col-3'),
    ('C3-M3','Prénom membre 11','Nom membre 11','1992-03-12','M','Lot 8 KM Antsirabe','Collecteur','0338234567','member.11@fed-agri.mg','SECRETARY','col-3'),
    ('C3-M4','Prénom membre 12','Nom membre 12','1988-05-10','F','Lot A K 50 Antsirabe','Distributeur','0382334567','member.12@fed-agri.mg','TREASURER','col-3'),
    ('C3-M5','Prénom membre 13','Nom membre 13','1999-08-11','M','Lot UV 80 Antsirabe','Apiculteur','0373365567','member.13@fed-agri.mg','CONFIRMED','col-3'),
    ('C3-M6','Prénom membre 14','Nom membre 14','1998-08-09','F','Lot UV 6 Antsirabe','Apiculteur','0378234567','member.14@fed-agri.mg','CONFIRMED','col-3'),
    ('C3-M7','Prénom membre 15','Nom membre 15','1998-01-13','M','Lot UV 7 Antsirabe','Apiculteur','0374914567','member.15@fed-agri.mg','CONFIRMED','col-3'),
    ('C3-M8','Prénom membre 16','Nom membre 16','1975-08-02','M','Lot UV 8 Antsirabe','Apiculteur','0370634567','member.16@fed-agri.mg','CONFIRMED','col-3');

INSERT INTO membership_fee VALUES
                               ('cot-1','Cotisation annuelle','ACTIVE','ANNUALLY',100000,'2026-01-01','col-1'),
                               ('cot-2','Cotisation annuelle','ACTIVE','ANNUALLY',100000,'2026-01-01','col-2'),
                               ('cot-3','Cotisation annuelle','ACTIVE','ANNUALLY',50000,'2026-01-01','col-3');

INSERT INTO account VALUES
                        ('C1-A-CASH','CASH',0,NULL,NULL,'col-1'),
                        ('C1-A-MOBILE-1','ORANGE_MONEY',0,'Mpanorina','0370489612','col-1'),
                        ('C2-A-CASH','CASH',0,NULL,NULL,'col-2'),
                        ('C2-A-MOBILE-1','ORANGE_MONEY',0,'Dobo voalohany','0320489612','col-2'),
                        ('C3-A-CASH','CASH',0,NULL,NULL,'col-3');

INSERT INTO payment (member_id, collectivity_id, account_id, amount, payment_method, payment_date)
VALUES
    ('C1-M1','col-1','C1-A-CASH',100000,'CASH','2026-01-01'),
    ('C1-M2','col-1','C1-A-CASH',100000,'CASH','2026-01-01'),
    ('C1-M3','col-1','C1-A-CASH',100000,'CASH','2026-01-01'),
    ('C1-M4','col-1','C1-A-CASH',100000,'CASH','2026-01-01'),
    ('C1-M5','col-1','C1-A-CASH',100000,'CASH','2026-01-01'),
    ('C1-M6','col-1','C1-A-CASH',100000,'CASH','2026-01-01'),
    ('C1-M7','col-1','C1-A-CASH',60000,'CASH','2026-01-01'),
    ('C1-M8','col-1','C1-A-CASH',90000,'CASH','2026-01-01'),
    ('C2-M1','col-2','C2-A-CASH',60000,'CASH','2026-01-01'),
    ('C2-M2','col-2','C2-A-CASH',90000,'CASH','2026-01-01'),
    ('C2-M3','col-2','C2-A-CASH',100000,'CASH','2026-01-01'),
    ('C2-M4','col-2','C2-A-CASH',100000,'CASH','2026-01-01'),
    ('C2-M5','col-2','C2-A-CASH',100000,'CASH','2026-01-01'),
    ('C2-M6','col-2','C2-A-CASH',100000,'CASH','2026-01-01'),
    ('C2-M7','col-2','C2-A-MOBILE-1',40000,'MOBILE_MONEY','2026-01-01'),
    ('C2-M8','col-2','C2-A-MOBILE-1',60000,'MOBILE_MONEY','2026-01-01');

ALTER TABLE member ADD COLUMN IF NOT EXISTS admission_date DATE;

UPDATE member SET admission_date = '2026-01-01' WHERE admission_date IS NULL;