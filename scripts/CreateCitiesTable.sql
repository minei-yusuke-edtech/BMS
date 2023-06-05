CREATE TABLE IF NOT EXISTS Cities(
    cityCode INTEGER,
    cityName VARCHAR(50),
    PRIMARY KEY (cityCode)
);

BEGIN TRANSACTION;
INSERT INTO Cities (cityCode, cityName) VALUES (47201, '那覇市');
INSERT INTO Cities (cityCode, cityName) VALUES (47205, '宜野湾市');
INSERT INTO Cities (cityCode, cityName) VALUES (47207, '石垣市');
INSERT INTO Cities (cityCode, cityName) VALUES (47208, '浦添市');
INSERT INTO Cities (cityCode, cityName) VALUES (47209, '名護市');
INSERT INTO Cities (cityCode, cityName) VALUES (47210, '糸満市');
INSERT INTO Cities (cityCode, cityName) VALUES (47211, '沖縄市');
INSERT INTO Cities (cityCode, cityName) VALUES (47212, '豊見城市');
INSERT INTO Cities (cityCode, cityName) VALUES (47213, 'うるま市');
INSERT INTO Cities (cityCode, cityName) VALUES (47214, '宮古島市');
INSERT INTO Cities (cityCode, cityName) VALUES (47215, '南城市');
INSERT INTO Cities (cityCode, cityName) VALUES (47301, '国頭村');
INSERT INTO Cities (cityCode, cityName) VALUES (47302, '大宜味村');
INSERT INTO Cities (cityCode, cityName) VALUES (47303, '東村');
INSERT INTO Cities (cityCode, cityName) VALUES (47306, '今帰仁村');
INSERT INTO Cities (cityCode, cityName) VALUES (47308, '本部町');
INSERT INTO Cities (cityCode, cityName) VALUES (47311, '恩納村');
INSERT INTO Cities (cityCode, cityName) VALUES (47313, '宜野座村');
INSERT INTO Cities (cityCode, cityName) VALUES (47314, '金武町');
INSERT INTO Cities (cityCode, cityName) VALUES (47315, '伊江村');
INSERT INTO Cities (cityCode, cityName) VALUES (47324, '読谷村');
INSERT INTO Cities (cityCode, cityName) VALUES (47325, '嘉手納町');
INSERT INTO Cities (cityCode, cityName) VALUES (47326, '北谷町');
INSERT INTO Cities (cityCode, cityName) VALUES (47327, '北中城村');
INSERT INTO Cities (cityCode, cityName) VALUES (47328, '中城村');
INSERT INTO Cities (cityCode, cityName) VALUES (47329, '西原町');
INSERT INTO Cities (cityCode, cityName) VALUES (47348, '与那原町');
INSERT INTO Cities (cityCode, cityName) VALUES (47350, '南風原町');
INSERT INTO Cities (cityCode, cityName) VALUES (47353, '渡嘉敷村');
INSERT INTO Cities (cityCode, cityName) VALUES (47354, '座間味村');
INSERT INTO Cities (cityCode, cityName) VALUES (47355, '粟国村');
INSERT INTO Cities (cityCode, cityName) VALUES (47356, '渡名喜村');
INSERT INTO Cities (cityCode, cityName) VALUES (47357, '南大東村');
INSERT INTO Cities (cityCode, cityName) VALUES (47358, '北大東村');
INSERT INTO Cities (cityCode, cityName) VALUES (47359, '伊平屋村');
INSERT INTO Cities (cityCode, cityName) VALUES (47360, '伊是名村');
INSERT INTO Cities (cityCode, cityName) VALUES (47361, '久米島町');
INSERT INTO Cities (cityCode, cityName) VALUES (47362, '八重瀬町');
INSERT INTO Cities (cityCode, cityName) VALUES (47375, '多良間村');
INSERT INTO Cities (cityCode, cityName) VALUES (47381, '竹富町');
INSERT INTO Cities (cityCode, cityName) VALUES (47382, '与那国町');
COMMIT;