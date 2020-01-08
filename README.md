# microService-crud
crud microservice

Please run productManagementApplication.java 

GET : http://localhost:8080/product/getAllProduct : get all products,
GET : http://localhost:8080/product/getByProductName/elegent%20white%20pump : get by product name
POST : http://localhost:8080/product/create : create a new product
request body parameter : application/json
{
    "productName": "elegent white pump",
    "url": "http://www.lifestylelabels.com/steven-by-steve-madden-pryme-pump.html",
    "image": "http://www.lifestylelabels.com/media/catalog/product/cache/1/small_image/210x/9df78eab33525d08d6e5fb8d27136e95/s/t/steven-by-steve-madden-pryme-pump.jpg",
    "price": 299.0,
    "msrp": 0.0,
    "productAvailability": true,
    "description": "Nothing will turn his head faster than you wearing the sexy Pryme pump from Steven by Steve Madden. This daring pump has a pretty patent leather upper with light shirring, a double stitch detail surrounding the collar, and a vampy almond shaped toe.",
    "merchant": {
        "merchantName": "Nike",
        "description": "sells nike products"
    },
    "category": {
        "categoryName": "shoes",
        "subcategoryName": "air",
        "gender": "men"
    }
}
PUT : http://localhost:8080/product/updateProduct/4 : update by product id
request body : application/json
{
    "productName": "elegent white pump",
    "url": "http://www.lifestylelabels.com/steven-by-steve-madden-pryme-pump.html",
    "image": "http://www.lifestylelabels.com/media/catalog/product/cache/1/small_image/210x/9df78eab33525d08d6e5fb8d27136e95/s/t/steven-by-steve-madden-pryme-pump.jpg",
    "price": 299.0,
    "msrp": 10.0,
    "productAvailability": true,
    "description": "Nothing will turn his head faster than you wearing the sexy Pryme pump from Steven by Steve Madden. This daring pump has a pretty patent leather upper with light shirring, a double stitch detail surrounding the collar, and a vampy almond shaped toe.",
    "merchant": {
        "merchantName": "puma",
        "description": "sells puma products"
    },
    "category": {
        "categoryName": "shoes",
        "subcategoryName": "air",
        "gender": "women"
    }
}

PUT : http://localhost:8080/product/updateByProductName/elegent%20white%20pump : update by product name
request body : application/json
{
    "productName": "elegent white pump",
    "url": "http://www.lifestylelabels.com/steven-by-steve-madden-pryme-pump.html",
    "image": "http://www.lifestylelabels.com/media/catalog/product/cache/1/small_image/210x/9df78eab33525d08d6e5fb8d27136e95/s/t/steven-by-steve-madden-pryme-pump.jpg",
    "price": 299.0,
    "msrp": 10.0,
    "productAvailability": true,
    "description": "Nothing will turn his head faster than you wearing the sexy Pryme pump from Steven by Steve Madden. This daring pump has a pretty patent leather upper with light shirring, a double stitch detail surrounding the collar, and a vampy almond shaped toe.",
    "merchant": {
        "merchantName": "puma",
        "description": "sells puma products"
    },
    "category": {
        "categoryName": "shoes",
        "subcategoryName": "air",
        "gender": "women"
    }
}

DELETE : http://localhost:8080/product/delete/2 : delete based on product id
DELETE : http://localhost:8080/product/delete/elegent%20white%20pump : delete by product name
