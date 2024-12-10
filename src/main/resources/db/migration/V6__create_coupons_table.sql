CREATE TABLE coupons (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    campaign_id INTEGER NOT NULL,
    type VARCHAR(6) NOT NULL,
    code_bar VARCHAR(8) NOT NULL UNIQUE,
    code_web VARCHAR(15) NOT NULL UNIQUE,
    status VARCHAR(3) NOT NULL,
    transaction_id VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_campaign FOREIGN KEY (campaign_id) REFERENCES campaigns(id) ON DELETE CASCADE
);
CREATE INDEX idx_coupons_type ON coupons(type);
CREATE INDEX idx_coupons_code_bar ON coupons(code_bar);
CREATE INDEX idx_coupons_code_web ON coupons(code_web);
CREATE INDEX idx_coupons_status ON coupons(status);