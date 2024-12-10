CREATE TABLE campaign (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_coupons INT NOT NULL,
    used_coupons_count INT NOT NULL,
    status BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    login_message TEXT,
    login_image VARCHAR(255),
    background_color VARCHAR(7),
    home_message_image VARCHAR(255),
    home_image VARCHAR(255),
    home_message TEXT,
    message_text_color VARCHAR(7),
    coupon_usage_message TEXT,
    legal_conditions TEXT
);