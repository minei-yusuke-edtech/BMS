DROP TABLE IF EXISTS Users;
CREATE TABLE IF NOT EXISTS Users(
    username VARCHAR(50),
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    mailAddress VARCHAR(50) NOT NULL,
    lastName VARCHAR(50),
    firstName VARCHAR(50),
    birthYear INTEGER,
    cityCode INTEGER,
    PRIMARY KEY (username),
    FOREIGN KEY (cityCode) REFERENCES Cities(cityCode)
);

BEGIN TRANSACTION;
-- password
INSERT INTO Users (username, password, mailAddress) VALUES ('test', '{bcrypt}$2a$10$Y/nI3mX3m3NDzf/Z1coTV.AzWT9mmYGffuqHYzaGZY7G4myIDwV/W', 'hoge@gmail.com');
INSERT INTO Users (username, password, mailAddress, lastName, firstName, birthYear, cityCode) VALUES ('user1', '{bcrypt}$2a$10$Y/nI3mX3m3NDzf/Z1coTV.AzWT9mmYGffuqHYzaGZY7G4myIDwV/W', 'hoge@yahoo.co.jp', 'hoge', 'fuga', 2020, 47215);
INSERT INTO Authorities (username, authority) VALUES ('user1', 'ROLE_USERS');
COMMIT;