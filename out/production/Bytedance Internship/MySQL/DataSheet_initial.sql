-- User Table
CREATE TABLE User (
    uid INT PRIMARY KEY,
    phone_number VARCHAR(15),
    avatar VARCHAR(255),
    nickname VARCHAR(50),
    favorites TEXT,
    news_preferences TEXT,
    browsing_history TEXT,
    comment_history TEXT,
    last_login_time TIMESTAMP
);

-- Content Table
CREATE TABLE Content (
    news_id INT PRIMARY KEY,
    title VARCHAR(255),
    cover_image VARCHAR(255),
    publish_datetime DATETIME,
    author VARCHAR(100),
    content TEXT,
    views INT,
    favorites INT,
    shares INT,
    promoted BOOLEAN
);

-- Comment Table
CREATE TABLE Comment (
    user_id INT REFERENCES User(uid),
    comment_id INT PRIMARY KEY,
    news_id INT REFERENCES Content(news_id),
    comment_content TEXT,
    comment_time TIMESTAMP,
    quoted_comment_id INT,
    likes INT,
    dislikes INT
);

-- Advertisement Table
CREATE TABLE Advertisement (
    ad_title VARCHAR(255),
    ad_content TEXT,
    image_link VARCHAR(255),
    placement VARCHAR(50),
    advertiser_id VARCHAR(50),
    expiration_date DATE,
    region_limit TEXT,
    device_limit TEXT,
    clicks INT,
    impressions INT,
    weight INT
);
