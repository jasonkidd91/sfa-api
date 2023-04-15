-- Insert sample data into customer table
INSERT INTO customer (first_name, last_name, email, reference_code)
VALUES ('John', 'Doe', 'john.doe@example.com', 'PGWD2E8E'),
       ('Jane', 'Smith', 'jane.smith@example.com', 'EGWD2E8P');

-- Insert sample data into portfolio table
INSERT INTO portfolio (portfolio_name, portfolio_desc)
VALUES ('Retirement', 'Simple Retirement Portfolio'),
       ('High Risk', 'Simple High Risk Portfolio');

-- Insert sample data into customer_portfolio table
INSERT INTO customer_portfolio (customer_id, portfolio_id, balance)
VALUES (1, 1, 10000.00),
       (1, 2, 5000.00),
       (2, 1, 7500.00);

-- Insert sample data into transaction table
INSERT INTO transaction (customer_id, portfolio_id, transaction_amount, transaction_date, transaction_type, transaction_status)
VALUES (1, 1, 500.00, CURRENT_TIMESTAMP, 'DEPOSIT', 'COMPLETED'),
       (1, 1, 10.00, CURRENT_TIMESTAMP, 'FLOATING', 'COMPLETED'),
       (1, 1, 15.00, CURRENT_TIMESTAMP, 'FLOATING', 'COMPLETED'),
       (1, 1, 20.00, CURRENT_TIMESTAMP, 'FLOATING', 'COMPLETED'),
       (1, 2, 1000.00, CURRENT_TIMESTAMP, 'DEPOSIT', 'COMPLETED'),
       (1, 2, -50.00, CURRENT_TIMESTAMP, 'FLOATING', 'COMPLETED'),
       (1, 2, -30.00, CURRENT_TIMESTAMP, 'FLOATING', 'COMPLETED'),
       (2, 1, -250.00, CURRENT_TIMESTAMP, 'DEPOSIT', 'COMPLETED');

-- Insert sample data into payment_method table
INSERT INTO payment_method (payment_id, payment_name, is_active) VALUES
(1, 'Credit Card', true),
(2, 'Debit Card', true),
(3, 'Bank Transfer', false),
(4, 'E-Wallet', true);

-- Insert sample data into customer_transaction table
INSERT INTO customer_transaction (customer_id, customer_portfolio_id, payment_id, frequency, transaction_amount, transaction_start_date, transaction_next_date, transaction_status)
VALUES (1, 1, 1, 'ONE_TIME', 100.00, '2022-05-01', '2022-05-01', 'ACTIVE'),
       (1, 2, 2, 'MONTHLY', 200.00, '2022-05-02', '2022-05-02', 'ACTIVE'),
       (2, 3, 4, 'ONE_TIME', 150.00, '2022-05-03', '2022-05-03', 'ACTIVE');