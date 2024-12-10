INSERT INTO roles (id, name, description, created_at, updated_at)
VALUES
    (1, 'Client', 'Role for application clients', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Operator', 'Role for system operators', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Admin', 'Role for administrators', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);