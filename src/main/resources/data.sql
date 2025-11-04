-- Insert dishes
INSERT INTO dish (name, category, price) VALUES ('Margherita Pizza', 'Main Course', 12.99);
INSERT INTO dish (name, category, price) VALUES ('Caesar Salad', 'Appetizer', 8.99);
INSERT INTO dish (name, category, price) VALUES ('Grilled Salmon', 'Main Course', 18.99);
INSERT INTO dish (name, category, price) VALUES ('Chocolate Cake', 'Dessert', 6.99);
INSERT INTO dish (name, category, price) VALUES ('Pasta Carbonara', 'Main Course', 14.99);
INSERT INTO dish (name, category, price) VALUES ('French Fries', 'Side', 4.99);
INSERT INTO dish (name, category, price) VALUES ('Chicken Wings', 'Appetizer', 9.99);
INSERT INTO dish (name, category, price) VALUES ('Beef Burger', 'Main Course', 13.99);
INSERT INTO dish (name, category, price) VALUES ('Ice Cream', 'Dessert', 5.99);
INSERT INTO dish (name, category, price) VALUES ('Onion Rings', 'Side', 4.49);

-- Insert default users (password is 'password' for both, encrypted with BCrypt)
INSERT INTO users (username, password, role, enabled) VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_ADMIN', true);
INSERT INTO users (username, password, role, enabled) VALUES ('user', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', true);