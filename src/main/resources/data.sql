--CLEAR WALLETS
DELETE FROM TRANSACTIONS;

DELETE FROM WALLETS;

--INSERT WALLETS
INSERT INTO
    WALLETS (
        ID, FULL_NAME, CPF, EMAIL, "PASSWORD", "TYPE", BALANCE
    )
VALUES (
        1, 'Carlos - User', 12345678900, 'carlos@test.com', '123456', 1, 1000.00
);

INSERT INTO
    WALLETS (
        ID, FULL_NAME, CPF, EMAIL, "PASSWORD", "TYPE", BALANCE
    )
VALUES (
        2, 'Karla - Lojista', 00987654321, 'Karla@test.com', '654321', 2, 7000.00
);