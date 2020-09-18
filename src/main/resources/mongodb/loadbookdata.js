db = connect("50.23.5.205:30017");

use Book
db.auth('bookdbuser', 'bookdbuserpwd')

db.Book.insertOne({"bookType": "FICTION", "isbn": "12345", "title": "Some Book", "author": "Tom Smith" })
db.Book.insertOne({"bookType": "FICTION", "isbn": "87887", "title": "Whatever", "author": "Aanand Agarwal" })
db.Book.insertOne({"bookType": "NONFICTION", "isbn": "99445", "title": "Another Book", "author": "Lucy Chen" })
db.Book.createIndex({ isbn: 1})
