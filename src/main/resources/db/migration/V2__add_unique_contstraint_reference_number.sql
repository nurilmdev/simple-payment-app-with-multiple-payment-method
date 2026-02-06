ALTER TABLE payments
ADD CONSTRAINT uk_reference_number UNIQUE (reference_number);