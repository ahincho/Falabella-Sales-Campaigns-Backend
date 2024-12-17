CREATE TABLE campaigns (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    login_message TEXT,
    login_image VARCHAR(255),
    background_color VARCHAR(7),
    home_image VARCHAR(255),
    home_message TEXT,
    home_message_image VARCHAR(255),
    message_text_color VARCHAR(7),
    coupon_usage_message TEXT,
    legal_conditions TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);