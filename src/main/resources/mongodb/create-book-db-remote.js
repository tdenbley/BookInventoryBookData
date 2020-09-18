db = connect("50.23.5.205:30017");

use admin
db.auth('admin', 'pa55w0rd')

db = db.getSiblingDB('Book')

db.createUser({
    user: 'bookdbuser', 
    pwd: 'bookdbuserpwd', 
    roles: [
        { role: 'dbOwner', 
        db: 'Book' }
    ] 
})

