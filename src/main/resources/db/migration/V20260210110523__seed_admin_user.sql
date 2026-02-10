-- insert roles
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

-- password = admin123 (bcrypt)
-- generated from BCryptPasswordEncoder
INSERT INTO users (username, password, enabled)
VALUES (
    'admin',
    '$2a$10$Dow1sF7H6x0pGxjvF6s9uO9h1sS4pRz1Y1x8u9oF4Lq6qJ9YpK7yG',
    true
);

-- assign admin role
INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1);
