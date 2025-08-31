CREATE TABLE "users" (
  "id" bigserial PRIMARY KEY,
  "username" varchar(50) UNIQUE NOT NULL,
  "password" varchar NOT NULL,
  "name" varchar(150) NOT NULL,
  "lastname" varchar(150) NOT NULL,
  "email" varchar(255) UNIQUE NOT NULL,
  "phone" varchar(20),
  "address" varchar(255),
  "status_id" integer DEFAULT '1',
  "is_enabled" boolean DEFAULT true,
  "account_no_expired" boolean DEFAULT true,
  "account_no_locked" boolean DEFAULT true,
  "credential_no_expired" boolean DEFAULT true,
  "last_login" timestamp,
  "last_login_ip" varchar(50),
  "created_at" timestamp NOT NULL,
  "created_by" varchar(50) NOT NULL,
  "updated_at" timestamp NOT NULL,
  "updated_by" varchar(50) NOT NULL
);

CREATE TABLE "roles" (
  "id" serial PRIMARY KEY,
  "description" varchar(20) UNIQUE NOT NULL,
  "comment" varchar(100),
  "status_id" integer DEFAULT '1',
  "created_at" timestamp NOT NULL,
  "created_by" varchar(50) NOT NULL,
  "updated_at" timestamp NOT NULL,
  "updated_by" varchar(50) NOT NULL
);

CREATE TABLE "permissions" (
  "id" serial PRIMARY KEY,
  "description" varchar(20) UNIQUE NOT NULL,
  "comment" varchar(100),
  "status_id" integer DEFAULT '1',
  "created_at" timestamp NOT NULL,
  "created_by" varchar(50) NOT NULL,
  "updated_at" timestamp NOT NULL,
  "updated_by" varchar(50) NOT NULL
);

CREATE TABLE "roles_permissions" (
  "id" serial PRIMARY KEY,
  "role_id" integer,
  "permission_id" integer,
  "status_id" integer DEFAULT '1',
  "created_at" timestamp NOT NULL,
  "created_by" varchar(50) NOT NULL,
  "updated_at" timestamp NOT NULL,
  "updated_by" varchar(50) NOT NULL
);

CREATE TABLE "menus" (
  "id" serial PRIMARY KEY,
  "description" varchar(20) UNIQUE NOT NULL,
  "comment" varchar(100),
  "status_id" integer DEFAULT '1',
  "icone" varchar(30),
  "route" varchar(200) NOT NULL,
  "created_at" timestamp NOT NULL,
  "created_by" varchar(50) NOT NULL,
  "updated_at" timestamp NOT NULL,
  "updated_by" varchar(50) NOT NULL
);

CREATE TABLE "users_roles" (
  "id" serial PRIMARY KEY,
  "user_id" integer,
  "role_id" integer,
  "status_id" integer DEFAULT '1',
  "created_at" timestamp NOT NULL,
  "created_by" varchar(50) NOT NULL,
  "updated_at" timestamp NOT NULL,
  "updated_by" varchar(50) NOT NULL
);

CREATE TABLE "roles_menus" (
  "id" serial PRIMARY KEY,
  "role_id" integer,
  "menu_id" integer,
  "status_id" integer DEFAULT '1',
  "created_at" timestamp NOT NULL,
  "created_by" varchar(50) NOT NULL,
  "updated_at" timestamp NOT NULL,
  "updated_by" varchar(50) NOT NULL
);

CREATE TABLE "status" (
  "id" serial PRIMARY KEY,
  "description" varchar(20) UNIQUE NOT NULL,
  "comment" varchar(100),
  "created_at" timestamp NOT NULL,
  "created_by" varchar(255) NOT NULL,
  "updated_at" timestamp NOT NULL,
  "updated_by" varchar(255) NOT NULL
);

CREATE TABLE "audit_login" (
  "id" UUID PRIMARY KEY,
  "user_id" bigserial,
  "username" varchar(50) NOT NULL,
  "comment" varchar(100),
  "user_agent" varchar(100),
  "ip_address" varchar(50),
  "created_at" timestamp NOT NULL,
  "is_successful" boolean NOT NULL
);


ALTER TABLE "users_roles" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "users_roles" ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("id");

ALTER TABLE "roles_menus" ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("id");

ALTER TABLE "roles_menus" ADD FOREIGN KEY ("menu_id") REFERENCES "menus" ("id");

ALTER TABLE "roles_permissions" ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("id");

ALTER TABLE "roles_permissions" ADD FOREIGN KEY ("permission_id") REFERENCES "permissions" ("id");

ALTER TABLE "users" ADD FOREIGN KEY ("status_id") REFERENCES "status" ("id");

ALTER TABLE "audit_login" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");