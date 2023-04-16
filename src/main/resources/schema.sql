-- Create customer table
CREATE TABLE customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE NOT NULL,
    reference_code VARCHAR(50) UNIQUE NOT NULL
    -- Add other customer profile columns as needed
);

-- Create portfolio table
CREATE TABLE portfolio (
    portfolio_id INT PRIMARY KEY AUTO_INCREMENT,
    portfolio_name VARCHAR(100) NOT NULL,
    portfolio_desc VARCHAR(100)
    -- Add other portfolio columns as needed
);

-- Create customer_portfolio table
CREATE TABLE customer_portfolio (
    customer_portfolio_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    portfolio_id INT,
    balance DECIMAL(10, 2),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (portfolio_id) REFERENCES portfolio(portfolio_id)
);

-- Create transaction table
CREATE TABLE transaction (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    customer_portfolio_id INT NOT NULL,
    transaction_amount DECIMAL(10, 2) NOT NULL,
    transaction_date TIMESTAMP NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    transaction_status VARCHAR(50) NOT NULL,
    -- Add other transaction columns as needed
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (customer_portfolio_id) REFERENCES customer_portfolio(customer_portfolio_id)
);

-- Create payment_method table
CREATE TABLE payment_method (
  payment_id INT PRIMARY KEY AUTO_INCREMENT,
  payment_name VARCHAR(255),
  is_active BOOLEAN
);

-- Create customer_transaction table
CREATE TABLE customer_transaction (
    customer_transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    customer_portfolio_id INT NOT NULL,
    frequency VARCHAR(10) NOT NULL,
    payment_id INT NOT NULL,
    transaction_amount DECIMAL(10, 2) NOT NULL,
    transaction_start_date DATE NOT NULL,
    transaction_end_date DATE,
    transaction_next_date DATE NOT NULL,
    transaction_status VARCHAR(50) NOT NULL,
    -- Add other customer transaction columns as needed
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (customer_portfolio_id) REFERENCES customer_portfolio(customer_portfolio_id),
    FOREIGN KEY (payment_id) REFERENCES payment_method(payment_id)
);