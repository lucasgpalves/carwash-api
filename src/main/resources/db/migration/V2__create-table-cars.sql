CREATE TABLE cars(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    license_plate VARCHAR(7) NOT NULL UNIQUE,
    model VARCHAR(30) NOT NULL,
    color VARCHAR(20) NOT NULL,
    status VARCHAR(20),
    owner_id UUID,
    FOREIGN KEY (owner_id) REFERENCES owners(id) ON DELETE CASCADE
);