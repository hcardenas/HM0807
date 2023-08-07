## cURL
the following is a cURL command you can use to test the app. please note the DATE format MM/dd/yy


```bash
curl --location --request POST 'http://localhost:8080/api/v1/checkout' \
--header 'Content-Type: application/json' \
--data-raw '{
    "tool_code": "JAKD",
    "rental_day_count": 5,
    "discount": 0,
    "checkout_date": "08/30/20"

}'
```