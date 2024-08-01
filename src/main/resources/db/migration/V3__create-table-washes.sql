CREATE TABLE washes (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    car_id UUID NOT NULL,
    description VARCHAR(255) NOT NULL,
    amount DOUBLE NOT NULL,
    is_paid BOOLEAN NOT NULL,
    starts_at TIMESTAMP NOT NULL,
    ends_at TIMESTAMP,
    FOREIGN KEY (car_id) REFERENCES cars(id)
);
