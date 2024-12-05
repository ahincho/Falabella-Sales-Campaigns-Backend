INSERT INTO roles (name, description, created_at, updated_at)
VALUES
    ('Client', 'Role for application clients', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Operator', 'Role for system operators', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Admin', 'Role for administrators', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);