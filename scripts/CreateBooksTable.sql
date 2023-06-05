CREATE TABLE IF NOT EXISTS Books(
    bookID SERIAL,
    bookTitle VARCHAR(50) NOT NULL,
    author VARCHAR(50),
    publisher VARCHAR(50),
    issue INTEGER,
    version VARCHAR(50),
    isbn VARCHAR(20),
    classCode VARCHAR(5),
    enabled BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(bookID)
);

START TRANSACTION;
-- INSERT INTO Books(bookTitle, author, publisher, issue, version, isbn, classCode) VALUES ('', '', '', 20xx, '第版', '', '');
INSERT INTO Books(bookTitle, author, publisher, issue, version, isbn, classCode) VALUES ('図解！ アルゴリズムのツボとコツがゼッタイにわかる本', '	中田　亨', '秀和システム', 2021, '第1版', '	9784798065052', 'C3055');
INSERT INTO Books(bookTitle, author, publisher, issue, version, isbn, classCode) VALUES ('スッキリわかるJava入門', '中山清喬,国本大悟', 'インプレス', 2019, '第3版', '9784295007807', 'C3055');
INSERT INTO Books(bookTitle, author, publisher, issue, version, isbn, classCode) VALUES ('SQL ゼロからはじめるデータベース操作', 'ミック', '翔泳社', 2016, '第2版', '9784798144450', 'C3055');
INSERT INTO Books(bookTitle, author, publisher, issue, version, isbn, classCode) VALUES ('1冊ですべて身につくHTML & CSSとWebデザイン入門講座', 'Mana', 'SBクリエイティブ', 2019, '第1版', '9784797398892', 'C3055');
COMMIT;