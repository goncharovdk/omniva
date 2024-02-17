
- SQLite is used as a development database just to do it fast. In a 
  real-world situation I'd use a real DB server and different datatypes.

- If you want to run the app, adjust the db connection string in application.
  properties to point on the omniva.sqlite file.

- I added some tables, fields and indices to the database, but this is for 
  illustration only, and far away from a real DB design. But it's 
  no-nonsense though.

- ORM is not used as we are essentially working only with barcode field for 
  this problem.

- It's not clear from the requirements, how the "Add new shipment" process 
  is handled. This is not the part of the test task,  but Bloom filter 
  should be updated when a new shipment is added. It's reasonable to suppose 
  that DatabaseService should be responsible for this. So   
  BarcodeCheckService might be a Subscriber (Observer) to DatabaseService.

- Then, if shipments are to be added, it might be needed to create Bloom 
  filter of somewhat bigger size than the current barcode count.

- Fetch Size parameter supposedly to be set when loading barcodes into the 
  Bloom filter. But it's hard to estimate the proper value for it from toy data.

- I understand, that for security concerns it might be needed to check the 
  barcode using POST method, not GET. But for the initial development phase 
  it was easier to do it in classic way. (And it probably will be easier 
  for the reviewer to test the app too.)
