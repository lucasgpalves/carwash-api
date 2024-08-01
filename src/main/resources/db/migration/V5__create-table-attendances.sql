CREATE TABLE attendances(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    employee_id UUID NOT NULL,
    starts_at TIMESTAMP NOT NULL,
    ends_at TIMESTAMP NOT NULL,
    is_present BOOLEAN NOT NULL,
    FOREIGN KEY(employee_id) REFERENCES employees(id)

);