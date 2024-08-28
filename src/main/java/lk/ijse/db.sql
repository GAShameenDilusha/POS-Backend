create table Customer(
                        cust_id VARCHAR(50) primary key,
                        name VARCHAR(50),
                        address VARCHAR(20),
                        contact VARCHAR(50)
)



create table Item(
                         item_id VARCHAR(50) primary key,
                         desc VARCHAR(50),
                         price VARCHAR(20),
                         qty VARCHAR(50)
)

create table Order(
                         order_id VARCHAR(50) primary key,
                         cust_id VARCHAR(50),
                         name VARCHAR(50),
                         item_id VARCHAR(50)
                         desc VARCHAR(50)
                         desc VARCHAR(50),
                         price VARCHAR(20),
                         qty VARCHAR(50)

)
