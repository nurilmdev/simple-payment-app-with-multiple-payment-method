CREATE TABLE payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reference_number VARCHAR(100),
    amount DECIMAL(19,2),
    method VARCHAR(50),
    status VARCHAR(20),
    created_at DATETIME
);