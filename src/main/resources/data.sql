INSERT INTO source_site(id, host_name)
    values(1001, 'https://cars.av.by/');
INSERT INTO source_site(id, host_name)
    values(1002, 'https://auto.ru/');

INSERT INTO query (id, name, description, href, created_date, updated_date, source_site_id)
    VALUES (2001, 'Opel Astra', 'Opel Astra from 2000 volume', 'https://cars.av.by/search?brand_id%5B0%5D=966&model_id%5B0%5D=5815&generation_id%5B0%5D=&year_from=&year_to=&currency=USD&price_from=1000&price_to=&transmission=2&body_id=&engine_type=1&engine_volume_min=2000&engine_volume_max=&driving_id=&mileage_min=&mileage_max=&region_id=&interior_material=&interior_color=&exchange=&search_time=', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1001);
INSERT INTO query (id, name, description, href, created_date, updated_date, source_site_id)
    VALUES (2002, 'Opel Corsa', 'Opel Corsa from 1600 volume', 'https://cars.av.by/search?brand_id%5B0%5D=966&model_id%5B0%5D=974&generation_id%5B0%5D=&year_from=&year_to=&currency=USD&price_from=1000&price_to=&transmission=2&body_id=&engine_type=1&engine_volume_min=1600&engine_volume_max=&driving_id=&mileage_min=&mileage_max=&region_id=&interior_material=&interior_color=&exchange=&search_time=', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1001);