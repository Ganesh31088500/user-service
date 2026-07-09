CREATE TABLE users
(
    id UUID PRIMARY KEY,

    auth_user_id UUID NOT NULL UNIQUE,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    email VARCHAR(255) UNIQUE NOT NULL,

    phone_number VARCHAR(20),

    profile_picture VARCHAR(500),

    status VARCHAR(30) NOT NULL,

    created_at TIMESTAMP,

    updated_at TIMESTAMP,

    created_by VARCHAR(100),

    updated_by VARCHAR(100)
);