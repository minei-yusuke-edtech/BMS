CREATE TABLE IF NOT EXISTS RentalStatus(
    rentStatus VARCHAR(20),
    PRIMARY KEY (rentStatus)
);

START TRANSACTION;
INSERT INTO RentalStatus VALUES ('貸出候補');
INSERT INTO RentalStatus VALUES ('貸出中');
INSERT INTO RentalStatus VALUES ('返却済');
COMMIT;