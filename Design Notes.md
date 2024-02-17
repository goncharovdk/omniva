
- SQLite is used as a development database just to do it fast. In a 
  real-world situation I'd use a real DB server and different datatypes. 
- I added some tables, fields and indices to the database, but this is for 
  illustration only, and far away from a real DB design. But it's 
  no-nonsense though.   
- ORM is not used as we are essentially working only with barcode field for 
  this problem. 
- It's not clear from the requirements, how the "Add new shipment" process 
  is handled. This is not the part of the test task,  but Bloom filter 
  should be updated when a new shipment is added.