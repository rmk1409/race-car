INSERT INTO source_site(id, host_name)
    values(1001, 'https://cars.av.by/');
INSERT INTO source_site(id, host_name)
    values(1002, 'https://auto.ru/');

INSERT INTO query (id, name, description, href, created_date, updated_date, source_site_id)
    VALUES (2001, 'Audi A3', 'Audi A3 with year 2008-2010 and price until 20_000 BYN', 'https://cars.av.by/search?brand_id%5B0%5D=6&model_id%5B0%5D=1428&generation_id%5B0%5D=&year_from=2008&year_to=2010&currency=BYN&price_from=&price_to=20000&body_id=&engine_volume_min=&engine_volume_max=&driving_id=&mileage_min=&mileage_max=&region_id=&interior_material=&interior_color=&exchange=&search_time=', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1001);
INSERT INTO query (id, name, description, href, created_date, updated_date, source_site_id)
    VALUES (2002, 'Acura Renegade', 'Jeep 2016-2019 with price 13k-14k', 'https://cars.av.by/search?brand_id%5B0%5D=1444&model_id%5B0%5D=&year_from=&year_to=2015&currency=USD&price_from=&price_to=&body_id=&engine_volume_min=&engine_volume_max=&driving_id=&mileage_min=&mileage_max=&region_id=&interior_material=&interior_color=&exchange=&search_time=', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1001);
INSERT INTO query (id, name, description, href, created_date, updated_date, source_site_id)
    VALUES (2003, 'Land Rover', 'Porshe 2019 year and any price', 'https://cars.av.by/search?brand_id%5B0%5D=584&model_id%5B0%5D=&year_from=&year_to=2014&currency=USD&price_from=&price_to=&body_id=&engine_volume_min=&engine_volume_max=&driving_id=&mileage_min=&mileage_max=&region_id=&interior_material=&interior_color=&exchange=&search_time=', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1001);