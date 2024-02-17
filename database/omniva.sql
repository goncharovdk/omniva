BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "people" (
	"personal_code"	TEXT,
	"name"	TEXT NOT NULL,
	"surname"	TEXT NOT NULL,
	PRIMARY KEY("personal_code")
);
CREATE TABLE IF NOT EXISTS "shipments" (
	"barcode"	TEXT NOT NULL,
	"sender"	TEXT NOT NULL,
	"receiver"	TEXT NOT NULL,
	"weight"	INTEGER,
	FOREIGN KEY("receiver") REFERENCES "people"("personal_code"),
	FOREIGN KEY("sender") REFERENCES "people"("personal_code"),
	PRIMARY KEY("barcode")
);
INSERT INTO "people" ("personal_code","name","surname") VALUES ('000000000000','Anonymous','Anonymous');
INSERT INTO "shipments" ("barcode","sender","receiver","weight") VALUES ('THISISAUSEDBARCODE','000000000000','000000000000',100);
CREATE INDEX IF NOT EXISTS "sender_FK" ON "shipments" (
	"sender"
);
CREATE INDEX IF NOT EXISTS "receiver_FK" ON "shipments" (
	"receiver"
);
COMMIT;
