BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "people" (
	"personal_code"	INTEGER,
	"name"	TEXT NOT NULL,
	"surname"	TEXT NOT NULL,
	PRIMARY KEY("personal_code")
);
CREATE TABLE IF NOT EXISTS "shipments" (
	"barcode"	TEXT,
	"sender"	INTEGER,
	"receiver"	INTEGER,
	"weight"	REAL,
	FOREIGN KEY("sender") REFERENCES "people"("personal_code"),
	FOREIGN KEY("receiver") REFERENCES "people"("personal_code"),
	PRIMARY KEY("barcode")
);
CREATE INDEX IF NOT EXISTS "sender_FK" ON "shipments" (
	"sender"
);
CREATE INDEX IF NOT EXISTS "receiver_FK" ON "shipments" (
	"receiver"
);
COMMIT;
