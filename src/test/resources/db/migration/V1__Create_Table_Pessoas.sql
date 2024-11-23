CREATE TABLE IF NOT EXISTS public.pessoas
(
    id SERIAL PRIMARY KEY,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    CONSTRAINT uk_ouqc5requard3nhnb6u3wvksm UNIQUE (email)
);