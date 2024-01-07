--Create User by Email
INSERT INTO User (uid, phone_number, avatar, nickname, last_login_time)
VALUES (10/*DEFAULT*/, 'user@example.com', 'default_avatar.jpg', 'New User', NULL);

--Edit User Account Information
UPDATE User
SET nickname = 'Updated Nickname', avatar = 'new_avatar.jpg'
WHERE uid = 1;

--User Login
-- Assuming email-based login for simplicity
SELECT * FROM User WHERE phone_number = 'user@example.com';

--Get Homepage News List for User
SELECT * FROM Content ORDER BY publish_datetime DESC LIMIT 10;

--Browse News List by Section
SELECT * FROM Content WHERE section = 'Politics' ORDER BY publish_datetime DESC LIMIT 10;

--Search News by Title and Content
SELECT * FROM Content
WHERE title LIKE '%keyword%' OR content LIKE '%keyword%'
ORDER BY publish_datetime DESC LIMIT 10;

--Access News and Load Comments
-- Assuming news_id = 1 for example
SELECT * FROM Content WHERE news_id = 1;
SELECT * FROM Comment WHERE news_id = 1;

-- User Favorite and Share News
-- Assuming user_id = 1 and news_id = 1 for example
INSERT INTO User_Favorites (user_id, news_id) VALUES (1, 1);
INSERT INTO User_Shares (user_id, news_id) VALUES (1, 1);

--User Comment on News
-- Assuming user_id = 1, news_id = 1 for example
INSERT INTO Comment (user_id, news_id, comment_content, comment_time)
VALUES (1, 1, 'Great news!', current_time() );

--User Like/Dislike Comment
-- Assuming comment_id = 1 for example
UPDATE Comment SET likes = likes + 1 WHERE comment_id = 1;
-- or
UPDATE Comment SET dislikes = dislikes + 1 WHERE comment_id = 1;

--User Delete Own Comment
-- Assuming user_id = 1 and comment_id = 1 for example
DELETE FROM Comment WHERE user_id = 1 AND comment_id = 1;

-- View User's Comment History
-- Assuming user_id = 1 for example
SELECT * FROM Comment WHERE user_id = 1;

-- View User's Browsing History
-- Assuming user_id = 1 for example
SELECT * FROM Browsing_History WHERE user_id = 1;

-- (Simulated) Publish News
INSERT INTO Content (news_id, title, content, publish_datetime)
VALUES (10, 'New Breaking News', 'Content of breaking news...', current_time() );

-- Get Ads by Content Type
-- Assuming content_type = 'Politics' for example
SELECT * FROM Advertisement WHERE content_type = 'Politics';

-- Publish Advertisement
INSERT INTO Advertisement (ad_title, ad_content, placement, advertiser_id, expiration_date, region_limit, device_limit, weight)
VALUES ('New Ad Title', 'Ad content goes here...', 'Homepage', 'AdAgency1', '2023-12-31', 'US', 'Desktop', 10);
