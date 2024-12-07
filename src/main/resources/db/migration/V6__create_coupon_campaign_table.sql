CREATE TABLE coupon_campaign (
    id SERIAL PRIMARY KEY,
    campaign_id INT NOT NULL,
    coupon_code VARCHAR(255) NOT NULL,
    is_used BOOLEAN DEFAULT FALSE,
    used_at TIMESTAMP,
    FOREIGN KEY (campaign_id) REFERENCES campaign(id)
);