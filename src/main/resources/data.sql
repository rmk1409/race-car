INSERT INTO source_site(id, host_name)
    values(1001, 'https://cars.av.by/');
INSERT INTO source_site(id, host_name)
    values(1002, 'https://auto.ru/');

INSERT INTO query (id, name, description, href, created_date, source_site_id)
    VALUES (2001, 'Audi A3', 'Audi A3 with year 2008-2010 and price until 20_000 BYN', 'https%3A%2F%2Fcars.av.by%2Fsearch%3Fbrand_id%255B0%255D%3D6%26model_id%255B0%255D%3D1428%26generation_id%255B0%255D%3D%26year_from%3D2008%26year_to%3D2010%26currency%3DUSD%26price_from%3D%26price_to%3D20000%26body_id%3D%26engine_volume_min%3D%26engine_volume_max%3D%26driving_id%3D%26mileage_min%3D%26mileage_max%3D%26region_id%3D%26interior_material%3D%26interior_color%3D%26exchange%3D%26search_time%3D', sysdate(), 1001);
INSERT INTO query (id, name, description, href, created_date, source_site_id)
    VALUES (2002, 'Jeep Renegade', 'Jeep 2016-2019 with price 13k-14k', 'https%3A%2F%2Fcars.av.by%2Fsearch%3Fbrand_id%255B0%255D%3D540%26model_id%255B0%255D%3D%26year_from%3D2016%26year_to%3D2019%26currency%3DUSD%26price_from%3D13000%26price_to%3D14000%26body_id%3D%26engine_volume_min%3D%26engine_volume_max%3D%26driving_id%3D%26mileage_min%3D%26mileage_max%3D%26region_id%3D%26interior_material%3D%26interior_color%3D%26exchange%3D%26search_time%3D', sysdate(), 1001);
INSERT INTO query (id, name, description, href, created_date, source_site_id)
    VALUES (2003, 'Porshe 2019', 'Porshe 2019 year and any price', 'https%3A%2F%2Fcars.av.by%2Fsearch%3Fbrand_id%255B0%255D%3D1485%26model_id%255B0%255D%3D%26year_from%3D2019%26year_to%3D%26currency%3DUSD%26price_from%3D%26price_to%3D%26body_id%3D%26engine_volume_min%3D%26engine_volume_max%3D%26driving_id%3D%26mileage_min%3D%26mileage_max%3D%26region_id%3D%26interior_material%3D%26interior_color%3D%26exchange%3D%26search_time%3D', sysdate(), 1001);