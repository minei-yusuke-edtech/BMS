CREATE TABLE IF NOT EXISTS RentalList(
    username VARCHAR(50) NOT NULL,
    bookID INTEGER NOT NULL,
    rentDate DATE,
    returnDate DATE,
    rentStatus VARCHAR(20),
    UNIQUE (username, bookID, rentDate),
    FOREIGN KEY (username) REFERENCES Users(username),
    FOREIGN KEY (bookID) REFERENCES Books(bookID),
    FOREIGN KEY (rentStatus) REFERENCES RentalStatus(rentStatus)
);