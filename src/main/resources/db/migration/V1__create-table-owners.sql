CREATE TABLE owners(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    phone_number VARCHAR(15) NOT NULL
);