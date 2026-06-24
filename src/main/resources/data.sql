INSERT INTO INVESTORS (first_name, last_name, email, date_of_birth) VALUES
                                                                        ('John', 'Smith', 'john.smith@email.com', '1955-03-15'),
                                                                        ('Sarah', 'Johnson', 'sarah.j@email.com', '1990-07-22');

INSERT INTO INVESTMENT_PRODUCTS (product_name, product_type, balance, investor_id) VALUES
                                                                                       ('John Retirement Fund', 'RETIREMENT_ANNUITY', 500000.00, 1),
                                                                                       ('John Living Annuity', 'LIVING_ANNUITY', 250000.00, 1),
                                                                                       ('Sarah Living Annuity', 'LIVING_ANNUITY', 180000.00, 2);